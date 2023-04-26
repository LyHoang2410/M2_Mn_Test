package MiniTest_25_04.Model;

public class Classroom {
    private int id;
    private String name;


    public Classroom(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Classroom(String name) {
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
