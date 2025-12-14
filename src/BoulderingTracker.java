import java.time.LocalDate;
import java.util.Scanner;

public class BoulderingTracker {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Bouldering Tracker!");

        // Main menu loop
        while (true) {
            System.out.print("Enter a command ('help' to see options): ");
            String inputRead = input.next().trim();

            if (inputRead.equals("quit")) {
                break;
            } else if (inputRead.equals("help")) {
                System.out.println("=== Commands ===");
                System.out.println("log - Log new session");
                System.out.println("view - View statistics");
                System.out.println("quit - Exits the program");
            } else if (inputRead.equals("log")) {
                logSession();
            } else if (inputRead.equals("view")) {
                view();
            } else {
                System.out.println("Invalid input.");
            }
        }

        input.close();
    }

    private static void printSession() {
        // Function printSession()
        // Calculate grade total
        // Calculate grade average
        // Calculate highest grade

        // Print date
        // Print calculated values
        // Print list of grades
    }

    // Logs a new session
    private static void logSession() {
        Scanner input = new Scanner(System.in);

        System.out.println("\n=== Log New Session ===");

        // Get session date
        System.out.print("Enter session date (YYYY-MM-DD) or press Enter for today: ");
        String inputDate = input.nextLine();

        LocalDate sessionDate;
        if (inputDate.isEmpty()) {
            sessionDate = LocalDate.now();
        } else {
            sessionDate = LocalDate.parse(inputDate);
        }

        // Get session duration
        System.out.print("Enter session duration (minutes): ");
        int inputDuration = input.nextInt();

        // Create new session
        ClimbingSession session = new ClimbingSession(sessionDate, inputDuration);

        while (true) {
            System.out.print("Enter grade for problem (number only) or -1 to finish: ");
            int inputGrade = input.nextInt();

            if (inputGrade == -1) {
                break;
            }
            // Else if grade is a number
            // Add number to list
            // Else
            // Ask for valid input
        }
        
        // Save data to file

        // Call function printSession() with data
        // Ask user to press Enter to return to menu

        input.close();
    }

    private static void view() {
        // Function view()
        // Read data from file
        // Set sessionCount = length of sessions list
        // Set totalClimbs = 0
        // Set hardestV = 0

        // Loop thru each session
        // Set totalV = 0
        // Set climbCount = list length
        // Add climbCount to totalClimbs
        // Loop thru list
        // Add grade to totalV
        // If grade > hardestV
        // hardestV = grade
        // Set avgV = totalV / climbCount

        // Print “Statistics”
        // Print “Total sessions logged” and “Total climbs logged”
        // Print “Hardest grade sent”
        // Ask user to press Enter to return to menu
    }

}
