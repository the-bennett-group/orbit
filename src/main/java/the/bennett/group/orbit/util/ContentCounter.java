package the.bennett.group.orbit.util;

import java.util.HashMap;

public class ContentCounter {
    private static final HashMap<String, Integer> counter = new HashMap<>();

    public HashMap<String, Integer> getCounter() {
        return counter;
    }

    public static void initialize() {
        counter.put("biomes", 0);
        counter.put("features", 0);
        counter.put("blocks", 0);
    }

    public static void countBiome() {
        bump("biomes");
    }
    public static void countFeature() {
        bump("features");
    }
    public static void countBlock() {
        bump("blocks");
    }

    private static void bump(String key) {
        int oldCount = counter.get(key);
        counter.replace(key, ++oldCount);
    }

    public static String report() {
        return "Registered " + counter.get("blocks") + " new blocks, "
                + counter.get("features") + " new features, and "
                + counter.get("biomes") + " new biomes.";
    }

}

