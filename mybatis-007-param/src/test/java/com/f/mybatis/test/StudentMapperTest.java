package com.f.mybatis.test;

import com.f.mybatis.mapper.StudentMapper;
import com.f.mybatis.pojo.Student;
import com.f.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fzy
 * @date 2024/1/9 22:18
 */
public class StudentMapperTest {
    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectById(1L);
        students.forEach(student -> {
            System.out.println(student);
        });
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectByName() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectByName("李四");
        students.forEach(student -> {
            System.out.println(student);
        });
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectByBirth() throws Exception {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Date birth = new SimpleDateFormat("yyyy-MM-dd").parse("1980-10-01");
        List<Student> students = mapper.selectByBirth(birth);
        students.forEach(student -> {
            System.out.println(student);
        });
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectBySex() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectBySex('男');
        students.forEach(student -> {
            System.out.println(student);
        });
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectByNameAndSex() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", "张三");
        paramMap.put("sex", '男');
        List<Student> students = mapper.selectByNameAndSex(paramMap);
        students.forEach(student -> {
            System.out.println(student);
        });
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testInsert() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = new Student();
        student.setName("李清");
        student.setAge(23);
        student.setHeight(1.78);
        // 创建Date对象时，年需减去1900，月从0开始
        student.setBirth(new Date(2010 - 1900, 9, 10, 0, 0, 0));
        student.setSex('女');
        int count = mapper.insert(student);
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectByNameAndAge() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectByNameAndAge("张三", 20);
        students.forEach(student -> {
            System.out.println(student);
        });
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectByNameAndAgeUsingParamAnnotation() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectByNameAndAge("张三", 20);
        students.forEach(student -> {
            System.out.println(student);
        });
        SqlSessionUtil.close(sqlSession);
    }
}
