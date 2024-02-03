import java.util.ArrayList;
import java.util.Random;

public class PairArranger {
    private final ArrayList<Team> myTeams = new ArrayList<Team>();
    private final ArrayList<Pair> pairs = new ArrayList<Pair>();

    static final int NUMBER_OF_RETRIES = 10;

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

    public int GetNumberOfPeople(){
        int numberOfPairs = 0;
        for (Team team : myTeams){
            numberOfPairs = numberOfPairs + team.GetTeamSize();
        }
        return numberOfPairs;
    }

    public void ArrangePairs(){
        System.out.println("Creating pairs...");
        int numberOfPeople = GetNumberOfPeople();
        if (numberOfPeople % 2 != 0){
            AddJoker();
            numberOfPeople++;
        }
        int numberOfPairs = numberOfPeople / 2;
        System.out.println("There are " + numberOfPeople + " people so " + numberOfPairs + " pairs.");
        for ( int i = 0; i < numberOfPairs; i++)
        {
            Boolean pairingFailed = false;
            int numberOfAttempts = 0;

            Team firstTeam = null;
            TeamMember teamMember1 = null;
            Team secondTeam = null;
            TeamMember teamMember2 = null;

            // Choose a random first team.
            while (firstTeam == null) {
                firstTeam = GetRandomOtherTeam(null);
                if (firstTeam.GetNumberOfUnpairedTeamMembers() == 0) {
                    firstTeam = null;
                }
            }
            // Choose a random second team.
            numberOfAttempts = 0;
            while (secondTeam == null) {
                if (numberOfAttempts > NUMBER_OF_RETRIES){
                    System.out.println("Unable to find pair after " + NUMBER_OF_RETRIES + " attempts.");
                    pairingFailed = true;
                    break;
                }
                secondTeam = GetRandomOtherTeam(firstTeam);
                if (secondTeam.GetNumberOfUnpairedTeamMembers() == 0) {
                    secondTeam = null;
                }
                numberOfAttempts++;
            }
            if ( pairingFailed ) {
                ClearPairs();
                pairingFailed = false;
            } else {
                // Chose a random team member from each team.
                while (teamMember1 == null) {
                    teamMember1 = firstTeam.GetRandomUnpairedTeamMember();
                }
                while (teamMember2 == null) {
                    teamMember2 = secondTeam.GetRandomUnpairedTeamMember();
                }

                System.out.println("Pairing: " + firstTeam.GetTeamName() + ":" + teamMember1.GetName() + " with " + secondTeam.GetTeamName() + ":" + teamMember2.GetName());
                teamMember1.SetPair(teamMember2);
                teamMember2.SetPair(teamMember1);
                Pair pair = new Pair(teamMember1.GetName(), teamMember2.GetName());
                pairs.add(pair);
            }
        }
    }

    public void OutputPairs(){
        System.out.println("Pairs...");
        for (Pair pair : pairs){
            System.out.println(pair.getFirstPerson() + "/" + pair.getSecondPerson());
        }
    }

    public void ClearPairs(){
        System.out.println("Clearing all pairs...");
        pairs.clear();
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
