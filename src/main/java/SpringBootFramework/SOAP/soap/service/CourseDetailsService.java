package SpringBootFramework.SOAP.soap.service;

import SpringBootFramework.SOAP.soap.bean.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CourseDetailsService {

    private static List<Course> courses = new ArrayList<>();

    static {
        Course course1 = new Course(1, "Spring SOAP", "Java Web Services: SOAP");
        courses.add(course1);

        Course course2 = new Course(2, "Spring Restful API", "Java Web Services: RESTful Api");
        courses.add(course2);

        Course course3 = new Course(3, "DevOps Docker", "Udemy Docker, Kubernetes & Terraform");
        courses.add(course3);

        Course course4 = new Course(4, "Python", "Django");
        courses.add(course4);

        Course course5 = new Course(5, "C Sharp", "dotNet");
        courses.add(course5);
    }

    public Course findById(int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null;
    }

    public List<Course> findAll() {
        return courses;
    }

    public boolean deleteById(int id) {
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (course.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
