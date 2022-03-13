package com.ashpex;


import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
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
        super();
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

    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", gender=" + gender + ", image=" + image + ", address=" +address + ", grade=" + grade + ", note=" + note;
    }

    public static ArrayList<Student> importStudent(String fileName, String extension) throws IOException {

        fileName = fileName + "." + extension;
        String header = "id,name,gender,image,address,grade,note";
        ArrayList<Student> list = new ArrayList<>();
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line = bufferedReader.readLine();
            if(line.equals(header)) {
                while ((line = bufferedReader.readLine()) != null) {
                    String[] fields = line.split(",");
                    Student student = new Student(fields[0], fields[1], fields[2], fields[3], fields[4], Float.parseFloat(fields[5]), fields[6]);
                    list.add(student);
                }
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

    public static void exportStudent(ArrayList<Student> studentArrayList, String fileName, String extension) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        fileName = fileName + "." + extension;
        FileWriter fileWriter = new FileWriter(fileName);

        String header = stringBuilder.append("id,name,gender,image,address,grade,note\n").toString();
        fileWriter.write(header);
        stringBuilder.setLength(0);

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

    public boolean setName(String name) {
        if(name.length() > 0) {
            this.name = name;
            return true;
        }
        else {
            System.err.println("Name is empty");
            return false;
        }
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

    public boolean setID(String id, ArrayList<Student> studentArrayList){
        for (Student student : studentArrayList) {
            if(student.id.equals(id)){
                System.err.println("ID already exists");
                return false;
            }
        }
        if(id.matches("[0-9]{8}") && !id.isEmpty()){
            this.id = id;
            return true;
        }
        else {
            System.err.println("ID must be 8 digits");
            return false;
        }
    }

    public void inputInfo(ArrayList<Student> studentArrayList) {
        Scanner Input = new Scanner(System.in);
        System.out.println("Input information of student");

        System.out.println("ID: ");
        while (!setID(Input.nextLine(),studentArrayList)) {
            System.out.println("ID: ");
        }

        System.out.println("Name: ");
        while(!setName(Input.nextLine())){
            System.out.println("Name: ");
        }


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

    public void updateInfo(ArrayList<Student> studentArrayList) {
        Scanner Input = new Scanner(System.in);
        System.out.println("Update information of student (press enter if there is no change)" );

        System.out.println("ID: ");
        String id = Input.nextLine();
        if (id.length() > 0 && setID(id,studentArrayList)) {
            this.id = id;
        }
        else if(id.length() > 0 && !setID(id,studentArrayList)){
            System.out.println("ID: ");
            while (!setID(Input.nextLine(),studentArrayList)) {
                System.out.println("ID: ");
            }
        }


        System.out.println("Name: ");
        String name = Input.nextLine();
        if(name.length() > 0) this.name = name;

        System.out.println("Gender: ");
        String gender = Input.nextLine();
        if (gender.length() > 0 && setGender(gender)) {
            this.gender = gender;
        }
        else if(gender.length() > 0 && !setGender(gender)){
            System.out.println("Gender: ");
            while (!setGender(Input.nextLine())) {
                System.out.println("Gender: ");
            }
        }

        System.out.println("Image: ");
        String image = Input.nextLine();
        if(image.length() > 0) this.image = image;

        System.out.println("Address: ");
        String address = Input.nextLine();
        if(image.length() > 0) this.address = address;

        System.out.println("Grade: ");
        String grade = Input.nextLine();
        if (grade.length() > 0 && setGrade(grade)) {
            this.grade = Float.parseFloat(grade);
        }
        else if(gender.length() > 0 && !setGrade(grade)){
            System.out.println("Grade: ");
            while (!setGrade(Input.nextLine())) {
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

/*    public void writeDataToBinaryFile(ArrayList<Student> studentArrayList) {
            try {
            FileOutputStream fos = new FileOutputStream("student.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(studentArrayList);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readDataFromBinaryFile(ArrayList<Student> studentArrayList) {
        try {
            FileInputStream fis = new FileInputStream("student.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            studentArrayList = (ArrayList<Student>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/
}
