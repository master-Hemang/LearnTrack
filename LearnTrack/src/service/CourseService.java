package service;


import entity.Course;
import exception.EntityNotFoundException;
import util.IdGenerator;
import java.util.ArrayList;
import java.util.List;

public class CourseService {
    private List<Course> courses;

    public CourseService() {
        this.courses = new ArrayList<>();
    }

    public Course addCourse(String name, String description, int durationWeeks) {
        String id = IdGenerator.getNextCourseId();
        Course course = new Course(id, name, description, durationWeeks);
        courses.add(course);
        return course;
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    public List<Course> getActiveCourses() {
        List<Course> activeCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.isActive()) {
                activeCourses.add(course);
            }
        }
        return activeCourses;
    }

    public Course getCourseById(String id) throws EntityNotFoundException {
        for (Course course : courses) {
            if (course.getId().equals(id) && course.isActive()) {
                return course;
            }
        }
        throw new EntityNotFoundException("Course with ID " + id + " not found");
    }

    public void deactivateCourse(String id) throws EntityNotFoundException {
        Course course = getCourseById(id);
        course.setActive(false);
    }

    public void activateCourse(String id) throws EntityNotFoundException {
        for (Course course : courses) {
            if (course.getId().equals(id)) {
                course.setActive(true);
                return;
            }
        }
        throw new EntityNotFoundException("Course with ID " + id + " not found");
    }
}
