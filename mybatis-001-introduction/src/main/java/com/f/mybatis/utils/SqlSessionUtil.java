package com.f.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author fzy
 * @date 2023/12/31 20:59
 */
public class SqlSessionUtil {
    private static SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
    private static SqlSessionFactory sqlSessionFactory = null;

    static {
        try {
            // SqlSessionFactory对象：一个SqlSessionFactory对应一个environment，一个environment通常是一个数据库。
            sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 工具类的构造方法一般都是私有化的。
    // 工具类中所有的方法都是静态的，直接采用类名即可调用。不需要new对象。
    // 为了防止new对象，构造方法私有化。
    private SqlSessionUtil() {
    }

    /**
     * 获取 SqlSession 对象
     */
    public static SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }
}