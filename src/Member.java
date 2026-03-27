public class Member {

    String name;
    Loan loanedBooks = new Loan();

    public Member(String name) {
        this.name = name;
    }

    @Override //idk what this does
    public String toString() {
        return this.name;
    }
}
