package com.f.mybatis.test;

import com.f.mybatis.mapper.CarMapper;
import com.f.mybatis.pojo.Car;
import com.f.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author fzy
 * @date 2024/1/7 11:21
 */
public class CarMapperTest {
    @Test
    public void testInsert() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car();
        car.setCarNum("1003");
        car.setBrand("问界");
        car.setGuidePrice(40.00);
        car.setProduceTime("2022-10-12");
        car.setCarType("新能源");
        int count = carMapper.insert(car);
        sqlSession.commit();
        System.out.println(count);
    }

    @Test
    public void testDeleteById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        int count = carMapper.deleteById(1L);
        sqlSession.commit();
        System.out.println(count);
    }

    @Test
    public void testUpdate() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car();
        car.setId(2L);
        car.setCarNum("1111");
        car.setBrand("劳斯莱斯");
        car.setGuidePrice(100.00);
        car.setProduceTime("2023-10-12");
        car.setCarType("混动");
        int count = carMapper.update(car);
        sqlSession.commit();
        System.out.println(count);
    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Car car = carMapper.selectById(2L);
        System.out.println(car);
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = carMapper.selectAll();
        cars.forEach(car -> {
            System.out.println(car);
        });
    }
}
