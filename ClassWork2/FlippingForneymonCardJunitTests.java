import static org.junit.Assert.*;
import org.junit.Test;

public class FlippingForneymonCardJunitTests {

    @Test
    public void testmatch() {
        FlippingForneymonCard defaultCard = new FlippingForneymonCard();
        FlippingForneymonCard defaultCard2 = new FlippingForneymonCard();
        assertEquals(defaultCard.match(defaultCard2), 2);

        FlippingForneymonCard burny = new FlippingForneymonCard("burny", "Burnymon", false);
        FlippingForneymonCard burny2 = new FlippingForneymonCard("burny", "Burnymon", true);
        assertEquals(burny.match(burny2), 2);
        assertEquals(burny2.match(burny), 2);

        burny2.flip();
        assertEquals(burny.match(burny2), 1);
        assertEquals(burny2.match(burny), 1);

        burny.flip();
        assertEquals(burny.match(burny2), 2);
        assertEquals(burny2.match(burny), 2);

        FlippingForneymonCard missingNu = new FlippingForneymonCard();
        assertEquals(missingNu.match(burny), 2);
        assertEquals(burny.match(missingNu), 2);

    }


}
