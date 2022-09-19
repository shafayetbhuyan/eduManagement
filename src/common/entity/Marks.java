package common.entity;

import common.entity.Assignment;
import common.entity.User;

import java.io.Serializable;

public class Marks implements Serializable {
    private String studentID;
    private String studentName;
    private String assignmentID;
    private String assignmentName;
    private int mark;

    public Marks(String studentID, String studentName, String assignmentID, String assignmentName, int mark) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.assignmentID = assignmentID;
        this.assignmentName = assignmentName;
        this.mark = mark;
    }


    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(String assignmentID) {
        this.assignmentID = assignmentID;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }


    @Override
    public String toString() {
        return "Marks{" +
                "studentID='" + studentID + '\'' +
                ", studentName='" + studentName + '\'' +
                ", assignmentID='" + assignmentID + '\'' +
                ", assignmentName='" + assignmentName + '\'' +
                ", mark=" + mark +
                '}';
    }
}
