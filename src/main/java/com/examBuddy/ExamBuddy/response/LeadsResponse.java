package com.examBuddy.ExamBuddy.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Created 06/09/20 10:45 PM
 *
 * @author Rohit Rawani
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class LeadsResponse extends  BaseResponse{

    private Long id;
}
