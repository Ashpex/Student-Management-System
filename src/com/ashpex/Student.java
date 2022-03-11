package com.ashpex;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student {
    String id;
    String name;
    String gender;
    String image;
    String address;
    String note;
    Float grade;

    public Student() {
    }

    public Student(String id, String name, String gender, String image, String address, Float grade, String note) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.image = image;
        this.address = address;
        this.grade = grade;
        this.note = note;
    }

    public Student(String id) {
        this.id = id;
    }

    public static ArrayList<Student> importStudent(String fileName) {
/*        Scanner Input = new Scanner(System.in);
        Input.nextLine();
        ArrayList<Student> list = new ArrayList<>();
        List<List<String>> records = new ArrayList<>();
        while(Input.hasNextLine()) {
            records.add(getRecordFromLine(Input.nextLine()));
        }
        for (List<String> record : records) {
            Student student = new Student(record.get(0), record.get(1), record.get(2), record.get(3), record.get(4), record.get(5), Float.parseFloat(record.get(6)));
            list.add(student);
        }
        return list;*/

/*        ArrayList<Student> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] fields = line.split(",");
            Student student = new Student(fields[0], fields[1], fields[2], fields[3], fields[4], Float.parseFloat(fields[5]), fields[6]);
            list.add(student);
        }
        return list;*/

        ArrayList<Student> list = new ArrayList<>();
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                Student student = new Student(fields[0], fields[1], fields[2], fields[3], fields[4], Float.parseFloat(fields[5]), fields[6]);
                list.add(student);
                line = bufferedReader.readLine();
            }
        }catch (IOException e){
            System.err.println("File not found");
        }
        return list;
    }



    private static List<String> getRecordFromLine(String nextLine) {
        List<String> record = new ArrayList<>();
        String[] fields = nextLine.split(",");
        for (String field : fields) {
            record.add(field);
        }
        return record;
    }

    public static void exportStudent(ArrayList<Student> studentArrayList, String fileName) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        //fileName = fileName + ".csv";
        FileWriter fileWriter = new FileWriter(fileName);

/*        String title = stringBuilder.append("id,name,gender,image,address,grade,note\n").toString();
        fileWriter.write(title);
        stringBuilder.setLength(0);*/

        for (Student student : studentArrayList) {
            stringBuilder.append(student.id + ",");
            stringBuilder.append(student.name + ",");
            stringBuilder.append(student.gender + ",");
            stringBuilder.append(student.image + ",");
            stringBuilder.append(student.address + ",");
            stringBuilder.append(student.grade + ",");
            stringBuilder.append(student.note + "\n");

            String content = stringBuilder.toString();
            fileWriter.write(content);
            stringBuilder.setLength(0);
        }
        fileWriter.flush();
        fileWriter.close();
        System.out.println("Done");
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public Boolean setGender(String gender) {
        if (gender.equals("male") || gender.equals("female")){
            this.gender = gender;
            return true;
        }
        else{
            System.err.println("Gender must be male or female");
            return false;
        }
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Float getGrade() {
        return grade;
    }

    public boolean setGrade(String grade) {
        try{
            Float.parseFloat(grade);
            if(Float.parseFloat(grade) < 0 || Float.parseFloat(grade) > 10 || grade.equals("")){
                System.err.println("Grade must be between 0 and 10");
                return false;
            }else{
                this.grade = Float.parseFloat(grade);
                return true;
            }
        }catch (NumberFormatException e){
            System.err.println("Grade must be a number");
            return false;
        }
    }

    public boolean setID(String id) {
        if(id.matches("[0-9]{8}") && !id.isEmpty()){
            this.id = id;
            return true;
        }
        else {
            System.err.println("ID must be 8 digits");
            return false;
        }
    }

    public void inputInfo() {
        Scanner Input = new Scanner(System.in);
        System.out.println("Input information of student");

        System.out.println("ID: ");
        while (!setID(Input.nextLine())){
            System.out.println("ID: ");
        }

        System.out.println("Name: ");
        this.name = Input.nextLine();


        System.out.println("Gender (male/female): ");
        while(!setGender(Input.nextLine())){
            System.out.println("Gender(male/female): ");
        }

        System.out.println("Image: ");
        this.image = Input.nextLine();

        System.out.println("Address: ");
        this.address = Input.nextLine();

        System.out.println("Grade: ");
        while(!setGrade(Input.nextLine())){
            System.out.println("Grade: ");
        }

        System.out.println("Note: ");
        this.note = Input.nextLine();
    }

    public void updateInfo() {
        Scanner Input = new Scanner(System.in);
        System.out.println("Update information of student (press enter if there is no change" );

        System.out.println("ID: ");
        if(Input.nextLine().length() > 0) {
            while (!setID(Input.nextLine())){
                System.out.println("ID: ");
            }
        }

        System.out.println("Name: ");
        String name = Input.nextLine();
        if(name.length() > 0) this.name = name;

        System.out.println("Gender: ");
        String gender = Input.nextLine();
        if(gender.length() > 0) {
            while(!setGender(Input.nextLine())){
                System.out.println("Gender(male/female): ");
            }
        }

        System.out.println("Image: ");
        String image = Input.nextLine();
        if(image.length() > 0) this.image = image;

        System.out.println("Address: ");
        String address = Input.nextLine();
        if(image.length() > 0) this.address = address;

        System.out.println("Grade: ");
        if(Input.nextLine().length() > 0) {
            while(!setGrade(Input.nextLine())){
                System.out.println("Grade: ");
            }
        }
    }

    public void showInfo(){
        System.out.println("==========================");
        System.out.println("ID: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Gender: " + this.gender);
        System.out.println("Address: " + this.address);
        System.out.println("Grade: " + this.grade);
        System.out.println("Note: " + this.note);
    }
}
