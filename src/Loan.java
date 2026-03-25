import java.util.ArrayList;
import java.util.List;

// returns the all loaned books and due dates
public class Loan {

    List<String> books;

    public void Loan() {
        this.books = new ArrayList<>();
    }

    public void AddLoan(String bookName) {
        // could consider to using bookIDs, as there could be books with the same name but different author
        if (!this.books.contains(bookName)) {
            this.books.add(bookName);
        }
        else {
            System.out.printf(bookName + " has already been loaned out!");
        }
    }

    public void RemoveLoan(String bookName) {
        if (this.books.contains(bookName)) {
            this.books.remove(bookName);
        }
        else {
            System.out.printf(bookName + " does not exist!");
        }
    }

    public List<String> GetLoans() {
        return this.books;
    }




//    public static void main(String[] args) {
//
//        List<String> loan1 = Loan();
//
//
//        for (int i = 0; i < 10; i++) {
//            int randomInt = Rand.randomInt(0, 100);
//
//            if (randomInt % 2 == 1) {
////                System.out.println("odd");
//
//
//            }
//            else {
//                System.out.println("even");
//            }
//        }
//    }



}
