package nl.fhict.classes;

import junit.extensions.TestSetup;
import nl.fhict.enums.Gender;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReservationTest {
    private Reservation reservation;

    @Before
    public void setup(){
        reservation = new Reservation();
    }

    @Test
    public void testNewCat(){
        assertEquals(0, this.reservation.getAnimals().size());
        this.reservation.newCat("Pussy", Gender.Female, "Scratches couch");
        assertEquals(1, this.reservation.getAnimals().size());
    }

    @Test
    public void testNewDog()
    {
        assertEquals(0, this.reservation.getAnimals().size());
        this.reservation.newDog("Daggoe", Gender.Male);
        assertEquals(1, this.reservation.getAnimals().size());
    }
}
