package com.ashpex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        ArrayList<Student> studentArrayList = new ArrayList<Student>();

/*        try {
            readDataFromBinaryFile(studentArrayList);
        }catch (IOException e){
            System.err.println("Error reading data from file");
        }*/
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
                        std.inputInfo();
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
                            studentArrayList.get(i).updateInfo();
                            found = true;
                        }
                    }
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
                    }
                    break;
                case 4:
                    if(studentArrayList.size() == 0){
                        System.out.println("There are no students");
                    }
                    for(Student std : studentArrayList){
                        std.showInfo();
                    }
                    break;
                case 5:
                    System.out.println("Enter file name to import: ");
                    String fileName = Input.nextLine();
                    studentArrayList = Student.importStudent(fileName);
                    System.out.println("Import success");
                    break;
                case 6:
                    System.out.println("Enter file name to export: ");
                    fileName = Input.nextLine();
                    Student.exportStudent(studentArrayList, fileName);
                    System.out.println("Export success");
                    break;
                case 7:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }while (choice != 7);
    }

    static void showMenu(){
        System.out.println("==============================");
        System.out.println("1. Add a student");
        System.out.println("2. Update a student");
        System.out.println("3. Delete a student");
        System.out.println("4. Show all students");
        System.out.println("5. Import students");
        System.out.println("6. Export students");
        System.out.println("7. Exit");
        System.out.println("Enter your choice: ");
    }


    public static void writeDataToBinaryFile(ArrayList<Student> studentArrayList) throws IOException {
        Student.exportStudent(studentArrayList, "student.dat");

    }

    public static void readDataFromBinaryFile(ArrayList<Student> studentArrayList) throws IOException {
        studentArrayList = Student.importStudent("student.dat");

    }
}
