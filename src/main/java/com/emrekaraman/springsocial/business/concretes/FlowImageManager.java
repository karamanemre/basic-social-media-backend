package com.emrekaraman.springsocial.business.concretes;

import com.emrekaraman.springsocial.business.abstracts.FlowImageService;
import com.emrekaraman.springsocial.business.constants.Messages;
import com.emrekaraman.springsocial.business.dtos.FlowImageDto;
import com.emrekaraman.springsocial.core.utilities.DataResult;
import com.emrekaraman.springsocial.core.utilities.SuccessDataResult;
import com.emrekaraman.springsocial.dataAccess.abstracts.FlowDao;
import com.emrekaraman.springsocial.dataAccess.abstracts.FlowImageDao;
import com.emrekaraman.springsocial.entities.concretes.Flow;
import com.emrekaraman.springsocial.entities.concretes.FlowImage;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class FlowImageManager implements FlowImageService {

    private final FlowImageDao flowImageDao;
    private final ModelMapper modelMapper;

    public FlowImageManager(FlowImageDao flowImageDao, ModelMapper modelMapper) {
        this.flowImageDao = flowImageDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<Flow> add(FlowImageDto flowImageDto) {
        FlowImage flowImage = modelMapper.map(flowImageDto, FlowImage.class);
        return new SuccessDataResult(this.flowImageDao.save(flowImage), Messages.SUCCESSFULLY_ADDED);
    }
}
