package org.god.ibatis.core;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.Map;

/**
 * 专门负责执行sql语句的会话对象
 *
 * @author fzy
 * @date 2024/1/3 20:17
 */
public class SqlSession {
    SqlSessionFactory factory;

    public SqlSession() {
    }

    public SqlSession(SqlSessionFactory factory) {
        this.factory = factory;
    }

    /**
     * 执行insert语句，向数据库表中插入记录
     *
     * @param sqlId sql语句的Id
     * @param pojo  插入的数据
     * @return
     */
    public int insert(String sqlId, Object pojo) {
        int count = 0;
        try {
            // JDBC代码，执行insert语句，完成插入操作
            Connection connection = factory.getTransaction().getConnection();
            // 配置文件中的sql语句：INSERT INTO t_user VALUES (#{id}, #{name}, #{age})
            String godbatisSql = factory.getMappedStatements().get(sqlId).getSql();
            // 需要将其转换为：INSERT INTO t_user VALUES (?, ?, ?)
            String sql = godbatisSql.replaceAll("#\\{[0-9A-Za-z_]*}", "?");
            PreparedStatement ps = connection.prepareStatement(sql);

            // 给 ? 占位符传值
            // 难点:
            // 1.你不知道有多少个?
            // 2.你不知道该将pojo对象中的哪个属性赋值给哪个?
            int fromIndex = 0;
            int questionMarkIndex = 1;    // ? 的下标
            while (true) {
                int jingIndex = godbatisSql.indexOf("#", fromIndex);
                int rightBraceIndex = godbatisSql.indexOf("}", fromIndex);
                if (jingIndex < 0 || rightBraceIndex < 0) {
                    break;
                }
                String propertyName = godbatisSql.substring(jingIndex + 2, rightBraceIndex).trim();
                String getMethodName = "get" + propertyName.toUpperCase().charAt(0) + propertyName.substring(1);
                Method getMethod = pojo.getClass().getDeclaredMethod(getMethodName);
                Object getMethodValue = getMethod.invoke(pojo);
                ps.setString(questionMarkIndex, getMethodValue.toString());
                fromIndex = rightBraceIndex + 1;
                questionMarkIndex++;
            }

            count = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 执行查询语句，返回一个对象。该方法只适合返回一条记录的sql语句
     *
     * @param sqlId
     * @param param
     * @return
     */
    public Object selectOne(String sqlId, Object param) {
        Object result = null;
        try {
            Connection connection = factory.getTransaction().getConnection();
            Map<String, MappedStatement> mappedStatements = factory.getMappedStatements();
            // 配置文件中的sql语句：SELECT * FROM t_user WHERE id = #{id}
            String godbatisSql = mappedStatements.get(sqlId).getSql();
            // 将其转换为：SELECT * FROM t_user WHERE id = ?
            String sql = godbatisSql.replaceAll("#\\{[0-9A-Za-z_]*}", "?");
            // 配置文件中的resultType：org.god.ibatis.pojo.User
            String resultType = mappedStatements.get(sqlId).getResultType();
            // 得到相应的Class对象
            Class<?> resultTypeClass = Class.forName(resultType);
            PreparedStatement ps = connection.prepareStatement(sql);
            // 给占位符传值（假设占位符只有一个，这里简化了）
            ps.setString(1, param.toString());
            ResultSet rs = ps.executeQuery();
            // 从结果集中取数据，封装java对象
            if (rs.next()) {
                result = resultTypeClass.newInstance();
                // 给result中的属性赋值
                // 难点：给 result 的哪个属性赋哪个值
                ResultSetMetaData rsmd = rs.getMetaData();  // 获取查询结果元数据，其中就有查询结果的列名
                int columnCount = rsmd.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String propertyName = rsmd.getColumnName(i);    // 下标要从1开始
                    // 拼接方法名
                    String setMethodName = "set" + propertyName.toUpperCase().charAt(0) + propertyName.substring(1);
                    // 获取set方法
                    Method setMethod = resultTypeClass.getDeclaredMethod(setMethodName, String.class);
                    // 调用set方法，给result中的属性赋值
                    setMethod.invoke(result, rs.getString(propertyName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 提交事务
    public void commit() {
        factory.getTransaction().commit();
    }

    // 回滚事务
    public void rollback() {
        factory.getTransaction().rollback();
    }

    // 关闭事务
    public void close() {
        factory.getTransaction().close();
    }
}
