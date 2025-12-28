package util;

public class IdGenerator {
    // Static counters for ID generation
    private static int studentIdCounter = 1000;
    private static int courseIdCounter = 2000;
    private static int enrollmentIdCounter = 3000;
    private static int trainerIdCounter = 4000;

    // Static methods for generating unique IDs
    public static String getNextStudentId() {
        return "STU" + (studentIdCounter++);
    }

    public static String getNextCourseId() {
        return "CRS" + (courseIdCounter++);
    }

    public static String getNextEnrollmentId() {
        return "ENR" + (enrollmentIdCounter++);
    }

    public static String getNextTrainerId() {
        return "TRN" + (trainerIdCounter++);
    }

    // Reset counters (for testing)
    public static void resetCounters() {
        studentIdCounter = 1000;
        courseIdCounter = 2000;
        enrollmentIdCounter = 3000;
        trainerIdCounter = 4000;
    }
}
