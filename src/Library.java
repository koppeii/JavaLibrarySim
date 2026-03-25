//Represents the entire library system

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Library {

    int currentDay; // Current simulation day
    int currentHour;

    List<String> presentInLibrary = new ArrayList<>();

    List<Member> members = new ArrayList<>();



    public void enter(String name) {
        if (!presentInLibrary.contains(name)) {
            presentInLibrary.add(name);

            System.out.printf("%s has entered the Library!\n", name);
            // different types (strings, integers) use different formats (%s, %d) to be written as strings
            // remembered seeing this in python
        }
    }

    public void leave(String name) {
        if (presentInLibrary.contains(name)) {
            presentInLibrary.remove(name);

            System.out.printf("%s has left the Library!\n", name);
        }
    }

    public void applyMembership(String personName) {
        // check if theyre in the library

        boolean existingMember = members.stream().anyMatch(member -> member.name.equals(personName));
        // checks if the person is already a member, new syntax
        // similar to pythons lambda

        if (presentInLibrary.contains(personName) && !existingMember) {
            members.add(new Member(personName));

            System.out.printf("%s has applied for a Membership!\n", personName);
        }
    }

    public void revokeMembership(String memberName) {
        // check if they have a membership
        // should i use the actual Member object as a parameter instead of a String?

        Optional<Member> foundMember = members.stream().filter(member -> member.name.equals(memberName)).findFirst();
        // Optional allows .filter to return nothing, not crashing the program
        // .isPresent() checks if existingMember is not null

        if (presentInLibrary.contains(memberName) && foundMember.isPresent()) {
            Member member = foundMember.get();
            // .get() unwraps the actual Member object

            int outstandingLoans = member.loanedBooks.size();

            if (outstandingLoans > 0) {
                System.out.printf("%s attempted to revoke their membership with %d outstanding loans!\n", memberName, outstandingLoans);
            }
            else {
                members.remove(member);

                System.out.printf("%s has revoked their membership!\n", memberName);
            }
        }
    }

    // should Member have the loan and return functions?

    public void loanRandomBook(String memberName, String bookName) {
        // check if the book exists, and if the person is a member
    }

    public void returnRandomBook(String memberName) {
        // checks the Members loaned books, returns a random one
    }

    public String randomMember() {
        // returns the name/object of a random individual in members

        return "";
    }

    public String randomPerson() {
        // returns the name of a random individual in presentInLibrary

        return "";
    }

    public String randomBook() {
        // returns a random string/Book from books

        return new Book("").name;
    }
}