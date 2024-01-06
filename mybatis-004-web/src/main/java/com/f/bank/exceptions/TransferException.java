package com.f.bank.exceptions;

/**
 * 转账失败异常
 * @author fzy
 * @date 2024/1/6 12:12
 */
public class TransferException extends Exception {
    public TransferException() {
    }

    public TransferException(String message) {
        super(message);
    }
}
