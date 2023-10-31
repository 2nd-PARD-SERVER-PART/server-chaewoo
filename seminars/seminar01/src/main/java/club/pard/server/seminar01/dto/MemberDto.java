package club.pard.server.seminar01.dto;

public class MemberDto {
    private String myName;
    private String teamName;

    public MemberDto(String myName, String teamName) {
        this.myName = myName;
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "myName='" + myName + '\'' +
                ", teamName='" + teamName + '\'' +
                '}';
    }

    public String getMyName() {
        return myName;
    }

    public String getTeamName() {
        return teamName;
    }
}