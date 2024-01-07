package com.f.mybatis.mapper;   //mapper包就是dao包

import com.f.mybatis.pojo.Car;

import java.util.List;

/**
 * @author fzy
 * @date 2024/1/7 11:06
 */
// 使用mybatis的话，一般不叫XxxDao，而是叫做XxxMapper
public interface CarMapper {
    /**
     * 新增car
     *
     * @param car
     */
    int insert(Car car);

    /**
     * 根据id删除car
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 更新car
     *
     * @param car
     * @return
     */
    int update(Car car);

    /**
     * 根据id查找car
     *
     * @param id
     * @return
     */
    Car selectById(Long id);

    /**
     * 查找所有car
     *
     * @return
     */
    List<Car> selectAll();
}
