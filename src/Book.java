import java.util.ArrayList;
import java.util.List;

public class Book {

    // fields
    String name;
    int dueDate; // due date of 0 will represent that it is not taken out
    List<Book> books = new ArrayList<>();




    public Book(String name) {
        this.name = name;
    }

}
