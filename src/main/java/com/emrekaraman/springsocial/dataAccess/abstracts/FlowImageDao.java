package com.emrekaraman.springsocial.dataAccess.abstracts;

import com.emrekaraman.springsocial.entities.concretes.FlowImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlowImageDao extends JpaRepository<FlowImage,Long> {
}
