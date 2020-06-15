package com.atjianyi.dao;

import com.atjianyi.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDAO  {

    List<User> findAll();

    User login(User user) throws Exception;

    void addUser(User user);

    void del(String id);

    User findUserById(int id);

    void updateUser(User user);

    List<User> findOther(int index, int rows, Map<String, String[]> condition);

    int getTotalCount(Map<String, String[]> condition);
}
