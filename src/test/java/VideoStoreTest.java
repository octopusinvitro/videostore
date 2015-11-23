import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VideoStoreTest {

    private Customer customer;

    @Before
    public void setUp() {
        customer = new Customer ("Fred");
    }

    @Test
    public void singleNewReleaseStatement() {
        customer.addRental (new Rental (new Movie ("The Cell", Movie.NEW_RELEASE), 3));
        assertEquals ("Rental Record for Fred\n\tThe Cell\t9.0\nYou owed 9.0\nYou earned 2 frequent renter points\n", customer.statement ());
        assertEquals ("<h1>Rentals for <em>Fred</em></h1>The Cell: 9.0<br><p>You owe <em>9.0</em></p><p>On this rental you earned <em>2</em> frequent renter points</p>", customer.htmlStatement());
    }

    @Test
    public void dualNewReleaseStatement() {
        customer.addRental (new Rental (new Movie ("The Cell", Movie.NEW_RELEASE), 3));
        customer.addRental (new Rental (new Movie ("The Tigger Movie", Movie.NEW_RELEASE), 3));
        assertEquals ("Rental Record for Fred\n\tThe Cell\t9.0\n\tThe Tigger Movie\t9.0\nYou owed 18.0\nYou earned 4 frequent renter points\n", customer.statement ());
        assertEquals ("<h1>Rentals for <em>Fred</em></h1>The Cell: 9.0<br>The Tigger Movie: 9.0<br><p>You owe <em>18.0</em></p><p>On this rental you earned <em>4</em> frequent renter points</p>", customer.htmlStatement());
    }

    @Test
    public void singleChildrensStatement() {
        customer.addRental (new Rental (new Movie ("The Tigger Movie", Movie.CHILDRENS), 3));
        assertEquals ("Rental Record for Fred\n\tThe Tigger Movie\t1.5\nYou owed 1.5\nYou earned 1 frequent renter points\n", customer.statement ());
        assertEquals ("<h1>Rentals for <em>Fred</em></h1>The Tigger Movie: 1.5<br><p>You owe <em>1.5</em></p><p>On this rental you earned <em>1</em> frequent renter points</p>", customer.htmlStatement());
    }

    @Test
    public void multipleRegularStatement() {
        customer.addRental (new Rental (new Movie ("Plan 9 from Outer Space", Movie.REGULAR), 1));
        customer.addRental (new Rental (new Movie ("8 1/2", Movie.REGULAR), 2));
        customer.addRental (new Rental (new Movie ("Eraserhead", Movie.REGULAR), 3));

        assertEquals ("Rental Record for Fred\n\tPlan 9 from Outer Space\t2.0\n\t8 1/2\t2.0\n\tEraserhead\t3.5\nYou owed 7.5\nYou earned 3 frequent renter points\n", customer.statement ());
        assertEquals ("<h1>Rentals for <em>Fred</em></h1>Plan 9 from Outer Space: 2.0<br>8 1/2: 2.0<br>Eraserhead: 3.5<br><p>You owe <em>7.5</em></p><p>On this rental you earned <em>3</em> frequent renter points</p>", customer.htmlStatement());
    }
}