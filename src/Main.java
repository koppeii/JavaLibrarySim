// Simulation
public class Main {
    public static void main(String[] args) {

        Library library = new Library();
        Helper helper = new Helper();

        int simulationLength = 7;
        int simulationFrequency = 10;

        while (library.currentDay < simulationLength) {

            // Advance time
            library.currentDay++;
            System.out.println("--- Day " + library.currentDay + " Start ---");

            // simulate a random event

            for (int i = 0; i < simulationFrequency; i++) {
                int randomNumber = Rand.randomInt(0, 6);

                switch (randomNumber) {
                    case 1:
                        library.enter(helper.randomIndividual());
                    case 2:
                        library.leave(helper.randomPerson());
                    case 3:
                        library.applyMembership(helper.randomPerson());
                    case 4:
                        library.revokeMembership(helper.randomMember());
                    case 5:
                        library.loanRandomBook(helper.randomMember(), helper.randomBook(library.books));
                    case 6:
                        Member randomMember = helper.randomMember();

                        library.returnRandomBook(randomMember, helper.randomBook(randomMember.loanedBooks.));
                }
            }

            if (library.currentDay >= simulationLength)
                Input.waitForUserToPressEnter("Press Enter to end the simulation.");
            else
                Input.waitForUserToPressEnter("Press Enter to simulate the next day.");
        }
    }
}