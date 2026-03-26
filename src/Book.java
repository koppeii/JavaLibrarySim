import java.util.ArrayList;
import java.util.List;

public class Book {

    // fields
    Library library = new Library();
    String name;
//    int dueDate = library.currentDay + 2; // due date of 0 will represent that it is not taken out
//
//    List<Integer> DueDates;
//    public Book() { this.DueDates = new ArrayList<>(); }
//
//    public void addDue() {
//        this.DueDates.add(dueDate);
//    }
    public Book(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return this.name;
    }
}
