package com.atjianyi.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {

    //声明连接池
    private static DataSource ds;
    static {
        InputStream is=null;
        try {
            //1. 读取文件
             is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties pro = new Properties();
            pro.load(is);

            //2. 创建连接池
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(is!=null);
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取数据库连接池
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws Exception {
        return ds.getConnection();
    }
}
