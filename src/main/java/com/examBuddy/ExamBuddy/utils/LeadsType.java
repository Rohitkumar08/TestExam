package com.examBuddy.ExamBuddy.utils;

/**
 * Created 06/09/20 11:27 PM
 *
 * @author Rohit Rawani
 */
public enum LeadsType {
    STUDENT("Student"), PARENT("Parent");

    private String type;

    LeadsType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}



