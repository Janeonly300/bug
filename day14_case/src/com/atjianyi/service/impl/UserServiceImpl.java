package com.atjianyi.service.impl;


import com.atjianyi.dao.UserDAO;
import com.atjianyi.domain.PageBean;
import com.atjianyi.domain.User;
import com.atjianyi.service.UserService;
import com.atjianyi.dao.impl.UserDAOImpl;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDAO  userDAO = new UserDAOImpl();
    @Override
    public List<User> findAll() {
        // 调用DAO完成查询逻辑
        return userDAO.findAll();
    }

    /**
     * 登录页面
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public User login(User user) throws Exception {
        return  userDAO.login(user);
    }

    @Override
    public void addUser(User user){
        userDAO.addUser(user);
    }

    @Override
    public void delUser(String id) {
        userDAO.del(id);
    }

    /**
     * 根据id查找。并作出
     * @param id
     * @return
     */
    @Override
    public User findUserById(int id) {
        return  userDAO.findUserById(id);
    }

    /**
     * 修改信息
     * @param user
     */
    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void delSelectUser(String[] ids) {
        for (String id : ids) {
            userDAO.del(id);
        }
    }

    @Override
    public PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition) {
        //1. 创建空的PageBean都西昂
        PageBean<User> pageBean = new PageBean<>();
        int i = Integer.parseInt(currentPage);
        int j = Integer.parseInt(rows);
        pageBean.setCurrentPage(i); // 设置当前页码
        pageBean.setRows(j); //设置每页显示的条数

        //2. 计算出limit第一个开始所引
        int index = (i - 1) * j;
        //3. 获取List对象
        pageBean.setList(userDAO.findOther(index,j,condition));
        System.out.println(userDAO.findOther(index,j, condition));

        //4. 设置总记录数字
        int totalCount = userDAO.getTotalCount(condition);
        pageBean.setTotalCount(totalCount);
        //5. 设置页码数字
        pageBean.setTotalPage(totalCount%j==0?totalCount/j:totalCount/j+1);

        return pageBean;


    }
}