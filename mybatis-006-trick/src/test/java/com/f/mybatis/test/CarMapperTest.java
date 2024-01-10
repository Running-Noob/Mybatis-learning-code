package com.f.mybatis.test;

import com.f.mybatis.mapper.CarMapper;
import com.f.mybatis.pojo.Car;
import com.f.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author fzy
 * @date 2024/1/9 18:41
 */
public class CarMapperTest {
    @Test
    public void testSelectByCarType() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        String carType = "新能源";
        List<Car> cars = mapper.selectByCarType(carType);
        cars.forEach(car -> {
            System.out.println(car);
        });
        SqlSessionUtil.close(sqlSession);
    }
}
