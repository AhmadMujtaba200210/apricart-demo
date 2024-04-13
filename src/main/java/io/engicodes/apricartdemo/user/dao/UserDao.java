package io.engicodes.apricartdemo.user.dao;

import io.engicodes.apricartdemo.user.User;



public interface UserDao {
    User getUserByUserId(Integer userId);
}
