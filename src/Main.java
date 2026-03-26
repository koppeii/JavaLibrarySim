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
                // should add weights to simulate more realistic actions

                int inOut = Rand.randomInt(0, 6);

                if (inOut == 0)
                    library.enter(helper.randomIndividual(library.members));
                else if (inOut == 1)
                    library.leave(helper.randomPersonInLibrary(library.presentInLibrary));

                for (String person : library.presentInLibrary) {
                    // new syntax, loop through every String in library.presentInLibrary, with person being the index
                    int randomAction = Rand.randomInt(0, 4);

//                    System.out.println(randomAction);

                    switch (randomAction) {
                        case 0:
                            library.applyMembership(person);
                        case 1:
                            library.revokeMembership(helper.randomMember(library.members));
                        case 2:
                            library.loanRandomBook(helper.randomMember(library.members), helper.randomBook(library.books));
                        case 3:
                            Member randomMember = helper.randomMember(library.members);

                            if (randomMember != null) {
                                library.returnRandomBook(randomMember, helper.randomBook(randomMember.loanedBooks.books));
                            };
                        }
                    }
                }
            if (library.currentDay >= simulationLength)
                Input.waitForUserToPressEnter("Press Enter to end the simulation.");
            else
                Input.waitForUserToPressEnter("Press Enter to simulate the next day.");
            }
    }
}