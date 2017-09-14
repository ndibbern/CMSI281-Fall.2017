package forneymon;

import static org.junit.Assert.*;
import org.junit.Test;

public class BurnymonTests {

    @Test
    public void testTakeDamage() {
        Burnymon burny = new Burnymon("Dave");
        assertEquals(burny.getHealth(), 15);
        burny.takeDamage(5, "dampy");
        assertEquals(burny.getHealth(), 10);
    }

    @Test
    public void testToString() {
        Burnymon burny = new Burnymon("Dave");
        assertEquals(burny.toString(), "Dave Dave");
    }

}
