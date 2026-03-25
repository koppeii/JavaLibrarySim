// Simulation
public class Main {
    public static void main(String[] args) {

        Library library = new Library();
        Helper helper = new Helper();

        int simulationLength = 7;
        int simulationFrequency = 10;

        String name = helper.generateRandomName();
        System.out.println(name);
        while (library.currentDay < simulationLength) {

            // Advance time
            library.currentDay++;
            System.out.println("--- Day " + library.currentDay + " Start ---");

            // simulate a random event

            for (int i = 0; i < simulationFrequency; i++) {
                int randomNumber = Rand.randomInt(0, 6);

                switch (randomNumber) {
                    case 1:
                        library.enter(helper.generateRandomName());
                    case 2:
                        library.leave(library.randomPerson());
                    case 3:
                        library.applyMembership(library.randomPerson());
                    case 4:
                        library.revokeMembership(library.randomMember());
                    case 5:
                        library.loanRandomBook(library.randomMember(), library.randomBook());
                    case 6:
                        library.returnRandomBook(library.randomMember());
                }
            }

            if (library.currentDay >= simulationLength)
                Input.waitForUserToPressEnter("Press Enter to end the simulation.");
            else
                Input.waitForUserToPressEnter("Press Enter to simulate the next day.");
        }
    }
}