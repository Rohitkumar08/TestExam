package com.examBuddy.ExamBuddy.service;

import com.examBuddy.ExamBuddy.database.mysql.dao.UserDao;
import com.examBuddy.ExamBuddy.database.mysql.entity.LeadsInfo;
import com.examBuddy.ExamBuddy.utils.BaseResponseUtil;
import com.examBuddy.ExamBuddy.request.LeadsRequest;
import com.examBuddy.ExamBuddy.response.LeadsResponse;
import com.examBuddy.ExamBuddy.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created 06/09/20 10:47 PM
 *
 * @author Rohit Rawani
 */
@Service
@Slf4j
public class UserService {


    @Autowired
    private UserDao userDao;

    /**
     * save leads info
     *
     * @param request
     * @return
     */
    public LeadsResponse saveLeads(LeadsRequest request) {
        LeadsInfo leadsInfo = LeadsInfo.builder().name(request.getName()).mobile(request.getMobile()).type(request.getType().getType()).build();
        leadsInfo.create();
        leadsInfo = userDao.save(leadsInfo);
        log.info("saving leads info into db with details {}", leadsInfo.toString());
        LeadsResponse response = LeadsResponse.builder().id(leadsInfo.getId()).build();
        return BaseResponseUtil.createBaseResponse(response, StatusCode.OK);
    }
}
