package com.hzn.sales.service.impl;

import com.hzn.sales.dao.IUserDao;
import com.hzn.sales.model.user.User;
import com.hzn.sales.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserDao userDao;

    public int addUser(User user) {
        return userDao.insertUser(user);
    }

    public int deleteUserById(int userId) {
        return userDao.deleteUserById(userId);
    }

    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    public User getUserById(int userId) {
        return userDao.selectUserById(userId);
    }

    public List<User> getAllUsers() {
        return userDao.selectAllUsers();
    }

    public User userExist(String username, String password) {
        User user=userDao.selectUserByUserName(username);
        if(user==null||(!user.getPassword().equals(password)))
            return null;
        else
            return user;
    }
}
