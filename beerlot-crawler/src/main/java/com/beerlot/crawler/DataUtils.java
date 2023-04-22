package com.beerlot.crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataUtils {
    public static String transformCategory(String category) {
        String transformed = "*" + category;
        if (category.contains("Altbier")) transformed = "Altbier";
        else if (category.contains("Barley Wine")) transformed = "Barley Wine";
        else if (category.contains("Bitter")) transformed = "Bitter";
        else if (category.contains("Blonde Ale")) transformed = "Blonde Ale";
        else if (category.contains("Brown Ale")) transformed = "Brown Ale";
        else if (category.contains("Steam Beer")) transformed = "Steam Beer";
        else if (category.contains("Cream Ale")) transformed = "Cream Ale";
        else if (category.contains("IIPA DIPA")) transformed = "IIPA/DIPA";
        else if (category.contains("IPA")) transformed = "IPA";
        else if (category.contains("Session IPA")) transformed = "Session IPA";
        else if (category.contains("Kölsch")) transformed = "Kölsch";
        else if (category.contains("Mild Ale")) transformed = "Mild Ale";
        else if (category.contains("Old Ale")) transformed = "Old Ale";
        else if (category.contains("Red Ale")) transformed = "Red Ale/Amber Ale";
        else if (category.contains("Pale Ale")) transformed = "Pale Ale";
        else if (category.contains("Scotch Ale")) transformed = "Scotch Ale";
        else if (category.contains("Scottish Ale")) transformed = "Scottish Ale";
        else if (category.contains("Strong Ale")) transformed = "Strong Ale";
        else if (category.contains("Amber Lager")) transformed = "Amber Lager";
        else if (category.contains("Doppelbock")) transformed = "Doppelbock";
        else if (category.contains("Weizenbock")) transformed = "Weizenbock";
        else if (category.contains("Maibock")) transformed = "Maibock";
        else if (category.contains("Eisbock")) transformed = "Eisbock";
        else if (category.contains("Dunkler Bock")) transformed = "Dunkelbock";
        else if (category.contains("Dark Lager")) transformed = "Dark Lager";
        else if (category.contains("Dortmunder Export")) transformed = "Helles";
        else if (category.contains("Malt Liquor")) transformed = "Malt Liquor";
        else if (category.contains("Märzen")) transformed = "Märzen";
        else if (category.contains("Pale Lager")) transformed = "Pale Lager";
        else if (category.contains("Pilsener")) transformed = "Pilsener";
        else if (category.contains("Radler")) transformed = "Radler";
        else if (category.contains("Schwarzbier")) transformed = "Schwarzbier";
        else if (category.contains("Kellerbier")) transformed = "Kellerbier";
        else if (category.contains("Belgian Ale - Pale / Golden / Single")) transformed = "Blonde Ale";
        else if (category.contains("Belgian Ale - Strong Dark")) transformed = "Brown Ale";
        else if (category.contains("Belgian Ale - Strong Pale")) transformed = "Pale Ale";
        else if (category.contains("Dubbel")) transformed = "Dubbel";
        else if (category.contains("Quadrupel")) transformed = "Quadrupel";
        else if (category.contains("Saison")) transformed = "Saison";
        else if (category.contains("Tripel")) transformed = "Tripel";
        else if (category.contains("Porter")) transformed = "Porter";
        else if (category.contains("Stout")) transformed = "Stout";
        else if (category.contains("Dunkelweizen")) transformed = "Dunkelweizen";
        else if (category.contains("Hefeweizen")) transformed = "Hefeweizen";
        else if (category.contains("Kristallweizen")) transformed = "Kristallweizen";
        else if (category.contains("Witbier")) transformed = "Belgian Wheat Ale";
        else if (category.contains("Wheat Ale")) transformed = "American Wheat Ale";
        else if (category.contains("Berliner Weisse")) transformed = "Berliner Weisse";
        else if (category.contains("Gose")) transformed = "Gose";
        else if (category.contains("Lambic")) transformed = "Lambic";
        else if (category.contains("Sour Flemish Ale")) transformed = "Flemish Ale";
        else if (category.contains("Apple Cider")) transformed = "Apple Cider";
        else if (category.contains("Flavored - Fruit")) transformed = "Fruit";
        else if (category.contains("Flavored - Pumpkin / Vegetables")) transformed = "Vegetable";
        else if (category.contains("Flavored - Chili / Peppers")) transformed = "Chili/Peppers";

        return transformed;
    }

    public static List<String> serialize(Beer beer) {
        List<String> serialized = new ArrayList<>();

        serialized.add(beer.getBrewery());
        serialized.add(String.valueOf(beer.getCalorie()));
        serialized.add(beer.getCalorieUnit());
        serialized.add(String.valueOf(beer.getVolume()));
        serialized.add(beer.getCategory());
        serialized.add(beer.getName());
        serialized.add(beer.getDescription());
        serialized.add(beer.getOriginCity());
        serialized.add(beer.getOriginCountry());
        serialized.add(beer.getGlassware().stream().collect(Collectors.joining(",")));
        return serialized.stream().collect(Collectors.toList());
    }
}
