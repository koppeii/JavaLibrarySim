import java.util.List;
import java.util.ArrayList;

public class Member {

    String name;
    Loan loanedBooks = new Loan();

    public Member(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return this.name;
    }
}
