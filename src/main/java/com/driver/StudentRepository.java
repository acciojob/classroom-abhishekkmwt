package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {


    HashMap<String,Student> studentDb=new HashMap<>();
    HashMap<String, Teacher> teacherDb=new HashMap<>();
    HashMap<String, List<String>> pairDb=new HashMap<>();

    public void addStudent(Student student){
        studentDb.put(student.getName(),student);
    }
    public void addTeacher(Teacher teacher){
        teacherDb.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher){
        List<String> students= pairDb.get(teacher);
        if(students == null){
            students = new ArrayList<>();
        }
        students.add(student);
        pairDb.put(teacher,students);
    }

    public Student getStudentByName(String name){
         return studentDb.get(name);
    }

    public Teacher getTeacherByName(String name){
        return teacherDb.get(name);
    }
    public List<String> getStudentsByTeacherName(String name){
        return pairDb.get(name);
    }

    public List<String> getAllStudents(){
        List<String> allStudentList = new ArrayList<>();
        for(String name : studentDb.keySet()){
            allStudentList.add(name);
        }
        return allStudentList;
    }

    public void deleteTeacherByName(String name){
        for(String student : pairDb.get(name)){
            studentDb.remove(student);
        }
        teacherDb.remove(name);
        pairDb.remove(name);
    }

    public void deleteAllTeachers(){
        for(Teacher teacher : teacherDb.values()){
            for(String student : pairDb.get(teacher)){
                studentDb.remove(student);
            }
            teacherDb.remove(teacher);
            pairDb.remove(teacher);
        }
    }
}
