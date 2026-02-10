package src1;

import src1.service.TrendingEngine;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        TrendingEngine engine = new TrendingEngine(5);

        engine.addEvent("AI", 1);
        engine.addEvent("AI", 2);
        engine.addEvent("Cricket", 3);
        engine.addEvent("Stocks", 4);
        engine.addEvent("AI", 5);

        List<String> top = engine.getTopK(2);

        System.out.println("Top Trending Topics: ");
        for (String topic : top) {
            System.out.println(topic);
        }
    }
}
