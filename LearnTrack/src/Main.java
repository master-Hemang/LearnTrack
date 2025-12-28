//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import entity.Student;
import entity.Course;
import entity.Enrollment;
import exception.EntityNotFoundException;
import service.StudentService;
import service.CourseService;
import service.EnrollmentService;
import util.InputValidator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static StudentService studentService = new StudentService();
    private static CourseService courseService = new CourseService();
    private static EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        // Add some sample data
        initializeSampleData();

        System.out.println("====================================");
        System.out.println("   LearnTrack Management System     ");
        System.out.println("====================================");

        while (running) {
            try {
                displayMainMenu();
                int choice = getIntInput("Enter your choice: ");

                switch (choice) {
                    case 1:
                        studentManagementMenu();
                        break;
                    case 2:
                        courseManagementMenu();
                        break;
                    case 3:
                        enrollmentManagementMenu();
                        break;
                    case 4:
                        displayStatistics();
                        break;
                    case 0:
                        System.out.println("Thank you for using LearnTrack!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }

        scanner.close();
    }

    private static void initializeSampleData() {
        // Add sample students
        studentService.addStudent("John", "Doe", "john.doe@email.com", "2024A");
        studentService.addStudent("Jane", "Smith", "jane.smith@email.com", "2024B");
        studentService.addStudent("Bob", "Johnson", "2024A");

        // Add sample courses
        courseService.addCourse("Java Programming", "Learn Core Java", 12);
        courseService.addCourse("Web Development", "HTML, CSS, JavaScript", 10);
        courseService.addCourse("Database Design", "SQL and Database Concepts", 8);
    }

    private static void displayMainMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("4. View Statistics");
        System.out.println("0. Exit");
        System.out.println("===============================");
    }

    private static void studentManagementMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n----- Student Management -----");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Deactivate Student");
            System.out.println("5. Update Student Email");
            System.out.println("0. Back to Main Menu");

            int choice = getIntInput("Enter your choice: ");

            try {
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewAllStudents();
                        break;
                    case 3:
                        searchStudentById();
                        break;
                    case 4:
                        deactivateStudent();
                        break;
                    case 5:
                        updateStudentEmail();
                        break;
                    case 0:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void courseManagementMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n----- Course Management -----");
            System.out.println("1. Add New Course");
            System.out.println("2. View All Courses");
            System.out.println("3. View Active Courses");
            System.out.println("4. Deactivate Course");
            System.out.println("5. Activate Course");
            System.out.println("0. Back to Main Menu");

            int choice = getIntInput("Enter your choice: ");

            try {
                switch (choice) {
                    case 1:
                        addCourse();
                        break;
                    case 2:
                        viewAllCourses();
                        break;
                    case 3:
                        viewActiveCourses();
                        break;
                    case 4:
                        deactivateCourse();
                        break;
                    case 5:
                        activateCourse();
                        break;
                    case 0:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void enrollmentManagementMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n----- Enrollment Management -----");
            System.out.println("1. Enroll Student in Course");
            System.out.println("2. View All Enrollments");
            System.out.println("3. View Student's Enrollments");
            System.out.println("4. Update Enrollment Status");
            System.out.println("0. Back to Main Menu");

            int choice = getIntInput("Enter your choice: ");

            try {
                switch (choice) {
                    case 1:
                        enrollStudent();
                        break;
                    case 2:
                        viewAllEnrollments();
                        break;
                    case 3:
                        viewStudentEnrollments();
                        break;
                    case 4:
                        updateEnrollmentStatus();
                        break;
                    case 0:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addStudent() {
        System.out.println("\n--- Add New Student ---");

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter email (or press Enter to skip): ");
        String email = scanner.nextLine();

        System.out.print("Enter batch: ");
        String batch = scanner.nextLine();

        try {
            Student student;
            if (email.isEmpty()) {
                student = studentService.addStudent(firstName, lastName, batch);
            } else {
                if (!InputValidator.isValidEmail(email)) {
                    System.out.println("Warning: Email format may be invalid.");
                }
                student = studentService.addStudent(firstName, lastName, email, batch);
            }

            System.out.println("Student added successfully!");
            System.out.println("Student ID: " + student.getId());
            System.out.println("Display Name: " + student.getDisplayName());
        } catch (Exception e) {
            System.out.println("Failed to add student: " + e.getMessage());
        }
    }

    private static void viewAllStudents() {
        System.out.println("\n--- All Students ---");
        List<Student> students = studentService.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("ID\tName\t\tBatch\t\tStatus\tEmail");
            System.out.println("----------------------------------------------------------");
            for (Student student : students) {
                System.out.printf("%s\t%s %s\t%s\t%s\t%s%n",
                        student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getBatch(),
                        student.isActive() ? "Active" : "Inactive",
                        student.getEmail() != null ? student.getEmail() : "N/A");
            }
        }
    }

    private static void searchStudentById() {
        System.out.print("\nEnter Student ID: ");
        String id = scanner.nextLine();

        try {
            Student student = studentService.getStudentById(id);
            System.out.println("\nStudent Found:");
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getDisplayName());
            System.out.println("Email: " + (student.getEmail() != null ? student.getEmail() : "N/A"));
            System.out.println("Batch: " + student.getBatch());
            System.out.println("Status: " + (student.isActive() ? "Active" : "Inactive"));
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deactivateStudent() {
        System.out.print("\nEnter Student ID to deactivate: ");
        String id = scanner.nextLine();

        try {
            studentService.deactivateStudent(id);
            System.out.println("Student deactivated successfully!");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateStudentEmail() {
        System.out.print("\nEnter Student ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();

        try {
            if (!InputValidator.isValidEmail(newEmail)) {
                System.out.println("Warning: Email format may be invalid.");
            }
            studentService.updateStudentEmail(id, newEmail);
            System.out.println("Email updated successfully!");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addCourse() {
        System.out.println("\n--- Add New Course ---");

        System.out.print("Enter course name: ");
        String name = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        int duration = getIntInput("Enter duration in weeks: ");

        try {
            Course course = courseService.addCourse(name, description, duration);
            System.out.println("Course added successfully!");
            System.out.println("Course ID: " + course.getId());
        } catch (Exception e) {
            System.out.println("Failed to add course: " + e.getMessage());
        }
    }

    private static void viewAllCourses() {
        System.out.println("\n--- All Courses ---");
        List<Course> courses = courseService.getAllCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            System.out.println("ID\tName\t\tDuration\tStatus\tDescription");
            System.out.println("----------------------------------------------------------------");
            for (Course course : courses) {
                System.out.printf("%s\t%s\t%d weeks\t%s\t%s%n",
                        course.getId(),
                        course.getName().length() > 10 ? course.getName().substring(0, 10) + "..." : course.getName(),
                        course.getDurationWeeks(),
                        course.isActive() ? "Active" : "Inactive",
                        course.getDescription().length() > 20 ? course.getDescription().substring(0, 20) + "..." : course.getDescription());
            }
        }
    }

    private static void viewActiveCourses() {
        System.out.println("\n--- Active Courses ---");
        List<Course> courses = courseService.getActiveCourses();

        if (courses.isEmpty()) {
            System.out.println("No active courses found.");
        } else {
            for (Course course : courses) {
                System.out.println(course.getId() + " - " + course.getName() +
                        " (" + course.getDurationWeeks() + " weeks)");
            }
        }
    }

    private static void deactivateCourse() {
        System.out.print("\nEnter Course ID to deactivate: ");
        String id = scanner.nextLine();

        try {
            courseService.deactivateCourse(id);
            System.out.println("Course deactivated successfully!");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void activateCourse() {
        System.out.print("\nEnter Course ID to activate: ");
        String id = scanner.nextLine();

        try {
            courseService.activateCourse(id);
            System.out.println("Course activated successfully!");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void enrollStudent() {
        System.out.println("\n--- Enroll Student ---");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();

        try {
            Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
            System.out.println("Enrollment successful!");
            System.out.println("Enrollment ID: " + enrollment.getId());
            System.out.println("Date: " + enrollment.getEnrollmentDate());
            System.out.println("Status: " + enrollment.getStatus());
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Failed to enroll: " + e.getMessage());
        }
    }

    private static void viewAllEnrollments() {
        System.out.println("\n--- All Enrollments ---");
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();

        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found.");
        } else {
            System.out.println("ID\tStudent ID\tCourse ID\tDate\t\tStatus");
            System.out.println("----------------------------------------------------------");
            for (Enrollment enrollment : enrollments) {
                System.out.printf("%s\t%s\t%s\t%s\t%s%n",
                        enrollment.getId(),
                        enrollment.getStudentId(),
                        enrollment.getCourseId(),
                        enrollment.getEnrollmentDate(),
                        enrollment.getStatus());
            }
        }
    }

    private static void viewStudentEnrollments() {
        System.out.print("\nEnter Student ID: ");
        String studentId = scanner.nextLine();

        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudentId(studentId);

        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found for this student.");
        } else {
            System.out.println("\nEnrollments for Student " + studentId + ":");
            System.out.println("ID\tCourse ID\tDate\t\tStatus");
            System.out.println("----------------------------------------------");
            for (Enrollment enrollment : enrollments) {
                System.out.printf("%s\t%s\t%s\t%s%n",
                        enrollment.getId(),
                        enrollment.getCourseId(),
                        enrollment.getEnrollmentDate(),
                        enrollment.getStatus());
            }
        }
    }

    private static void updateEnrollmentStatus() {
        System.out.print("\nEnter Enrollment ID: ");
        String enrollmentId = scanner.nextLine();

        System.out.println("Select new status:");
        System.out.println("1. ACTIVE");
        System.out.println("2. COMPLETED");
        System.out.println("3. CANCELLED");

        int choice = getIntInput("Enter choice: ");
        String status;

        switch (choice) {
            case 1: status = "ACTIVE"; break;
            case 2: status = "COMPLETED"; break;
            case 3: status = "CANCELLED"; break;
            default:
                System.out.println("Invalid choice. Setting to ACTIVE.");
                status = "ACTIVE";
        }

        try {
            enrollmentService.updateEnrollmentStatus(enrollmentId, status);
            System.out.println("Enrollment status updated successfully!");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayStatistics() {
        System.out.println("\n=== LearnTrack Statistics ===");
        System.out.println("Total Students: " + studentService.getTotalStudents());
        System.out.println("Active Students: " + studentService.getActiveStudentsCount());

        List<Course> courses = courseService.getAllCourses();
        int activeCourses = 0;
        for (Course course : courses) {
            if (course.isActive()) activeCourses++;
        }
        System.out.println("Total Courses: " + courses.size());
        System.out.println("Active Courses: " + activeCourses);

        System.out.println("Total Enrollments: " + enrollmentService.getAllEnrollments().size());
        System.out.println("============================\n");
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }
}