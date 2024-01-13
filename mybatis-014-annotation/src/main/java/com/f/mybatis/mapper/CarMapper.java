package com.f.mybatis.mapper;

import com.f.mybatis.pojo.Car;
import org.apache.ibatis.annotations.*;

/**
 * @author fzy
 * @date 2024/1/13 20:02
 */
public interface CarMapper {
    /**
     * 使用注解的方式插入汽车信息
     *
     * @param car
     * @return
     */
    @Insert("INSERT INTO t_car VALUES (null, #{carNum}, #{brand}, #{guidePrice}, #{produceTime}, #{carType})")
    int insert(Car car);

    /**
     * 使用注解的方式删除汽车的信息
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM t_car WHERE id = #{id}")
    int deleteById(Long id);

    /**
     * 使用注解的方式更新汽车的信息
     *
     * @param id
     * @return
     */
    @Update("UPDATE t_car SET car_num = #{carNum} WHERE id = #{id}")
    int updateById(@Param("id") Long id, @Param("carNum") String carNum);

    /**
     * 根据注解的方式查询汽车的信息
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM t_car WHERE id = #{id}")
    Car selectById(Long id);
}