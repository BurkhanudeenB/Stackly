package com.example.StudentManagementSystem;


import java.util.HashMap;
import java.util.Map;

public class StudentService {

    private final HashMap<Integer, Student> students = new HashMap<>();

    public boolean addStudent(Student student) {

        if (students.containsKey(student.getStudentId())) {
            System.out.println("Student ID already exists!");
            return false;
        }

        students.put(student.getStudentId(), student);

        System.out.println("Student added successfully!");
        return true;
    }

    public Student searchStudent(int studentId) {

        return students.get(studentId);
    }

    public void searchStudentByName(String name) {

        boolean found = false;

        for (Student student : students.values()) {

            if (student.getStudentName().equalsIgnoreCase(name)) {
                student.displayStudent();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Student not found!");
        }
    }

    public void displayAllStudents() {

        if (students.isEmpty()) {
            System.out.println("No student records available!");
            return;
        }

        for (Student student : students.values()) {
            student.displayStudent();
        }
    }

    public boolean updateMarks(int studentId, double marks) {

        Student student = students.get(studentId);

        if (student == null) {
            return false;
        }

        student.setMarks(marks);
        System.out.println("Marks updated successfully!");
        return true;
    }

    public boolean updateAttendance(int studentId, double attendance) {

        Student student = students.get(studentId);

        if (student == null) {
            return false;
        }

        student.setAttendance(attendance);
        System.out.println("Attendance updated successfully!");
        return true;
    }

    public boolean updateStatus(int studentId, StudentStatus status) {

        Student student = students.get(studentId);

        if (student == null) {
            return false;
        }

        student.setStatus(status);
        System.out.println("Student status updated successfully!");
        return true;
    }

    public boolean removeStudent(int studentId) {

        if (students.remove(studentId) != null) {
            System.out.println("Student removed successfully!");
            return true;
        }

        System.out.println("Student not found!");
        return false;
    }

    public void displayTopper() {

        if (students.isEmpty()) {
            System.out.println("No student records available!");
            return;
        }

        Student topper = null;

        for (Student student : students.values()) {

            if (topper == null ||
                    student.getMarks() > topper.getMarks()) {
                topper = student;
            }
        }

        System.out.println("\nTOPPER DETAILS");
        topper.displayStudent();
    }

    public void displayStudentCount() {

        System.out.println("Total Students: " + students.size());
    }

    public void displayStudentsByStatus(StudentStatus status) {

        boolean found = false;

        for (Student student : students.values()) {

            if (student.getStatus() == status) {
                student.displayStudent();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No students found with status: " + status);
        }
    }

    public void displayStudentsByDepartment(String department) {

        boolean found = false;

        for (Student student : students.values()) {

            if (student.getDepartment().equalsIgnoreCase(department)) {
                student.displayStudent();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No students found in this department!");
        }
    }

    public void displayStudentSummary() {

        int passed = 0;
        int failed = 0;
        double totalMarks = 0;

        for (Student student : students.values()) {

            totalMarks += student.getMarks();

            if (student.getMarks() >= 50) {
                passed++;
            } else {
                failed++;
            }
        }

        System.out.println("\n===== STUDENT SUMMARY =====");
        System.out.println("Total Students : " + students.size());
        System.out.println("Passed         : " + passed);
        System.out.println("Failed         : " + failed);

        if (!students.isEmpty()) {
            System.out.printf("Average Marks  : %.2f%n",
                    totalMarks / students.size());
        }
    }
    public java.util.Set<Integer> getStudentIds() {
        return new java.util.TreeSet<>(students.keySet());
    }
}