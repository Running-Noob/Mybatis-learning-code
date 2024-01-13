package com.f.mybatis.test;

import com.f.mybatis.mapper.CarMapper;
import com.f.mybatis.pojo.Car;
import com.f.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

/**
 * @author fzy
 * @date 2024/1/13 14:19
 */
public class CarMapperTest {
    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        // 第一次查询
        Car car1 = mapper.selectById(9L);
        System.out.println(car1);
        // 手动清空一级缓存
        sqlSession.clearCache();
        // 第二次查询
        Car car2 = mapper.selectById(9L);
        System.out.println(car2);
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectById2() throws Exception {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        CarMapper mapper1 = sqlSession1.getMapper(CarMapper.class);
        CarMapper mapper2 = sqlSession2.getMapper(CarMapper.class);
        Car car1 = mapper1.selectById2(8L);
        // 在这里关闭sqlSession1，sqlSession1一级缓存中的数据会保存到二级缓存中
        sqlSession1.close();
        Car car2 = mapper2.selectById2(8L);
        sqlSession2.close();
    }

    //@Test
    //public void testSelectById2() throws Exception {
    //    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
    //    SqlSession sqlSession1 = sqlSessionFactory.openSession();
    //    SqlSession sqlSession2 = sqlSessionFactory.openSession();
    //    CarMapper mapper1 = sqlSession1.getMapper(CarMapper.class);
    //    CarMapper mapper2 = sqlSession2.getMapper(CarMapper.class);
    //    // 下面这行代码执行结束之后，数据缓存到了一级缓存中（sqlSession1中）
    //    // 如果还没有关闭sqlSession1对象，二级缓存中还是没有数据的
    //    Car car1 = mapper1.selectById2(8L);
    //    // 下面这行代码执行结束之后，数据缓存到了一级缓存中（sqlSession2中）
    //    Car car2 = mapper2.selectById2(8L);
    //    // sqlSession1和sqlSession2分别关闭后，它们的一级缓存中的数据会被保存到二级缓存中
    //    sqlSession1.close();
    //    sqlSession2.close();
    //}
}
