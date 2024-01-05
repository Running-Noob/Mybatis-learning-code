package org.god.ibatis.core;

import java.sql.Connection;

/**
 * MANAGED事务管理器(在这里godbatis对这个类不实现)
 * @author fzy
 * @date 2024/1/3 15:20
 */
public class ManagedTransaction implements Transaction{
    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }

    @Override
    public void close() {

    }

    @Override
    public void openConnection() {

    }

    @Override
    public Connection getConnection() {
        return null;
    }
}
