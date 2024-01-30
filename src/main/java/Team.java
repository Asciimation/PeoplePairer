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

    public int GetNumberOfDatelessTeamMembers(){
        int numberWithoutDate = 0;
        for (TeamMember teamMember : teamMembers) {
            if (!teamMember.HasDate()){
                numberWithoutDate++;
            }
        }
        return numberWithoutDate;
    }

    public TeamMember GetRandomTeamMemberWithoutDate(){
        TeamMember teamMemberWithoutDate = null;
        int numberOfDatelessTeamMembers = GetNumberOfDatelessTeamMembers();
        if (numberOfDatelessTeamMembers == 0) {
            return teamMemberWithoutDate;
        } else {
            Random rand = new Random();
            int randomIndex = 0;
            TeamMember teamMember = null;
            while( teamMemberWithoutDate == null ) {
                randomIndex = rand.nextInt(teamMembers.size());
                teamMember = teamMembers.get(randomIndex);
                if (!teamMember.HasDate()){
                    teamMemberWithoutDate = teamMember;
                }
            }
        }
        return teamMemberWithoutDate;
    }
}
