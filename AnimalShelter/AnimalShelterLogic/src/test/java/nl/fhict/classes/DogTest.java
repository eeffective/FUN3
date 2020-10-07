package nl.fhict.classes;
import nl.fhict.enums.Gender;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class DogTest {
    private Dog dog;
    @Before
    public void setup(){
        dog = new Dog("Goofy", Gender.Male);
    }
    @Test
    public void testConstructor(){
        assertEquals("Goofy", this.dog.getName());
        assertEquals(Gender.Male, this.dog.getGender());
        assertNull(this.dog.getReservedBy());
        assertEquals(LocalDate.now().atStartOfDay(), this.dog.getLastWalk().atStartOfDay());
        assertFalse(this.dog.isWalkNeeded());
    }
    @Test
    public void testReservation()
    {
        assertNull(this.dog.getReservedBy());
        assertTrue(this.dog.Reserve("Mickey Mouse"));
        assertNotNull(this.dog.getReservedBy());
        assertEquals("Mickey Mouse", this.dog.getReservedBy().getName());
        assertFalse(this.dog.Reserve("Minnie Mouse"));
    }
}
