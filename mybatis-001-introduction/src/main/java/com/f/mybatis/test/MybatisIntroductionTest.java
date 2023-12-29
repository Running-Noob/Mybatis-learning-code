package com.f.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


/**
 * @author fzy
 * @date 2023/12/29 21:25
 */
public class MybatisIntroductionTest {
    public static void main(String[] args) throws IOException {
        // 1.获取SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 2.获取SqlSessionFactory对象
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");    // getResourceAsStream默认从类的根路径下开始查找资源
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);   // 一般情况下，一个数据库对应一个SqlSessionFactory对象
        // 3.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4.执行sql语句
        int insertCar = sqlSession.insert("insertCar"); // 返回值是影响数据库表当中的记录条数
        System.out.println("插入了 " + insertCar + " 条记录.");
        // 5.手动提交（sqlSession默认不会自动提交）
        sqlSession.commit();
    }
}
