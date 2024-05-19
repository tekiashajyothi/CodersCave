import java.util.*;
class Librarymanagementsystem {
    public static void main(String[] args) {
        Library library = new Library();

        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", 123456));
        library.addBook(new Book("The Lord of the Rings", "J.R.R. Tolkien", 111222));
        library.addBook(new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 333444));
        

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to Book World");
            System.out.println("1. Display Books");
            System.out.println("2. Borrow");
            System.out.println("3. Return");
            System.out.println("4. Exit");
            System.out.print("Enter option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter the ISBN Code of the book you want to borrow: ");
                    int borrowISBN = sc.nextInt();
                    library.borrowBook(borrowISBN);
                    break;
                case 3:
                    System.out.print("Enter the ISBN Code of the book you want to return: ");
                    int returnISBN = sc.nextInt();
                    library.returnBook(returnISBN);
                    break;
                case 4:
                    System.out.println("Thank you! visit again..");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please check and try again.");
            }
        }
    }
}


class Book {
    public String booktitle;
    public String author;
    public int ISBN;
    public boolean Available;

    public Book(String booktitle, String author, int ISBN) {
        this.booktitle = booktitle;
        this.author = author;
        this.ISBN = ISBN;
        this.Available = true;
    }

    public String getTitle() {
        return booktitle;
    }

    public String getAuthor() {
        return author;
    }

    public int getISBN() {
        return ISBN;
    }

    public boolean isAvailable() {
        return Available;
    }

    public void setAvailable(boolean available) {
        Available = available;
    }
}

class Library {
    private List<Book> bookList;

    public Library() {
        this.bookList = new ArrayList<>();
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public void displayBooks() {
        System.out.println("Library Books:");
        int i=1;
        for (Book book : bookList) {
            System.out.println(i+". "+book.getTitle() + " by " + book.getAuthor() + " (ISBN: " + book.getISBN() + ")");
            i++;
        }
    }

    public Book findBookByISBN(int ISBN) {
        for (Book book : bookList) {
            if (book.getISBN() == ISBN) {
                return book;
            }
        }
        return null;
    }

    public void borrowBook(int ISBN) {
        Book book = findBookByISBN(ISBN);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            System.out.println("Thanks for borrowing the Book!");
        } else {
            System.out.println("Currently, Book is not available for borrowing.");
        }
    }

    public void returnBook(int ISBN) {
        Book book = findBookByISBN(ISBN);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            System.out.println("Book returned successfully");
        } else {
            System.out.println("Invalid book or already available.");
        }
    }
}