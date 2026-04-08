public class Member {

    private final String name;
    private final Loan loanedBooks = new Loan();

    public Member(String name) {this.name = name;}

    public String getName() {
        return name;
    }

    public Loan getLoanedBooks() {
        return loanedBooks;
    }
}
