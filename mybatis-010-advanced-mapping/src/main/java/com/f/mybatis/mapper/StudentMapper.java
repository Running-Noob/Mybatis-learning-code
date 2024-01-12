package com.f.mybatis.mapper;

import com.f.mybatis.pojo.Student;

/**
 * @author fzy
 * @date 2024/1/11 16:28
 */
public interface StudentMapper {
    /**
     * 根据id获取学生信息，同时获取学生关联的班级信息
     *
     * @param id
     * @return
     */
    Student selectById(Integer id);

    /**
     * 根据id获取学生信息，同时获取学生关联的班级信息，使用association标签
     *
     * @param id
     * @return
     */
    Student selectByIdAssociation(Integer id);

    /**
     * 分步查询第一步，根据学生id查学生信息
     *
     * @param id
     * @return
     */
    Student selectByIdStep1(Integer id);

    /**
     * 一对多分步查询第二步，根据班级id查学生信息
     *
     * @param cid
     * @return
     */
    Student selectByIdStep2(Integer cid);
}
