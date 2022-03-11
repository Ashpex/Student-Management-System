package com.ashpex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        ArrayList<Student> studentArrayList = new ArrayList<Student>();
        System.out.println("Welcome to Student Management System");
        try {
            studentArrayList = Student.importStudent("student.dat");
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

        int choice;
        Scanner Input = new Scanner(System.in);
        do{
            showMenu();
            choice = Integer.parseInt(Input.nextLine());
            switch (choice){
                case 1:
                    System.out.println("Enter number of students to add: ");
                    int n = Integer.parseInt(Input.nextLine());
                    for(int i = 0; i < n; i++){
                        Student std = new Student();
                        std.inputInfo(studentArrayList);
                        studentArrayList.add(std);
                        writeDataToBinaryFile(studentArrayList);
                    }
                    break;
                case 2:
                    System.out.println("Enter student id to update: ");
                    String id = Input.nextLine();
                    boolean found = false;
                    for(int i = 0; i < studentArrayList.size(); i++){
                        if(studentArrayList.get(i).getId().equals(id)){
                            studentArrayList.get(i).updateInfo(studentArrayList);
                            found = true;
                        }
                    }
                    writeDataToBinaryFile(studentArrayList);
                    if(!found){
                        System.out.println("Student not found");
                    }
                    break;
                case 3:
                    System.out.println("Enter student id to delete: ");
                    found = false;
                    id = Input.nextLine();
                    for(int i = 0; i < studentArrayList.size(); i++){
                        if(studentArrayList.get(i).getId().equals(id)){
                            studentArrayList.remove(i);
                            found = true;
                        }
                    }
                    if (!found){
                        System.out.println("Student not found");
                    }
                    else {
                        System.out.println("Student deleted");
                        writeDataToBinaryFile(studentArrayList);
                    }
                    break;
                case 4:
                    if(studentArrayList.size() == 0){
                        System.out.println("There are no students");
                    }
                    else {
                        System.out.println("=================================================");
                        System.out.println("| 1. Increasing based on student ID \t\t\t|");
                        System.out.println("| 2. Decreasing based on student ID \t\t\t|");
                        System.out.println("| 3. Increasing based on student grade \t\t\t|");
                        System.out.println("| 4. Decreasing based on student grade \t\t\t|");
                        System.out.println("=================================================");
                        System.out.println("Enter your choice: ");
                        int sortChoice = Integer.parseInt(Input.nextLine());
                        switch (sortChoice){
                            case 1:
                                studentArrayList.sort(new Comparator<Student>() {
                                    @Override
                                    public int compare(Student student, Student t1) {
                                        return student.getId().compareTo(t1.getId());
                                    }
                                });
                                for (Student std : studentArrayList){
                                    std.showInfo();
                                }
                                break;
                            case 2:
                                studentArrayList.sort(new Comparator<Student>() {
                                    @Override
                                    public int compare(Student student, Student t1) {
                                        return t1.getId().compareTo(student.getId());
                                    }
                                });
                                Collections.reverseOrder();
                                for (Student std : studentArrayList){
                                    std.showInfo();
                                }
                                break;
                            case 3:
                                studentArrayList.sort(new Comparator<Student>() {
                                    @Override
                                    public int compare(Student student, Student t1) {
                                        return student.getGrade().compareTo(t1.getGrade());
                                    }
                                });
                                for (Student std : studentArrayList){
                                    std.showInfo();
                                }
                                break;
                            case 4:
                                studentArrayList.sort(new Comparator<Student>() {
                                    @Override
                                    public int compare(Student student, Student t1) {
                                        return t1.getGrade().compareTo(student.getGrade());
                                    }
                                });
                                Collections.reverseOrder();
                                for (Student std : studentArrayList){
                                    std.showInfo();
                                }
                                break;
                            default:
                                System.out.println("Invalid choice");

                        }
                    }
/*                    for(Student std : studentArrayList){
                        std.showInfo();
                    }*/
                    break;
                case 5:
                    System.out.println("Enter file name to import: ");
                    String fileName = Input.nextLine();
                    studentArrayList = Student.importStudent(fileName);
                    System.out.println("Import success");
                    writeDataToBinaryFile(studentArrayList);
                    break;
                case 6:
                    System.out.println("Enter file name to export: ");
                    fileName = Input.nextLine();
                    Student.exportStudent(studentArrayList, fileName);
                    System.out.println("Export success");
                    break;
                case 7:
                    System.out.println("Exit");
                    writeDataToBinaryFile(studentArrayList);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }while (choice != 7);
    }

    static void showMenu(){
        System.out.println("==============================");
        System.out.println("| 1. Add a student \t\t\t|");
        System.out.println("| 2. Update a student \t\t|");
        System.out.println("| 3. Delete a student \t\t|");
        System.out.println("| 4. Show all students \t\t|");
        System.out.println("| 5. Import students \t\t|");
        System.out.println("| 6. Export students \t\t|");
        System.out.println("| 7. Exit \t\t\t\t\t|");
        System.out.println("==============================");
        System.out.println("Enter your choice: ");
    }


    public static void writeDataToBinaryFile(ArrayList<Student> studentArrayList) throws IOException {
        Student.exportStudent(studentArrayList, "student.dat");

    }

    public static void readDataFromBinaryFile() throws IOException {
        ArrayList<Student> studentArrayList = Student.importStudent("student.dat");

    }

}
