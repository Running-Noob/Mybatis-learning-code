package com.f.mybatis.test;

import com.f.mybatis.mapper.CarMapper;
import com.f.mybatis.pojo.Car;
import com.f.mybatis.utils.SqlSessionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author fzy
 * @date 2024/1/13 16:24
 */
public class CarMapperTest {
    @Test
    public void testSelectByPage() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        int pageNum = 3;    // 显示第几页：页码
        int pageSize = 5;   // 获取每页显示的记录条数
        int startIndex = (pageNum - 1) * pageSize;  // 计算开始下标
        List<Car> cars = mapper.selectByPage(startIndex, pageSize);
        cars.forEach(car -> {
            System.out.println(car);
        });
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectAllByPageHelper() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);

        // 注意：在执行DQL语句之前，开启分页功能
        int pageNum = 3;
        int pageSize = 5;
        PageHelper.startPage(pageNum, pageSize);

        List<Car> cars = mapper.selectAllByPageHelper();
        cars.forEach(car -> {
            System.out.println(car);
        });
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectAllByPageHelperAndPageInfo() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);

        // 注意：在执行DQL语句之前，开启分页功能
        int pageNum = 3;
        int pageSize = 5;
        PageHelper.startPage(pageNum, pageSize);

        List<Car> cars = mapper.selectAllByPageHelper();

        // 注意：在DQL语句执行之后，封装分页信息对象PageInfo
        // PageInfo对象是PageHelper插件提供的，用来封装分页相关信息的对象
        PageInfo<Car> carPageInfo = new PageInfo<>(cars, 5);
        System.out.println(carPageInfo);
        // PageInfo{pageNum=3, pageSize=5, size=2, startRow=11, endRow=12, total=12, pages=3,
        // list=Page{count=true, pageNum=3, pageSize=5, startRow=10, endRow=15, total=12, pages=3, reasonable=false, pageSizeZero=false}
        // [Car{id=16, carNum='6567', brand='蔚来', guidePrice=36.0, produceTime='2020-11-01', carType='新能源'},
        // Car{id=17, carNum='1234', brand='宝马', guidePrice=44.0, produceTime='2020-11-01', carType='汽油'}],
        // prePage=2, nextPage=0, isFirstPage=false, isLastPage=true, hasPreviousPage=true,
        // hasNextPage=false, navigatePages=5, navigateFirstPage=1, navigateLastPage=3, navigatepageNums=[1, 2, 3]}
        SqlSessionUtil.close(sqlSession);
    }
}
