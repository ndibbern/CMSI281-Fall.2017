import static org.junit.Assert.*;
import org.junit.Test;

public class YarnTests {

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


    @Test
    public void testYarn() { Yarn ball = new Yarn(); }

    @Test
    public void testIsEmpty() {
        Yarn ball = new Yarn();
        assertTrue(ball.isEmpty());
        ball.insert("not_empty");
        assertFalse(ball.isEmpty());

        //CODE

        ball = new Yarn();
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
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(2, ball.getSize());
        ball.insert("unique");
        assertEquals(3, ball.getSize());

        //My test
        ball = new Yarn();
        for (int i = 0; i < 10; i ++) {
            ball.insert(strings[i]);
        }
        assertEquals(ball.getSize(), 10);

        Yarn nati = new Yarn();
        for (int i = 0; i < 150; i++) {
            nati.insert(strings[(int) (Math.random()*strings.length)]);
        }
        assertEquals(nati.getSize(), 150);
    }

    @Test
    public void testGetUniqueSize() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(1, ball.getUniqueSize());
        ball.insert("unique");
        assertEquals(2, ball.getUniqueSize());

        //My test
        Yarn nati = new Yarn();
        for(int i = 0; i < 150; i++){
            nati.insert(strings[0]);
        }
        assertEquals(nati.getUniqueSize(), 1);
    }

    @Test
    public void testInsert() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertTrue(ball.contains("dup"));
        assertTrue(ball.contains("unique"));

        //My test
        Yarn nati = new Yarn();
        for(int i = 0; i < 100; i ++){
            assertTrue(nati.insert(strings[0]));
        }
        assertTrue(nati.contains(strings[0]));
    }

    @Test
    public void testRemove() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(2, ball.getSize());
        assertEquals(1, ball.getUniqueSize());
        ball.remove("dup");
        assertEquals(1, ball.getSize());
        assertEquals(1, ball.getUniqueSize());

        // My tests
        Yarn nati = new Yarn();
        for (int i = 0; i < 10; i ++) {
            nati.insert(strings[i]);
        }
        for (int i = 0; i < 10; i ++) {
            nati.remove(strings[i]);
        }
        for (int i = 0; i < 10; i ++) {
            assertFalse(nati.contains(strings[i]));
        }
        nati = new Yarn();
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
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertEquals(3, ball.getSize());
        assertEquals(2, ball.getUniqueSize());
        ball.removeAll("dup");
        assertEquals(1, ball.getSize());
        assertEquals(1, ball.getUniqueSize());

        // My tests
        Yarn nati = new Yarn();
        for (int i = 0; i < 10; i ++) {
            nati.insert(strings[i]);
        }
        for (int i = 0; i < 10; i ++) {
            nati.removeAll(strings[i]);
        }
        for (int i = 0; i < 10; i ++) {
            assertFalse(nati.contains(strings[i]));
        }
        nati = new Yarn();
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
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertEquals(2, ball.count("dup"));
        assertEquals(1, ball.count("unique"));
        assertEquals(0, ball.count("forneymon"));

        //My test

        Yarn nati= new Yarn();
        for (int i = 0; i < 100; i++) {
            nati.insert(strings[i%20]);
        }
        for (int i = 0; i < 20; i ++) {
            assertTrue(nati.count(strings[i]) == 5);
        }
    }

    @Test
    public void testContains() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertTrue(ball.contains("dup"));
        assertTrue(ball.contains("unique"));
        assertFalse(ball.contains("forneymon"));

        //My test
        Yarn nati = new Yarn();
        for (int i = 0; i < 100; i++){
            nati.insert(strings[i%20]);
        }
        for (int i = 0; i < 20; i ++) {
            assertTrue(nati.contains(strings[i]));
        }
    }

    @Test
    public void testCopy() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        Yarn dolly = new Yarn(ball);
        assertEquals(2, dolly.count("dup"));
        assertEquals(1, dolly.count("unique"));
        dolly.insert("cool");
        assertFalse(ball.contains("cool"));

        //My test
        Yarn nati = new Yarn();
        for (int i = 0; i < 20; i ++) {
            nati.insert(strings[i]);
        }

        Yarn natiCopy = new Yarn(nati);
        natiCopy.remove(strings[0]);
        assertTrue(nati.contains(strings[0]));
        assertFalse(natiCopy.contains(strings[0]));
    }

    @Test
    public void testGetNth() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("cool");
        Yarn comparison = new Yarn(ball);
        for (int i = 0; i < ball.getSize(); i++) {
            String gotten = ball.getNth(i);
            assertTrue(comparison.contains(gotten));
            comparison.remove(gotten);
        }

        //My test
        Yarn nati = new Yarn();
        for (int i = 0; i < 100; i ++) {
            nati.insert(strings[i % 20]);
        }
        for(int i = 0; i < 20; i ++) {
            int count = 0;
            for (int j = 0; j < 100; j++) {
                count += nati.getNth(j) == strings[i] ? 1 : 0;
            }
            assertTrue(count == 5);
        }
    }

    @Test
    public void testGetMostCommon() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("cool");
        assertEquals("dup", ball.getMostCommon());
        ball.insert("cool");
        String mc = ball.getMostCommon();
        assertTrue(mc.equals("dup") || mc.equals("cool"));

        //My test
        Yarn nati = new Yarn();
        for (int i = 0; i < 20; i ++) {
            nati.insert(strings[i]);
        }
        nati.insert(strings[0]);
        assertTrue(nati.getMostCommon().equals(strings[0]));
    }

    @Test
    public void testSwap() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("yo");
        y2.insert("sup");
        y1.swap(y2);
        assertTrue(y1.contains("yo"));
        assertTrue(y1.contains("sup"));
        assertTrue(y2.contains("dup"));
        assertTrue(y2.contains("unique"));
        assertFalse(y1.contains("dup"));


        // My test
        Yarn nati = new Yarn();
        for (int i = 0; i < 20; i ++) {
            nati.insert(strings[i]);
        }

        Yarn natiEmpty = new Yarn();
        nati.swap(natiEmpty);
        assertTrue(nati.isEmpty());
        for (int i = 0; i < 20; i ++) {
            assertTrue(natiEmpty.contains(strings[i]));
        }

    }

    @Test
    public void testToString() {
        Yarn y1 = new Yarn();
        assertTrue(y1.toString().equals("{  }"));
        y1.insert("unique");
        assertTrue(y1.toString().equals("{ \"unique\": 1 }"));
        y1.insert("dup");
        y1.insert("dup");
        assertTrue(y1.toString().equals("{ \"unique\": 1, \"dup\": 2 }") ||
                   y1.toString().equals("{ \"dup\": 2, \"unique\": 1 }"));

        //My test
        Yarn nati = new Yarn();
        assertTrue(nati.toString().equals("{  }"));

        for (int i = 0; i < 2; i ++) {
            nati.insert(strings[i]);
        }
        assertTrue(nati.toString().equals("{ \"corgi\": 1, \"dalmata\": 1 }") ||
                nati.toString().equals("{ \"dalmata\": 1, \"corgi\": 1 }"));
    }

    @Test
    public void testKnit() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("dup");
        y2.insert("cool");
        Yarn y3 = Yarn.knit(y1, y2);
        assertEquals(3, y3.count("dup"));
        assertEquals(1, y3.count("unique"));
        assertEquals(1, y3.count("cool"));
        y3.insert("test");
        assertFalse(y1.contains("test"));
        assertFalse(y2.contains("test"));

        //My test
        Yarn nati = new Yarn();
        Yarn nati2 = new Yarn();
        for(int i = 0; i < 40; i ++){
            nati.insert(strings[(int) (Math.random()*strings.length)]);
        }
        for(int i = 0; i < 40; i ++){
            nati2.insert(strings[(int) (Math.random()*strings.length)]);
        }
        Yarn knitted = Yarn.knit(nati, nati2);
        assertEquals(knitted.getSize(), nati.getSize() + nati2.getSize());
        for(String string: strings){
            assertEquals(knitted.count(string), nati.count(string) + nati2.count(string));
        }
    }

    @Test
    public void testTear() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("dup");
        y2.insert("cool");
        Yarn y3 = Yarn.tear(y1, y2);
        assertEquals(1, y3.count("dup"));
        assertEquals(1, y3.count("unique"));
        assertFalse(y3.contains("cool"));
        y3.insert("test");
        assertFalse(y1.contains("test"));
        assertFalse(y2.contains("test"));

        //My test
        Yarn nati = new Yarn();
        Yarn nati2 = new Yarn();
        for(int i = 0; i < 100; i ++){
            nati.insert(strings[(int) (Math.random()*strings.length)]);
        }
        for(int i = 0; i < 100; i ++){
            nati2.insert(strings[(int) (Math.random()*strings.length)]);
        }
        Yarn teared = Yarn.tear(nati, nati2);
        for(String string: strings) {
            assertEquals(teared.count(string), nati.count(string) - nati2.count(string) > 0 ? nati.count(string) - nati2.count(string) : 0);
        }
    }

    @Test
    public void testSameYarn() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("unique");
        y2.insert("dup");
        y2.insert("dup");
        assertTrue(Yarn.sameYarn(y1, y2));
        assertTrue(Yarn.sameYarn(y2, y1));
        y2.insert("test");
        assertFalse(Yarn.sameYarn(y1, y2));


        // My test
        Yarn nati = new Yarn();
        Yarn nati2 = new Yarn();
        for (int i = 0; i < 40; i ++) {
            nati.insert(strings[(int) (Math.random()*strings.length)]);
        }
        for(int i = 0; i < 30; i ++){
            nati2.insert(strings[(int) (Math.random()*strings.length)]);
        }
        Yarn knitted = Yarn.knit(nati, nati2);
        Yarn knitted2 = Yarn.knit(nati2, nati);
        assertFalse(Yarn.sameYarn(nati, nati2));
        assertFalse(Yarn.sameYarn(nati2, nati));
        assertTrue(Yarn.sameYarn(knitted, knitted2));

    }

}
