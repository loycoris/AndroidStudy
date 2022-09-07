package com.android.usedemo;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;

public class MapDemo {
    public static void main(String[] args) {
        StudentModel.init();
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                List<Student> students = StudentModel.getStudents();
                for (Student s : students) {
                    for (Student.Course course : s.getCourseList()) {
                        System.out.println(course);
                    }
                }
            }
        }).start();*/
        Observable.fromIterable(StudentModel.getStudents())
                .flatMap(new Function<Student, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Student student) throws Throwable {
                        return Observable.fromIterable(student.getCourseList());
                    }
                }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                System.out.println(o);
            }
        });
    }
}
