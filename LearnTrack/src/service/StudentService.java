package service;


import entity.Student;
import exception.EntityNotFoundException;
import util.IdGenerator;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private List<Student> students;

    public StudentService() {
        this.students = new ArrayList<>();
    }

    // Add student with all details
    public Student addStudent(String firstName, String lastName, String email, String batch) {
        String id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, email, batch);
        students.add(student);
        return student;
    }

    // Add student without email (method overloading)
    public Student addStudent(String firstName, String lastName, String batch) {
        String id = IdGenerator.getNextStudentId();
        Student student = new Student(firstName, lastName, batch);
        student.setId(id);
        students.add(student);
        return student;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public List<Student> getActiveStudents() {
        List<Student> activeStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.isActive()) {
                activeStudents.add(student);
            }
        }
        return activeStudents;
    }

    public Student getStudentById(String id) throws EntityNotFoundException {
        for (Student student : students) {
            if (student.getId().equals(id) && student.isActive()) {
                return student;
            }
        }
        throw new EntityNotFoundException("Student with ID " + id + " not found");
    }

    public void deactivateStudent(String id) throws EntityNotFoundException {
        Student student = getStudentById(id);
        student.setActive(false);
    }

    public void updateStudentEmail(String id, String newEmail) throws EntityNotFoundException {
        Student student = getStudentById(id);
        student.setEmail(newEmail);
    }

    public int getTotalStudents() {
        return students.size();
    }

    public int getActiveStudentsCount() {
        int count = 0;
        for (Student student : students) {
            if (student.isActive()) {
                count++;
            }
        }
        return count;
    }
}
