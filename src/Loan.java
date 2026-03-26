import java.util.ArrayList;
import java.util.List;

// can return the all loaned books
public class Loan {

    List<Book> books;

    public Loan() { this.books = new ArrayList<>(); }

    public void addLoan(Book book) {
        // could consider to using bookIDs, as there could be books with the same name but different author
        if (!this.books.contains(book)) {
            this.books.add(book);

        }
        else {
            System.out.printf("%s has already been loaned out!", book.name);
        }
    }

    public void removeLoan(Book book) {
        if (this.books.contains(book)) {
            this.books.remove(book);
        }
        else {
            System.out.printf("%s either does not exist, or the member has not loaned out the book!", book.name);
        }
    }


}
