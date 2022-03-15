package com.emrekaraman.springsocial.dao;

import com.emrekaraman.springsocial.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

    boolean existsByUserName(String userName);
}
