package com.hzn.sales.service;

import com.hzn.sales.model.user.User;

import java.util.List;

public interface IUserService {
    int addUser(User user);

    int deleteUserById(int userId);

    int updateUser(User user);

    User getUserById(int userId);

    List<User> getAllUsers();

    User userExist(String username,String password);
}
