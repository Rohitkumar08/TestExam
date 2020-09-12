package com.examBuddy.ExamBuddy.database.mysql.dao;

import com.examBuddy.ExamBuddy.database.mysql.entity.LeadsInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created 06/09/20 11:33 PM
 *
 * @author Rohit Rawani
 */
@Repository
public interface UserDao extends CrudRepository<LeadsInfo, Long> {

}
