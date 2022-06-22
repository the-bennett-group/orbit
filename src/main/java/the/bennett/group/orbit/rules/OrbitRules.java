package the.bennett.group.orbit.rules;

import net.minecraft.world.level.GameRules;


public class OrbitRules {
    public static GameRules.Key<GameRules.BooleanValue> RULE_DO_CORROSION;
    public static GameRules.Key<GameRules.BooleanValue> RULE_DO_ACID_DAMAGE;
    
    public static void initialize() {
        RULE_DO_CORROSION = register("doCorrosionTick", GameRules.Category.UPDATES, GameRules.BooleanValue.create(true));
        RULE_DO_ACID_DAMAGE = register("doOrbitAcidDamage", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));
    }

    private static <T extends GameRules.Value<T>> GameRules.Key<T> register(String name, GameRules.Category category, GameRules.Type<T> type) {
        GameRules.Key<T> key = new GameRules.Key<T>(name, category);
        GameRules.Type<?> type2 = GameRules.GAME_RULE_TYPES.put(key, type);
        if (type2 != null) {
            throw new IllegalStateException("Duplicate game rule registration for " + name);
        } else {
            return key;
        }
    }
}
