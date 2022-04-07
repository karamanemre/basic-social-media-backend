package com.emrekaraman.springsocial.dataAccess.abstracts;

import com.emrekaraman.springsocial.entities.concretes.FollowedList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowedDao extends JpaRepository<FollowedList,Long> {


    List<FollowedList> findByUser_Id(Long userId);
}
