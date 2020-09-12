package com.examBuddy.ExamBuddy;

import com.examBuddy.ExamBuddy.response.BaseResponse;
import com.examBuddy.ExamBuddy.utils.BaseResponseUtil;
import com.examBuddy.ExamBuddy.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
public class ExamBuddyApplication {

    @Autowired
    ConfigUtils configUtils;

	public static void main(String[] args) {
		SpringApplication.run(ExamBuddyApplication.class, args);
	}

    @RequestMapping(value = "/test")
    public ResponseEntity<BaseResponse> redirect() {
        return ResponseEntity.ok(BaseResponseUtil.createSuccessBaseResponse());
    }



    @Bean
    CommandLineRunner commandLineRunner(final ApplicationContext ctx) {
        return args -> {
            log.info("started application =====================  {} {} =====================", configUtils.getStringValue("spring.application.name"), configUtils.getStringValue("spring.application.version"));
        };
    }
}
