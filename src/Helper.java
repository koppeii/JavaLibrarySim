
public class Helper {

    // for helper functions that don't really have a place anywhere else
    private static final String[] first_names = {"Mike", "Ted", "Alex", "Gab", "Gabriel", "Micheal"};
    private static final String[] last_names = {"Brown", "Smith", "Altura", "Licup", "Upao", "Altura"};

    public String generateRandomName() {
        int first = Rand.randomInt(0, first_names.length);
        int last = Rand.randomInt(0, last_names.length);
        String firstName = first_names[first];
        String lastName = last_names[last];

        return firstName + lastName;
    }

     public boolean pluralCheck(int count) {

        return false;
     }

}
