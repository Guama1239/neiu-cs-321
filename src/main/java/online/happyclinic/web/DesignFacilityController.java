package online.happyclinic.web;

import lombok.extern.slf4j.Slf4j;
import online.happyclinic.Order;
import online.happyclinic.Facility;
import online.happyclinic.Service;
import online.happyclinic.Service.Type;
import online.happyclinic.data.FacilityRepository;
import online.happyclinic.data.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/registerfacility")
@SessionAttributes("order")
public class DesignFacilityController {

    private final ServiceRepository serviceRepo;
    private final FacilityRepository facilityRepo;

    @Autowired
    public DesignFacilityController(ServiceRepository serviceRepo, FacilityRepository facilityRepo) {
        this.serviceRepo = serviceRepo;
        this.facilityRepo = facilityRepo;
    }

    @GetMapping
    public String showDesignForm(){
        return "registerfacility";
    }

    @PostMapping
    public String processRegisterFacility(@Valid @ModelAttribute("facility") Facility facility, Errors errors, Model model){
        if (errors.hasErrors())
            return "registerfacility";

        Facility saveFacility = facilityRepo.save(facility);
        Order order = (Order) model.getAttribute("order");
        order.addDesign(saveFacility);
        log.info("Processing..." + facility);
        return "redirect:/orders/current";

    }

    @ModelAttribute
    public void addAttributes(Model model) {
        List<Service> services = (List<Service>) serviceRepo.findAll();
        //List<RequestType> services = createregisterfacilityList();
        Type[] types = Service.Type.values();
        for (Type type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(services, type));
        }
    //        model.addAttribute("design", new Request());
    }

    @ModelAttribute(name = "facility")
    public Facility addFacilityToModel() {return new Facility();}

    @ModelAttribute(name = "order")
    public Order addOrderToModel(){ return new Order();}

    private List<Service> filterByType(List<Service> services, Type type) {
        return services
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }


}
