package the.bennett.group.orbit.blocks.fluid;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import the.bennett.group.orbit.blocks.OrbitBlocks;
import the.bennett.group.orbit.items.OrbitItems;
import the.bennett.group.orbit.rules.OrbitRules;
import the.bennett.group.orbit.tags.OrbitTags;
import the.bennett.group.orbit.util.RegistryUtils;

import java.util.Random;


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
		if (level.getGameRules().getBoolean(OrbitRules.RULE_DO_CORROSION)) {
			int i = random.nextInt(3);
			if (i > 0) {
				BlockPos acidTargetedBlockPos = blockPos;

				for(int j = 0; j < i; ++j) {
					acidTargetedBlockPos = acidTargetedBlockPos.offset(random.nextInt(3) - 1, 1, random.nextInt(3) - 1);
					if (!level.isLoaded(acidTargetedBlockPos)) {
						return;
					}

					BlockState acidTargetedBlock = level.getBlockState(acidTargetedBlockPos);
					if (acidTargetedBlock.isAir()) {
						if (this.hasCorrodibleNeighbors(level, acidTargetedBlockPos)) {
							level.setBlockAndUpdate(acidTargetedBlockPos, BaseFireBlock.getState(level, acidTargetedBlockPos));
							return;
						}
					} else if (acidTargetedBlock.getMaterial().blocksMotion()) {
						return;
					}
				}
			} else {
				for(int j = 0; j < 3; ++j) { //3 attempts to set nearby block on fire TODO: corrosion
					BlockPos pos = blockPos.offset(random.nextInt(3) - 1, 0, random.nextInt(3) - 1);
					if (!level.isLoaded(pos)) {
						return;
					}

					if (level.isEmptyBlock(pos.above()) && isCorrodible(level, pos)) {
						Integer decider = random.nextInt(2) - 1;
						level.setBlockAndUpdate(pos.above(), BaseFireBlock.getState(level, pos));

					}
				}
			}

		}
	}



	public static boolean isCorrodible(Level level, BlockPos blockPos) {
		Block currentBlock = level.getBlockState(blockPos).getBlock();
		return Registry.BLOCK.getOrCreateTag(OrbitTags.CORRODIBLE_BLOCKS).contains(RegistryUtils.findHolder(currentBlock, Registry.BLOCK));
	}

	private boolean hasCorrodibleNeighbors(Level level, BlockPos blockPos) {
		Direction[] directions = Direction.values();

		for (Direction direction : directions) {
			if (isCorrodible(level, blockPos.relative(direction))) {
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

