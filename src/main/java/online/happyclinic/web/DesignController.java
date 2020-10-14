package online.happyclinic.web;

import lombok.extern.slf4j.Slf4j;
import online.happyclinic.Order;
import online.happyclinic.Request;
import online.happyclinic.RequestType;
import online.happyclinic.RequestType.Type;
import online.happyclinic.data.RequestRepository;
import online.happyclinic.data.RequestTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/requestinfo")
@SessionAttributes("order")
public class DesignController {

    private final RequestTypeRepository RequestTypeRepo;
    private final RequestRepository requesRepo;

    @Autowired
    public DesignController(RequestTypeRepository RequestTypeRepo, RequestRepository requesRepo) {
        this.RequestTypeRepo = RequestTypeRepo;
        this.requesRepo = requesRepo;
    }

    @GetMapping
    public String showDesignForm(){
        return "requestinfo";
    }

    @PostMapping
    public String processRequestInfo(@Valid @ModelAttribute("design") Request design, Errors errors, Model model){
        if (errors.hasErrors())
            return "requestinfo";

        Request saveRequest = requesRepo.save(design);
        Order order = (Order) model.getAttribute("order");
        order.addDesign(saveRequest);
        log.info("Processing..." + design);
        return "redirect:/requests/current";

    }

    @ModelAttribute
    public void addAttributes(Model model) {
        List<RequestType> RequestsInfo = (List<RequestType>) RequestTypeRepo.findAll();
        //List<RequestType> RequestsInfo = createRequestInfoList();
        Type[] types = RequestType.Type.values();
        for (Type type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(RequestsInfo, type));
        }
    //        model.addAttribute("design", new Request());
    }

    @ModelAttribute(name = "design")
    public Request addRequestToModel() {return new Request();}

    @ModelAttribute(name = "order")
    public Order addOrderToModel(){ return new Order();}

    private List<RequestType> filterByType(List<RequestType> RequestsInfo, Type type) {
        return RequestsInfo
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }


}
