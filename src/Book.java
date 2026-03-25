import java.util.ArrayList;
import java.util.List;

public class Book {

    // fields
    String name;
    int dueDate; // due date of 0 will represent that it is not taken out
    List<Book> books = new ArrayList<>();
    {
        books.add(new Book("Fundamentals of Thermodynamics"));
        books.add(new Book("5 Steps to a 5: AP Chemistry"));
        books.add(new Book("The Bible"));
    }


    public Book(String name) {
        this.name = name;
    }

}
