package io.project.covidtracker.models;

public class LocationStats {
    private String province;
    private String country;
    private int latestCases;

    @Override
    public String toString() {
        return "LocationStats{" +
                "province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", latestCases=" + latestCases +
                '}';
    }

    //Getters and Setters
    public int getLatestCases() {
        return latestCases;
    }

    public String getCountry() {
        return country;
    }

    public String getProvince() {
        return province;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLatestCases(int latestCases) {
        this.latestCases = latestCases;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
