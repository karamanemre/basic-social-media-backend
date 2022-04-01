package com.emrekaraman.springsocial.dataAccess.abstracts;

import com.emrekaraman.springsocial.entities.concretes.Flow;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface FlowDao extends JpaRepository<Flow,Long> {

    Page<Flow> findAllByUserUsername(String username, Pageable pageable);
    Long countByIdGreaterThan(Long id);
    List<Flow> findByIdGreaterThan(Long id);
}
