package com.user.service;

import com.user.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */
public interface UserService {

    int addUser(User user);

    int deleteUser(Long id);

    int updateUserById(User user);

    List<User> getUser();

    User getUserById(Long id);
}
