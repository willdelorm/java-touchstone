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

    // Display session summary
    public String printSummary() {
        String output = "Session Summary:\n";
        output += "====================\n";
        output += "Date: " + this.getDate().toString() + "\n";
        output += "Duration: " + this.getDuration() + " minutes\n\n";
        output += "List of problems sent:\n";

        // Loop through problems and add each to the output
        for (Problem problem : this.getProblems()) {
            output += "V" + problem.getGrade() + ", ";
        }

        output += "====================\n";
        output += "Total problems sent: " + this.getProblemCount() + "\n";

        
        // Calculate grade total
        // Calculate grade average
        // Calculate highest grade
        
        // Print date
        // Print calculated values
        // Print list of grades
        return output;
    }

    // String method for file save
    public String toString() {
        String output = this.date.toString() + "," + this.duration;

        // Loop through problems and add each to the output
        for (Problem problem : problems) {
            output += "," + problem.getGrade();
        }

        output += "\n";

        return output;
    }
}