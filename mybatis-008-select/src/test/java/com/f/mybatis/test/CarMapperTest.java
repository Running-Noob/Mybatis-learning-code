package com.f.mybatis.test;

import com.f.mybatis.mapper.CarMapper;
import com.f.mybatis.pojo.Car;
import com.f.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author fzy
 * @date 2024/1/10 15:32
 */
public class CarMapperTest {
    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = mapper.selectById(2L);
        System.out.println(car);
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAll();
        cars.forEach(car -> System.out.println(car));
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectByIdReturnMap() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Map<String, Object> result = mapper.selectByIdReturnMap(2L);
        for (Map.Entry<String, Object> entry : result.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectAllReturnListMap() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Map<String, Object>> results = mapper.selectAllReturnListMap();
        results.forEach(result -> {
            for (Map.Entry<String, Object> entry : result.entrySet()) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
            }
            System.out.println("==========");
        });
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectAllReturnBigMap() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        // 返回的大Map
        Map<Long, Map<String, Object>> results = mapper.selectAllReturnBigMap();
        /**
         * {
         *      2={carType=混动, carNum=1111, guidePrice=100.00, produceTime=2023-10-12, id=2, brand=劳斯莱斯},
         *      4={carType=新能源, carNum=1003, guidePrice=40.00, produceTime=2022-10-12, id=4, brand=问界}
         * }
         */
        System.out.println(results);
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectAllByResultMap() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAllByResultMap();
        cars.forEach(car -> System.out.println(car));
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectAllByMapUnderscoreToCamelCase() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAllByMapUnderscoreToCamelCase();
        cars.forEach(car -> System.out.println(car));
        SqlSessionUtil.close(sqlSession);
    }
}
