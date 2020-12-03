package online.happyclinic.web;

import lombok.extern.slf4j.Slf4j;
import online.happyclinic.Facility;
import online.happyclinic.data.FacilityRepository;
import online.happyclinic.data.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/delete12")
public class DeleteFacilityController {

    private FacilityRepository facilityRepo;
    private ServiceRepository serviceRepo;
    @Autowired
    public DeleteFacilityController(FacilityRepository facilityRepo, ServiceRepository serviceRepo){
        this.facilityRepo = facilityRepo;
        this.serviceRepo = serviceRepo;
    }

    @GetMapping("/{facilityId}")
    public String updateFacility(@PathVariable("facilityId") Long id, Model model){
        facilityRepo.deleteById(id);
        return "redirect:/primarytableview";
    }

    @PostMapping("/updated/{facilityId}")
    public String deleteFacility(@PathVariable("facilityId") long id){
        // delete facility
        facilityRepo.deleteById(id);
        return "redirect:/primarytableview";
    }


}

