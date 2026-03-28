import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Helper {

    // for helper functions that don't really have a place anywhere else
    private static final String[] first_names = {"Mike", "Ted", "Alex", "Gab", "Gabriel", "Micheal", "Paul", "Alice", "Grimm", "John", "Jane", "Mary", "Lewis",
    "Elma", "Victoria", "Agnes"};
    private static final String[] last_names = {"Brown", "Smith", "Altura", "Licup", "Upao", "Altura", "Rempel", "Smith", "Liddell", "Doe", "Sue", "Caroll",
    "Tachyon"};

    private static final List<Integer> eventTypes = new ArrayList<>(Arrays.asList(0, 1));
    private static final List<Double> weightTypes = new ArrayList<>(Arrays.asList(80d, 20d));

    private static final WeightedSelection<Integer> weightedSelection= new WeightedSelection<>(eventTypes, weightTypes);
        // static means that the value is shared across all classes/objects, one new Helper(); can change this function if it werent a private final, can also be changed
        // by another new Helper();

    public String generateRandomName() {
        int first = Rand.randomInt(0, first_names.length);
        int last = Rand.randomInt(0, last_names.length);
        String firstName = first_names[first];
        String lastName = last_names[last];

        return firstName + " " + lastName;
    }

     public String pluralCheck(int count) {
        if (count == 1)
            return "";

        return "s";
     }

     public String randomIndividual(List<Member> memberList) {
        int random = weightedSelection.selectRandomItem();

        if (random == 1)
            return generateRandomName();
        else {
            Member randomMember = randomMember(memberList);

            if (randomMember != null)
                return randomMember(memberList).name;
            else
                return "";
        }
     }

    public Member randomMember(List<Member> memberList) {
        // returns the name/object of a random individual in members

        if (!memberList.isEmpty()) {
            int random = Rand.randomInt(0, memberList.size());

            return memberList.get(random);
        }

        return null;
    }

    public String randomPersonInLibrary(List<String> people) {
        // returns the name of a random individual in presentInLibrary

        if (!people.isEmpty()) {
            int random = Rand.randomInt(0, people.size());

            return people.get(random);
        }
        return "";
    }

    public Book randomBook(List<Book> bookList) {
        // returns a random string/Book from the given bookList
        if (!bookList.isEmpty()) {
            int random = Rand.randomInt(0, bookList.size());

            return bookList.get(random);
        }

        return null;
    }
}