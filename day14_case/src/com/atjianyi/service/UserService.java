package com.atjianyi.service;

import com.atjianyi.domain.PageBean;
import com.atjianyi.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 业务逻辑层
 */
public interface UserService  {
    List<User> findAll();

    User login(User user) throws Exception;

    void addUser(User user);

    void delUser(String id);

    User findUserById(int id);

    void updateUser(User user);

    void delSelectUser(String[] ids);

    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
