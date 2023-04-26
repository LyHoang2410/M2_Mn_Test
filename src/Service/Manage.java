package MiniTest_25_04.Service;

public interface Manage<E> {
    E create();
    E edit();
    E delete();
    E findById();
    void displayAll();


}
