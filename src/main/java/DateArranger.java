import java.util.ArrayList;
import java.util.Random;

public class DateArranger {
    private final ArrayList<Team> myTeams = new ArrayList<Team>();
    private final ArrayList<DatePair> dates = new ArrayList<DatePair>();

    public void SetUp3TeamsWith9Members(){

        Team team1 = new Team("Team 1");
        TeamMember team1Member1 = new TeamMember("Team1 Member1");
        team1.AddTeamMember(team1Member1);
        TeamMember team1Member2 = new TeamMember("Team1 Member2");
        team1.AddTeamMember(team1Member2);
        TeamMember team1Member3 = new TeamMember("Team1 Member3");
        team1.AddTeamMember(team1Member3);
        myTeams.add(team1);

        Team team2 = new Team("Team 2");
        TeamMember team2Member1 = new TeamMember("Team2 Member1");
        team2.AddTeamMember(team2Member1);
        TeamMember team2Member2 = new TeamMember("Team2 Member2");
        team2.AddTeamMember(team2Member2);
        TeamMember team2Member3 = new TeamMember("Team2 Member3");
        team2.AddTeamMember(team2Member3);
        myTeams.add(team2);

        Team team3 = new Team("Team 3");
        TeamMember team3Member1 = new TeamMember("Team3 Member1");
        team3.AddTeamMember(team3Member1);
        TeamMember team3Member2 = new TeamMember("Team3 Member2");
        team3.AddTeamMember(team3Member2);
        TeamMember team3Member3 = new TeamMember("Team3 Member3");
        team3.AddTeamMember(team3Member3);
        myTeams.add(team3);
    }

    public void AddJoker() {
        // This team member is needed to make up an even number, so we don't end up with an unpaired person.
        Team jokerTeam = new Team("Joker");
        TeamMember joker = new TeamMember("Joker");
        jokerTeam.AddTeamMember(joker);
        myTeams.add(jokerTeam);
    }

    public int GetNumberOfDaters(){
        int numberOfDaters = 0;
        for (Team team : myTeams){
            numberOfDaters = numberOfDaters + team.GetTeamSize();
        }
        return numberOfDaters;
    }

    public void ArrangeDates(){
        System.out.println("Creating dates...");
        int numberOfDaters = GetNumberOfDaters();
        if (numberOfDaters % 2 != 0){
            AddJoker();
            numberOfDaters++;
        }
        int numberOfPairs = numberOfDaters / 2;
        System.out.println("There are " + numberOfDaters + " daters so " + numberOfPairs + " dates.");
        for ( int i = 0; i < numberOfPairs; i++)
        {
            Team firstTeam = null;
            TeamMember teamMember1 = null;
            Team secondTeam = null;
            TeamMember teamMember2 = null;

            // Choose a random first team.
            while (firstTeam == null) {
                firstTeam = GetRandomOtherTeam(null);
                if (firstTeam.GetNumberOfDatelessTeamMembers() == 0) {
                    firstTeam = null;
                }
            }
            // Choose a random second team.
            while (secondTeam == null) {
                secondTeam = GetRandomOtherTeam(firstTeam);
                if (secondTeam.GetNumberOfDatelessTeamMembers() == 0) {
                    secondTeam = null;
                }
            }
            // Chose a random team member from each team.
            while (teamMember1 == null) {
                teamMember1 = firstTeam.GetRandomTeamMemberWithoutDate();
            }
            while (teamMember2 == null) {
                teamMember2 = secondTeam.GetRandomTeamMemberWithoutDate();
            }

            // Set dates.
            System.out.println("Pairing: " + firstTeam.GetTeamName() + ":" + teamMember1.GetName() + " with " + secondTeam.GetTeamName() + ":" + teamMember2.GetName());
            teamMember1.SetDate(teamMember2);
            teamMember2.SetDate(teamMember1);
            DatePair datePair = new DatePair(teamMember1.GetName(), teamMember2.GetName());
            dates.add(datePair);

        }
    }

    public void OutputDates(){
        System.out.println("Dates...");
        for (DatePair datePair : dates){
            System.out.println(datePair.getFirstPerson() + "/" + datePair.getSecondPerson());
        }
    }

    public boolean AddTeam (Team team) {
        // Teams should be unique.
        if ( myTeams.contains(team)) {
            return false;
        } else {
            myTeams.add(team);
            return true;
        }
    }

    public int GetNumberOfTeams(){
        return myTeams.size();
    }

    public Boolean HasTeamName(String teamName){
        for (Team team : myTeams){
            if (team.GetTeamName().equals(teamName)){
                return true;
            }
        }
        return false;
    }

    public int GetNumberOfTeamMembers(){
        int numberOfTeamMembers = 0;
        for (Team team : myTeams){
            numberOfTeamMembers = numberOfTeamMembers + team.GetTeamSize();
        }
        return numberOfTeamMembers;
    }

    public Team GetRandomOtherTeam(Team myTeam){
        Team returnedTeam = null;
        int randomIndex = 0;
        if ( myTeams.size() > 1 ) {
            Random rand = new Random();
            while (returnedTeam == null) {
                randomIndex = rand.nextInt(myTeams.size());
                returnedTeam = myTeams.get(randomIndex);
                if (myTeam == returnedTeam) {
                    // We cannot return ourselves.
                    returnedTeam = null;
                }
            }
        }
        return returnedTeam;
    }
}
