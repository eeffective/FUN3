package nl.fhict.classes;


import nl.fhict.enums.Gender;
import org.junit.*;

import static org.junit.Assert.*;

public class CatTest {
    private Cat cat;
    @Before
    public void setup(){
        cat = new Cat("Garfield", Gender.Male, "Extremely lazy");
    }
    @Test
    public void testConstructor(){
        assertEquals("Garfield", this.cat.getName());
        assertEquals(Gender.Male, this.cat.getGender());
        assertNull(this.cat.getReservedBy());
        assertEquals("Extremely lazy", this.cat.getBadHabits());
    }
    @Test
    public void testReservation()
    {
        assertNull(this.cat.getReservedBy());
        assertTrue(this.cat.Reserve("John Doe"));
        assertNotNull(this.cat.getReservedBy());
        assertEquals("John Doe", this.cat.getReservedBy().getName());
        assertFalse(this.cat.Reserve("Jane Doe"));
    }
}
