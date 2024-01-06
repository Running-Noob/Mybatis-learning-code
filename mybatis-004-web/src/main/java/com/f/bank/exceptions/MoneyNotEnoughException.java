package com.f.bank.exceptions;

/**
 * 账户余额不足的异常
 * @author fzy
 * @date 2024/1/6 12:05
 */
public class MoneyNotEnoughException extends Exception {
    public MoneyNotEnoughException() {
    }

    public MoneyNotEnoughException(String message) {
        super(message);
    }
}
