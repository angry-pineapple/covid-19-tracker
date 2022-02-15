package io.project.covidtracker.services;

import io.project.covidtracker.models.LocationStats;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CovidDataService {
    private String url = "https://github.com/CSSEGISandData/COVID-19/blob/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv?raw=true";
    private List<LocationStats> stat_list = new ArrayList();                        //ArrayList to hold LocationStats objects

    public List<LocationStats> getStat_list() {
        return stat_list;
    }

    @PostConstruct
    @Scheduled(cron="* * 1 * * *")
    public void getCovidData() throws Exception{
        URL covidDataURL = new URL(getUrl());
        URLConnection yc = covidDataURL.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

        String line ="";
        int i=0;
        while (line !=null){
            line= in.readLine();                                                    //returns a comma separated string
            if(i ==0){                                                              //skip the headers
                i+=1;
                continue;
            }
            if (line != null){
                List<String> split_line_list = Arrays.asList(line.split(",")); //split the comma separated string
                LocationStats location_stats =new LocationStats();                  //new LocationStats model object

                location_stats.setProvince(split_line_list.get(0));                 //set State/Province
                location_stats.setCountry(split_line_list.get(1));                  //set Country
                location_stats.setLatestCases(Integer.parseInt(split_line_list.get(split_line_list.size()-1))); // set latest number of cases ie. the last column
                stat_list.add(location_stats);                                      // add LocationStats model object to stat_list
            }
        }
        in.close();                                                                 // close the file

    }

    public String getUrl() {
        return url;
    }
}
