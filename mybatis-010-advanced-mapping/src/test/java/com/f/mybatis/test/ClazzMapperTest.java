package com.f.mybatis.test;

import com.f.mybatis.mapper.ClazzMapper;
import com.f.mybatis.pojo.Clazz;
import com.f.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @author fzy
 * @date 2024/1/11 16:40
 */
public class ClazzMapperTest {
    @Test
    public void testSelectByIdCollection() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Clazz clazz = mapper.selectByIdCollection(1000);
        System.out.println(clazz);
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectByIdStep1() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Clazz clazz = mapper.selectByIdStep1(1000);
        System.out.println(clazz);
        SqlSessionUtil.close(sqlSession);
    }
}
