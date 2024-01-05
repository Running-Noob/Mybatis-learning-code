package com.f.mybatis.test;

import com.f.mybatis.pojo.Food;
import org.god.ibatis.core.SqlSession;
import org.god.ibatis.core.SqlSessionFactory;
import org.god.ibatis.core.SqlSessionFactoryBuilder;
import org.god.ibatis.utils.Resources;
import org.junit.Test;

/**
 * @author fzy
 * @date 2024/1/5 17:10
 */
public class GodBatisTest {
    @Test
    public void testGodBatisInsert() {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("godbatis-config.xml"));
        SqlSession sqlSession = factory.openSession();
        Food food = new Food();
        food.setId("1");
        food.setName("rice");
        food.setColor("white");
        int count = sqlSession.insert("food.insertFood", food);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testGodBatisSelectOne() {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("godbatis-config.xml"));
        SqlSession sqlSession = factory.openSession();
        Object obj = sqlSession.selectOne("food.selectById", "1");
        System.out.println(obj);
        sqlSession.commit();
        sqlSession.close();
    }
}
