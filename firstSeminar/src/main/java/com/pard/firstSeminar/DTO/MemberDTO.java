package com.pard.firstSeminar.DTO;

public class MemberDTO {
    private String myName;
    private String teamName;

    public MemberDTO(String myName, String teamName) {
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