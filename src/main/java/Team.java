import java.util.ArrayList;
import java.util.Random;

public class Team {
    private String teamName = "";

    private final ArrayList<TeamMember> teamMembers = new ArrayList<TeamMember>();

    public Team (String name){
        teamName = name;
    }

    public String GetTeamName(){
        return teamName;
    }

    public Boolean HasTeamMemberName(String teamMemberName){
        for (TeamMember teamMember : teamMembers){
            String name = teamMember.GetName();
            if (teamMemberName.equals(name)){
                return true;
            }
        }
        return false;
    }

    public Boolean AddTeamMember(TeamMember newTeamMember){
        // Team members should be unique based on name.
        for (TeamMember teamMember : teamMembers) {
            if ((teamMember.GetName()).equals(newTeamMember.GetName())) {
                return false;
            }
        }
        teamMembers.add(newTeamMember);
        return true;
    }

    public int GetTeamSize(){
        return teamMembers.size();
    }

    public int GetNumberOfUnpairedTeamMembers(){
        int numberUnpaired = 0;
        for (TeamMember teamMember : teamMembers) {
            if (!teamMember.IsPaired()){
                numberUnpaired++;
            }
        }
        return numberUnpaired;
    }

    public TeamMember GetRandomUnpairedTeamMember(){
        TeamMember UnpairedTeamMember = null;
        int numberOfUnpairedTeamMembers = GetNumberOfUnpairedTeamMembers();
        if (numberOfUnpairedTeamMembers == 0) {
            return UnpairedTeamMember;
        } else {
            Random rand = new Random();
            int randomIndex = 0;
            TeamMember teamMember = null;
            while( UnpairedTeamMember == null ) {
                randomIndex = rand.nextInt(teamMembers.size());
                teamMember = teamMembers.get(randomIndex);
                if (!teamMember.IsPaired()){
                    UnpairedTeamMember = teamMember;
                }
            }
        }
        return UnpairedTeamMember;
    }
}
