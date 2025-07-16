package com.AttendanceRegister.sdc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "attendance_table")
public class AttendanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String date;

    // ✅ New field to track multiple classes per day
    private int classNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    // 👉 Constructors
    public AttendanceRecord() {}

    public AttendanceRecord(String status, String date, int classNumber, User user, Subject subject) {
        this.status = status;
        this.date = date;
        this.classNumber = classNumber;
        this.user = user;
        this.subject = subject;
    }

    // 👉 Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "AttendanceRecord{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                ", classNumber=" + classNumber +
                ", userId=" + (user != null ? user.getId() : null) +
                ", subjectId=" + (subject != null ? subject.getId() : null) +
                '}';
    }
}
