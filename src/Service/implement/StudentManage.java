package MiniTest_25_04.Service.implement;

import MiniTest_25_04.Model.Classroom;
import MiniTest_25_04.Model.Student;
import MiniTest_25_04.Service.Manage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class StudentManage implements Manage<Student> {
    private final Scanner scanner;
    private final ArrayList<Student> students;
    private final ClassroomManage classroomManage;

    public StudentManage(ClassroomManage classroomManage, Scanner scanner) {
        this.scanner = scanner;
        students = new ArrayList<>();
        this.classroomManage = classroomManage;
        students.add(new Student(1, "Lee1", 28, "Male", 9, classroomManage.getClassrooms().get(0)));
        students.add(new Student(2, "Lee2", 20, "Male", 8, classroomManage.getClassrooms().get(0)));
        students.add(new Student(3, "Lee3", 29, "Male", 10, classroomManage.getClassrooms().get(1)));
    }

    public ClassroomManage getClassroomManage() {
        return classroomManage;
    }

    public Integer getAge(int count) {
        System.out.println("Enter student age: ");
        int age;
        do {
            age = Integer.parseInt(scanner.nextLine());
            count++;
            if (count == 4) {
                return null;
            } else if (age < 0 || age > 60) {
                System.out.println("Please re-enter");
            }
        } while ((age < 0 || age > 60));
        return age;
    }

    private String getGender() {
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Other");
        System.out.println("Enter your choice:");
        String num = scanner.nextLine();
        if (num.equals("")) {
            return "Constant";
        } else {
            int choice = Integer.parseInt(num);
            switch (choice) {
                case 1:
                    return "Male";
                case 2:
                    return "Female";
                case 3:
                    return "Other";
            }
            return "";
        }
    }

    private Classroom getClassroom() {
        System.out.println("Enter classroom of student");
        Classroom classroom;
        do {
            classroomManage.displayAll();
            classroom = classroomManage.findById();
        } while (classroom == null);
        return classroom;
    }

    private Double getAvgPoint(int count) {
        System.out.println("Enter the student's GPA: ");
        double avg;
        do {
            avg = Double.parseDouble(scanner.nextLine());
            count++;
            if (count == 4) {
                return null;
            } else if (avg < 0 || avg > 10) {
                System.out.println("Please Re-enter: ");
            }
        } while ((avg < 0 || avg > 10));
        return avg;
    }

    private String getGender(int count) {
        System.out.println("Enter the student's gender: ");
        String gender;
        do {
            gender = getGender();
            count++;
            if (count == 4) {
                return null;
            }
        } while (gender.equals(""));
        return gender;
    }

    @Override
    public Student create() {
        int count = 0;
        System.out.println("Enter student name: ");
        String name = scanner.nextLine();
        Integer age = getAge(count);
        if (age == null) return null;
        String gender = getGender(count);
        if (gender == null) return null;
        Double avg = getAvgPoint(count);
        if (avg == null) return null;
        Classroom classroom = getClassroom();
        Student student = new Student(id, name, age, gender, avg, classroom);
        students.add(student);
        return student;
    }
    @Override
    public Student edit() {
        Student student = findById();
        if (student != null) {
            System.out.println("Enter new student name: ");
            String name = scanner.nextLine();
            if (!name.equals("")) {
                student.setName(name);
            }
            System.out.println("Enter new student age");
            String age = scanner.nextLine();
            if (!age.equals("")) {
                student.setAge(Integer.parseInt(age));
            }
            System.out.println("Enter new gender student: ");
            String gender;
            do {
                gender = getGender();
                if (!gender.equals("") && !gender.equals("unchanged")) {
                    student.setGender(gender);
                }
            } while (gender.equals(""));
            System.out.println("Enter your student's new GPA: ");
            String avg = scanner.nextLine();
            if (!avg.equals("")) {
                student.setAvgPoint(Double.parseDouble(avg));
            }
            System.out.println("Enter a student's new classroom");
            Classroom classroom = getClassroom();
            if (classroom != null) {
                student.setClassroom(classroom);
            }
        }
        return student;
    }

    @Override
    public Student delete() {
        Student student = findById();
        if (student != null) {
            students.remove(student);
        }
        return student;
    }

    @Override
    public Student findById() {
        displayAll();
        System.out.println("Enter the number you want to find");
        int id = Integer.parseInt(scanner.nextLine());
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    @Override
    public void displayAll() {
        for (Student student : students) {
            student.toString();
        }
    }

    public void displayOne(Student student) {
        if (student != null) {
            student.toString();
        } else {
            System.out.println("Not exist this object! ");
        }
    }

    public void deleteByClassroom(Classroom classroom) {
        Iterator<Student> studentIterator = students.iterator();
        while (studentIterator.hasNext()) {
            if (studentIterator.next().getClassroom().equals(classroom)) {
                studentIterator.remove();
            }
        }

    }

    public void displayByClassroom() {
        classroomManage.toString();
        System.out.println("Enter the ID you want to select: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (Student student : students) {
            if (student.getClassroom().getId() == id) {
                displayOne(student);
            }
        }
    }

    public void displayStudentMinPoint() {
        System.out.println("Show students' lowest scores! ");
        double min = students.get(0).getAvgPoint();
        for (Student student : students) {
            if (student.getAvgPoint() < min) {
                min = student.getAvgPoint();
            }
        }
        for (Student student : students) {
            if (student.getAvgPoint() == min) {
                displayOne(student);
            }
        }
    }

    public void displayStudentMaxPoint() {
        System.out.println("Show students' highest scores!");
        double max = students.get(0).getAvgPoint();
        for (Student student : students) {
            if (student.getAvgPoint() > max) {
                max = student.getAvgPoint();
            }
        }
        for (Student student : students) {
            if (student.getAvgPoint() == max) {
                displayOne(student);
            }
        }
    }

    public void displayStudentByRank() {
        System.out.println("The average scores of students sorted by rankings is:");
        for (Student student : students) {
            if (student.getAvgPoint() > 8) {

            }
        }
    }

    private void displayInMethodByRank(Student student, String rank) {
        System.out.printf("%-10s%-10s%-10s%-10s%-10s%-15s%s",
                student.getId(), student.getName(), student.getAge(),
                student.getGender(), student.getAvgPoint(),
                student.getClassroom(), rank + "\n");
    }

    public void displayStudentByGender() {
        System.out.println("Showing students by gender is: ");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Other");
        System.out.println("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                for (Student student : students) {
                    if (student.getGender().equals("Male")) {
                        displayOne(student);
                    }
                }
                break;
            case 2:
                for (Student student : students) {
                    if (student.getGender().equals("Female")) {
                        displayOne(student);
                    }
                }
                break;
            case 3:
                for (Student student : students) {
                    if (student.getGender().equals("Other")) {
                        displayOne(student);
                    }
                }
                break;
        }
    }
}
