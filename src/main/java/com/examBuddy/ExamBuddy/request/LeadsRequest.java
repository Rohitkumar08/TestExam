package com.examBuddy.ExamBuddy.request;

import com.examBuddy.ExamBuddy.utils.LeadsType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created 06/09/20 10:40 PM
 *
 * @author Rohit Rawani
 */
@Data
public class LeadsRequest {

    private String name;

    private String mobile;

    private LeadsType type;

    @JsonProperty("email_id")
    private String emailId;

    @JsonProperty("preferred_time_slot")
    private String timeSlot;

}
