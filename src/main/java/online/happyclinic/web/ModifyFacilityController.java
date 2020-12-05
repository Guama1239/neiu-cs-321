package online.happyclinic.web;

import lombok.extern.slf4j.Slf4j;
import online.happyclinic.Facility;
import online.happyclinic.Service;
import online.happyclinic.data.FacilityRepository;
import online.happyclinic.data.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/modify")
public class ModifyFacilityController {

    private FacilityRepository facilityRepo;
    private ServiceRepository serviceRepo;

    @Autowired
    public ModifyFacilityController(FacilityRepository facilityRepo, ServiceRepository serviceRepo) {
        this.facilityRepo = facilityRepo;
        this.serviceRepo = serviceRepo;
    }

    @GetMapping("/{facilityId}")
    public String updateFacility(@PathVariable("facilityId") long id, Model model){
        Facility facility = facilityRepo.findById(id).get();
        model.addAttribute("serviceIds", getServicesIds(facility));
        model.addAttribute("facility", facility);
        addIngredientsToModel(model);
        return "update-facility";
    }

    @GetMapping("/view/{facilityId}")
    public String viewFacility(@PathVariable("facilityId") long id, Model model){
        Facility facility = facilityRepo.findById(id).get();
        model.addAttribute("serviceIds", getServicesIds(facility));
        model.addAttribute("facility", facility);
        addIngredientsToModel(model);
        return "view-facility";
    }



    @PostMapping("/update/{facilityId}")
    public String processUpdateFacility(@PathVariable("facilityId") long id, @Valid @ModelAttribute("facility") Facility facility, Errors errors){
        if (errors.hasErrors())
            return "update-facility";

        Facility newFacility = facilityRepo.findById(id).get();
        newFacility.setServices(facility.getServices());
        newFacility.setName(facility.getName());
        facilityRepo.save(newFacility);
        log.info("Processing changes.." + newFacility);
        return "redirect:/primarytableview";
    }


    private List<String> getServicesIds(Facility facility) {
        List<Service> services = facility.getServices();
        List<String> serviceIds = new ArrayList<>();
        for (Service service: services) {
            serviceIds.add(service.getId());
        }
        return serviceIds;
    }

    private void addIngredientsToModel(Model model) {
        List<Service> services = (List<Service>) serviceRepo.findAll();
        Service.Type[] types = Service.Type.values();
        for (Service.Type type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(services, type));
        }
    }

    private List<Service> filterByType(List<Service> services, Service.Type type) {
        return services
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }


//    @GetMapping("/delete/{facilityId}")
//    public String updateFacilityToDelete(@PathVariable("facilityId") long id, Facility facility){
//        Facility newFacility = facilityRepo.findById(id).get();
//        newFacility.setServices(facility.getServices());
//        newFacility.setName(facility.getName());
//       // serviceRepo.deleteAll(List<Service>);
//        facilityRepo.delete(newFacility);
//        log.info("Processing changes.." + newFacility);
//        return "redirect:/primarytableview";
//    }

//    @PostMapping
//    public String processDeleteFacility(@PathVariable("facilityId") long id, @Valid @ModelAttribute("facility") Facility facility, Errors errors){
//        if (errors.hasErrors())
//            return "update-facility";

//        Facility newFacility = facilityRepo.findById(id).get();
//        newFacility.setServices(facility.getServices());
//        newFacility.setName(facility.getName());
//        facilityRepo.delete(newFacility);
//        log.info("Processing changes.." + newFacility);
//        return "redirect:/primarytableview";
//    }
}
