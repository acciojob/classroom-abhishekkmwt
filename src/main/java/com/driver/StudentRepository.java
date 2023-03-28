package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {


    HashMap<String,Student> studentDb=new HashMap<>();
    HashMap<String, Teacher> teacherDb=new HashMap<>();
    HashMap<Student, Teacher> pairDb=new HashMap<>();

    public void addStudent(Student student){
        studentDb.put(student.getName(),student);
    }
    public void addTeacher(Teacher teacher){
        teacherDb.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher){
        Student student1=studentDb.get(student);
        Teacher teacher1=teacherDb.get(teacher);

        pairDb.put(student1,teacher1);
    }

    public Student getStudentByName(String name){
         return studentDb.get(name);
    }

    public Teacher getTeacherByName(String name){
        return teacherDb.get(name);
    }
    public List<String> getStudentsByTeacherName(String name){
       List<String> studentListByTeacherName = new ArrayList<>();
       for(Student student : pairDb.keySet()){
           if(pairDb.get(student).getName().equals(name)){
               studentListByTeacherName.add(student.getName());
           }
       }
       return studentListByTeacherName;
    }

    public List<String> getAllStudents(){
        List<String> allStudentList = new ArrayList<>();
        for(String name : studentDb.keySet()){
            allStudentList.add(name);
        }
        return allStudentList;
    }

    public void deleteTeacherByName(String name){
        teacherDb.remove(name);
        for(Student student : pairDb.keySet()){
            if(pairDb.get(student).getName().equals(name)){
                studentDb.remove(student.getName());
                pairDb.remove(student);
            }
        }
    }

    public void deleteAllTeachers(){
        for(Teacher teacher : teacherDb.values()){
            teacherDb.remove(teacher.getName());
            for(Student student : pairDb.keySet()){
                if(pairDb.get(student).getName().equals(teacher.getName())){
                    studentDb.remove(student.getName());
                    pairDb.remove(student);
                }
            }
        }
    }
}
