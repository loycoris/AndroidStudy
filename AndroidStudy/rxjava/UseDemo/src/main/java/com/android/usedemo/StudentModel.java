package com.android.usedemo;

import java.util.ArrayList;
import java.util.List;

public class StudentModel {
    static List<Student> students;

    public static void init() {
        students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student s = new Student();
            List<Student.Course> courses = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                Student.Course course = new Student.Course();
                course.setCourseName("Course" + j);
                courses.add(course);
            }
            s.setName("Student" + i);
            s.setCourseList(courses);
            students.add(s);
        }
    }

    public static List<Student> getStudents() {
        return students;
    }

    public static void setStudents(List<Student> students) {
        StudentModel.students = students;
    }
}
