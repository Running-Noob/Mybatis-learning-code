package com.f.mybatis.test;

import com.f.mybatis.mapper.StudentMapper;
import com.f.mybatis.pojo.Student;
import com.f.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @author fzy
 * @date 2024/1/11 16:28
 */
public class StudentMapperTest {
    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectById(1);
        System.out.println(student);
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectByIdAssociation() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectByIdAssociation(1);
        System.out.println(student);
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectByIdStep1() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectByIdStep1(5);
        System.out.println(student);
        SqlSessionUtil.close(sqlSession);
    }
}
