package common.entity;

import java.io.Serializable;

public class SubmitedAssignment implements Serializable {
    private String studentName;
    private String studentID;
    private String assignmentNo;
    private String assignmentName;
    private String publishDate;
    private String submitDate;
    private String assignmentFilePath;


    public SubmitedAssignment(String studentName, String studentID, String assignmentNo, String assignmentName, String publishDate, String submitDate, String assignmentFilePath) {
        this.studentName = studentName;
        this.studentID = studentID;
        this.assignmentNo = assignmentNo;
        this.assignmentName = assignmentName;
        this.publishDate = publishDate;
        this.submitDate = submitDate;
        this.assignmentFilePath = assignmentFilePath;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getAssignmentNo() {
        return assignmentNo;
    }

    public void setAssignmentNo(String assignmentNo) {
        this.assignmentNo = assignmentNo;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public String getAssignmentFilePath() {
        return assignmentFilePath;
    }

    public void setAssignmentFilePath(String assignmentFilePath) {
        this.assignmentFilePath = assignmentFilePath;
    }
}
