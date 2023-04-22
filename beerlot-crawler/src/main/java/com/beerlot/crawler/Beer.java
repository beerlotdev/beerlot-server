package com.beerlot.crawler;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Beer {
    private String name;
    private String brewery;
    private String originCity;
    private String originCountry;
    private float volume;
    private String category;
    private String description;
    private int calorie;
    private String calorieUnit;
    private List<String> glassware;

    @Override
    public String toString() {
        return "Beer Information - " +
                "name: " + this.name +
                "brewery: " + this.brewery +
                "originCity: " + this.originCity +
                "originCountry: " + this.originCountry +
                "volume: " + this.volume +
                "category: " + this.category +
                "description: " + this.description +
                "calorie: " + this.calorie +
                "calorieUnit: " + this.calorieUnit +
                "num of glassware: " + this.glassware.size()
                ;
    }
}
