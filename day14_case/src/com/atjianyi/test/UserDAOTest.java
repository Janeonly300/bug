package com.atjianyi.test;

import com.atjianyi.dao.UserDAO;
import com.atjianyi.dao.impl.UserDAOImpl;
import com.atjianyi.domain.User;
import org.junit.Test;

import java.util.List;

public class UserDAOTest {
    private UserDAO dao = new UserDAOImpl();

    /**
     * 测试dao
     */
    @Test
    public void testFindAll(){
        List<User> all = dao.findAll();
        all.forEach(System.out::println
        );
    }
}