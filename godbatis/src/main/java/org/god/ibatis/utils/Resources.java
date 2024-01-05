package org.god.ibatis.utils;

import java.io.InputStream;

/**
 * GodBatis框架提供的一个工具类
 * 这个工具类专门完成“类路径”中资源的加载
 *
 * @author fzy
 * @date 2024/1/3 14:15
 */
public class Resources {
    /**
     * 工具类的构造方法建议私有化
     * 因为工具类的方法都是静态的，不需要创建对象就能调用
     * 为了避免 new 对象，所以构造方法私有化
     */
    private Resources() {
    }

    /**
     * 从类路径中加载资源
     * @param resource 放在类路径中的资源文件
     * @return 指向资源文件的一个输入流
     */
    public static InputStream getResourceAsStream(String resource) {
        return ClassLoader.getSystemClassLoader().getResourceAsStream(resource);
    }
}
