package com.examBuddy.ExamBuddy.controller;

import com.examBuddy.ExamBuddy.request.LeadsRequest;
import com.examBuddy.ExamBuddy.response.LeadsResponse;
import com.examBuddy.ExamBuddy.service.UserService;
import com.examBuddy.ExamBuddy.utils.BaseResponseUtil;
import com.examBuddy.ExamBuddy.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created 06/09/20 10:39 PM
 *
 * @author Rohit Rawani
 */

@RestController
@RequestMapping("/user/v1")
@Slf4j
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping(value = "/save")
    public ResponseEntity<LeadsResponse> savePaymentDetails(@RequestBody LeadsRequest request) {
        try {
            return ResponseEntity.ok(userService.saveLeads(request));
        } catch (Exception e) {
            log.error("Error while adding new account details {}", e);
            return new ResponseEntity<>(BaseResponseUtil.createBaseResponse(new LeadsResponse(), StatusCode.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
