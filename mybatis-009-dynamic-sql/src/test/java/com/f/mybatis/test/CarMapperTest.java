package com.f.mybatis.test;

import com.f.mybatis.mapper.CarMapper;
import com.f.mybatis.pojo.Car;
import com.f.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fzy
 * @date 2024/1/10 15:32
 */
public class CarMapperTest {
    @Test
    public void testSelectByMultiCondition() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByMultiCondition("问界", null, "新能源");
        cars.forEach(car -> {
            System.out.println(car);
        });
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectByMultiConditionWithWhere() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByMultiConditionWithWhere("问界", null, "新能源");
        cars.forEach(car -> {
            System.out.println(car);
        });
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectByMultiConditionWithTrim() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByMultiConditionWithTrim("问界", null, "新能源");
        cars.forEach(car -> {
            System.out.println(car);
        });
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testUpdateBySet() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car();
        car.setId(4L);
        car.setCarType("氢能");
        int count = mapper.updateBySet(car);
        System.out.println(count);
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectByChoose() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByChoose("问界", null, "新能源");
        cars.forEach(car -> {
            System.out.println(car);
        });
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testDeleteByIds() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Long[] ids = {1L, 2L, 3L};
        int count = mapper.deleteByIds(ids);
        System.out.println(count);
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testInsertCars() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(null, "4444", "宝马", 55.00, "2010-01-01", "汽油"));
        cars.add(new Car(null, "5555", "福特", 25.00, "2020-11-01", "汽油"));
        mapper.insertCars(cars);
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }
}
