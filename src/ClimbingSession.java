import java.time.LocalDate;
import java.util.ArrayList;

public class ClimbingSession {
    // Private attributes
    private LocalDate date;
    private int duration;
    private ArrayList<Problem> problems;


    // Constructor takes the date and duration parameters
    public ClimbingSession(LocalDate date, int duration) {
        this.date = date;
        this.duration = duration;
        this.problems = new ArrayList<Problem>();
    }
    
    // Method to add a problem
    public void addProblem(Problem problem) {
        problems.add(problem);
    }
    
    // Get date method
    public LocalDate getDate() {
        return this.date;
    }

    // Get duration method
    public int getDuration() {
        return this.duration;
    }

    // Get list of problems method
    public ArrayList<Problem> getProblems() {
        return this.problems;
    }

    // Get problem count method
    public int getProblemCount() {
        return this.problems.size();
    }

    // String method
    public String toString() {
        String output = "";
        output += "Session Summary:\n";
        output += "====================\n";
        output += "Date: " + this.date.toString() + "\n";
        output += "Duration: " + this.duration + "\n\n";
        output += "Climbs Sent\n";

        // Loop through problems and add each to the output
        for (Problem problem : problems) {
            output += "V" + problem.getGrade() + "\n";
        }

        output += "====================\n";
        output += "Total problems sent: " + getProblemCount();

        return output;
    }
}