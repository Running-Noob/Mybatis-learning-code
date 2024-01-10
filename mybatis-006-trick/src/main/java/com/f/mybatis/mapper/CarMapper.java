package com.f.mybatis.mapper;

import com.f.mybatis.pojo.Car;

import java.util.List;

/**
 * @author fzy
 * @date 2024/1/9 18:37
 */
public interface CarMapper {
    /**
     * 查询特定类型的车
     * @param carType 汽车类型
     * @return 查询到的车的集合
     */
    List<Car> selectByCarType(String carType);
}
