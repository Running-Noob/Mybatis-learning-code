package com.f.mybatis.mapper;

import com.f.mybatis.pojo.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fzy
 * @date 2024/1/13 14:20
 */
public interface CarMapper {
    /**
     * 分页查询
     *
     * @param startIndex 起始下标
     * @param pageSize   每页显示的记录条数
     * @return
     */
    List<Car> selectByPage(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 查询所有的Car信息，并使用分页查询插件PageHelper
     *
     * @return
     */
    List<Car> selectAllByPageHelper();
}