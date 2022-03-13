# Student-Management-System
Simple Student Management System that works in commandline built using Java

### 1. Program information:

Student management program (save the list of students into a binary file (.dat)), allows to perform the following operations:

- Add Students (ID, Name, Gender, Score, Image, Address, Notes)
- Update student information
- Delete students
- View the list of students:
  - ID ascending, descending
  - Score ascending, descending
- Import/Export the list of students to a text file. (csv)

**Link source code**: [https://github.com/Ashpex/Student-Management-System](https://github.com/Ashpex/Student-Management-System)

### 2. Notes when running the program:

#### 2.1. How to pack the `.jar` file:

After compiling java file to `.class`, `cd` to the root directory containing the `META-INF` folder and run the following command:

```shell
jar cvfm StudentSystem.jar META-INF/MANIFEST.MF com/ashpex/*.class
```

#### 2.2. How to run the program:

`cd` to the directory containing the `jar` file and run the following command:

```shell
java -jar StudentSystem.jar
```

#### 2.3. Constraints when entering data:

The program will ask the user to re-enter if the following constraints are not met:

- Student ID contains only characters from 0-9 and cannot exceed 8 characters.
- Student ID must not match existing data.
- Student's gender must be male or female
- Student scores must be real numbers and range from 0 to 10.
