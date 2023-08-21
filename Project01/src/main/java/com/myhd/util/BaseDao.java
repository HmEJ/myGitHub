package com.myhd.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * className BaseDao 操控sql语句的通用工具类, 有关流closeAll()方法, 更新数据库表updateInfo()方法,
 * 查询数据库表selectInfo()方法, 统计数据库表中信息count()方法
 * packageName com.itstudy.config
 * Description BaseDao
 *
 * @author huian
 * @version 1.0
 * @Date: 2023/8/15 18:49
 */
public class BaseDao {
    /*静态连接对象*/
    public static Connection connection;
    private static PreparedStatement prepareStatement;
    private static ResultSet query;
    private static DataSource dataSource;
    /*日志输出对象log4j*/
    private static Logger logger = Logger.getLogger(BaseDao.class);
    /*加载德鲁伊连接池*/
    static {
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("properties.properties");
            Properties properties = new Properties();
            properties.load(is);
            /*加载驱动和连接数据库*/
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("数据库连接失败, 请检查数据库连接url, username, password");
        }
    }
    /**
     * Description: BaseDao 工具类的无参构造私有化
     * @return null
     * @author huian
     * @Date 2023/8/16
     * */
    public BaseDao(){
    }

    /**
     * Description: closeAll 关闭所有的连接流
     * @return void
     * @author huian
     * @Date 2023/8/16
     * */
    public static void closeAll(Connection connection, PreparedStatement preparedStatement, ResultSet query){
        try {
            if (query != null) {
                query.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("关流失败");
        }
    }

    /**
     * Description: updateInfo 删除, 更改, 增加三种的通用方法
     * @return int
     * @author huian
     * @Date 2023/8/16
     * */
    public static int updateInfo(String sql, Object...objects) {
        try {
            connection = dataSource.getConnection();
            /*获得语句执行对象*/
            prepareStatement = connection.prepareStatement(sql);
            for (int i = 0; i < objects.length; i++) {
                /*配置参数*/
                prepareStatement.setObject(i + 1, objects[i]);
            }
            return prepareStatement.executeUpdate();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
                logger.error("sql语句回滚失败");
            }
            e.printStackTrace();
            logger.error(e);
        } finally {
            closeAll(connection, prepareStatement, query);
        }
        return -1;
    }

    /**
     * Description: selectInfo 查询方法
     * @return java.util.List<java.lang.Object>
     * @author huian
     * @Date 2023/8/16
     * */
    public static List<?> selectInfo(String sql, Class<?> clazz, Object...objects) {
        try {
            List<Object> arrayList = new ArrayList<>();
            connection = dataSource.getConnection();
            /*获得语句执行对象*/
            prepareStatement = connection.prepareStatement(sql);
            for (int i = 0; i < objects.length; i++) {
                /*配置参数*/
                prepareStatement.setObject(i + 1, objects[i]);
            }
            /*查询结果集*/
            query = prepareStatement.executeQuery();
            /*通过反射为对象属性赋值*/
            Field[] fields = clazz.getDeclaredFields();
            while (query.next()) {
                Object instance = clazz.getDeclaredConstructor().newInstance();
                for (Field field : fields) {
                    field.setAccessible(true);
                    field.set(instance, query.getObject(field.getName()));
                }
                arrayList.add(instance);
            }
            return arrayList;
        } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
                logger.error("sql语句回滚失败");
            }
            e.printStackTrace();
            logger.error(e);
        } finally {
            closeAll(connection, prepareStatement, query);
        }
        return new ArrayList<>();
    }


    /**
     * Description: countInfo
     * 根据条件统计表格中的总数据量, 可以用于根据单页展示数量计算总页数, 也可以用于判断当前登录或者注册时用户是否存在,
     * 也可以根据日期范围或者商品id来统计销售金额
     * sql语句一般为: select count(*) as count from tb_name;
     * select count(*) as count from tb_name where id = ?;
     * select sum(*) as count from tb_name where date between ? and ?;
     * @return int
     * @author huian
     * @Date 2023/8/18
     * */
    public static Object countInfo(String sql, Object... objects){
        Object count = 0;
        try {
            connection = dataSource.getConnection();
            prepareStatement = connection.prepareStatement(sql);
            for (int i = 0; i < objects.length; i++) {
                /*配置参数*/
                prepareStatement.setObject(i + 1, objects[i]);
            }
            query = prepareStatement.executeQuery();
            if (query.next()) {
                count = query.getObject("count");
            }
            return count;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
                logger.error("sql语句回滚失败");
            }
            e.printStackTrace();
            logger.error(e);
        } finally {
            BaseDao.closeAll(connection, prepareStatement, query);
        }
        return count;
    }

}
