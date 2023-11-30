package SpringBootFramework.SOAP.soap;

import SpringBootFramework.SOAP.soap.bean.Course;
import SpringBootFramework.SOAP.soap.service.CourseDetailsService;
import com.mysoap.courses.CourseDetails;
import com.mysoap.courses.GetCourseDetailsRequest;
import com.mysoap.courses.GetCourseDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

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

    private CourseDetails mapCourse(Course course) {
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getDescription());
        return courseDetails;
    }
}
