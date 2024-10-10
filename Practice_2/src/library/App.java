package library;

import java.util.*;

public class App {
    public static void main(String[] args) {
        List<String> bookNames = Arrays.asList(
                "All you need is kill", "Made in Abyss", "Spy x Family",
                "Jujutsu Kaisen", "Chainsaw Man", "Berserk",
                "Attack on titan", "Tokyo Ghoul", "Ajin",
                "Kasane"
        );

        List<String> names = Arrays.asList(
                "Роман", "Никита", "Артём", "Илья", "Даня"
        );

        Library library = new Library();

        Collections.shuffle(bookNames);
        Collections.shuffle(names);
        for (int i = 0; i < bookNames.size(); i++) {
            String bookName = bookNames.get(i);

            library.addToCatalog(new Book(bookName));
        }

        // filling readers
        List<Reader> readers = new ArrayList<>();
        for (String name : names) {
            Reader reader = new Reader(name);
            readers.add(reader);
            library.addReader(reader);
        }

        // books giving
        int personIndex = 0;
        List<Book> tempLibraryCatalog = library.getCatalog();
        for (Book book : tempLibraryCatalog) {
            readers.get(personIndex).takeBook(book);
            library.getBook(book.getName());

            personIndex = (personIndex % (readers.size() - 1)) + 1;
        }

        System.out.println(library);
    }
}