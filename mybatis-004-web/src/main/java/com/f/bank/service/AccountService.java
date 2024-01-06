package com.f.bank.service;

import com.f.bank.exceptions.MoneyNotEnoughException;
import com.f.bank.exceptions.TransferException;

/**
 * 账户业务类接口
 *
 * @author fzy
 * @date 2024/1/6 11:33
 */
public interface AccountService {
    /**
     * 账户转账业务
     * @param fromActno 转出账号
     * @param toActno 转入账号
     * @param money 转账金额
     */
    void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, TransferException;
}
