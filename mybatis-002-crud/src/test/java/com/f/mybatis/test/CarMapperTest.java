package com.f.mybatis.test;

import com.f.mybatis.bean.Car;
import com.f.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fzy
 * @date 2023/12/31 21:33
 */
public class CarMapperTest {
    @Test
    public void testUpdateCar() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Car car = new Car();
        car.setCarNum("1001");
        car.setGuidePrice(50.00);
        sqlSession.update("updateCar", car);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteCarByNum() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        sqlSession.delete("deleteCarByNum", "1003");
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertCarByPojo() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Car car = new Car(null, "1004", "比亚迪", 33.23, "2020-10-01", "燃油车");
        sqlSession.insert("insertCar", car);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertCar() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Map<String, Object> map = new HashMap<>();
        map.put("k1", "1003");
        map.put("k2", "问界");
        map.put("k3", 41.00);
        map.put("k4", "2023-11-11");
        map.put("k5", "新能源");

        // insert方法的参数：
        // 第一个参数：sqlId，从 CarMapper.xml 文件中复制
        // 第二个参数：封装数据的对象
        sqlSession.insert("insertCar", map);
        sqlSession.commit();
        sqlSession.close();
    }
}
