package org.god.ibatis.core;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.god.ibatis.utils.Resources;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SqlSessionFactory构建器对象
 * 通过SqlSessionFactoryBuilder的build方法来解析godbatis-config.xml文件，
 * 然后创建SqlSessionFactory对象
 *
 * @author fzy
 * @date 2024/1/3 14:21
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactoryBuilder() {
    }

    /**
     * 解析godbatis-config.xml文件，来构建SqlSessionFactory对象
     *
     * @param is 指向godbatis-config.xml文件的一个输入流
     * @return SqlSessionFactory对象
     */
    public SqlSessionFactory build(InputStream is) {
        SqlSessionFactory factory = null;
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(is);
            Element environments = (Element) document.selectSingleNode("/configuration/environments");
            String defaultId = environments.attributeValue("default");
            Element environment = (Element) document.selectSingleNode("/configuration/environments/environment[@id='" + defaultId + "']");
            Element transactionElt = environment.element("transactionManager");
            Element dataSourceElt = environment.element("dataSource");
            // 用于存储SqlMapper.xml文件的路径（SqlMapper.xml文件可能不止一个）
            List<String> sqlMapperXMLPaths = new ArrayList<>();
            Element mappersElt = (Element) document.selectSingleNode("/configuration/mappers");
            List<Element> mapperElts = mappersElt.elements("mapper");
            for (Element mapperElt : mapperElts) {
                String resourcePath = mapperElt.attributeValue("resource");
                sqlMapperXMLPaths.add(resourcePath);
            }
            // 解析配置文件，创建数据源对象
            DataSource dataSource = getDataSource(dataSourceElt);
            // 解析配置文件，创建事务管理器对象
            Transaction transaction = getTransaction(transactionElt, dataSource);
            // 解析配置文件，获取所有的SQL映射对象
            Map<String, MappedStatement> mappedStatements = getMappedStatements(sqlMapperXMLPaths);
            // 将以上信息封装到SqlSessionFactory对象中
            factory = new SqlSessionFactory(transaction, mappedStatements);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factory;
    }

    /**
     * 解析所有的SqlMapper.xml文件，然后构建Map集合
     *
     * @param sqlMapperXMLPaths
     * @return
     */
    private Map<String, MappedStatement> getMappedStatements(List<String> sqlMapperXMLPaths) {
        Map<String, MappedStatement> mappedStatements = new HashMap<>();
        for (int i = 0; i < sqlMapperXMLPaths.size(); i++) {
            String sqlMapperXMLPath = sqlMapperXMLPaths.get(i);
            try {
                SAXReader saxReader = new SAXReader();
                Document document = saxReader.read(Resources.getResourceAsStream(sqlMapperXMLPath));
                Element mapperElt = (Element) document.selectSingleNode("/mapper");
                String namespace = mapperElt.attributeValue("namespace");
                List<Element> sqlElts = mapperElt.elements();
                for (Element sqlElt : sqlElts) {
                    String id = sqlElt.attributeValue("id");
                    String sqlId = namespace + "." + id;    //得到sql语句的sqlId
                    String resultType = sqlElt.attributeValue("resultType");
                    String sql = sqlElt.getTextTrim();
                    MappedStatement mappedStatement = new MappedStatement(sql, resultType); //得到MappedStatement对象
                    mappedStatements.put(sqlId, mappedStatement);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mappedStatements;
    }

    /**
     * 获取事务管理器对象
     *
     * @param transactionElt 事务管理器标签元素
     * @param dataSource     数据源对象
     * @return 事务管理器对象
     */
    private Transaction getTransaction(Element transactionElt, DataSource dataSource) {
        Transaction transaction = null;
        String type = transactionElt.attributeValue("type").trim().toUpperCase();
        switch (type) {
            case Const.JDBC_TRANSACTION:
                transaction = new JDBCTransaction(dataSource, false);   //默认开启事务，将来需要手动提交
                break;
            case Const.MANAGED_TRANSACTION:
                transaction = new ManagedTransaction();
                break;
            default:
        }
        return transaction;
    }

    /**
     * 获取数据源对象
     *
     * @param dataSourceElt 数据源标签元素
     * @return 数据源对象
     */
    private DataSource getDataSource(Element dataSourceElt) {
        // 获取dataSourceElt的所有property
        Map<String, String> map = new HashMap<>();
        List<Element> propertyElts = dataSourceElt.elements("property");
        for (Element propertyElt : propertyElts) {
            String name = propertyElt.attributeValue("name");
            String value = propertyElt.attributeValue("value");
            map.put(name, value);
        }

        DataSource dataSource = null;
        String type = dataSourceElt.attributeValue("type").trim().toUpperCase();
        switch (type) {
            case Const.UNPOOLED_DATASOURCE:
                dataSource = new UnpooledDataSource(map.get("driver"), map.get("url"), map.get("username"), map.get("password"));
                break;
            case Const.POOLED_DATASOURCE:
                dataSource = new PooledDataSource();
                break;
            case Const.JNDI_DATASOURCE:
                dataSource = new JNDIDataSource();
                break;
            default:
        }
        return dataSource;
    }
}
