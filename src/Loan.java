import java.util.ArrayList;
import java.util.List;

// can return the all loaned books
public class Loan {

    List<String> books;

    public Loan() {
        this.books = new ArrayList<>();
    }

    public void addLoan(String bookName) {
        // could consider to using bookIDs, as there could be books with the same name but different author
        if (!this.books.contains(bookName)) {
            this.books.add(bookName);
        }
        else {
            System.out.printf(bookName + " has already been loaned out!");
        }
    }

    public void removeLoan(String bookName) {
        if (this.books.contains(bookName)) {
            this.books.remove(bookName);
        }
        else {
            System.out.printf(bookName + " either does not exist, or the member has not loaned out the book!");
        }
    }

    public List<String> GetLoans() {
        return this.books;
    }
}
