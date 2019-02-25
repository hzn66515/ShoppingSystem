package com.hzn.sales.dao;

import com.hzn.sales.model.user.User;

import java.util.List;

public interface IUserDao {
    int insertUser(User user);

    int deleteUserById(int userId);

    int updateUser(User user);

    User selectUserById(int userId);

    List<User> selectAllUsers();

    User selectUserByUserName(String username);
}
