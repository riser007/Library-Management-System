import java.util.*;

class Book {
    private String title;
    private String author;
    private String ISBN;
    private int publicationYear;
    private boolean isAvailable;

    public Book(String title, String author, String ISBN, int publicationYear) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publicationYear = publicationYear;
        this.isAvailable = true;
    }

    
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getISBN() { return ISBN; }
    public int getPublicationYear() { return publicationYear; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", publicationYear=" + publicationYear +
                ", isAvailable=" + isAvailable +
                '}';
    }
}


class Patron {
    private String name;
    private String patronId;
    private String email;
    private List<Book> borrowedBooks;

    public Patron(String name, String patronId, String email) {
        this.name = name;
        this.patronId = patronId;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }


    public String getName() { return name; }
    public String getPatronId() { return patronId; }
    public String getEmail() { return email; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "Patron{" +
                "name='" + name + '\'' +
                ", patronId='" + patronId + '\'' +
                ", email='" + email + '\'' +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }
}


class LibraryInventory {
    private List<Book> books;
    private Map<Book, Patron> borrowedBooks;

    public LibraryInventory() {
        this.books = new ArrayList<>();
        this.borrowedBooks = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }
        return result;
    }

    public Book checkoutBook(String ISBN, Patron patron) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN) && book.isAvailable()) {
                book.setAvailable(false);
                borrowedBooks.put(book, patron);
                patron.borrowBook(book);
                return book;
            }
        }
        return null;
    }

    public void returnBook(String ISBN, Patron patron) {
        for (Book book : borrowedBooks.keySet()) {
            if (book.getISBN().equals(ISBN) && borrowedBooks.get(book).equals(patron)) {
                book.setAvailable(true);
                patron.returnBook(book);
                borrowedBooks.remove(book);
                return;
            }
        }
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
}

// Factory Pattern for Book and Patron creation
class LibraryFactory {
    public static Book createBook(String title, String author, String ISBN, int publicationYear) {
        return new Book(title, author, ISBN, publicationYear);
    }

    public static Patron createPatron(String name, String patronId, String email) {
        return new Patron(name, patronId, email);
    }
}

// Main class to demonstrate the system
public class LibraryManagementSystem {
    public static void main(String[] args) {
        LibraryInventory inventory = new LibraryInventory();

        // Add books using the factory
        Book book1 = LibraryFactory.createBook("awesome blossom", "Timothy", "123456789", 1949);
        Book book2 = LibraryFactory.createBook("one flew over", "Pat Cummins", "987654321", 1960);
        inventory.addBook(book1);
        inventory.addBook(book2);

        // Add patrons using the factory
        Patron patron1 = LibraryFactory.createPatron("head", "P001", "head@example.com");
        Patron patron2 = LibraryFactory.createPatron("david", "P002", "david@example.com");

        // Checkout and return books
        inventory.checkoutBook("123456789", patron1);
        System.out.println("Available books after checkout: " + inventory.getAvailableBooks());

        inventory.returnBook("123456789", patron1);
        System.out.println("Available books after return: " + inventory.getAvailableBooks());
    }
}
