package com.f.mybatis.mapper;

import com.f.mybatis.pojo.Car;

/**
 * @author fzy
 * @date 2024/1/13 14:20
 */
public interface CarMapper {
    /**
     * 根据汽车id查询汽车信息
     *
     * @param id
     * @return
     */
    Car selectById(Long id);

    /**
     * 根据汽车id查询汽车信息
     *
     * @param id
     * @return
     */
    Car selectById2(Long id);
}