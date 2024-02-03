import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PairTest {

    @Test
    public void testGetFirstPerson() {
        String firstPerson = "First Person";
        String secondPerson = "Second Person";
        Pair pair = new Pair(firstPerson,secondPerson);
        String person = pair.getFirstPerson();
        assertEquals(person, firstPerson, "Person name not correct.");
    }

    @Test
    public void testGetSecondPerson() {
        String firstPerson = "First Person";
        String secondPerson = "Second Person";
        Pair pair = new Pair(firstPerson,secondPerson);
        String person = pair.getSecondPerson();
        assertEquals(person, secondPerson, "Person name not correct.");
    }
}