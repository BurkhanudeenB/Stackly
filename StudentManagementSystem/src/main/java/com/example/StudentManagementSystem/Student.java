package com.example.StudentManagementSystem;

public class Student {
    private int studentId;
    private String studentName;
    private String department;
    private double marks;
    private double attendance;
    private StudentStatus status;

    public Student(int studentId, String studentName, String department,double marks, double attendance) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.department = department;
        this.marks = marks;
        this.attendance = attendance;
        this.status = StudentStatus.ACTIVE;
    }
    public int getStudentId(){
        return studentId;
    }
    public String getStudentName(){
        return studentName;
    }
    public String getDepartment(){
        return department;
    }
    public double getMarks(){
        return marks;
    }
    public double getAttendance(){
        return attendance;
    }
    public StudentStatus getStatus(){
        return status;
    }
    public void setStudentName(String studentName){
        this.studentName = studentName;
    }
    public void setDepartment(String department){
        this.department = department;
    }
    public void setMarks(double marks){
        if(marks >= 0 && marks <= 100){
            this.marks = marks;
        }
    }
    public void setAttendance(double attendance){
        if(attendance >= 0 && attendance <= 100){
            this.attendance = attendance;
        }
    }
    public void setStatus(StudentStatus status){
        this.status = status;
    }

    public String getGrade(){
        if(marks >= 90){
            return "O";
        }
        else if(marks >= 80){
            return "A";
        }
        else if(marks >= 70){
            return "B";
        }
        else if(marks >= 60){
            return "C";
        }
        else if(marks >= 50){
            return "D";
        }
        else if(marks >= 40){
            return "E";
        }
        else {
            return "Fail";
        }
    }

    public void displayStudent(){
        System.out.println("=========================");
        System.out.println("Student ID: " + studentId);
        System.out.println("Student Name: " + studentName);
        System.out.println("Department: " + department);
        System.out.println("Marks: " + marks);
        System.out.println("Attendance: " + attendance);
        System.out.println("Status: " + status);
        System.out.println("==========================");
    }
}
