package SpringBootFramework.SOAP.soap;

import com.mysoap.courses.CourseDetails;
import com.mysoap.courses.GetCourseDetailsRequest;
import com.mysoap.courses.GetCourseDetailsResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CourseDetailsEndpoint {

    @PayloadRoot(namespace = "http://mySOAP.com/courses", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(request.getId());
        courseDetails.setName("Java Web Service Udemy Course");
        courseDetails.setDescription("Spring Framework and Spring Boot with SOAP");
        response.setCourseDetails(courseDetails);
        return response;
    }
}
