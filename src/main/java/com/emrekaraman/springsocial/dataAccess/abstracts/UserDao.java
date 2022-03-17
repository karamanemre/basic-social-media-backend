package com.emrekaraman.springsocial.dataAccess.abstracts;

import com.emrekaraman.springsocial.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Long> {


    boolean existsByUsername(String username);

    User findByUsername(String username);
}
