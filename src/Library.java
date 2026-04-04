//Represents the entire library system

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Library {

    int currentDay; // Current simulation day
    int currentHour;

    final Helper helper = new Helper();

    List<String> presentInLibrary = new ArrayList<>();
    List<String> thiefList = new ArrayList<>();

    List<Member> members = new ArrayList<>();
    List<Book> books = new ArrayList<>();

    private static final int maxLoans = 3;
    private static final int maxMembers = 8;

    public Library() {
        // creates books for library when new Library();

        books.add(new Book("Fundamentals of Thermodynamics"));
        books.add(new Book("5 Steps to a 5: AP Chemistry"));
        books.add(new Book("The Bible"));
        books.add(new Book("Roses and Champagne"));
        books.add(new Book("It ends with us"));
        books.add(new Book("Chemistry 2"));
        books.add(new Book("Forrest Gump"));
        books.add(new Book("Forrest Gump: Gump & Co."));
        books.add(new Book("Little Red Riding Hood"));
        books.add(new Book("Hansel and Gretel"));
        books.add(new Book("Cinderella"));
        books.add(new Book("Alice in Wonderland"));
        books.add(new Book("1984"));
        books.add(new Book("Little Match Girl"));
        books.add(new Book("Java For Dummies"));
    }

    public void enter(String name) {
        if (!presentInLibrary.contains(name) && !name.isBlank()) {
            presentInLibrary.add(name);

            System.out.printf("%s has entered the Library!\n", name);
                // different types (strings, integers) use different formats (%s, %d) to be written as strings
                // remembered seeing this in python
        }
    }

    public void leave(String name) {
        if (presentInLibrary.contains(name) && !name.isBlank()) {
            presentInLibrary.remove(name);

            System.out.printf("%s has left the Library!\n", name);
        }
    }

    public void applyMembership(String personName) {
        // check if theyre in the library

        if (personName.isBlank()) return;

        boolean existingMember = members.stream()
                .anyMatch(member -> member.name.equals(personName));
            // checks if the person is already a member, new syntax
            // similar to pythons lambda

        if (presentInLibrary.contains(personName) && !existingMember) {
            if (members.size() >= maxMembers) {
                System.out.printf("%s has attempted to apply for a membership, but the max of %d members has been reached!\n", personName, maxMembers);
                return;
            }

            if (thiefList.contains(personName)) {
                System.out.printf("The thief \"%s\" has attempt to apply for a membership!", personName);
                return;
            }

            members.add(new Member(personName));

            System.out.printf("%s has applied for a Membership!\n", personName);
        }
    }

    public void revokeMembership(Member member) {
        // check if they have a membership

        if (member == null) return;

        if (presentInLibrary.contains(member.name) && members.contains(member)) {

            int outstandingLoans = member.loanedBooks.books.size();

            if (outstandingLoans > 0) {
                System.out.printf("%s attempted to revoke their membership with %d outstanding loan%s!\n", member.name, outstandingLoans, helper.pluralCheck(outstandingLoans));
                    // could have a function that adds a "s" when the count != 1
            }
            else {
                members.remove(member);

                System.out.printf("%s has revoked their membership!\n", member.name);
            }
        }
    }

    // should Member have the loan and return functions?

    public void loanRandomBook(Member member, Book book) {
        // check if the book exists, and if the person is a member

        if (member == null || book == null) return;

        if (books.contains(book) && presentInLibrary.contains(member.name)) {
            if (member.loanedBooks.books.size() >= maxLoans) {
                System.out.printf("%s has attempted to take more than %d book%s at once!\n", member.name, maxLoans, helper.pluralCheck(maxLoans));
                return;
            }

            member.loanedBooks.addLoan(book);
            books.remove(book);

            System.out.printf("%s has taken out the book \"%s\"!\n", member.name, book.name);
        }
    }

    public void returnRandomBook(Member member, Book book) {
        // finds a random book in the memberName's loans

        if (member == null || book == null) return;

        if (member.loanedBooks.books.contains(book) && presentInLibrary.contains(member.name)) {
            member.loanedBooks.removeLoan(book);
            books.add(book);

            System.out.printf("%s has returned the book \"%s\"!\n", member.name, book.name);
        }
    }

    public void stealBook(String personName, Book book) {

        if (personName.isBlank() || book == null) return;

        if (presentInLibrary.contains(personName)) {
            books.remove(book);
            System.out.printf("%s has stolen the book! \"%s\"!\n", personName, book.name);

            Optional<Member> existingMember = members.stream()
                    .filter(member -> member.name.equals(personName))
                    .findFirst();
                // finds if the thief, using their name, is a member.
                // if not found, existingMember = null, else returns the appropiate Member

            if (existingMember != null)
                members.remove(existingMember);

            if (!thiefList.contains(personName))
                thiefList.add(personName);

            leave(personName);
        }
    }
}