package com.ssp.dao;

import com.ssp.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
//<User,Long>  实体类，主键类型
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);
}
