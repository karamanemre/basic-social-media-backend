package com.emrekaraman.springsocial.business.abstracts;

import com.emrekaraman.springsocial.core.utilities.DataResult;
import com.emrekaraman.springsocial.entities.concretes.FollowedList;

public interface FollowedListService {

    DataResult<FollowedList> findByUserId(Long userId);
}
