package com.example.StudentManagementSystem;


import java.util.Scanner;

public class StudentManagementSystem {

    private static final Scanner sc = new Scanner(System.in);

    private static final StudentService service = new StudentService();

    private static final StudentFileService fileService =
            new StudentFileService();

    public static void main(String[] args) {

        fileService.loadStudents(service);

        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Search Student by Name");
            System.out.println("5. Update Student Marks");
            System.out.println("6. Update Student Attendance");
            System.out.println("7. Update Student Status");
            System.out.println("8. Remove Student");
            System.out.println("9. Display Topper");
            System.out.println("10. Display Student Count");
            System.out.println("11. Filter Students by Status");
            System.out.println("12. Filter Students by Department");
            System.out.println("13. Display Student Summary");
            System.out.println("14. Save Records");
            System.out.println("0. Exit");

            choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    service.displayAllStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    System.out.print("Enter student name: ");
                    String name = sc.nextLine().trim();
                    service.searchStudentByName(name);
                    break;

                case 5:
                    updateMarks();
                    break;

                case 6:
                    updateAttendance();
                    break;

                case 7:
                    updateStatus();
                    break;

                case 8:
                    int removeId = readInt("Enter student ID: ");
                    service.removeStudent(removeId);
                    break;

                case 9:
                    service.displayTopper();
                    break;

                case 10:
                    service.displayStudentCount();
                    break;

                case 11:
                    filterByStatus();
                    break;

                case 12:
                    System.out.print("Enter department: ");
                    String department = sc.nextLine().trim();
                    service.displayStudentsByDepartment(department);
                    break;

                case 13:
                    service.displayStudentSummary();
                    break;

                case 14:
                    fileService.saveStudents(service);
                    break;

                case 0:
                    fileService.saveStudents(service);
                    System.out.println("Thank you for using Student Management System!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 0);

        sc.close();
    }

    private static void addStudent() {

        int id = readInt("Enter Student ID: ");

        if (service.searchStudent(id) != null) {
            System.out.println("Student ID already exists!");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Department: ");
        String department = sc.nextLine().trim();

        double marks = readDouble("Enter Marks (0-100): ");

        if (marks < 0 || marks > 100) {
            System.out.println("Marks must be between 0 and 100!");
            return;
        }

        double attendance = readDouble("Enter Attendance (0-100): ");

        if (attendance < 0 || attendance > 100) {
            System.out.println("Attendance must be between 0 and 100!");
            return;
        }

        if (name.isBlank() || department.isBlank() ||
                name.contains("|") || department.contains("|")) {
            System.out.println("Invalid name or department!");
            return;
        }

        Student student = new Student(
                id, name, department, marks, attendance
        );

        service.addStudent(student);
    }

    private static void searchStudent() {

        int id = readInt("Enter Student ID: ");

        Student student = service.searchStudent(id);

        if (student != null) {
            student.displayStudent();
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void updateMarks() {

        int id = readInt("Enter Student ID: ");

        if (service.searchStudent(id) == null) {
            System.out.println("Student not found!");
            return;
        }

        double marks = readDouble("Enter new marks (0-100): ");

        if (marks < 0 || marks > 100) {
            System.out.println("Marks must be between 0 and 100!");
            return;
        }

        service.updateMarks(id, marks);
    }

    private static void updateAttendance() {

        int id = readInt("Enter Student ID: ");

        if (service.searchStudent(id) == null) {
            System.out.println("Student not found!");
            return;
        }

        double attendance =
                readDouble("Enter new attendance (0-100): ");

        if (attendance < 0 || attendance > 100) {
            System.out.println("Attendance must be between 0 and 100!");
            return;
        }

        service.updateAttendance(id, attendance);
    }

    private static void updateStatus() {

        int id = readInt("Enter Student ID: ");

        if (service.searchStudent(id) == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.println("1. ACTIVE");
        System.out.println("2. INACTIVE");
        System.out.println("3. COMPLETED");

        int statusChoice = readInt("Select status: ");

        StudentStatus status;

        switch (statusChoice) {
            case 1:
                status = StudentStatus.ACTIVE;
                break;

            case 2:
                status = StudentStatus.INACTIVE;
                break;

            case 3:
                status = StudentStatus.COMPLETED;
                break;

            default:
                System.out.println("Invalid status!");
                return;
        }

        service.updateStatus(id, status);
    }

    private static void filterByStatus() {

        System.out.println("1. ACTIVE");
        System.out.println("2. INACTIVE");
        System.out.println("3. COMPLETED");

        int choice = readInt("Select status: ");

        switch (choice) {
            case 1:
                service.displayStudentsByStatus(StudentStatus.ACTIVE);
                break;

            case 2:
                service.displayStudentsByStatus(StudentStatus.INACTIVE);
                break;

            case 3:
                service.displayStudentsByStatus(StudentStatus.COMPLETED);
                break;

            default:
                System.out.println("Invalid status!");
        }
    }

    private static int readInt(String message) {

        while (true) {
            try {
                System.out.print(message);
                int value = Integer.parseInt(sc.nextLine().trim());
                return value;

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer!");
            }
        }
    }

    private static double readDouble(String message) {

        while (true) {
            try {
                System.out.print(message);
                double value = Double.parseDouble(sc.nextLine().trim());

                if (!Double.isFinite(value)) {
                    System.out.println("Enter a valid number!");
                    continue;
                }

                return value;

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }
}