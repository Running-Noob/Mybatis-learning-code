package org.god.ibatis.core;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * 数据源的实现类：POOLED
 * 使用godbatis框架内置的数据库连接池来获取Connection对象（这里就不实现数据库连接池了）
 * @author fzy
 * @date 2024/1/3 15:37
 */
public class PooledDataSource implements javax.sql.DataSource {
    @Override
    public Connection getConnection() throws SQLException {
        // 从数据库连接池中获取Connection对象（这个数据库连接池是godbatis框架内部封装好的）
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
