// Simulation
public class Main {
    public static void main(String[] args) {

        Library library = new Library();
        Helper helper = new Helper();

        int simulationLength = 100;
        int simulationFrequency = 10;

        String name = helper.generateRandomName();
        System.out.println(name);



        while (library.currentDay < simulationLength) {

            // Advance time
            library.currentDay++;
            System.out.println("--- Day " + library.currentDay + " Start ---");

            // simulate a random event

            for (int i = 0; i < simulationFrequency; i++) {
                // should add weights to simulate more realistic actions

                int inOut = Rand.randomInt(0, 4);

                if (inOut == 0)
                    library.enter(helper.randomIndividual(library.members));
                else if (inOut == 1)
                    library.leave(helper.randomPersonInLibrary(library.presentInLibrary));

                for (int p = 0; p < library.presentInLibrary.size(); p++) {

                    int randomAction = Rand.randomInt(0, 5);

                    switch (randomAction) {
                        case 0:
                            library.applyMembership(library.presentInLibrary.get(p));
                            break;
                        case 1:
                            library.revokeMembership(helper.randomMember(library.members));
                            break;
                        case 2:
                            library.loanRandomBook(helper.randomMember(library.members), helper.randomBook(library.books));
                            break;
                        case 3:
                            Member randomMember = helper.randomMember(library.members);

                            if (randomMember != null) {
                                library.returnRandomBook(randomMember, helper.randomBook(randomMember.loanedBooks.books));
                            }
                            break;
                        case 4:
                            //works as intended as guess...?
                            //if ur in gta at least
                            String thief = helper.randomPersonInLibrary(library.presentInLibrary);

                            if (thief != null) {
                                library.stealBook(thief, helper.randomBook(library.books));
                            }
                    }
                }
            }
            if (library.members.isEmpty()){
                System.out.println("No members yet!");
            }
            else {
                System.out.println("Members: " + library.members);
            }
            
            System.out.println("Books: " + library.books);

            if (library.currentDay >= simulationLength)
                Input.waitForUserToPressEnter("Press Enter to end the simulation.");
            else
                Input.waitForUserToPressEnter("Press Enter to simulate the next day.");
            }
    }
}