// Simulation
public class Main {
    public static void main(String[] args) {

        Library lib = new Library();
        lib.Books();
        lib.Members();
        int simulationLength = 100;

        while (lib.currentDay < simulationLength) {

            // Advance time
            lib.currentDay++;
            System.out.println("\n--- Day " + lib.currentDay + " ---");

            System.out.println(lib.books);

            // simulate a random event

            int randomNumber = Rand.randomInt(0, 4); // could generate 0, 1, 2, or 3
            switch (randomNumber) {
                case 1:
                    lib.removeBooks(1);

                case 2:
                    if (lib.books.get(1).isEmpty()) {
                    lib.returnBook(0);
                }
                    else {
                        System.out.println(121313);
                    }


//                randomNumber == 2 && lib.books.get(1).isEmpty()) {
//                    lib.returnBook(1);
            }

            switch (randomNumber) {

            }

            Input.waitForUserToPressEnter("Press Enter to simulate the next day.");
        }
    }
}