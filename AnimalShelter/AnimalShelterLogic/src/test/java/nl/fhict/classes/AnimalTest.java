package nl.fhict.classes;

import nl.fhict.classes.Animal;
import nl.fhict.enums.Gender;
import org.junit.*;

import static org.junit.Assert.*;

public class AnimalTest {
    private Animal animal;

    @Before
    public void setup(){
        animal = new Dog("Speedy", Gender.Male);
    }

    @Test
    public void testConstructor()
    {
        assertEquals("Speedy", this.animal.getName());
        assertEquals(Gender.Male, this.animal.getGender());
        assertNull(this.animal.getReservedBy());
    }
}
