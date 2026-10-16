package com.example.OnlineVotingSystem.Controller;
import java.util.*;

public class OnlineVotingSystem {

    static HashMap<String, User> users = new HashMap<> ();
    static HashMap<Integer, Candidate> candidates = new HashMap<> ();
    static Scanner sc = new Scanner(System.in);

    public static void main (String [] args){
        candidates.put(101,new Candidate(101,"Arun"));
        candidates.put(102,new Candidate(102,"Furkhan"));
        candidates.put(103,new Candidate(103,"Shrishail"));
        candidates.put(104,new Candidate(104,"Vishnu"));
        candidates.put(105,new Candidate(105,"Naveen"));
        candidates.put(106,new Candidate(106,"Naveen kumar"));
        candidates.put(107,new Candidate(107,"Krishna kisore"));
        candidates.put(108,new Candidate(108,"Zerald"));

        int choice = 0;
        do{
            System.out.println("\n --------Online Voting System -------");
            System.out.println("1. Register User");
            System.out.println("2. Login and Vote");
            System.out.println("3. View Candidates ");
            System.out.println("4. View Total Votes");
            System.out.println("5. Display Winner");
            System.out.println("6. Display Runner-up");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            if(!sc.hasNextInt()){
                System.out.println("Invalid input! Enter a Number!");
                sc.nextLine();
                continue;
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginAndVote();
                    break;
                case 3:
                    viewCandidates();
                    break;
                case 4:
                    viewTotalVotes();
                    break;
                case 5:
                    displayWinner();
                    break;
                case 6:
                    displayRunnerUp();
                    break;
                case 7:
                    System.out.println("Thns for ur Voting....Welcome again for ur next Election");
                    break;
                default:
                    System.out.println("Invalid input! Enter a Number!");
            }
        }
        while(choice !=7);
        sc.close();
    }
    static void registerUser(){
        System.out.println("========User Registration ==========");
        System.out.print("Enter User ID: ");
        String userId = sc.nextLine().trim();

        if(userId.isEmpty()){
            System.out.println("User ID cannot be empty!");
            return;
        }

        if(users.containsKey(userId)){
            System.out.println("User ID is already in use!");
            return;
        }

        System.out.println("Enter Password: ");
        String password = sc.nextLine().trim();

        if(password.isEmpty()){
            System.out.println("Password cannot be empty!");
            return;
        }

        users.put(userId,new User(userId,password));
        System.out.println("User Registered Successfully!");
    }
    static void loginAndVote(){
        System.out.println("========User Login and Vote ==========");
        System.out.print("Enter User ID: ");
        String userId = sc.nextLine().trim();
        System.out.print("Enter Password: ");
        String password = sc.nextLine().trim();

        User user = users.get(userId);

        if(user == null || !user.getPassword().equals(password)){
            System.out.println("Invalid User ID or Password!");
            return;
        }

        if(user.hasVoted()){
            System.out.println("User is already voted!");
        }

        viewCandidates();
        System.out.println("Enter Candidate ID to Vote: :");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid Candidate ID!");
            sc.nextLine();
            return;
        }

        int candidateId = sc.nextInt();
        sc.nextLine();

        Candidate candidate = candidates.get(candidateId);

        if (candidate == null) {
            System.out.println("Candidate not found!");
            return;
        }

        candidate.addVote();
        user.setHasVoted(true);

        System.out.println("Vote cast successfully!");
    }
    static void viewCandidates() {

        System.out.println("\n----- CANDIDATE LIST -----");

        for (Candidate candidate : candidates.values()) {
            System.out.println(
                    "ID: " + candidate.getCandidateId()
                            + " | Name: " + candidate.getCandidateName()
            );
        }
    }

    static void viewTotalVotes() {

        System.out.println("\n----- TOTAL VOTES -----");

        int totalVotes = 0;

        for (Candidate candidate : candidates.values()) {
            candidate.displayCandidates();
            totalVotes += candidate.getVotes();
        }

        System.out.println("Total Votes Cast: " + totalVotes);
    }

    static void displayWinner() {

        if (candidates.isEmpty()) {
            System.out.println("No candidates available.");
            return;
        }

        Candidate winner = null;

        for (Candidate candidate : candidates.values()) {
            if (winner == null || candidate.getVotes() > winner.getVotes()) {
                winner = candidate;
            }
        }

        if (winner == null || winner.getVotes() == 0) {
            System.out.println("No votes have been cast yet.");
            return;
        }

        System.out.println("\n----- ELECTION WINNER -----");
        winner.displayCandidates();
    }

    static void displayRunnerUp() {

        Candidate winner = null;
        Candidate runnerUp = null;

        for (Candidate candidate : candidates.values()) {

            if (winner == null || candidate.getVotes() > winner.getVotes()) {
                runnerUp = winner;
                winner = candidate;
            } else if (runnerUp == null
                    || candidate.getVotes() > runnerUp.getVotes()) {
                runnerUp = candidate;
            }
        }

        if (winner == null || winner.getVotes() == 0) {
            System.out.println("No votes have been cast yet.");
            return;
        }

        if (runnerUp == null || runnerUp.getVotes() == 0) {
            System.out.println("No runner-up available yet.");
            return;
        }

        System.out.println("\n----- RUNNER-UP -----");
        runnerUp.displayCandidates();
    }
}

