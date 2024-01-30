public class TeamMember {
    private String teamMemberName = "";
    private TeamMember teamMemberDate = null;

    public TeamMember (String name){
        teamMemberName = name;
    }

    public String GetName(){
        return teamMemberName;
    }

    public TeamMember GetDate(){
        return teamMemberDate;
    }

    public Boolean HasDate(){
        if (teamMemberDate != null){
            return true;
        } else {
            return false;
        }
    }

    public void SetDate(TeamMember date){
        teamMemberDate = date;
    }
}
