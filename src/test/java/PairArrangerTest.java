import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PairArrangerTest {

    @Test
    public void testGetNumberOfTeamsNoTeamsCreated() {
        PairArranger pairArranger = new PairArranger();
        assertEquals(0, pairArranger.GetNumberOfTeams(), "Number of teams not correct.");
    }

    @Test
    public void testAddTeamNoExistingTeams() {
        PairArranger pairArranger = new PairArranger();
        Team team = new Team("Test Team 1");
        TeamMember team1Member1 = new TeamMember("Member 1");
        assertTrue(team.AddTeamMember(team1Member1), "Team member should have been added");
        assertEquals(0, pairArranger.GetNumberOfTeams(), "Number of teams should be zero.");
        assertTrue(pairArranger.AddTeam(team), "Team was not added successfully but should have been.");
        assertEquals(1, pairArranger.GetNumberOfTeams(), "Number of teams should be 1.");
    }
    @Test
    public void testAddTeamOneExistingTeamSameTeam() {
        PairArranger pairArranger = new PairArranger();
        Team team = new Team("Test Team 1");
        TeamMember team1Member1 = new TeamMember("Member 1");
        assertEquals(0, pairArranger.GetNumberOfTeams(), "Number of teams should be zero.");
        assertTrue(team.AddTeamMember(team1Member1), "Team member should have been added");
        assertTrue(pairArranger.AddTeam(team), "Team was not added successfully but should have been.");
        assertEquals(1, pairArranger.GetNumberOfTeams(), "Number of teams should be 1.");
        assertFalse(pairArranger.AddTeam(team), "Team was added but should not have been.");
        assertEquals(1, pairArranger.GetNumberOfTeams(), "Number of teams should be 1.");
    }

    @Test
    public void testAddTeamOneExistingTeamDifferentTeam() {
        PairArranger pairArranger = new PairArranger();
        Team team1 = new Team("Test Team 1");
        TeamMember team1Member1 = new TeamMember("Member 1");
        assertTrue(team1.AddTeamMember(team1Member1), "Team member should have been added");
        assertEquals(0, pairArranger.GetNumberOfTeams(), "Number of teams should be zero.");
        assertTrue(pairArranger.AddTeam(team1), "Team was not added successfully but should have been.");
        assertEquals(1, pairArranger.GetNumberOfTeams(), "Number of teams should be 1.");

        Team team2 = new Team("Test Team 2");
        TeamMember team2Member1 = new TeamMember("Member 1");
        assertTrue(team2.AddTeamMember(team2Member1), "Team member should have been added");
        assertTrue(pairArranger.AddTeam(team2), "Team was not added successfully but should have been.");
        assertEquals(2, pairArranger.GetNumberOfTeams(), "Number of teams should be 2.");
    }

    @Test
    public void testGetNumberOfTeams() {
        PairArranger pairArranger = new PairArranger();
        pairArranger.SetUp3TeamsWith9Members();
        assertEquals(3, pairArranger.GetNumberOfTeams(), "Number of teams not correct.");
    }

    @Test
    public void testGetNumberOfTeamMembersNoTeams() {
        PairArranger pairArranger = new PairArranger();
        assertEquals(0, pairArranger.GetNumberOfTeamMembers(), "Number of teams not correct.");
    }

    @Test
    public void testGetNumberOfTeamMembers() {
        PairArranger pairArranger = new PairArranger();
        pairArranger.SetUp3TeamsWith9Members();
        assertEquals(pairArranger.GetNumberOfTeamMembers(), 9, "Number of team members not correct.");
    }

    @Test
    public void testHasTeamNameTrueFalse() {
        PairArranger pairArranger = new PairArranger();
        String testTeamName = "Test Team Name 1";
        Team team = new Team(testTeamName);
        assertTrue(pairArranger.AddTeam(team), "Team was not added successfully but should have been.");
        assertTrue(pairArranger.HasTeamName(testTeamName), "Should return true for this team name.");
        assertFalse(pairArranger.HasTeamName("Some other name"), "Should false true for this team name.");
    }

    @Test
    public void testGetRandomOtherTeamNoTeams() {
        PairArranger pairArranger = new PairArranger();
        Team team1 = new Team("Test Team 1");
        assertEquals(0, pairArranger.GetNumberOfTeams(), "Number of teams should be 0.");
        Team randomTeam = pairArranger.GetRandomOtherTeam(team1);
        assertNull(randomTeam, "This should return null as there are no teams.");
    }

    @Test
    public void testGetRandomOtherTeamOneTeam() {
        PairArranger pairArranger = new PairArranger();
        Team team1 = new Team("Test Team 1");
        assertTrue(pairArranger.AddTeam(team1), "Team was not added successfully but should have been.");
        assertEquals(1, pairArranger.GetNumberOfTeams(), "Number of teams should be 1.");
        Team randomTeam = pairArranger.GetRandomOtherTeam(team1);
        assertNull(randomTeam, "This should return null as there is one team and we can't return ourselves.");
    }

    @Test
    public void testGetRandomOtherTeamTwoTeams() {
        PairArranger pairArranger = new PairArranger();
        Team team1 = new Team("Test Team 1");
        assertTrue(pairArranger.AddTeam(team1), "Team was not added successfully but should have been.");
        assertEquals(1, pairArranger.GetNumberOfTeams(), "Number of teams should be 1.");
        Team team2 = new Team("Test Team 2");
        assertTrue(pairArranger.AddTeam(team2), "Team was not added successfully but should have been.");
        assertEquals(2, pairArranger.GetNumberOfTeams(), "Number of teams should be 2.");
        Team randomTeam = pairArranger.GetRandomOtherTeam(team1);
        assertEquals(randomTeam, team2, "This should return true as we should return the other team.");
    }

    @Test
    public void testGetNumberOfPeopleNoTeams() {
        PairArranger pairArranger = new PairArranger();
        int getNumberOfPeople = pairArranger.GetNumberOfPeople();
        assertEquals(getNumberOfPeople, 0, "Should be zero people.");
    }

    @Test
    public void testGetNumberOfPeopleOneTeamsNoMembers() {
        PairArranger pairArranger = new PairArranger();
        Team team1 = new Team("Test Team 1");
        assertTrue(pairArranger.AddTeam(team1), "Team was not added successfully but should have been.");
        assertEquals(1, pairArranger.GetNumberOfTeams(), "Number of teams should be 1.");
        int numberOfPeople = pairArranger.GetNumberOfPeople();
        assertEquals(numberOfPeople, 0, "Should be zero people.");
    }

    @Test
    public void testGetNumberOfPeopleOneTeamsOneMembers() {
        PairArranger pairArranger = new PairArranger();
        Team team1 = new Team("Test Team 1");
        TeamMember team1Member1 = new TeamMember("Member 1");
        assertTrue(team1.AddTeamMember(team1Member1), "Team member should have been added");
        assertTrue(pairArranger.AddTeam(team1), "Team was not added successfully but should have been.");
        assertEquals(1, pairArranger.GetNumberOfTeams(), "Number of teams should be 1.");
        int numberOfPeople = pairArranger.GetNumberOfPeople();
        assertEquals(numberOfPeople, 1, "Should be 1 person.");
    }

    @Test
    public void testGetNumberOfPeopleOneTeamsTwoMembers() {
        PairArranger pairArranger = new PairArranger();
        Team team1 = new Team("Test Team 1");
        TeamMember team1Member1 = new TeamMember("Member 1");
        TeamMember team1Member2 = new TeamMember("Member 2");
        assertTrue(team1.AddTeamMember(team1Member1), "Team member should have been added");
        assertTrue(team1.AddTeamMember(team1Member2), "Team member should have been added");
        assertTrue(pairArranger.AddTeam(team1), "Team was not added successfully but should have been.");
        assertEquals(1, pairArranger.GetNumberOfTeams(), "Number of teams should be 1.");
        int numberOfPeople = pairArranger.GetNumberOfPeople();
        assertEquals(numberOfPeople, 2, "Should be 2 people.");
    }

    @Test
    public void testGetNumberOfPeopleTwoTeamsOneMemberPerTeam() {
        PairArranger pairArranger = new PairArranger();
        Team team1 = new Team("Test Team 1");
        TeamMember team1Member1 = new TeamMember("Member 1");
        assertTrue(team1.AddTeamMember(team1Member1), "Team member should have been added");
        assertTrue(pairArranger.AddTeam(team1), "Team was not added successfully but should have been.");
        Team team2 = new Team("Test Team 2");
        TeamMember team2Member1 = new TeamMember("Member 1");
        assertTrue(team2.AddTeamMember(team2Member1), "Team member should have been added");
        assertTrue(pairArranger.AddTeam(team2), "Team was not added successfully but should have been.");
        assertEquals(2, pairArranger.GetNumberOfTeams(), "Number of teams should be 2.");
        int numberOfPeople = pairArranger.GetNumberOfPeople();
        assertEquals(numberOfPeople, 2, "Should be 2 people.");
    }

    @Test
    public void testGetNumberOfPeopleTwoTeamsMultipleMembersPerTeam() {
        PairArranger pairArranger = new PairArranger();
        Team team1 = new Team("Test Team 1");
        TeamMember team1Member1 = new TeamMember("Member 1");
        TeamMember team1Member2 = new TeamMember("Member 2");
        assertTrue(team1.AddTeamMember(team1Member1), "Team member should have been added");
        assertTrue(team1.AddTeamMember(team1Member2), "Team member should have been added");
        assertTrue(pairArranger.AddTeam(team1), "Team was not added successfully but should have been.");
        Team team2 = new Team("Test Team 2");
        TeamMember team2Member1 = new TeamMember("Member 1");
        TeamMember team2Member2 = new TeamMember("Member 2");
        assertTrue(team2.AddTeamMember(team1Member1), "Team member should have been added");
        assertTrue(team2.AddTeamMember(team1Member2), "Team member should have been added");
        assertTrue(pairArranger.AddTeam(team2), "Team was not added successfully but should have been.");
        assertEquals(2, pairArranger.GetNumberOfTeams(), "Number of teams should be 2.");
        int numberOfPeople = pairArranger.GetNumberOfPeople();
        assertEquals(numberOfPeople, 4, "Should be 4 people.");
    }
}