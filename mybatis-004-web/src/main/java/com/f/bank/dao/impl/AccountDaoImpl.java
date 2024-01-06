package com.f.bank.dao.impl;

import com.f.bank.dao.AccountDao;
import com.f.bank.pojo.Account;
import com.f.bank.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * @author fzy
 * @date 2024/1/6 11:51
 */
public class AccountDaoImpl implements AccountDao {
    @Override
    public Account selectByActno(String actno) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Account account = (Account) sqlSession.selectOne("account.selectByActno", actno);
        return account;
    }

    @Override
    public int updateAct(Account act) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        int count = sqlSession.update("account.updateAct", act);
        return count;
    }
}
