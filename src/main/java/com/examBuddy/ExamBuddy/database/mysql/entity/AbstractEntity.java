package com.examBuddy.ExamBuddy.database.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Column;

/**
 * Created 06/09/20 11:29 PM
 *
 * @author Rohit Rawani
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbstractEntity {

    @Column(name =  "created")
    private Long created;

    @Column(name = "updated")
    private Long updated;

    @Column(name = "deleted")
    private Integer deleted;

    public void update(String updatedBy) {
        setUpdated(System.currentTimeMillis());
    }

    public void create() {
        setCreated(System.currentTimeMillis());
        setUpdated(System.currentTimeMillis());
        setDeleted(0);
    }

}
