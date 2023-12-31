package com.f.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author fzy
 * @date 2023/12/30 11:28
 */
public class MybatisCompleteTest {
    public static void main(String[] args) {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = null;
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession sqlSession = null;
        try {
            sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
            // 开启会话（底层会开启事务）
            sqlSession = sqlSessionFactory.openSession();
            // 执行 sql 语句，处理相关业务
            int count = sqlSession.insert("insertCar");
            System.out.println("插入 " + count + " 条数据");
            // 如果没有发生任何异常，则提交事务
            sqlSession.commit();
        } catch (Exception e) {
            // 回滚事务
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            e.printStackTrace();
        } finally {
            // 关闭会话，释放资源
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
