package com.f.mybatis.mapper;

import com.f.mybatis.pojo.Car;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * @author fzy
 * @date 2024/1/10 15:29
 */
public interface CarMapper {
    Car selectById(Long id);

    List<Car> selectAll();

    Map<String, Object> selectByIdReturnMap(Long id);

    List<Map<String, Object>> selectAllReturnListMap();

    /**
     * 查询所有的Car，返回一个大Map集合
     * 大Map集合的key是每条记录的主键值
     * 大Map集合的value是每条记录
     *
     * @return
     */
    @MapKey("id")
    // 将查询结果的id值作为整个大Map集合的key
    Map<Long, Map<String, Object>> selectAllReturnBigMap();

    /**
     * 查询所有Car的信息，使用resultMap标签进行结果映射
     *
     * @return
     */
    List<Car> selectAllByResultMap();

    /**
     * 查询所有Car的信息，但是启用了驼峰命名自动映射机制
     *
     * @return
     */
    List<Car> selectAllByMapUnderscoreToCamelCase();
}
