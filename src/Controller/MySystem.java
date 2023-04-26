package MiniTest_25_04.Controller;


import MiniTest_25_04.Model.Classroom;
import MiniTest_25_04.Model.Student;
import MiniTest_25_04.Service.implement.ClassroomManage;
import MiniTest_25_04.Service.implement.StudentManage;

import java.util.Scanner;

public class MySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClassroomManage classroomManage = new ClassroomManage(scanner);
        StudentManage studentManage = new StudentManage(classroomManage, scanner);
        superMenu(studentManage, scanner);
    }

    private static void superMenu(StudentManage studentManage, Scanner scanner) {
        do {
            System.out.println("-----MENU-----");
            System.out.println("1. Create new student");
            System.out.println("2. Edit student by Id");
            System.out.println("3. Delete a student by Id");
            System.out.println("4. Show students by Id");
            System.out.println("5. Show all students by Id");
            System.out.println("6. Show students by min point");
            System.out.println("7. Show students by max point");
            System.out.println("8. Show all students by rank");
            System.out.println("9.Show all students by gender");
            System.out.println("10. Show all students by classroom");
            System.out.println("11. Action with Classroom");
            System.out.println("0. Exit");
            System.out.println("Enter your choice");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    Student studentCreate = studentManage.create();
                    System.out.println("Student create is: ");
                    studentManage.displayOne(studentCreate);
                    break;
                case 2:
                    Student studentEdit = studentManage.edit();
                    System.out.println("Student Edit is: ");
                    studentManage.displayOne(studentEdit);
                    break;
                case 3:
                    Student studentDelete = studentManage.delete();
                    System.out.println("Student delete is: ");
                    studentManage.displayOne(studentDelete);
                    break;
                case 4:
                    Student student = studentManage.findById();
                    System.out.println("Student is: ");
                    studentManage.displayOne(student);
                    break;
                case 5:
                    studentManage.displayAll();
                    break;
                case 6:
                    studentManage.displayStudentMinPoint();
                    break;
                case 7:
                    studentManage.displayStudentMaxPoint();
                    break;
                case 8:
                    studentManage.displayStudentByRank();
                    break;
                case 9:
                    studentManage.displayStudentByGender();
                    break;
                case 10:
                    studentManage.displayByClassroom();
                    break;
                case 11:
                    subMenu(studentManage, scanner);
                    break;
                case 0:
                    System.exit(0);
            }
        } while (true);
    }

    private static void subMenu(StudentManage studentManage, Scanner scanner) {
        int choice;
        do {
            System.out.println("-----MENU-----");
            System.out.println("1. Create new Classroom");
            System.out.println("2. Edit Classroom by id");
            System.out.println("3. Delete Classroom by id");
            System.out.println("4. Show Classroom by id");
            System.out.println("5. Show all Classroom");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    Classroom classroomCreate = studentManage.getClassroomManage().create();
                    System.out.println("Classroom created is: ");
                    studentManage.getClassroomManage().displayOne(classroomCreate);
                    break;
                case 2:
                    Classroom classroomEdit = studentManage.getClassroomManage().edit();
                    System.out.println("Classroom edited is: ");
                    studentManage.getClassroomManage().displayOne(classroomEdit);
                    break;
                case 3:
                    Classroom classroomDelete = studentManage.getClassroomManage().delete();
                    studentManage.deleteByClassroom(classroomDelete);
                    System.out.println("Classroom Deleted is: ");
                    studentManage.getClassroomManage().displayOne(classroomDelete);
                    break;
                case 4:
                    Classroom classroom = studentManage.getClassroomManage().findById();
                    System.out.println("Classroom is: ");
                    studentManage.getClassroomManage().displayOne(classroom);
                    break;
                case 5:
                    studentManage.getClassroomManage().displayAll();
                    break;
            }
        } while (choice != 0);
    }
}
