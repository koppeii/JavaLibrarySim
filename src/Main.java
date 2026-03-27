import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Simulation
public class Main {
    public static void main(String[] args) {

        Library library = new Library();
        Helper helper = new Helper();

        List<Integer> eventTypes = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
            // represents the 7 events that can occur
        List<Double> weightTypes = new ArrayList<>(Arrays.asList(20d, 10d, 25d, 5d, 20d, 19d, 1d));
            // weightTypes.size() should match the .size() of eventTypes

        WeightedSelection<Integer> weightedSelection = new WeightedSelection<>(eventTypes, weightTypes);

        int simulationLength = 67;
        int simulationFrequency = 10;

        while (library.currentDay < simulationLength) {

            // Advance time
            library.currentDay++;
            System.out.println("--- Day " + library.currentDay + " Start ---");

            // simulate a random event

            while (library.currentHour < simulationFrequency) {
                int randomEvent = weightedSelection.selectRandomItem();

                // Advance time
                library.currentHour++;

                switch (randomEvent) {
                    case 0:
                        library.enter(helper.randomIndividual(library.members));
                        break;

                    case 1:
                        library.leave(helper.randomPersonInLibrary(library.presentInLibrary));
                        break;

                    case 2:
                        library.applyMembership(helper.randomPersonInLibrary(library.presentInLibrary));
                        break;

                    case 3:
                        library.revokeMembership(helper.randomMember(library.members));
                        break;

                    case 4:
                        library.loanRandomBook(helper.randomMember(library.members), helper.randomBook(library.books));
                        break;

                    case 5:
                        Member randomMember = helper.randomMember(library.members);

                        if (randomMember != null)
                            library.returnRandomBook(randomMember, helper.randomBook(randomMember.loanedBooks.books));
                        break;

                    case 6:
                        String thief = helper.randomPersonInLibrary(library.presentInLibrary);

                        if (thief != null)
                            library.stealBook(thief, helper.randomBook(library.books));
                        break;
                }
            }

            library.currentHour = 0;

            System.out.println();

            if (library.members.isEmpty())
                System.out.println("No members yet!");
            else
                System.out.println("Members: " + library.members);
            
            System.out.println("Books: " + library.books);

            if (library.currentDay >= simulationLength)
                Input.waitForUserToPressEnter("Press Enter to end the simulation.");
            else
                Input.waitForUserToPressEnter("Press Enter to simulate the next day.");
        }
    }
}