package online.happyclinic.web;

import lombok.extern.slf4j.Slf4j;
import online.happyclinic.RequestInfo;
import online.happyclinic.RequestInfo.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/requestinfo")// This is going to handle multiple types of operations
public class DesignController {

    @GetMapping
    public String showDesignForm(){
        return "requestinfo";
    }

    @PostMapping
    public String processRequestInfo(@Valid @ModelAttribute("design") Request design, Errors errors){
        if (errors.hasErrors())
            return "requestinfo";

        return "redirect:/requests/current";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        List<RequestInfo> RequestsInfo = createRequestInfoList();
        Type[] types = RequestInfo.Type.values();
        for (Type type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(RequestsInfo, type));
        }
        model.addAttribute("design", new Request());
    }

    private List<RequestInfo> filterByType(List<RequestInfo> RequestsInfo, Type type) {
        return RequestsInfo
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    private List<RequestInfo> createRequestInfoList() {
        List<RequestInfo> RequestsInfo = Arrays.asList(
                new RequestInfo("ASC", "Ambulatory surgical services", Type.FACILITY_TYPE),
                new RequestInfo("BC", "Birth center", Type.FACILITY_TYPE),
                new RequestInfo("BB", "Blood banks", Type.FACILITY_TYPE),
                new RequestInfo("CMF", "Clinics and medical offices", Type.FACILITY_TYPE),
                new RequestInfo("DEC", "Diabetes education centers", Type.FACILITY_TYPE),
                new RequestInfo("DC", "Dialysis Centers", Type.FACILITY_TYPE),
                new RequestInfo("HH", "Hospice homes", Type.FACILITY_TYPE),
                new RequestInfo("HO", "Hospitals", Type.FACILITY_TYPE),
                new RequestInfo("IRC", "Imaging and radiology centers", Type.FACILITY_TYPE),
                new RequestInfo("MHT", "Mental health and addiction treatment centers", Type.FACILITY_TYPE),
                new RequestInfo("NH", "Nursing homes", Type.FACILITY_TYPE),
                new RequestInfo("ORC", "Orthopedic and other rehabilitation centers", Type.FACILITY_TYPE),
                new RequestInfo("TLH", "Telehealth", Type.FACILITY_TYPE),
                new RequestInfo("IC", "Inpatient care", Type.MEDICAL_SERVICES),
                new RequestInfo("UC", "Urgent care", Type.MEDICAL_SERVICES),
                new RequestInfo("AC", "Ambulatory care", Type.MEDICAL_SERVICES)
        );
        return RequestsInfo;
    }
}
