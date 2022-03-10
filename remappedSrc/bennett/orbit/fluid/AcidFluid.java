package bennett.orbit.fluid;


import bennett.orbit.blocks.OrbitBlocks;
import bennett.orbit.tags.OrbitTags;
import bennett.orbit.items.OrbitItems;

import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;


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
	public BlockState createLegacyBlock(FluidState fluidState) {
		return OrbitBlocks.ACID.defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(fluidState));
	}

	public int getDropOff(LevelReader levelReader) {
		return 2;
	}


	protected boolean isRandomlyTicking(){return true;}

	public void randomTick(Level level, BlockPos blockPos, FluidState fluidState, Random random) {
		if (level.getGameRules().getBoolean(GameRules.RULE_DOFIRETICK)) {
			int i = random.nextInt(3);
			if (i > 0) {
				BlockPos blockPos2 = blockPos;

				for(int j = 0; j < i; ++j) {
					blockPos2 = blockPos2.offset(random.nextInt(3) - 1, 1, random.nextInt(3) - 1);
					if (!level.isLoaded(blockPos2)) {
						return;
					}

					BlockState blockState = level.getBlockState(blockPos2);
					if (blockState.isAir()) {
						if (this.hasCorrodibleNeighbors(level, blockPos2)) {
							level.setBlockAndUpdate(blockPos2, BaseFireBlock.getState(level, blockPos2));
							return;
						}
					} else if (blockState.getMaterial().blocksMotion()) {
						return;
					}
				}
			} else {
				for(int blockPos2 = 0; blockPos2 < 3; ++blockPos2) {
					BlockPos j = blockPos.offset(random.nextInt(3) - 1, 0, random.nextInt(3) - 1);
					if (!level.isLoaded(j)) {
						return;
					}

					if (level.isEmptyBlock(j.above()) && this.isCorrodible(level, j)) {
						Integer decider = random.nextInt(2) - 1;
						level.setBlockAndUpdate(j.above(), BaseFireBlock.getState(level, j));

					}
				}
			}

		}
	}



	public static boolean isCorrodible(Level level, BlockPos blockPos) {
		return OrbitTags.CORRODIBLE_BLOCKS.contains(level.getBlockState(blockPos).getBlock());
	}

	private boolean hasCorrodibleNeighbors(Level level, BlockPos blockPos) {
		Direction[] var3 = Direction.values();
		int var4 = var3.length;

		for(int var5 = 0; var5 < var4; ++var5) {
			Direction direction = var3[var5];
			if (this.isCorrodible(level, blockPos.relative(direction))) {
				return true;
			}
		}

		return false;
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

