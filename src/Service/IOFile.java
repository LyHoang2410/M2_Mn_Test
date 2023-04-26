package MiniTest_25_04.Service;

import MiniTest_25_04.Model.Student;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile {
    public void writeFile(List<Student> studentList) throws IOException {
        File f = new File("C:\\Users\\Asus\\Desktop\\M2_Mini_Test\\src\\MiniTest_25_04\\data\\Student.txt");
        FileWriter fileWriter = new FileWriter(f);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (Student student : studentList) {
            bufferedWriter.write(student.toString() + "\n");
        }
        bufferedWriter.close();
        fileWriter.close();
    }

    public List<Student> readFile() throws IOException {
        List<Student> students = new ArrayList<>();
        File f = new File("C:\\Users\\Asus\\Desktop\\M2_Mini_Test\\src\\MiniTest_25_04\\data\\Student.txt");
        FileReader fileReader = new FileReader(f);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String c;
        while ((c = bufferedReader.readLine()) != null) {
            String str[] = c.split(", ");
            Student student = new Student(Integer.parseInt(str[0]), str[1], Integer.parseInt(str[2]),
                    str[3], Double.parseDouble(str[4]), str[5]);
            students.add(student);
        }
        bufferedReader.close();
        fileReader.close();
        return students;
    }
}
