package rbt.orbit.fluid;


import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import rbt.orbit.blocks.OrbitBlocks;
import rbt.orbit.items.OrbitItems;


public abstract class AcidFluid extends BaseOrbitFluid {

	@Override
	public Fluid getSource() {
		return OrbitFluids.SOURCE_ACID;
	}

	@Override
	public Fluid getFlowing() {
		return OrbitFluids.FLOWING_ACID;
	}

	@Override
	public Item getBucket() {
		return OrbitItems.ACID_BUCKET;
	}

	@Override
	protected BlockState createLegacyBlock(FluidState fluidState) {
		return OrbitBlocks.ACID.defaultBlockState().setValue(LiquidBlock.LEVEL, AcidFluid.getLegacyLevel(fluidState));
	}

	public int getDropOff(LevelReader levelReader) {
		return 2;
	}

	public static class Flowing extends AcidFluid {
		@Override
		protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
			super.createFluidStateDefinition(builder);
			builder.add(LEVEL);
		}

		@Override
		public int getAmount(FluidState fluidState) {
			return fluidState.getValue(LEVEL);
		}

		@Override
		public boolean isSource(FluidState fluidState) {
			return false;
		}
	}

	public static class Source extends AcidFluid {
		@Override
		public int getAmount(FluidState fluidState) {
			return 8;
		}

		@Override
		public boolean isSource(FluidState fluidState) {
			return true;
		}
	}


}