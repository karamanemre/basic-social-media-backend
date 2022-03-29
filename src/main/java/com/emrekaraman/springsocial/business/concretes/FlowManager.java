package com.emrekaraman.springsocial.business.concretes;

import com.emrekaraman.springsocial.business.abstracts.FlowService;
import com.emrekaraman.springsocial.business.abstracts.UserService;
import com.emrekaraman.springsocial.business.constants.Messages;
import com.emrekaraman.springsocial.business.dtos.FlowDto;
import com.emrekaraman.springsocial.business.dtos.PagesDto;
import com.emrekaraman.springsocial.core.utilities.DataResult;
import com.emrekaraman.springsocial.core.utilities.SuccessDataResult;
import com.emrekaraman.springsocial.dataAccess.abstracts.FlowDao;
import com.emrekaraman.springsocial.entities.concretes.Flow;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FlowManager implements FlowService {

    private final FlowDao flowDao;
    private final ModelMapper modelMapper;

    public FlowManager(FlowDao flowDao, ModelMapper modelMapper) {
        this.flowDao = flowDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<Flow> add(FlowDto flowDto) {
        Flow flow = modelMapper.map(flowDto, Flow.class);
        flow.setCreationDate(new Date());
        return new SuccessDataResult(this.flowDao.save(flow),Messages.SUCCESSFULLY_ADDED);
    }

    @Override
    public DataResult<List<Flow>> getAll(int pageNo,int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize,Sort.by("creationDate").descending());
        return new SuccessDataResult(new PagesDto<Flow>(this.flowDao.findAll(pageable)),Messages.SUCCESSFULLY_GETALL);
    }

    @Override
    public DataResult<List<Flow>> getAllByUserId(String username, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, Sort.by("creationDate").descending());
        return new SuccessDataResult(new PagesDto<Flow>(this.flowDao.findAllByUserUsername(username,pageable)),Messages.SUCCESSFULLY_GETALL);
    }


}