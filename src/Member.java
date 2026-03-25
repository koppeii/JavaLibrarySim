import java.util.List;
import java.util.ArrayList;

public class Member {

    String name;
    List<Loan> loanedBooks = new ArrayList<>();

    public Member(String name) {
        this.name = name;
    }
}
