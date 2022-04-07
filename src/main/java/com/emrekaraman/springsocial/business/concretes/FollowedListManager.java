package com.emrekaraman.springsocial.business.concretes;

import com.emrekaraman.springsocial.business.abstracts.FollowedListService;
import com.emrekaraman.springsocial.core.utilities.DataResult;
import com.emrekaraman.springsocial.core.utilities.SuccessDataResult;
import com.emrekaraman.springsocial.dataAccess.abstracts.FollowedDao;
import com.emrekaraman.springsocial.entities.concretes.FollowedList;
import org.springframework.stereotype.Service;

@Service
public class FollowedListManager implements FollowedListService {

    private final FollowedDao followedDao;

    public FollowedListManager(FollowedDao followedDao) {
        this.followedDao = followedDao;
    }

    @Override
    public DataResult<FollowedList> findByUserId(Long userId) {
        return new SuccessDataResult(followedDao.findByUser_Id(userId));
    }
}
