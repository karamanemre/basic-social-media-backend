package com.emrekaraman.springsocial.business.abstracts;

import com.emrekaraman.springsocial.business.dtos.FlowDto;
import com.emrekaraman.springsocial.core.utilities.DataResult;
import com.emrekaraman.springsocial.core.utilities.Result;
import com.emrekaraman.springsocial.entities.concretes.Flow;

import java.util.List;

public interface FlowService {

    DataResult<Flow> add(FlowDto flowDto);
    DataResult<List<Flow>> getAll(int pageNo,int pageSize);
    DataResult<List<Flow>> getAllByUserId(String username,int pageNo,int pageSize);
}
