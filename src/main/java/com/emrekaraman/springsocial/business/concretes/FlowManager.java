package com.emrekaraman.springsocial.business.concretes;

import com.emrekaraman.springsocial.auth.userAuthService.UserDetailsManager;
import com.emrekaraman.springsocial.business.abstracts.FlowService;
import com.emrekaraman.springsocial.business.constants.Messages;
import com.emrekaraman.springsocial.business.dtos.FlowDto;
import com.emrekaraman.springsocial.business.dtos.PagesDto;
import com.emrekaraman.springsocial.core.business.BusinessRules;
import com.emrekaraman.springsocial.core.utilities.*;
import com.emrekaraman.springsocial.dataAccess.abstracts.FlowDao;
import com.emrekaraman.springsocial.entities.concretes.Flow;
import com.emrekaraman.springsocial.entities.concretes.User;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Override
    public DataResult<User> findNewPosts(Long id) {
        return new SuccessDataResult(this.flowDao.countByIdGreaterThan(id));
    }

    @Override
    public DataResult<List<Flow>> findByIdGreaterThan(Long id) {
        return new SuccessDataResult(this.flowDao.findByIdGreaterThan(id));
    }

    @Override
    public DataResult<Flow> findById(Long id) {
        Optional<Flow> flow = this.flowDao.findById(id);
        if (!flow.isEmpty()){
            return new SuccessDataResult(flow.get(),Messages.FLOW_FOUND);
        }
        return new ErrorDataResult(Messages.FLOW_NOT_FOUND);
    }

    @Override
    public Result isAuthorization(Long flowId,UserDetailsManager userDetailsManager) {
        Optional<Flow> result = this.flowDao.findById(flowId);
        if (result.get().getUser().getId() != userDetailsManager.getUser().getId()){
            return new ErrorResult(Messages.UNAUTHORIZE);
        }
        return new SuccessResult();
    }

    @Override
    public Result deleteById(Long id, UserDetailsManager userDetailsManager) {
        Result result = BusinessRules.run(
                        findById(id),isAuthorization(id,
                        userDetailsManager
                ));
        if (result != null){
            return new ErrorResult(result.getMessage());
        }
        this.flowDao.deleteById(id);
        return new SuccessResult(Messages.SUCCESSFULLY_PROCESS);
    }
}
