package com.example.service;

import com.example.model.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private List<Student> students = new ArrayList<>();

    //1) addStudent 메서드 추가 - 비즈니스 로직
    public void addStudent(Student student) {
        students.add(student);
    }

    //2) findStudentByName 메서드 추가
    public Student findStudentByName(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    //3) getAllStudents 메서드 추가
    public List<Student> getAllStudents() {
        return students;
    }
}
