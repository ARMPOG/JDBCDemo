
import manager.BookManager;
import model.Books;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
	// write your code here
        BookManager bkmanager = new BookManager();
        /*boolean b = bkmanager.saveAll(Arrays.asList(
                new Books("In Search of Lost Time", "Marcel Proust", 4215),
                new Books("Ulysses", "James Joyce", 730),
                new Books("Don Quixote", "Miguel de Cervante", 863),
                new Books("War and Peace", "Leo Tolstoy", 1225),
                new Books("Lolita", "Vladimir Nabokov", 317)
        ));*/

        /*Books bookName = bkmanager.getABookByName("Lolita");
        System.out.println(bookName);*/

        bkmanager.updateByBookName("Lolita");


       // bkmanager.deleteAllBooks();
       // bkmanager.deleteABook("Lolita");
    }
}
