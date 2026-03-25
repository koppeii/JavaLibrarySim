import java.util.List;

public class Helper {

    // for helper functions that don't really have a place anywhere else

    public String generateRandomName() {


        return "";
    }

     public boolean pluralCheck(int count) {

        return false;
     }

     public String randomIndividual() {
        int random = Rand.randomInt(0, 1);

        if (random == 1)
            return generateRandomName();
        else
            return randomMember().name;
     }

    public Member randomMember() {
        // returns the name/object of a random individual in members

        return new Member("");
    }

    public String randomPerson() {
        // returns the name of a random individual in presentInLibrary

        return "";
    }

    public Book randomBook(List<Book> bookList) {
        // returns a random string/Book from the given bookList

        return new Book("");
    }
}
