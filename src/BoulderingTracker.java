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
    private static ArrayList<ClimbingSession> sessions = new ArrayList<ClimbingSession>();

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Bouldering Tracker!");

        // Load existing sessions
        if (output.exists()) {
            try {
                List<String> sessionsData = Files.readAllLines(output.toPath());
                for (String data : sessionsData) {
                    String[] dataList = data.split(",");
                    String date = dataList[0];
                    String duration = dataList[1];
                    String[] grades = Arrays.copyOfRange(dataList, 2, dataList.length);

                    ClimbingSession tempSession = new ClimbingSession(LocalDate.parse(date),
                            Integer.parseInt(duration));

                    for (String grade : grades) {
                        tempSession.addProblem(new Problem(Integer.parseInt(grade)));
                    }

                    sessions.add(tempSession);
                }

            } catch (IOException ex) {
                System.out.println("Error reading file: " + ex.getMessage());
            }
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
            sessions.add(session);
        } catch (IOException ex) {
            System.out.println("Error writing to file: " + ex.getMessage());
        }

        // Print session summary
        System.out.println(session.printSummary());
    }

    // Print climber statistics
    private static void view() {
        // Checks for sessions before continuing
        if (sessions.size() == 0) {
            System.out.println("Log a session first.");
            return;
        }

        int totalTime = 0, totalSends = 0, totalV = 0, maxV = 0;
        ClimbingSession longestSession = null;

        // Loop through each session and update statistics
        for (ClimbingSession session : sessions) {
            int duration = session.getDuration();
            totalTime += session.getDuration();
            if (longestSession == null) {
                longestSession = session;
            } else if (duration > longestSession.getDuration()) {
                longestSession = session;
            }

            totalSends += session.getProblemCount();

            for (Problem problem : session.getProblems()) {
                int grade = problem.getGrade();
                if (grade > maxV)
                    maxV = grade;
                totalV += grade;
            }
        }

        // Print all the statistics
        System.out.println("=== Statistics ===");

        System.out.println("\n-- Totals --");
        System.out.println("Sessions logged: " + sessions.size());
        System.out.println("Time climbing: " + totalTime + " minutes");
        System.out.println("Boulders sent: " + totalSends);
        System.out.println("V points: " + totalV);

        System.out.println("\n-- Averages --");
        System.out.println("Session length: " + totalTime / sessions.size() + " minutes");
        System.out.println("Sends per session: " + totalSends / sessions.size());
        System.out.println("Average V: " + totalV / totalSends);

        System.out.println("\n-- Bests --");
        System.out.println("Hardest grade sent: " + maxV);
        System.out.println(
                "Longest session: " + longestSession.getDate() + " for " + longestSession.getDuration() + " minutes");
    }
}
