package the.bennett.group.orbit.entity;

import net.minecraft.core.Registry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import the.bennett.group.orbit.Orbit;

public class OrbitEntities {
    //public static final EntityType<TestCow> TEST_COW_TYPE = register(new EntityType<>());
    public static void initialize() {

    }

    public static <E extends Entity> EntityType<E> register(EntityType<E> entity, String name) {
        return Registry.register(Registry.ENTITY_TYPE, Orbit.newId(name), entity);
    }
}
