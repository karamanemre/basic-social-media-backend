package com.emrekaraman.springsocial.business.abstracts;

import com.emrekaraman.springsocial.business.dtos.FlowImageDto;
import com.emrekaraman.springsocial.core.utilities.DataResult;
import com.emrekaraman.springsocial.entities.concretes.Flow;
import org.springframework.web.multipart.MultipartFile;

public interface FlowImageService {

    DataResult<Flow> add(FlowImageDto flowImageDto);

}
