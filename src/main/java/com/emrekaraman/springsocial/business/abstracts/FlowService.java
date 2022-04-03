package com.emrekaraman.springsocial.business.abstracts;

import com.emrekaraman.springsocial.auth.userAuthService.UserDetailsManager;
import com.emrekaraman.springsocial.business.dtos.FlowDto;
import com.emrekaraman.springsocial.core.utilities.DataResult;
import com.emrekaraman.springsocial.core.utilities.Result;
import com.emrekaraman.springsocial.entities.concretes.Flow;
import com.emrekaraman.springsocial.entities.concretes.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface FlowService {

    DataResult<Flow> add(FlowDto flowDto);
    DataResult<List<Flow>> getAll(int pageNo,int pageSize);
    DataResult<Flow> findById(Long flowId);
    Result isAuthorization(Long id,UserDetailsManager userDetailsManager);
    DataResult<List<Flow>> getAllByUserId(String username,int pageNo,int pageSize);
    DataResult<User> findNewPosts(Long id);
    DataResult<List<Flow>> findByIdGreaterThan(Long id);
    Result deleteById(Long id, UserDetailsManager userDetailsManager);
}
