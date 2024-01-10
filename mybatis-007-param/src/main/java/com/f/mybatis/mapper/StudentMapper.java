package com.f.mybatis.mapper;

import com.f.mybatis.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author fzy
 * @date 2024/1/9 22:14
 */
public interface StudentMapper {
    /**
     * 当接口中的方法的参数只有一个，并且参数的数据类型都是简单类型
     * 根据 `id` 查、根据 `name` 查、根据 `birth` 查、根据 `sex` 查
     */
    List<Student> selectById(Long id);

    List<Student> selectByName(String name);

    List<Student> selectByBirth(Date birth);

    List<Student> selectBySex(Character sex);

    /**
     * 当接口中的方法的参数只有一个，并且参数的类型是Map
     */
    List<Student> selectByNameAndSex(Map<String, Object> paramMap);

    /**
     * 当接口中的方法的参数只有一个，并且参数的类型是实体类POJO
     */
    int insert(Student student);

    /**
     * 接口中的方法的参数有多个
     * 这里根据name和age查询Student的信息
     * 如果是多个参数的话，mybatis框架底层是怎么做的呢
     * mybatis框架会自动创建一个Map集合，并且Map集合是以这种方式存储参数的：
     * map.put("arg0", name);  map.put("param1", name);
     * map.put("arg1", age);   map.put("param2", age);
     *
     * @param name
     * @param age
     * @return
     */
    List<Student> selectByNameAndAge(String name, Integer age);

    /**
     * Param注解
     * mybatis框架底层实现原理由
     * map.put("arg0", name);
     * 转变为 map.put("name", name);
     *
     * @param name
     * @param age
     * @return
     */
    List<Student> selectByNameAndAgeUsingParamAnnotation(@Param("name") String name, @Param("age") Integer age);
}
