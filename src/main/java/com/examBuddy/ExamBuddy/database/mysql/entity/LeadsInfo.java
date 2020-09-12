package com.examBuddy.ExamBuddy.database.mysql.entity;

import com.examBuddy.ExamBuddy.utils.LeadsType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * Created 06/09/20 11:24 PM
 *
 * @author Rohit Rawani
 */
@Entity
@Table(name = "leads_info")
@SuperBuilder
@AllArgsConstructor
@Data
public class LeadsInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "type")
    private String type;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "preferred_time_slot")
    private String timeSlot;

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
