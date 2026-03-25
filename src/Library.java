//Represents the entire library system

import java.util.ArrayList;
class Library {

    int currentDay; // Current simulation day
    int currentHour;
    ArrayList<String> books = new ArrayList<>(10);
    ArrayList<String> removedBooks = new ArrayList<>(10);
    ArrayList<String> members = new ArrayList<>(10);

    // add more fields here
    public void Books() {
        books.add("Farenheit 451");
        books.add("The Bible");
    }

    public void returnBook(int x) {
        String book = removedBooks.get(x);
        books.add(x, book);
        removedBooks.remove(book);
    }
    public void removeBooks(int x) {
        String book = books.get(x);
        removedBooks.add(book);
        books.remove(book);
    }
    public void Members() {
        members.add("John");
        members.add("Micheal");
    }


    // add constructor(s) here


}