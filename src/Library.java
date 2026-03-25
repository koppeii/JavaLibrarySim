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

    public void revokeMembership(Member member) {
        // check if they have a membership

        if (presentInLibrary.contains(member.name)) {

            int outstandingLoans = member.loanedBooks.size();

            if (outstandingLoans > 0) {
                System.out.printf("%s attempted to revoke their membership with %d outstanding loans!\n", member.name, outstandingLoans);
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
    }

    public void returnRandomBook(Member member, Book book) {
        // finds a random book in the memberName's loans


    }
}