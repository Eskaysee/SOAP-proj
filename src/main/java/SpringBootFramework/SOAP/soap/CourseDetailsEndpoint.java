package SpringBootFramework.SOAP.soap;

import SpringBootFramework.SOAP.soap.bean.Course;
import SpringBootFramework.SOAP.soap.service.CourseDetailsService;
import com.mysoap.courses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    CourseDetailsService courseDetailsService;

    @PayloadRoot(namespace = "http://mySOAP.com/courses", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        Course course = courseDetailsService.findById(request.getId());
        response.setCourseDetails(mapCourse(course));
        return response;
    }

    @PayloadRoot(namespace = "http://mySOAP.com/courses", localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) {
        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
        List<Course> courses = courseDetailsService.findAll();
        response.getCourseDetails().addAll(mapCourseList(courses));
        return response;
    }

    @PayloadRoot(namespace = "http://mySOAP.com/courses", localPart = "DeleteCourseRequest")
    @ResponsePayload
    public DeleteCourseResponse processDeleteCourseRequest(@RequestPayload DeleteCourseRequest request) {
        DeleteCourseResponse response = new DeleteCourseResponse();
        boolean isRemoved = courseDetailsService.deleteById(request.getId());
        response.setStatus(isRemoved);
        return response;
    }

    private CourseDetails mapCourse(Course course) {
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getDescription());
        return courseDetails;
    }

    private List<CourseDetails> mapCourseList(List<Course> courses) {
        List<CourseDetails> courseDetails = new ArrayList<>();
        for (Course course : courses) {
            courseDetails.add(mapCourse(course));
        }
        return courseDetails;
    }
}
