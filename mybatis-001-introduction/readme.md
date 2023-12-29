开发我的第一个Mybatis程序
1. `resource`目录：
   - 放在这个目录当中的，一般都是资源文件、配置文件。
   - 直接放到`resources`目录下的资源，等同于放到了类的根路径下。
2. 开发步骤：
   - 第一步：打包方式 jar
   - 第二步：引入依赖
     - mybatis依赖
     - mysql驱动依赖
   - 第三步：编写mybatis核心配置文件：`mybatis-config.xml`
     - **注意**：
       - 第一：这个文件名不是必须叫做 `mybatis-config.xml`，可以用其他名字，只是大家都采用这个名字
       - 第二：这个文件存放的位置也不是固定的，可以随意，但一般情况下，会放到类的根路径下（我们放在`resources`目录下）
   - 第四步：编写`XxxMapper.xml`文件，在这个配置文件中编写sql语句。
     - 这个文件名也不是固定的，放的位置也不是固定的，在这里我们给它起名为 `CarMapper.xml`，放到类的根路径下（我们放在`resources`目录下）
   - 第五步：在 `mybatis-config.xml` 文件中指定 `XxxMapper.xml` 文件的路径。
     - `<mapper resource="CarMapper.xml"/>`
     - **注意**：resource属性会自动从类的根路径下开始查找资源
   - 第六步：编写Mybatis程序，使用Mybatis的类库，编写mybatis程序，连接数据库，做CRUD。
     - 在Mybatis中，负责执行sql语句的对象叫做：`SqlSession`
     - `SqlSession` 是专门用来执行sql语句的，是一个Java程序和数据库之间的一次会话。
     - 要想获取 `SqlSession` 对象，需要先获取 `SqlSessionFactory` 对象，通过 `SqlSessionFactory` 对象来生产 `SqlSession` 对象。
     - 怎么获取 `SqlSessionFactory` 对象？
       - 首先获取 `SqlSessionFactoryBuilder` 对象
       - 再通过 `SqlSessionFactoryBuilder` 对象的 `build` 方法，来获取一个 `SqlSessionFactory` 对象。
     - 因此，Mybatis核心对象包括：
       - `SqlSessionFactoryBuilder`
       - `SqlSessionFactory`
       - `SqlSession`
         - `SqlSessionFactoryBuilder` --创建--> `SqlSessionFactory` --生产--> `SqlSession`
3. 官方文档：从 XML 中构建 `SqlSessionFactory`
   - 通过官方文档可以得知：
     - 第一：在Mybatis中一定有一个重要对象，这个对象是 `SqlSessionFactory` 对象
     - 第二：`SqlSessionFactory` 对象的创建需要 XML
4. mybatis中有两个主要的配置文件：
   - 一个是 `mybatis-config.xml`，这是核心配置文件，主要配置连接数据库的信息等。（一个）
   - 另一个是 `XxxMapper.xml`，这个文件是专门用来编写sql语句的配置文件。（一个表对应一个）
     - `t_user`表，一般会对应一个 `UserMapper.xml`。
     - `t_student`表，一般会对应一个 `StudentMapper.xml`。