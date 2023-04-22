package com.beerlot.crawler;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final String INPUT_PATH = "src/main/resources/data/name-list/beer-name-list-1.txt";
    private static final String OUTPUT_PATH = "src/main/resources/data/raw-data/beer-raw-data-1-100.xlsx";
    private static final String OUTPUT_SHEET_NAME = "BeerLot Raw Data";


    public static void main(String[] args) throws InterruptedException, IOException {
        List<String> beerNames = new Reader().getListFromFile(INPUT_PATH);

        Crawler crawler = new Crawler();
        Writer writer = new Writer(OUTPUT_PATH, OUTPUT_SHEET_NAME);

        writer.addHeader();

        for(String beerName : beerNames) {
            Beer beer = crawler.crawl(beerName);
            writer.addRow(DataUtils.serialize(beer).toArray());
        }

        writer.close();
    }
}
