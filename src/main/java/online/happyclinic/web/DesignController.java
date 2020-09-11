package online.happyclinic.web;

import online.happyclinic.RequestInfo;
import online.happyclinic.RequestInfo.Type;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/design")// This is going to handle multiple types of operations
public class DesignController {

    @GetMapping
    public String showDesignForm(){
        return "design";
    }

    private List<RequestInfo> createRequestInfoList() {
        List<RequestInfo> RequestInfo = Arrays.asList(
                new RequestInfo("HOSP", "Hospital", Type.GENERAL_PRACTICE),
                new RequestInfo("INSU", "INSURANCE GROUP", Type.GENERAL_PRACTICE)
                // I will request the same info for each of the Types in the Enum
                // but I am not sure if that is the right direction of software design
                // Please provide feedback. Thank you.
        );
        return RequestInfo;
    }
}
