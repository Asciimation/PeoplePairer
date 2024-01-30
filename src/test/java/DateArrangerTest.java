import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DateArrangerTest {

    @Test
    public void testGetNumberOfTeamsNoTeamsCreated() {
        DateArranger dateArranger = new DateArranger();
        assertEquals(0, dateArranger.GetNumberOfTeams(), "Number of teams not correct.");
    }

    @Test
    public void testAddTeamNoExistingTeams() {
        DateArranger dateArranger = new DateArranger();
        Team team = new Team("Test Team 1");
        TeamMember team1Member1 = new TeamMember("Member 1");
        assertTrue(team.AddTeamMember(team1Member1), "Team member should have been added");
        assertEquals(0, dateArranger.GetNumberOfTeams(), "Number of teams should be zero.");
        assertTrue(dateArranger.AddTeam(team), "Team was not added successfully but should have been.");
        assertEquals(1, dateArranger.GetNumberOfTeams(), "Number of teams should be 1.");
    }
    @Test
    public void testAddTeamOneExistingTeamSameTeam() {
        DateArranger dateArranger = new DateArranger();
        Team team = new Team("Test Team 1");
        TeamMember team1Member1 = new TeamMember("Member 1");
        assertEquals(0, dateArranger.GetNumberOfTeams(), "Number of teams should be zero.");
        assertTrue(team.AddTeamMember(team1Member1), "Team member should have been added");
        assertTrue(dateArranger.AddTeam(team), "Team was not added successfully but should have been.");
        assertEquals(1, dateArranger.GetNumberOfTeams(), "Number of teams should be 1.");
        assertFalse(dateArranger.AddTeam(team), "Team was added but should not have been.");
        assertEquals(1, dateArranger.GetNumberOfTeams(), "Number of teams should be 1.");
    }

    @Test
    public void testAddTeamOneExistingTeamDifferentTeam() {
        DateArranger dateArranger = new DateArranger();
        Team team1 = new Team("Test Team 1");
        TeamMember team1Member1 = new TeamMember("Member 1");
        assertTrue(team1.AddTeamMember(team1Member1), "Team member should have been added");
        assertEquals(0, dateArranger.GetNumberOfTeams(), "Number of teams should be zero.");
        assertTrue(dateArranger.AddTeam(team1), "Team was not added successfully but should have been.");
        assertEquals(1, dateArranger.GetNumberOfTeams(), "Number of teams should be 1.");

        Team team2 = new Team("Test Team 2");
        TeamMember team2Member1 = new TeamMember("Member 1");
        assertTrue(team2.AddTeamMember(team2Member1), "Team member should have been added");
        assertTrue(dateArranger.AddTeam(team2), "Team was not added successfully but should have been.");
        assertEquals(2, dateArranger.GetNumberOfTeams(), "Number of teams should be 2.");
    }

    @Test
    public void testGetNumberOfTeams() {
        DateArranger dateArranger = new DateArranger();
        dateArranger.SetUp3TeamsWith9Members();
        assertEquals(3, dateArranger.GetNumberOfTeams(), "Number of teams not correct.");
    }

    @Test
    public void testGetNumberOfTeamMembersNoTeams() {
        DateArranger dateArranger = new DateArranger();
        assertEquals(0, dateArranger.GetNumberOfTeamMembers(), "Number of teams not correct.");
    }

    @Test
    public void testGetNumberOfTeamMembers() {
        DateArranger dateArranger = new DateArranger();
        dateArranger.SetUp3TeamsWith9Members();
        assertEquals(dateArranger.GetNumberOfTeamMembers(), 9, "Number of team members not correct.");
    }

    @Test
    public void testHasTeamNameTrueFalse() {
        DateArranger dateArranger = new DateArranger();
        String testTeamName = "Test Team Name 1";
        Team team = new Team(testTeamName);
        assertTrue(dateArranger.AddTeam(team), "Team was not added successfully but should have been.");
        assertTrue(dateArranger.HasTeamName(testTeamName), "Should return true for this team name.");
        assertFalse(dateArranger.HasTeamName("Some other name"), "Should false true for this team name.");
    }

    @Test
    public void testGetRandomOtherTeamNoTeams() {
        DateArranger dateArranger = new DateArranger();
        Team team1 = new Team("Test Team 1");
        assertEquals(0, dateArranger.GetNumberOfTeams(), "Number of teams should be 0.");
        Team randomTeam = dateArranger.GetRandomOtherTeam(team1);
        assertNull(randomTeam, "This should return null as there are no teams.");
    }

    @Test
    public void testGetRandomOtherTeamOneTeam() {
        DateArranger dateArranger = new DateArranger();
        Team team1 = new Team("Test Team 1");
        assertTrue(dateArranger.AddTeam(team1), "Team was not added successfully but should have been.");
        assertEquals(1, dateArranger.GetNumberOfTeams(), "Number of teams should be 1.");
        Team randomTeam = dateArranger.GetRandomOtherTeam(team1);
        assertNull(randomTeam, "This should return null as there is one team and we can't return ourselves.");
    }

    @Test
    public void testGetRandomOtherTeamTwoTeams() {
        DateArranger dateArranger = new DateArranger();
        Team team1 = new Team("Test Team 1");
        assertTrue(dateArranger.AddTeam(team1), "Team was not added successfully but should have been.");
        assertEquals(1, dateArranger.GetNumberOfTeams(), "Number of teams should be 1.");
        Team team2 = new Team("Test Team 2");
        assertTrue(dateArranger.AddTeam(team2), "Team was not added successfully but should have been.");
        assertEquals(2, dateArranger.GetNumberOfTeams(), "Number of teams should be 2.");
        Team randomTeam = dateArranger.GetRandomOtherTeam(team1);
        assertEquals(randomTeam, team2, "This should return true as we should return the other team.");
    }

    @Test
    public void testGetNumberOfDatersNoTeams() {
        DateArranger dateArranger = new DateArranger();
        int numberOfDaters = dateArranger.GetNumberOfDaters();
        assertEquals(numberOfDaters, 0, "Should be zero daters.");
    }

    @Test
    public void testGetNumberOfDatersOneTeamsNoMembers() {
        DateArranger dateArranger = new DateArranger();
        Team team1 = new Team("Test Team 1");
        assertTrue(dateArranger.AddTeam(team1), "Team was not added successfully but should have been.");
        assertEquals(1, dateArranger.GetNumberOfTeams(), "Number of teams should be 1.");
        int numberOfDaters = dateArranger.GetNumberOfDaters();
        assertEquals(numberOfDaters, 0, "Should be zero daters.");
    }

    @Test
    public void testGetNumberOfDatersOneTeamsOneMembers() {
        DateArranger dateArranger = new DateArranger();
        Team team1 = new Team("Test Team 1");
        TeamMember team1Member1 = new TeamMember("Member 1");
        assertTrue(team1.AddTeamMember(team1Member1), "Team member should have been added");
        assertTrue(dateArranger.AddTeam(team1), "Team was not added successfully but should have been.");
        assertEquals(1, dateArranger.GetNumberOfTeams(), "Number of teams should be 1.");
        int numberOfDaters = dateArranger.GetNumberOfDaters();
        assertEquals(numberOfDaters, 1, "Should be 1 dater.");
    }

    @Test
    public void testGetNumberOfDatersOneTeamsTwoMembers() {
        DateArranger dateArranger = new DateArranger();
        Team team1 = new Team("Test Team 1");
        TeamMember team1Member1 = new TeamMember("Member 1");
        TeamMember team1Member2 = new TeamMember("Member 2");
        assertTrue(team1.AddTeamMember(team1Member1), "Team member should have been added");
        assertTrue(team1.AddTeamMember(team1Member2), "Team member should have been added");
        assertTrue(dateArranger.AddTeam(team1), "Team was not added successfully but should have been.");
        assertEquals(1, dateArranger.GetNumberOfTeams(), "Number of teams should be 1.");
        int numberOfDaters = dateArranger.GetNumberOfDaters();
        assertEquals(numberOfDaters, 2, "Should be 2 daters.");
    }

    @Test
    public void testGetNumberOfDatersTwoTeamsOneMemberPerTeam() {
        DateArranger dateArranger = new DateArranger();
        Team team1 = new Team("Test Team 1");
        TeamMember team1Member1 = new TeamMember("Member 1");
        assertTrue(team1.AddTeamMember(team1Member1), "Team member should have been added");
        assertTrue(dateArranger.AddTeam(team1), "Team was not added successfully but should have been.");
        Team team2 = new Team("Test Team 2");
        TeamMember team2Member1 = new TeamMember("Member 1");
        assertTrue(team2.AddTeamMember(team2Member1), "Team member should have been added");
        assertTrue(dateArranger.AddTeam(team2), "Team was not added successfully but should have been.");
        assertEquals(2, dateArranger.GetNumberOfTeams(), "Number of teams should be 2.");
        int numberOfDaters = dateArranger.GetNumberOfDaters();
        assertEquals(numberOfDaters, 2, "Should be 2 daters.");
    }

    @Test
    public void testGetNumberOfDatersTwoTeamsMultipleMembersPerTeam() {
        DateArranger dateArranger = new DateArranger();
        Team team1 = new Team("Test Team 1");
        TeamMember team1Member1 = new TeamMember("Member 1");
        TeamMember team1Member2 = new TeamMember("Member 2");
        assertTrue(team1.AddTeamMember(team1Member1), "Team member should have been added");
        assertTrue(team1.AddTeamMember(team1Member2), "Team member should have been added");
        assertTrue(dateArranger.AddTeam(team1), "Team was not added successfully but should have been.");
        Team team2 = new Team("Test Team 2");
        TeamMember team2Member1 = new TeamMember("Member 1");
        TeamMember team2Member2 = new TeamMember("Member 2");
        assertTrue(team2.AddTeamMember(team1Member1), "Team member should have been added");
        assertTrue(team2.AddTeamMember(team1Member2), "Team member should have been added");
        assertTrue(dateArranger.AddTeam(team2), "Team was not added successfully but should have been.");
        assertEquals(2, dateArranger.GetNumberOfTeams(), "Number of teams should be 2.");
        int numberOfDaters = dateArranger.GetNumberOfDaters();
        assertEquals(numberOfDaters, 4, "Should be 4 daters.");
    }
}