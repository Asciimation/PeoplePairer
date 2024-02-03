public class TeamMember {
    private String teamMemberName = "";
    private TeamMember teamMemberPair = null;

    public TeamMember (String name){
        teamMemberName = name;
    }

    public String GetName(){
        return teamMemberName;
    }

    public TeamMember GetDate(){
        return teamMemberPair;
    }

    public Boolean IsPaired(){
        if (teamMemberPair != null){
            return true;
        } else {
            return false;
        }
    }

    public void SetPair(TeamMember pair){
        teamMemberPair = pair;
    }
}
