import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TeamTest {

    @Test
    public void testSetTeamNameGetTeamName() {
        String testTeamName = "Test Team Name 1";
        Team team = new Team(testTeamName);
        String name = team.GetTeamName();
        assertEquals(testTeamName, name, "Team name returned is not correct.");
    }

    @Test
    public void testHasTeamMemberNameTrueFalse() {
        String testTeamName = "Test Team Name 1";
        Team team = new Team(testTeamName);
        String testName = "Test Name 1";
        TeamMember teamMember = new TeamMember(testName);
        assertTrue(team.AddTeamMember(teamMember), "Team member should have been added");
        assertTrue(team.HasTeamMemberName(testName), "Should return true for this team member name.");
        assertFalse(team.HasTeamMemberName("Some other name"), "Should false true for this team member name.");
    }

    @Test
    public void testAdd1TeamMember(){
        String testTeamName = "Test Team Name 1";
        Team team = new Team(testTeamName);
        String testName = "Test Name 1";
        TeamMember teamMember = new TeamMember(testName);
        assertTrue(team.AddTeamMember(teamMember), "Team member should have been added");
        assertEquals(team.GetTeamSize(), 1, "Team size not correct.");
    }

    @Test
    public void testAddMultipleUniqueTeamMembers(){
        String testTeamName = "Test Team Name 1";
        Team team = new Team(testTeamName);
        assertTrue(team.AddTeamMember(new TeamMember("Test Name 1")), "Team member should have been added");
        assertTrue(team.AddTeamMember(new TeamMember("Test Name 2")), "Team member should have been added");
        assertTrue(team.AddTeamMember(new TeamMember("Test Name 3")), "Team member should have been added");
        assertEquals(team.GetTeamSize(), 3, "Team size not correct.");
    }

    @Test
    public void testAddMultipleTeamMembersNonUnique(){
        String testTeamName = "Test Team Name 1";
        Team team = new Team(testTeamName);
        assertTrue(team.AddTeamMember(new TeamMember("Test Name 1")), "Team member should have been added");
        assertTrue(team.AddTeamMember(new TeamMember("Test Name 2")), "Team member should have been added");
        assertTrue(team.AddTeamMember(new TeamMember("Test Name 3")), "Team member should have been added");
        assertEquals(team.GetTeamSize(), 3, "Team size not correct.");
        assertFalse(team.AddTeamMember(new TeamMember("Test Name 1")), "Team member should not have been added");
        assertEquals(team.GetTeamSize(), 3, "Team size not correct.");
    }

    @Test
    public void testGetRandomTeamMemberEmptyTeam(){
        String testTeamName = "Test Team Name 1";
        Team team = new Team(testTeamName);
        TeamMember randomTeamMember = null;
        randomTeamMember = team.GetRandomUnpairedTeamMember();
        assertNull(randomTeamMember, "Random team member should be null for empty team.");
    }

    @Test
    public void testGetRandomTeamMemberOneWithDate(){
        String testTeamName = "Test Team Name 1";
        String teamMemberWithoutPairName = "Without pair";
        Team team = new Team(testTeamName);
        TeamMember teamMemberWithPair = new TeamMember("Test Name 1");
        TeamMember teamMemberWithoutPair = new TeamMember(teamMemberWithoutPairName);
        teamMemberWithPair.SetPair(teamMemberWithoutPair);
        assertTrue(team.AddTeamMember(teamMemberWithPair), "Team member should have been added");
        TeamMember randomTeamMember = null;
        randomTeamMember = team.GetRandomUnpairedTeamMember();
        assertNull(randomTeamMember, "No team member should be returned.");
    }

    @Test
    public void testGetRandomTeamMemberTwoMembersWithoutDates(){
        String testTeamName = "Test Team Name 1";
        Team team = new Team(testTeamName);
        assertTrue(team.AddTeamMember(new TeamMember("Test Name 1")), "Team member should have been added");
        assertTrue(team.AddTeamMember(new TeamMember("Test Name 2")), "Team member should have been added");
        TeamMember randomTeamMember = null;
        randomTeamMember = team.GetRandomUnpairedTeamMember();
        assertNotNull(randomTeamMember, "Random team member should be returned.");
    }

    @Test
    public void testGetRandomTeamMemberTwoMembersOneWithDate(){
        String testTeamName = "Test Team Name 1";
        String teamMemberWithoutPairName = "Without pair";
        Team team = new Team(testTeamName);
        TeamMember teamMemberWithPair = new TeamMember("Test Name 1");
        TeamMember teamMemberWithoutPair = new TeamMember(teamMemberWithoutPairName);
        teamMemberWithPair.SetPair(teamMemberWithoutPair);
        assertTrue(team.AddTeamMember(teamMemberWithPair), "Team member should have been added");
        assertTrue(team.AddTeamMember(teamMemberWithoutPair), "Team member should have been added");
        TeamMember randomTeamMember = null;
        randomTeamMember = team.GetRandomUnpairedTeamMember();
        assertNotNull(randomTeamMember, "Random team member 2 should be returned.");
        assertEquals(randomTeamMember.GetName(), teamMemberWithoutPairName, "Team member name incorrect.");
    }

    @Test
    public void testGetRandomTeamMemberNoneWithDates(){
        String testTeamName = "Test Team Name 1";
        Team team = new Team(testTeamName);
        assertTrue(team.AddTeamMember(new TeamMember("Test Name 1")), "Team member should have been added");
        assertTrue(team.AddTeamMember(new TeamMember("Test Name 2")), "Team member should have been added");
        assertTrue(team.AddTeamMember(new TeamMember("Test Name 3")), "Team member should have been added");
        TeamMember randomTeamMember = null;
        randomTeamMember = team.GetRandomUnpairedTeamMember();
        assertNotNull(randomTeamMember, "Random team member not returned from non empty team.");
    }
}