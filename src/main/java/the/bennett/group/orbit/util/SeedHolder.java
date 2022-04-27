package the.bennett.group.orbit.util;

public  class SeedHolder {
    /**
     * World seed for worldgen when not specified by JSON by Haven King
     * <a href="https://github.com/Hephaestus-Dev/seedy-behavior/blob/master/src/main/java/dev/hephaestus/seedy/mixin/world/gen/GeneratorOptionsMixin.java">https://github.com/Hephaestus-Dev/seedy-behavior/blob/master/src/main/java/dev/hephaestus/seedy/mixin/world/gen/GeneratorOptionsMixin.java</a>
     */
    private static long SEED = 0;

    public static long seed() {
        return SEED;
    }

    public static long setSeed(long seed) {
        SEED = seed;
        return seed;
    }
}
