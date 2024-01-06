package com.f.bank.service.impl;

import com.f.bank.dao.AccountDao;
import com.f.bank.dao.impl.AccountDaoImpl;
import com.f.bank.exceptions.MoneyNotEnoughException;
import com.f.bank.exceptions.TransferException;
import com.f.bank.pojo.Account;
import com.f.bank.service.AccountService;
import com.f.bank.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * @author fzy
 * @date 2024/1/6 11:36
 */
public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao = new AccountDaoImpl();

    @Override
    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, TransferException {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // 1.判断转出账户的余额是否充足（select）
        Account fromAct = accountDao.selectByActno(fromActno);
        if (fromAct.getBalance() < money) {
            // 2.如果转出账户的余额不足，提示用户
            throw new MoneyNotEnoughException("您的账户余额不足!");
        }
        try {
            // 3.如果转出账户余额充足，更新转出账户余额（update）
            // 先更新内存中java对象的信息，再更新数据库
            Account toAct = accountDao.selectByActno(toActno);
            fromAct.setBalance(fromAct.getBalance() - money);
            toAct.setBalance(toAct.getBalance() + money);
            int count = accountDao.updateAct(fromAct);
            // 4.更新转入账户余额（update）
            count += accountDao.updateAct(toAct);
            // 提交事务
            sqlSession.commit();
        } catch (Exception e) {
            throw new TransferException("转账失败, 原因未知");
        } finally {
            // 释放资源
            SqlSessionUtil.close(sqlSession);
        }
    }
}
