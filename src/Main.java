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
        List<Double> weightTypes = new ArrayList<>(Arrays.asList(25d, 5d, 25d, 5d, 25d, 14d, 1d));
            // weightTypes.size() should match the .size() of eventTypes

        WeightedSelection<Integer> weightedSelection = new WeightedSelection<>(eventTypes, weightTypes);

        final int simulationLength = 14;
        final int simulationFrequency = 6;
        final boolean verbose = false;

        while (library.currentDay < simulationLength) {

            // Advance time
            library.currentDay++;
            System.out.println("--- Day " + library.currentDay + "/" + simulationLength + " Start ---");

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
                        if (randomMember == null) break;

                        library.returnRandomBook(randomMember, helper.randomBook(randomMember.loanedBooks.books));
                        break;

                    case 6:
                        String thief = helper.randomPersonInLibrary(library.presentInLibrary);
                        if (thief.isBlank()) break;

                        library.stealBook(thief, helper.randomBook(library.books));
                        break;
                }
            }

            library.currentHour = 0;

            for (int m = library.presentInLibrary.size() - 1; m >= 0; m--)
                library.leave(library.presentInLibrary.get(m));
                // libraries tend to empty out when they close

            System.out.println();

            if (library.members.isEmpty())
                System.out.println("No members yet!");
            else {
                if (verbose)
                    System.out.println("Members: " + library.members);
                else
                    System.out.println("Members: " + library.members.size());
            }

            if (verbose)
                System.out.println("Available books: " + library.books);
            else
                System.out.println("Available Books " + library.books.size());


            if (library.currentDay >= simulationLength)
                Input.waitForUserToPressEnter("Press Enter to end the simulation.");
            else
                Input.waitForUserToPressEnter("Press Enter to simulate the next day.");
        }
    }
}