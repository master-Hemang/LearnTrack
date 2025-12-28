package service;

import entity.Enrollment;
import entity.Student;
import entity.Course;
import exception.EntityNotFoundException;
import util.IdGenerator;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {
    private List<Enrollment> enrollments;
    private StudentService studentService;
    private CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.enrollments = new ArrayList<>();
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enrollStudent(String studentId, String courseId)
            throws EntityNotFoundException {

        // Check if student exists and is active
        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourseById(courseId);

        // Check if enrollment already exists
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId().equals(studentId) &&
                    enrollment.getCourseId().equals(courseId) &&
                    enrollment.getStatus().equals("ACTIVE")) {
                return enrollment; // Already enrolled
            }
        }

        String id = IdGenerator.getNextEnrollmentId();
        Enrollment enrollment = new Enrollment(id, studentId, courseId);
        enrollments.add(enrollment);
        return enrollment;
    }

    public List<Enrollment> getEnrollmentsByStudentId(String studentId) {
        List<Enrollment> studentEnrollments = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId().equals(studentId)) {
                studentEnrollments.add(enrollment);
            }
        }
        return studentEnrollments;
    }

    public List<Enrollment> getEnrollmentsByCourseId(String courseId) {
        List<Enrollment> courseEnrollments = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourseId().equals(courseId)) {
                courseEnrollments.add(enrollment);
            }
        }
        return courseEnrollments;
    }

    public void updateEnrollmentStatus(String enrollmentId, String status)
            throws EntityNotFoundException {

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId().equals(enrollmentId)) {
                enrollment.setStatus(status);
                return;
            }
        }
        throw new EntityNotFoundException("Enrollment with ID " + enrollmentId + " not found");
    }

    public List<Enrollment> getAllEnrollments() {
        return new ArrayList<>(enrollments);
    }
}
