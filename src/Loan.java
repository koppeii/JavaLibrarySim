import java.util.ArrayList;
import java.util.List;

public class Loan {

    private List<Book> books;

    public Loan() { this.books = new ArrayList<>(); }

    public void addLoan(Book book) {
        // could consider to using bookIDs, as there could be books with the same name
        if (!this.books.contains(book))
            this.books.add(book);
        else
            System.out.printf("\"%s\" has already been loaned out!", book.getName());
    }

    public void removeLoan(Book book) {
        if (this.books.contains(book))
            this.books.remove(book);
        else
            System.out.printf("\"%s\" either does not exist, or the member has not loaned out the book!", book.getName());
    }

    public int getSize() {
        return books.size();
    }

    public boolean bookExistsInLoans(Book book) {
        return books.contains(book);
    }

    public List<Book> returnLoans() {
        return books;
    }
}