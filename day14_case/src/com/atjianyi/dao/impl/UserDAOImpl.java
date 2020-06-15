package com.atjianyi.dao.impl;

import com.atjianyi.dao.UserDAO;
import com.atjianyi.domain.User;
import com.atjianyi.utils.JDBCUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDAOImpl implements UserDAO {
    //创建操作对象
    private QueryRunner qr = new QueryRunner();

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        String sql = "select * from `user`";
        MapListHandler map = new MapListHandler();
        try {
            List<Map<String, Object>> query = qr.query(JDBCUtils.getConnection(), sql, map);
            for (Map<String, Object> map1 : query) {
                User user = new User();
                BeanUtils.populate(user ,map1);
                list.add(user);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User login(User user) throws Exception {
        String sql = "select name,password from `user` where name=? and password=?";
        User query = qr.query(JDBCUtils.getConnection(), sql, new BeanHandler<>(User.class), user.getName(), user.getPassword());
        return query;
    }

    @Override
    public void addUser(User user) {
        String sql = "insert into user(username,sex,age,qq,address,email) values(?,?,?,?,?,?)";
        try {
            qr.update(JDBCUtils.getConnection(),sql,user.getUsername(),user.getSex(),user.getAge(),user.getQq(),user.getAddress(),user.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void del(String id)  {
        String sql = "delete from user where id=?";
        int i = Integer.parseInt(id);
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            qr.update(conn,sql,i);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                DbUtils.closeQuietly(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User findUserById(int id) {
        String sql = "select * from `user` where id=?";

        try {
            User query = qr.query(JDBCUtils.getConnection(), sql, new BeanHandler<User>(User.class),id);
            return query;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateUser(User user) {
        String sql = "update user set username=?,sex=?,age=?,qq=?,address=?,email=? where id=?";
        String username = user.getUsername();
        String sex = user.getSex();
        int age = user.getAge();
        String qq = user.getQq();
        String address = user.getAddress();
        String email = user.getEmail();
        int id = user.getId();

        try {
            qr.update(JDBCUtils.getConnection(),sql,username,sex,age,qq,address,email,id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

        }
    }

    @Override
    public List<User> findOther(int index, int rows, Map<String, String[]> condition) {

        //1. 定义基础的模板
        String sql = "select username,sex,age,qq,address,email from `user` where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();
        //2. 遍历map
        Set<String> strings = condition.keySet();
        for (String key : strings) {
            if ("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }
            String value = condition.get(key)[0];
            if(value!=null &&!"".equals(condition.get(key)[0])) {
                //拼接
                sb.append(" and " + key + " like ?" );
                params.add("%"+value+"%");
            }
        }
        sb.append(" limit ?,? ");
        params.add(index);
        params.add(rows);


        ArrayList<User> list = new ArrayList<>();
        MapListHandler map = new MapListHandler();
        Connection connection = null;
        try {
             connection = JDBCUtils.getConnection();
            List<Map<String, Object>> query = qr.query(connection,sb.toString(), map, params.toArray());
            for (Map<String, Object> map1 : query) {
                User user = new User();
                BeanUtils.populate(user,map1);
                list.add(user);
            }
            return list;
        } catch (SQLException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeQuietly(connection);
        }
        return null;
    }

    @Override
    public int getTotalCount(Map<String, String[]> condition) {
        //1. 定义模板
        String sql = "select count(*) from `user` where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> list = new ArrayList<>();
        //2. 遍历map
        Set<String> strings = condition.keySet();
        for (String key : strings) {
            if ("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }
            String value = condition.get(key)[0];
            if(value!=null &&!"".equals(condition.get(key)[0])) {
                //拼接
                sb.append(" and " + key + " like ?" );
                list.add("%"+value+"%");
            }
        }

        Long count;
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            count = qr.query(conn,sb.toString(), new ScalarHandler<Long>(),list.toArray());
            int count1 = (int)((long)count);
            return count1;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeQuietly(conn);
        }
        return 0;
    }


}
