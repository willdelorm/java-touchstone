public class Problem {
    // Attribute to hold the grade
    private int grade;

    // Constructor that takes the grade as a parameter
    public Problem(int grade) {
        this.grade = grade;
    }

    // Get method for the grade
    public int getGrade() {
        return this.grade;
    }

    // String method to display grade
    public String toString() {
        return "V" + grade;
    }
}