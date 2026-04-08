//Represents the entire library system

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Library {

    private int currentDay; // Current simulation day
    private int currentHour;

    private final Helper helper = new Helper();

    private final List<String> presentInLibrary = new ArrayList<>();
    private final List<String> thiefList = new ArrayList<>();

    private final List<Member> members = new ArrayList<>();
    private final List<Book> books = new ArrayList<>();

    private static final int maxLoans = 3;
    private static final int maxMembers = 24;

    public Library() {
        // creates books for library when new Library();

        addBooks("Fundamentals of Thermodynamics", 2);
        addBooks("5 Steps to a 5: AP Chemistry", 3);
        addBooks("The Bible", 7);
        addBooks("Roses and Champagne", 2);
        addBooks("It ends with us", 2);
        addBooks("Chemistry 2", 1);
        addBooks("Forrest Gump", 3);
        addBooks("Forrest Gump: Gump & Co.", 2);
        addBooks("Little Red Riding Hood", 3);
        addBooks("Hansel and Gretel", 2);
        addBooks("Cinderella", 4);
        addBooks("Alice in Wonderland", 6);
        addBooks("Little Match Girl", 2);
        addBooks("Java For Dummies", 24);
    }

    public void setCurrentHour(int hour) {
        currentHour = hour;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public int getCurrentHour() {
        return currentHour;
    }

    public void dayStep(int step) {
        currentDay += step;
    }

    public void hourStep(int step) {
        currentHour += step;
    }

    public List<String> getPresentInLibrary() {
        return presentInLibrary;
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Book> getBooks() {
        return books;
    }

    private void addBooks(String name, int copies) {
        for (int i = 0; i < copies; i++)
            books.add(new Book(name));
    }

    public void enter(String name) {
        if (name == null) return;

        if (!presentInLibrary.contains(name)) {
            presentInLibrary.add(name);

            System.out.printf("%s has entered the Library!\n", name);
                // different types (strings, integers) use different formats (%s, %d) to be written as strings
                // remembered seeing this in python
        }
    }

    public void leave(String name) {
        if (name == null) return;

        if (presentInLibrary.contains(name)) {
            presentInLibrary.remove(name);

            System.out.printf("%s has left the Library!\n", name);
        }
    }

    public void applyMembership(String personName) {
        // check if theyre in the library

        if (personName == null) return;

        boolean existingMember = members.stream()
                .anyMatch(member -> member.getName().equals(personName));
            // checks if the person is already a member, new syntax
            // similar to pythons lambda

        if (presentInLibrary.contains(personName) && !existingMember) {
            if (members.size() >= maxMembers) {
                System.out.printf("%s has attempted to apply for a membership, but the max of %d members has been reached!\n", personName, maxMembers);
                return;
            }

            if (thiefList.contains(personName)) {
                System.out.printf("The thief \"%s\" has attempt to apply for a membership!\n", personName);
                return;
            }

            members.add(new Member(personName));

            System.out.printf("%s has applied for a Membership!\n", personName);
        }
    }

    public void revokeMembership(Member member) {
        // check if they have a membership

        if (member == null) return;

        if (presentInLibrary.contains(member.getName()) && members.contains(member)) {

            int outstandingLoans = member.getLoanedBooks().getSize();

            if (outstandingLoans > 0) {
                System.out.printf("%s attempted to revoke their membership with %d outstanding loan%s!\n", member.getName(), outstandingLoans, helper.pluralCheck(outstandingLoans));
                    // could have a function that adds a "s" when the count != 1
            }
            else {
                members.remove(member);

                System.out.printf("%s has revoked their membership!\n", member.getName());
            }
        }
    }

    // should Member have the loan and return functions?

    public void loanRandomBook(Member member, Book book) {
        // check if the book exists, and if the person is a member

        if (member == null || book == null) return;

        if (books.contains(book) && presentInLibrary.contains(member.getName())) {
            if (member.getLoanedBooks().getSize() >= maxLoans) {
                System.out.printf("%s has attempted to take more than %d book%s at once!\n", member.getName(), maxLoans, helper.pluralCheck(maxLoans));
                return;
            }

            member.getLoanedBooks().addLoan(book);
            books.remove(book);

            System.out.printf("%s has taken out the book \"%s\"!\n", member.getName(), book.getName());
        }
    }

    public void returnRandomBook(Member member, Book book) {
        // finds a random book in the memberName's loans

        if (member == null || book == null) return;

        if (member.getLoanedBooks().bookExistsInLoans(book) && presentInLibrary.contains(member.getName())) {
            member.getLoanedBooks().removeLoan(book);
            books.add(book);

            System.out.printf("%s has returned the book \"%s\"!\n", member.getName(), book.getName());
        }
    }

    public void stealBook(String personName, Book book) {

        if (personName == null || book == null) return;

        if (presentInLibrary.contains(personName)) {
            books.remove(book);
            System.out.printf("%s has stolen the book! \"%s\"!\n", personName, book.getName());

            Optional<Member> existingMember = members.stream()
                    .filter(member -> member.getName().equals(personName))
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