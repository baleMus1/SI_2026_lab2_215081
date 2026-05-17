import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class SI2026Lab2Test{

    private Library library=new Library();

    @BeforeEach
    public void setUp(){
        library=new Library();
        library.addBook(new Book("Clean Code", "Robert C. Martin", "Programming"));
        library.addBook(new Book("Effective Java", "Joshua Bloch", "Programming"));
        library.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy"));
        library.addBook(new Book("1984", "George Orwell", "Dystopian"));
    }

    @Test
    public void searchBookEveryStatementTest(){
        // S->1->2->END
        assertThrows(IllegalArgumentException.class, () -> library.searchBookByTitle(""));
        // S->1->3->4->5->6->7->9->END
        List<Book> results=library.searchBookByTitle("Clean Code");
        assertNotNull(results);
        assertEquals(1,results.size());
        assertFalse(results.isEmpty());
        // S->1->3->4->5->7->8->END
        List<Book> notFound=library.searchBookByTitle("RandomNaslovStoGoNema");
        assertNull(notFound);

    }

    @Test
    public void borrowBookEveryBranchTest(){
        // ako avtorot ili imteo ne e poznato da frli throw (ako stavime samo ednoto da e kje pagja sekokash posle vo && kade proveruva equals)
        assertThrows(IllegalArgumentException.class,()->library.borrowBook("","Robert C. Martin"));
        assertThrows(IllegalArgumentException.class,()->library.borrowBook("Clean Code",""));

        //tuka kje gi pomineme grankite kade ja iznajmuvame knigata
        assertDoesNotThrow(()->library.borrowBook("Clean Code","Robert C. Martin"));
        // sega tuka kje probame so istata kniga koja ja iznajmivme vo prethodniot red i treba da frli RuntimeException
        assertThrows(RuntimeException.class,()->library.borrowBook("Clean Code","Robert C. Martin"));

        // uste kniga koja e random
        assertThrows(RuntimeException.class,()->library.borrowBook("Nepostoecka Kniga","Nepostoecki Avtor"));
    }

    @Test
    public void searchBookMultipleConditionTest(){
        // if (book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed())
        // imame 4 redovi vo tabela na vistinitost neka poduslovite bidat A=book.getTitle().equalsIgnoreCase(title) i B=!book.isBorrowed()
        // A=T B=T ; A=T B=F ; A=F B=T ; A=F B=F
        // ova ni ukazuva deka vo poslednite dva test cases ne ni e vazen B ako a e False, togas B ne go ni gleame
        // zatoa minimalen broj na test cases e 3

        // A=T ^ B=T
        List<Book> Lista1=library.searchBookByTitle("Clean Code");
        assertNotNull(Lista1);
        assertFalse(Lista1.isEmpty());
        // A=T ^ B=F
        assertDoesNotThrow(()->library.borrowBook("Clean Code","Robert C. Martin"));
        List<Book> Lista2=library.searchBookByTitle("Clean Code");
        assertNull(Lista2);
        // A=F ^ B ne e biten
        List<Book> Lista3=library.searchBookByTitle("Nepostoecki naslov");
        assertNull(Lista3);

    }
    @Test
    public void borrowBookMultipleConditionTest(){
        assertThrows(IllegalArgumentException.class,()->library.borrowBook("",""));
        assertThrows(IllegalArgumentException.class,()->library.borrowBook("Clean Code",""));
        assertThrows(IllegalArgumentException.class,()->library.borrowBook("","Joshua Bloch"));
        assertDoesNotThrow(()->library.borrowBook("Clean Code","Robert C. Martin"));

    }
}