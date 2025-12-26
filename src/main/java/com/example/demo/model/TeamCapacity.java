package com.example.demo.model;

public class TeamCapacity {

    private Long id;
    private String teamName;
    private int totalMembers;
    private int availableMembers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
    }

    public int getAvailableMembers() {
        return availableMembers;
    }

    public void setAvailableMembers(int availableMembers) {
        this.availableMembers = availableMembers;
    }
}
