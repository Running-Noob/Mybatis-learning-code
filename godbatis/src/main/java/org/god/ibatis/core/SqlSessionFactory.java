package org.god.ibatis.core;

import java.util.Map;

/**
 * SqlSessionFactory对象：
 * 一个数据库对应一个SqlSessionFactory对象
 * 通过SqlSessionFactory对象可以获取SqlSession对象（开启会话）
 * 一个SqlSessionFactory对象可以开启多个SqlSession
 *
 * @author fzy
 * @date 2024/1/3 14:24
 */
public class SqlSessionFactory {
    // 思考：SqlSessionFactory应该定义哪些属性？
    // 1.事务管理器属性
    // 2.数据源属性
    // 3.存放sql语句的Map集合，key是sqlId，value是对应的sql标签信息对象

    /**
     * 事务管理器属性
     * 事务管理器需要能够灵活切换
     * SqlSessionFactory类中的事务管理器应该是面向接口编程的
     */
    private Transaction transaction;

    /**
     * 数据源属性(可以由transaction获得，不需要在这里定义)
     */

    /**
     * 存放sql语句的Map集合
     * key是sqlId
     * value是对应的sql标签信息对象
     */
    private Map<String, MappedStatement> mappedStatements;

    public SqlSessionFactory() {
    }

    public SqlSessionFactory(Transaction transaction, Map<String, MappedStatement> mappedStatements) {
        this.transaction = transaction;
        this.mappedStatements = mappedStatements;
    }

    /**
     * 获取sql会话对象
     *
     * @return
     */
    public SqlSession openSession() {
        // 开启会话的前提是开启连接
        transaction.openConnection();
        // 创建SqlSession对象
        SqlSession sqlSession = new SqlSession(this);
        return sqlSession;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Map<String, MappedStatement> getMappedStatements() {
        return mappedStatements;
    }

    public void setMappedStatements(Map<String, MappedStatement> mappedStatements) {
        this.mappedStatements = mappedStatements;
    }
}
