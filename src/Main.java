// Simulation
public class Main {
    public static void main(String[] args) {

        Library library = new Library();
        Helper helper = new Helper();

        int simulationLength = 7;
        int simulationFrequency = 10;

        String name = helper.generateRandomName();
        System.out.println(name);

        library.books.add(new Book("Fundamentals of Thermodynamics"));
        library.books.add(new Book("5 Steps to a 5: AP Chemistry"));
        library.books.add(new Book("The Bible"));

        while (library.currentDay < simulationLength) {

            // Advance time
            library.currentDay++;
            System.out.println("--- Day " + library.currentDay + " Start ---");

            // simulate a random event

            for (int i = 0; i < simulationFrequency; i++) {
                int randomNumber = Rand.randomInt(0, 6);

                switch (randomNumber) {
                    // reformat, the odds of someone entering the library, and applying for a membership, and taking out a book is 1/216
                    case 1:
                        library.enter(helper.randomIndividual(library.members));
                    case 2:
                        library.leave(helper.randomPersonInLibrary(library.presentInLibrary));
                    case 3:
                        library.applyMembership(helper.randomPersonInLibrary(library.presentInLibrary));
                    case 4:
                        library.revokeMembership(helper.randomMember(library.members));
                    case 5:
                        library.loanRandomBook(helper.randomMember(library.members), helper.randomBook(library.books));
                    case 6:
                        Member randomMember = helper.randomMember(library.members);

                        if (randomMember != null) {
                            library.returnRandomBook(randomMember, helper.randomBook(randomMember.loanedBooks.books));
                        };
                }
            }

            if (library.currentDay >= simulationLength)
                Input.waitForUserToPressEnter("Press Enter to end the simulation.");
            else
                Input.waitForUserToPressEnter("Press Enter to simulate the next day.");
        }
    }
}