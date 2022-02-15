package io.project.covidtracker.controller;

import io.project.covidtracker.models.LocationStats;
import io.project.covidtracker.services.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CovidDataService cdata;

    @GetMapping("/")
    public String home(Model model){
        Integer totalReportedCases=0;
        List<LocationStats> all_stats= cdata.getStat_list();
        for(int i=0; i< all_stats.size(); i++){
            totalReportedCases += all_stats.get(i).getLatestCases();
        }
        model.addAttribute("cdata",cdata.getStat_list());
        model.addAttribute("totalReportedCases",totalReportedCases);
        return "home";
    }
}
