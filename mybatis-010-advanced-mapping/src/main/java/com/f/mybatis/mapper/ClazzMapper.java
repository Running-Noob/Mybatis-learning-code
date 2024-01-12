package com.f.mybatis.mapper;

import com.f.mybatis.pojo.Clazz;

/**
 * @author fzy
 * @date 2024/1/11 16:40
 */
public interface ClazzMapper {
    /**
     * 分步查询第二步，根据班级id查班级信息
     *
     * @param id
     * @return
     */
    Clazz selectByIdStep2(Integer id);

    /**
     * 根据id查询班级信息，同时获得相关联的学生信息
     *
     * @param id
     * @return
     */
    Clazz selectByIdCollection(Integer id);

    /**
     * 一对多分步查询第一步，根据班级id查班级信息
     *
     * @param id
     * @return
     */
    Clazz selectByIdStep1(Integer id);
}
