package com.example.hoang.goeat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hoang on 10/22/2017.
 */

public class SampleDataProvider {
    public static List<DataItem> dataItemList;
    public static Map<String, DataItem> dataItemMap;

    static {
        dataItemList = new ArrayList<>();
        dataItemMap = new HashMap<>();

        addItem(new DataItem(null, "Bicicletta", "biciclettalogo.JPG", "http://www.bicicletta.com.au/"));
        addItem(new DataItem(null, "Lilotang", "lilotanglogo.JPG", "http://chairmangroup.com.au/lilotang/"));
        addItem(new DataItem(null, "Onred", "onredlogo.JPG", "http://www.onred.com.au/"));
        addItem(new DataItem(null, "Parlour", "parlourlogo.JPG", "http://www.parlour.net.au/"));

       }

    private static void addItem(DataItem item){
        dataItemList.add(item);
        dataItemMap.put(item.getItemId(), item);
    }
}
