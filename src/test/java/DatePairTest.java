import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DatePairTest {

    @Test
    public void testGetFirstPerson() {
        String firstPerson = "First Person";
        String secondPerson = "Second Person";
        DatePair datePair = new DatePair(firstPerson,secondPerson);
        String person = datePair.getFirstPerson();
        assertEquals(person, firstPerson, "Person name not correct.");
    }

    @Test
    public void testGetSecondPerson() {
        String firstPerson = "First Person";
        String secondPerson = "Second Person";
        DatePair datePair = new DatePair(firstPerson,secondPerson);
        String person = datePair.getSecondPerson();
        assertEquals(person, secondPerson, "Person name not correct.");
    }
}