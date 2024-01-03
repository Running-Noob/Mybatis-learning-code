package com.f.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

/**
 * @author fzy
 * @date 2024/1/2 15:29
 */
public class ConfigurationTest {
    @Test
    public void testEnvironment() throws Exception {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 使用默认的环境，即对应mybatis数据库
        SqlSessionFactory sqlSessionFactory1 = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        // 通过environment的id使用指定的环境，即对应javaweb数据库
        SqlSessionFactory sqlSessionFactory2 = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"), "javawebDB");
        SqlSession sqlSession = sqlSessionFactory1.openSession();
        Object car = sqlSession.selectOne("car.selectCarById", 1);
        System.out.println(car);
    }
}