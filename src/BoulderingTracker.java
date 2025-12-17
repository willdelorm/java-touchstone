import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BoulderingTracker {
    private static Scanner input = new Scanner(System.in);
    private static File output = new File("output.txt");

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Bouldering Tracker!");

        // Load existing sessions
        ArrayList<ClimbingSession> sessions = new ArrayList<ClimbingSession>();
        try {
            List<String> sessionsData = Files.readAllLines(output.toPath());
            for (String data : sessionsData) {
                String[] dataList = data.split(",");
                String date = dataList[0];
                String duration = dataList[1];
                String[] grades = Arrays.copyOfRange(dataList, 2, dataList.length);

                ClimbingSession tempSession = new ClimbingSession(LocalDate.parse(date), Integer.parseInt(duration));

                for (String grade : grades) {
                    tempSession.addProblem(new Problem(Integer.parseInt(grade)));
                }

                sessions.add(tempSession);
            }
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }

        // Main menu loop
        while (true) {
            System.out.print("Enter a command ('help' to see options): ");
            String inputRead = input.nextLine();

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

    // Logs a new session
    private static void logSession() {
        System.out.println("\n=== Log New Session ===");

        // Get session date
        LocalDate sessionDate = null;
        while (sessionDate == null) {
            System.out.print("Enter session date (YYYY-MM-DD) or press Enter for today: ");
            try {
                String inputDate = input.nextLine();

                if (inputDate.isEmpty()) {
                    sessionDate = LocalDate.now();
                } else {
                    sessionDate = LocalDate.parse(inputDate);
                }
            } catch (DateTimeParseException ex) {
                System.out.println("Wrong date format.");
            }
        }

        // Get session duration
        int sessionDuration = -1;
        while (sessionDuration == -1) {
            System.out.print("Enter session duration (minutes): ");
            try {
                String inputDuration = input.nextLine();
                sessionDuration = Integer.parseInt(inputDuration);
            } catch (NumberFormatException ex) {
                System.out.println("Not an integer.");
            }
        }

        // Create new session
        ClimbingSession session = new ClimbingSession(sessionDate, sessionDuration);

        // Add problem grades to session
        while (true) {
            try {
                System.out.print("Enter V grade for problem (number only) or -1 to finish: ");
                String inputGrade = input.nextLine();
                int problemGrade = Integer.parseInt(inputGrade);

                if (problemGrade == -1) {
                    break;
                } else if (problemGrade < 0) {
                    System.out.println("Invalid number. Must be 0 or higher.");
                } else {
                    Problem newProblem = new Problem(problemGrade);
                    session.addProblem(newProblem);
                }
            } catch (NumberFormatException ex) {
                System.out.println("Not an integer.");
            }
        }

        // Write session to file
        try {
            Files.writeString(output.toPath(), session.toString(), StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException ex) {
            System.out.println("Error writing to file: " + ex.getMessage());
        }

        // Print session summary
        System.out.println(session.printSummary());
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
