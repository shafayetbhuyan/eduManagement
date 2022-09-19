package common.entity;


import java.io.Serializable;
import java.time.LocalDate;


public class Assignment implements Serializable {
    private String assignmentId;
    private String assignmentName;
    private String assignmentSubject;
    private String assignmentFilePath;
    private LocalDate publishedDate;
    private LocalDate SubmitDate;

    public Assignment(String assignmentId, String assignmentName, String assignmentSubject, String assignmentFilePath, LocalDate publishedDate, LocalDate submitDate) {
        this.assignmentId = assignmentId;
        this.assignmentName = assignmentName;
        this.assignmentSubject = assignmentSubject;
        this.assignmentFilePath = assignmentFilePath;
        this.publishedDate = publishedDate;
        SubmitDate = submitDate;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getAssignmentSubject() {
        return assignmentSubject;
    }

    public void setAssignmentSubject(String assignmentSubject) {
        this.assignmentSubject = assignmentSubject;
    }

    public String getAssignmentFilePath() {
        return assignmentFilePath;
    }

    public void setAssignmentFilePath(String assignmentFilePath) {
        this.assignmentFilePath = assignmentFilePath;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public LocalDate getSubmitDate() {
        return SubmitDate;
    }

    public void setSubmitDate(LocalDate submitDate) {
        SubmitDate = submitDate;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentId=" + assignmentId +
                ", assignmentName='" + assignmentName + '\'' +
                ", assignmentSubject='" + assignmentSubject + '\'' +
                ", assignmentFilePath='" + assignmentFilePath + '\'' +
                ", publishedDate=" + publishedDate +
                ", SubmitDate=" + SubmitDate +
                '}';
    }
}
