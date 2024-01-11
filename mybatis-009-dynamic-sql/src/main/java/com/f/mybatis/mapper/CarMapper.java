package com.f.mybatis.mapper;

import com.f.mybatis.pojo.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fzy
 * @date 2024/1/10 15:29
 */
public interface CarMapper {
    /**
     * 多条件查询
     *
     * @param brand      品牌
     * @param guidePrice 指导价
     * @param carType    汽车类型
     * @return
     */
    List<Car> selectByMultiCondition(@Param("brand") String brand,
                                     @Param("guidePrice") Double guidePrice,
                                     @Param("carType") String carType);

    /**
     * 多条件查询，使用where标签，让where子句更加智能
     *
     * @param brand
     * @param guidePrice
     * @param carType
     * @return
     */
    List<Car> selectByMultiConditionWithWhere(@Param("brand") String brand,
                                              @Param("guidePrice") Double guidePrice,
                                              @Param("carType") String carType);

    /**
     * 多条件查询，使用trim标签
     *
     * @param brand
     * @param guidePrice
     * @param carType
     * @return
     */
    List<Car> selectByMultiConditionWithTrim(@Param("brand") String brand,
                                             @Param("guidePrice") Double guidePrice,
                                             @Param("carType") String carType);

    /**
     * 更新数据，使用set标签
     *
     * @param car
     * @return
     */
    int updateBySet(Car car);

    /**
     * 使用choose when otherwise标签
     *
     * @param brand
     * @param guidePrice
     * @param carType
     * @return
     */
    List<Car> selectByChoose(@Param("brand") String brand,
                             @Param("guidePrice") Double guidePrice,
                             @Param("carType") String carType);

    /**
     * 批量删除，使用foreach标签
     *
     * @param ids
     * @return
     */
    int deleteByIds(@Param("ids") Long[] ids);

    /**
     * 批量插入，使用foreach标签
     *
     * @param cars
     * @return
     */
    int insertCars(@Param("cars") List<Car> cars);
}
