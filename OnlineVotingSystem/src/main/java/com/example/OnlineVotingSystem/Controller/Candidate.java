package com.example.OnlineVotingSystem.Controller;

public class Candidate {
    private int candidateId;
    private String candidateName;
    private int votes;

    public Candidate(int candidateId, String candidateName ){
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.votes = 0;
    }

    public int getCandidateId() {
        return candidateId;
    }
    public String getCandidateName() {
        return candidateName;
    }
    public int getVotes() {
        return votes;
    }
    public void addVote() {
        this.votes++;
    }

    public void displayCandidates() {
        System.out.println("Candidate ID: " + candidateId);
        System.out.println("Candidate Name: " + candidateName);
        System.out.println("Total Votes: " + votes);

        System.out.println("--------------------------------------");
    }


}
