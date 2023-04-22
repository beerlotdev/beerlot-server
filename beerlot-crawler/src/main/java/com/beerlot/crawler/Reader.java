package com.beerlot.crawler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {

    Reader() {}

    public List<String> getListFromFile(String inputPath) throws FileNotFoundException {
        File file = new File(inputPath);

        Scanner scanner = new Scanner(file);

        List<String> beerNameList = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String value = scanner.nextLine();
            beerNameList.add(value);
        }

        return beerNameList;
    }
}
