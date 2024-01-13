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
    private static SqlSessionFactory sqlSessionFactory = null;
    // 增加ThreadLocal对象
    private static ThreadLocal<SqlSession> local = new ThreadLocal<>();

    static {
        try {
            // SqlSessionFactory对象：一个SqlSessionFactory对应一个environment，一个environment通常是一个数据库。
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
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
    //public static SqlSession openSession() {
    //    return sqlSessionFactory.openSession();
    //}
    // 将上面的代码改为下面的代码
    public static SqlSession openSession() {
        SqlSession sqlSession = local.get();
        if (sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession();
            // 将SqlSession对象绑定到当前线程上
            local.set(sqlSession);
        }
        return sqlSession;
    }

    /**
     * 关闭SqlSession对象（从当前线程中移除SqlSession对象）
     *
     * @param sqlSession
     */
    public static void close(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.close();
            // 解绑SqlSession对象。
            // 因为Tomcat服务器支持线程池，也就是说，用过的线程对象t1，可能下一次还会使用这个t1线程，
            // 为了避免错误使用之前绑定的SqlSession对象，所以要解绑
            local.remove();
        }
    }
}