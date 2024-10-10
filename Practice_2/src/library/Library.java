package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private final Map<String, Book> catalogBook;

    private final Map<Integer, Reader> readers;

    public Library() {
        this.catalogBook = new HashMap<>();
        this.readers = new HashMap<>();
    }

    public Book getBook(String name) {
        return this.catalogBook.remove(name);
    }

    public void addToCatalog(Book book) {
        this.catalogBook.putIfAbsent(book.getName(), book);
    }

    public void addReader(Reader reader) {
        this.readers.putIfAbsent(reader.getrId(), reader);
    }

    public List<Book> getCatalog() {
        return new ArrayList<>(this.catalogBook.values());
    }

    public List<Reader> getReaders() {
        return new ArrayList<>(this.readers.values());
    }

    @Override
    public String toString() {
        String result = "";
        result += "Books in library: " + getCatalog().size() + "\nBooks Information:\n";
        for (Book book : getCatalog()) {
            result += "\tlibrary.Book " + book.getName() + "\n";
        }
        result += "Readers Information:\n";
        result += "Amount: " + getReaders().size() + "\nPersonal:\n";
        int booksTaken = 0;
        for (Reader reader : getReaders()) {
            booksTaken += reader.getTakenBooks().size();
            result += "\tlibrary.Reader: " + reader.getName() + " has " + reader.getTakenBooks().size() + " books" + "\n";
        }
        result += "Books taken: " + booksTaken;

        return result;
    }
}
