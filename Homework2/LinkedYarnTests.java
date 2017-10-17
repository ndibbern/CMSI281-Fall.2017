import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.Timeout;

public class LinkedYarnTests {

    String[] strings = {
            "corgi",
            "dalmata",
            "labrador",
            "golden",
            "maltes",
            "perritos",
            "lindos",
            "nati",
            "vic",
            "cachorros",
            "pug",
            "samoyed",
            "boston",
            "terrier",
            "chihuahua",
            "chichito",
            "fran",
            "amoroso",
            "fede",
            "ale"};

    // =================================================
    // Test Configuration
    // =================================================

    // Global timeout to prevent infinite loops from
    // crashing the test suite
    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);

    // Used as the basic empty LinkedYarn to test; the @Before
    // method is run before every @Test
    LinkedYarn ball;
    @Before
    public void init () {
        ball = new LinkedYarn();
    }


    // =================================================
    // Unit Tests
    // =================================================

    // Initialization Tests
    // -------------------------------------------------
    @Test
    public void testInit() {
        assertTrue(ball.isEmpty());
        assertEquals(0, ball.getSize());
    }

    // Basic Tests
    // -------------------------------------------------
    @Test
    public void testIsEmpty() {
        assertTrue(ball.isEmpty());
        ball.insert("not_empty");
        assertFalse(ball.isEmpty());

        //MY CODE

        ball = new LinkedYarn();
        for(int i = 0; i < 10; i ++) {
            ball.insert(strings[i]);
        }
        assertFalse(ball.isEmpty());

        for(int i = 0; i < 10; i ++) {
            ball.remove(strings[i]);
        }
        assertTrue(ball.isEmpty());

        for(int i = 0; i < 150; i++){
            ball.insert(strings[(int) (Math.random()*strings.length)]);
        }
        assertFalse(ball.isEmpty());

        for(int i = 0; i < strings.length; i ++){
            ball.removeAll(strings[i]);
        }
        assertTrue(ball.isEmpty());


    }

    @Test
    public void testGetSize() {
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(2, ball.getSize());
        ball.insert("unique");
        assertEquals(3, ball.getSize());

        //My test
        ball = new LinkedYarn();
        for (int i = 0; i < 10; i ++) {
            ball.insert(strings[i]);
        }
        assertEquals(ball.getSize(), 10);

        LinkedYarn nati = new LinkedYarn();
        for (int i = 0; i < 150; i++) {
            nati.insert(strings[(int) (Math.random()*strings.length)]);
        }
        assertEquals(nati.getSize(), 150);
    }

    @Test
    public void testGetUniqueSize() {
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(1, ball.getUniqueSize());
        ball.insert("unique");
        assertEquals(2, ball.getUniqueSize());
    }

    // LinkedYarn Manipulation Tests
    // -------------------------------------------------
    @Test
    public void testInsert() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertTrue(ball.contains("dup"));
        assertTrue(ball.contains("unique"));
    }

    @Test
    public void testRemove() {
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(2, ball.getSize());
        assertEquals(1, ball.getUniqueSize());
        int dups = ball.remove("dup");
        assertEquals(1, ball.getSize());
        assertEquals(1, ball.getUniqueSize());
        assertEquals(1, dups);

        // My tests
        LinkedYarn nati = new LinkedYarn();
        for (int i = 0; i < 10; i ++) {
            nati.insert(strings[i]);
        }
        for (int i = 0; i < 10; i ++) {
            nati.remove(strings[i]);
        }
        for (int i = 0; i < 10; i ++) {
            assertFalse(nati.contains(strings[i]));
        }
        nati = new LinkedYarn();
        for (int i = 0; i < 150; i++) {
            nati.insert(strings[i%20]);
        }
        for(int i = 0; i < 20; i ++) {
            nati.remove(strings[i]);
        }
        for(int i = 0; i < 20; i ++) {
            assertTrue(nati.contains(strings[i]));
        }
    }

    @Test
    public void testRemoveAll() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertEquals(3, ball.getSize());
        assertEquals(2, ball.getUniqueSize());
        ball.removeAll("dup");
        assertEquals(1, ball.getSize());
        assertEquals(1, ball.getUniqueSize());

        // My tests
        LinkedYarn nati = new LinkedYarn();
        for (int i = 0; i < 10; i ++) {
            nati.insert(strings[i]);
        }
        for (int i = 0; i < 10; i ++) {
            nati.removeAll(strings[i]);
        }
        for (int i = 0; i < 10; i ++) {
            assertFalse(nati.contains(strings[i]));
        }
        nati = new LinkedYarn();
        for (int i = 0; i < 150; i++) {
            nati.insert(strings[i%20]);
        }
        for(int i = 0; i < 20; i ++) {
            nati.removeAll(strings[i]);
        }
        for(int i = 0; i < 20; i ++) {
            assertFalse(nati.contains(strings[i]));
        }
    }

    @Test
    public void testCount() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertEquals(2, ball.count("dup"));
        assertEquals(1, ball.count("unique"));
        assertEquals(0, ball.count("forneymon"));

        //My test

        LinkedYarn nati= new LinkedYarn();
        for (int i = 0; i < 100; i++) {
            nati.insert(strings[i%20]);
        }
        for (int i = 0; i < 20; i ++) {
            assertTrue(nati.count(strings[i]) == 5);
        }
    }

    @Test
    public void testContains() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertTrue(ball.contains("dup"));
        assertTrue(ball.contains("unique"));
        assertFalse(ball.contains("forneymon"));

        //My test
        LinkedYarn nati = new LinkedYarn();
        for (int i = 0; i < 100; i++){
            nati.insert(strings[i%20]);
        }
        for (int i = 0; i < 20; i ++) {
            assertTrue(nati.contains(strings[i]));
        }
    }
    // This is tested pretty much everywhere so...


    @Test
    public void testGetMostCommon() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("cool");
        assertEquals("dup", ball.getMostCommon());
        ball.insert("cool");
        String mc = ball.getMostCommon();
        assertTrue(mc.equals("dup") || mc.equals("cool"));


    }

    // Iterator Tests
    // -------------------------------------------------
    @Test
    public void testIteratorBasics() {
        ball.insert("a");
        ball.insert("a");
        ball.insert("a");
        ball.insert("b");
        LinkedYarn.Iterator it = ball.getIterator();

        // Test next()
        LinkedYarn dolly = new LinkedYarn(ball);
        while (true) {
            String gotten = it.getString();
            assertTrue(dolly.contains(gotten));
            dolly.remove(gotten);
            if (it.hasNext()) {it.next();} else {break;}
        }
        assertTrue(dolly.isEmpty());
        assertFalse(it.hasNext());

        // Test prev()
        dolly = new LinkedYarn(ball);
        while (true) {
            String gotten = it.getString();
            assertTrue(dolly.contains(gotten));
            dolly.remove(gotten);
            if (it.hasPrev()) {it.prev();} else {break;}
        }
        assertTrue(dolly.isEmpty());
        assertFalse(it.hasPrev());

        int countOfReplaced = ball.count(it.getString());
        it.replaceAll("replaced!");
        assertEquals(countOfReplaced, ball.count("replaced!"));
        assertTrue(it.isValid());

        ball.insert("c");
        assertFalse(it.isValid());
    }

    // Inter-LinkedYarn Tests
    // -------------------------------------------------
    @Test
    public void testCopy() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        LinkedYarn dolly = new LinkedYarn(ball);
        assertEquals(2, dolly.count("dup"));
        assertEquals(1, dolly.count("unique"));
        dolly.insert("cool");
        assertFalse(ball.contains("cool"));
    }

    @Test
    public void testSwap() {
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        LinkedYarn y2 = new LinkedYarn();
        y2.insert("yo");
        y2.insert("sup");
        y1.swap(y2);
        assertTrue(y1.contains("yo"));
        assertTrue(y1.contains("sup"));
        assertTrue(y2.contains("dup"));
        assertTrue(y2.contains("unique"));
        assertFalse(y1.contains("dup"));
    }

    // Static Method Tests
    // -------------------------------------------------
    @Test
    public void testKnit() {
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        LinkedYarn y2 = new LinkedYarn();
        y2.insert("dup");
        y2.insert("cool");
        LinkedYarn y3 = LinkedYarn.knit(y1, y2);
        assertEquals(3, y3.count("dup"));
        assertEquals(1, y3.count("unique"));
        assertEquals(1, y3.count("cool"));
        y3.insert("test");
        assertFalse(y1.contains("test"));
        assertFalse(y2.contains("test"));
    }

    @Test
    public void testTear() {
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        LinkedYarn y2 = new LinkedYarn();
        y2.insert("dup");
        y2.insert("cool");
        LinkedYarn y3 = LinkedYarn.tear(y1, y2);
        assertEquals(1, y3.count("dup"));
        assertEquals(1, y3.count("unique"));
        assertFalse(y3.contains("cool"));
        y3.insert("test");
        assertFalse(y1.contains("test"));
        assertFalse(y2.contains("test"));
    }

    @Test
    public void testSameYarn() {
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        LinkedYarn y2 = new LinkedYarn();
        y2.insert("unique");
        y2.insert("dup");
        y2.insert("dup");
        assertTrue(LinkedYarn.sameYarn(y1, y2));
        assertTrue(LinkedYarn.sameYarn(y2, y1));
        y2.insert("test");
        assertFalse(LinkedYarn.sameYarn(y1, y2));
    }

}
