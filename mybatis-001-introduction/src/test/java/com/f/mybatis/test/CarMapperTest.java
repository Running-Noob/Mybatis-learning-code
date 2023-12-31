package com.f.mybatis.test;

import com.f.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

/**
 * @author fzy
 * @date 2023/12/30 12:09
 */
public class CarMapperTest {
    @Test
    public void testInsertCarByUtil() {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionUtil.openSession();
            sqlSession.insert("insertCar");
            sqlSession.commit();
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test
    public void testInsertCar() {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = null;
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession sqlSession = null;
        try {
            sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
            // 开启会话（底层会开启事务）
            sqlSession = sqlSessionFactory.openSession();
            // 执行 sql 语句，处理相关业务
            sqlSession.insert("insertCar");
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
