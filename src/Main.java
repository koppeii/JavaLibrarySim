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

        final int SIMULATIONLENGTH = 67;
        final int SIMULATIONFREQUENCY = 10;
        final boolean VERBOSE = false;

        while (library.getCurrentDay() < SIMULATIONLENGTH) {

            // Advance time
            library.dayStep(1);
            System.out.println("--- Day " + library.getCurrentDay() + "/" + SIMULATIONLENGTH + " Start ---");

            // simulate a random event

            while (library.getCurrentHour() < SIMULATIONFREQUENCY) {
                int randomEvent = weightedSelection.selectRandomItem();

                // Advance time
                library.hourStep(1);

                switch (randomEvent) {
                    case 0:
                        library.enter(helper.getRandomIndividual(library.getMembers()));
                        break;

                    case 1:
                        library.leave(helper.getRandomPersonInLibrary(library.getPresentInLibrary()));
                        break;

                    case 2:
                        library.applyMembership(helper.getRandomPersonInLibrary(library.getPresentInLibrary()));
                        break;

                    case 3:
                        library.revokeMembership(helper.getRandomMember(library.getMembers()));
                        break;

                    case 4:
                        library.loanRandomBook(helper.getRandomMember(library.getMembers()), helper.getRandomBook(library.getBooks()));
                        break;

                    case 5:
                        Member randomMember = helper.getRandomMember(library.getMembers());
                        if (randomMember == null) break;

                        library.returnRandomBook(randomMember, helper.getRandomBook(randomMember.getLoanedBooks().returnLoans()));
                        break;

                    case 6:
                        String thief = helper.getRandomPersonInLibrary(library.getPresentInLibrary());
                        if (thief == null) break;

                        library.stealBook(thief, helper.getRandomBook(library.getBooks()));
                        break;
                }
            }

            library.setCurrentHour(0);

            for (int m = library.getPresentInLibrary().size() - 1; m >= 0; m--)
                library.leave(library.getPresentInLibrary().get(m));
                // libraries tend to empty out when they close
                // this weird format is used because when removing a member, .size() changes, so this is best adapted for that reason

            System.out.println();

            if (library.getMembers().isEmpty())
                System.out.println("No members yet!");
            else {
                if (VERBOSE)
                    System.out.println("Members: " + library.getMembers());
                else
                    System.out.println("Members: " + library.getMembers().size());
            }

            if (VERBOSE)
                System.out.println("Available books: " + library.getBooks());
            else
                System.out.println("Available Books " + library.getBooks().size());


            if (library.getCurrentDay() >= SIMULATIONLENGTH)
                Input.waitForUserToPressEnter("Press Enter to end the simulation.");
            else
                Input.waitForUserToPressEnter("Press Enter to simulate the next day.");
        }
    }
}