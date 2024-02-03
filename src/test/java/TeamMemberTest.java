import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TeamMemberTest {

    @Test
    public void testSetNameGetName() {
        String testName = "Test Name 1";
        TeamMember teamMember = new TeamMember(testName);
        String name = teamMember.GetName();
        assertEquals(testName, name, "Team member name returned is not correct.");
    }

    @Test
    public void testGetDateWithNoneSet() {
        String testName = "Test Name 1";
        TeamMember teamMember = new TeamMember(testName);
        TeamMember teamMemberDate = teamMember.GetDate();
        assertNull(teamMemberDate, "Team member date should be null.");
    }

    @Test
    public void testHasDateWithNoneSet() {
        String testName = "Test Name 1";
        TeamMember teamMember = new TeamMember(testName);
        assertFalse(teamMember.IsPaired(), "Should be false.");
    }

    @Test
    public void testGetDate() {
        String testName = "Test Name 1";
        TeamMember teamMember = new TeamMember(testName);
        String dateName = "Test Date Name 1";
        TeamMember teamMemberDate = new TeamMember(dateName);
        teamMember.SetPair(teamMemberDate);
        TeamMember returnedTeamMemberDate = teamMember.GetDate();
        assertEquals(dateName, returnedTeamMemberDate.GetName(), "Team member name returned is not correct.");
    }

    @Test
    public void testHasDateWithDateSet() {
        String testName = "Test Name 1";
        TeamMember teamMember = new TeamMember(testName);
        String pairName = "Test Date Name 1";
        TeamMember teamMemberDate = new TeamMember(pairName);
        teamMember.SetPair(teamMemberDate);
        assertTrue(teamMember.IsPaired(), "Should be true.");
    }
}