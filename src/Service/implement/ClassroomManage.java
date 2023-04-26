package MiniTest_25_04.Service.implement;




import MiniTest_25_04.Model.Classroom;
import MiniTest_25_04.Service.Manage;

import java.util.ArrayList;
import java.util.Scanner;

public class ClassroomManage implements Manage<Classroom> {
    private final Scanner scanner;
    private final ArrayList<Classroom> classrooms;

    public ClassroomManage(Scanner scanner) {
        this.scanner = scanner;
        classrooms = new ArrayList<>();
        classrooms.add(new Classroom(1,"CodeGym"));
        classrooms.add(new Classroom(2,"IELTS"));
    }

    public ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }

    @Override
    public Classroom create() {
        System.out.println("Enter classroom name:");
        String name = scanner.nextLine();
        Classroom classroom = new Classroom(name);
        classrooms.add(classroom);
        return classroom;
    }

    @Override
    public Classroom edit() {
        Classroom classroom = findById();
        if (classroom != null) {
            System.out.println("Enter new classroom name: ");
            String name = scanner.nextLine();
            if (!name.equals("")) {
                classroom.setName(name);
            }
        }
        return null;
    }

    @Override
    public Classroom delete() {
        Classroom classroom = findById();
        if (classroom != null) {
            classrooms.remove(classroom);
        }
        return classroom;
    }

    @Override
    public Classroom findById() {
        displayAll();
        System.out.println("Enter the number you want to find: ");
        String index = scanner.nextLine();
        if (!index.equals("")) {
            int id = Integer.parseInt(index);
            for (Classroom classroom : classrooms) {
                if (classroom.getId() == id) {
                    return classroom;
                }
            }
        }
        return null;
    }

    @Override
    public void displayAll() {
        for (Classroom classroom : classrooms) {
            classroom.toString();
        }
    }
    public void displayOne(Classroom classroom){
        if(classroom != null){
            classroom.toString();
        }else {
            System.out.println("Not exist this object! ");
        }
    }
}
