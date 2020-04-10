package com.ssp.service;

import com.ssp.pojo.User;

public interface UserService {

    User checkUser(String username,String password);
}
