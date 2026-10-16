package com.example.StudentManagementSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StudentFileService {

    private static final String FILE_NAME = "students.txt";

    public void saveStudents(StudentService service) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (int id : service.getStudentIds()) {

                Student student = service.searchStudent(id);

                if (student != null) {

                    writer.write(
                            student.getStudentId() + "|" +
                                    student.getStudentName() + "|" +
                                    student.getDepartment() + "|" +
                                    student.getMarks() + "|" +
                                    student.getAttendance() + "|" +
                                    student.getStatus()
                    );

                    writer.newLine();
                }
            }

            System.out.println("Student records saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving records: " + e.getMessage());
        }
    }

    public void loadStudents(StudentService service) {

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length != 6) {
                    continue;
                }

                try {
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    String department = data[2];
                    double marks = Double.parseDouble(data[3]);
                    double attendance = Double.parseDouble(data[4]);
                    StudentStatus status =
                            StudentStatus.valueOf(data[5]);

                    if (name.isBlank() ||
                            department.isBlank() ||
                            marks < 0 || marks > 100 ||
                            attendance < 0 || attendance > 100) {
                        continue;
                    }

                    Student student = new Student(
                            id, name, department, marks, attendance
                    );

                    student.setStatus(status);
                    service.addStudent(student);

                } catch (IllegalArgumentException e) {
                    System.out.println("Skipping invalid student record.");
                }
            }

            System.out.println("Student records loaded successfully!");

        } catch (IOException e) {
            System.out.println("No previous student records found.");
        }
    }
}