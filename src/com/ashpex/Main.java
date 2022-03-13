package com.ashpex;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        File file = new File("student.dat");
        if(!file.exists())
            try {
                if(file.createNewFile())
                    System.out.println("student.dat file has been created");
                else {
                    System.out.println("Can not create student.dat file");
                }
            } catch (IOException e) {
                System.out.println("There's something wrong");
            }

        ArrayList<Student> studentArrayList = new ArrayList<Student>();
        System.out.println("Welcome to Student Management System");
        System.out.println("==============================");
        System.out.println("Reading existing data...");
        try {
            studentArrayList = readDataFromBinaryFile(file);
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage() + "\n" + "No existing data found. Creating new data...");
            System.out.println("==============================");
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
                        writeDataToBinaryFile(file,studentArrayList);
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
                    writeDataToBinaryFile(file,studentArrayList);
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
                        writeDataToBinaryFile(file,studentArrayList);
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
                    System.out.println("Enter file name to import(.csv): ");
                    String fileName = Input.nextLine().toString().split("\\.")[0];
                    studentArrayList = Student.importStudent(fileName,"csv");
                    System.out.println("Import success");
                    writeDataToBinaryFile(file,studentArrayList);
                    break;
                case 6:
                    System.out.println("Enter file name to export(.csv): ");
                    fileName = Input.nextLine().toString().split("\\.")[0];
                    Student.exportStudent(studentArrayList, fileName,"csv");
                    System.out.println("Export success");
                    break;
                case 7:
                    System.out.println("Exit");
                    writeDataToBinaryFile(file,studentArrayList);
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


    static ArrayList<Student> readDataFromBinaryFile(File file){
        ArrayList<Student> studentArrayList = new ArrayList<>();
        Object obj;
        try {
            if(file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new  ObjectInputStream(fileInputStream);
                while((obj = objectInputStream.readObject()) != null) {
                    studentArrayList.add((Student)obj);
                }
                objectInputStream.close();
            }
        } catch (ClassNotFoundException ex) {
        } catch (EOFException e){return studentArrayList;} catch (IOException e) {
            System.out.println("Error");
        }
        return studentArrayList;
    }
    static void writeDataToBinaryFile(File file, ArrayList<Student> list) {
        try {
            if(file.exists()) {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

                for(Student i :list) {
                    objectOutputStream.writeObject(i);
                }
                System.out.println("Write success");
                objectOutputStream.close();
            }
        } catch (IOException  e) {
            System.out.println("Error");
        } finally {
        }
    }
}
