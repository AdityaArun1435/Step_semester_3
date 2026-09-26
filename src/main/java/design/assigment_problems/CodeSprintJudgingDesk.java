package design.assigment_problems;

import java.util.*;

interface ScoringRule {
    double computeScore(double idea, double execution, double presentation);
}

class InnovationScoring implements ScoringRule {
    public double computeScore(double idea, double execution, double presentation) {
        return idea * 0.5 + execution * 0.3 + presentation * 0.2;
    }
}

class OpenScoring implements ScoringRule {
    public double computeScore(double idea, double execution, double presentation) {
        return (idea + execution + presentation) / 3.0;
    }
}

class Student {
    private String name;

    public Student(String name) { this.name = name; }

    public String getName() { return name; }
}

class Project {
    private String title;
    private double idea, execution, presentation;
    private boolean scored = false;

    public Project(String title) { this.title = title; }

    public String getTitle() { return title; }

    public void recordScore(double idea, double execution, double presentation) {
        this.idea = idea;
        this.execution = execution;
        this.presentation = presentation;
        this.scored = true;
    }

    public boolean isScored() { return scored; }

    public double getIdea() { return idea; }

    public double getExecution() { return execution; }

    public double getPresentation() { return presentation; }
}

class Team {
    private String name;
    private List<Student> members;
    private ScoringRule scoringRule;
    private Project project;

    public Team(String name, List<Student> members, ScoringRule scoringRule) {
        this.name = name;
        this.members = members;
        this.scoringRule = scoringRule;
    }

    public String getName() { return name; }

    public List<Student> getMembers() { return members; }

    public void submit(Project project) { this.project = project; }

    public Project getProject() { return project; }

    public double finalScore() {
        return scoringRule.computeScore(project.getIdea(), project.getExecution(), project.getPresentation());
    }
}

enum HackathonState { OPEN, JUDGING, PUBLISHED }

class Hackathon {
    private List<Team> teams = new ArrayList<>();
    private Set<Student> registeredStudents = new HashSet<>();
    private HackathonState state = HackathonState.OPEN;

    public Team registerTeam(String name, List<Student> members, ScoringRule scoringRule) {
        if (members.size() < 2 || members.size() > 4) {
            System.out.println("Registration failed: A team must have 2 to 4 members.");
            return null;
        }
        for (Student s : members) {
            if (registeredStudents.contains(s)) {
                System.out.println("Registration failed: " + s.getName() + " is already on a team.");
                return null;
            }
        }
        Team team = new Team(name, members, scoringRule);
        teams.add(team);
        registeredStudents.addAll(members);
        System.out.println("Team " + name + " registered (" + members.size() + " members, " + trackName(scoringRule) + " track).");
        return team;
    }

    private String trackName(ScoringRule rule) {
        return rule instanceof InnovationScoring ? "Innovation" : "Open";
    }

    public void submitProject(Team team, String title) {
        if (team.getProject() != null) {
            System.out.println("Submission failed: " + team.getName() + " has already submitted a project.");
            return;
        }
        Project project = new Project(title);
        team.submit(project);
        System.out.println("Project '" + title + "' submitted by " + team.getName() + ".");
    }

    public void scoreProject(Project project, double idea, double execution, double presentation) {
        if (state == HackathonState.PUBLISHED) {
            System.out.println("Rescore rejected: Results have already been published.");
            return;
        }
        project.recordScore(idea, execution, presentation);
        state = HackathonState.JUDGING;
        System.out.println("Score recorded for '" + project.getTitle() + "'.");
    }

    public void publishResults() {
        state = HackathonState.PUBLISHED;
        for (Team team : teams) {
            if (team.getProject() != null && team.getProject().isScored()) {
                System.out.printf("Final score: %.2f%n", team.finalScore());
            }
        }
        System.out.println("Results published.");
    }
}

public class CodeSprintJudgingDesk {
    public static void main(String[] args) {
        Hackathon hackathon = new Hackathon();

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");
        Team byteBusters = hackathon.registerTeam("ByteBusters", Arrays.asList(asha, ravi, neha), new InnovationScoring());

        Student kiran = new Student("Kiran");
        hackathon.registerTeam("SoloCoder", Arrays.asList(kiran), new OpenScoring());

        hackathon.submitProject(byteBusters, "SmartAttend");
        hackathon.scoreProject(byteBusters.getProject(), 8, 7, 9);
        hackathon.publishResults();
        hackathon.scoreProject(byteBusters.getProject(), 10, 7, 9);
    }
}
