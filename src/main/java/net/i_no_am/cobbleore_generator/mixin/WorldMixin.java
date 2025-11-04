package net.i_no_am.cobbleore_generator.mixin;

import net.i_no_am.cobbleore_generator.CobbleoreGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(World.class)
public class WorldMixin {

    /**
     * Intercept when a block state is being set and replace cobblestone with ores
     * This catches cobblestone generation from fluid interactions
     * BUT excludes piston movements
     */
    @ModifyVariable(
        method = "setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;II)Z",
        at = @At("HEAD"),
        argsOnly = true,
        ordinal = 0
    )
    private BlockState modifyCobblestoneToOre(BlockState state, BlockPos pos, BlockState stateAgain, int flags) {
        World world = (World) (Object) this;
        
        // Only run on server side
        if (world.isClient()) {
            return state;
        }
        
        // Check if the block being placed is cobblestone
        if (state.getBlock() == Blocks.COBBLESTONE) {
            // Skip if this is a piston movement
            // Pistons typically use flags: 2 (NOTIFY_LISTENERS), 18 (NOTIFY_LISTENERS | NO_OBSERVER), or 82
            // Flag 2 = Block.NOTIFY_LISTENERS
            // Flag 16 = Block.NO_OBSERVER  
            // Flag 64 = Block.MOVED (piston specific)
            // Piston movements use combinations like 2, 18 (2|16), or 82 (2|16|64)
            
            // Check if MOVED flag (64) is set - this is piston-specific
            boolean isPistonMove = (flags & 64) != 0;
            
            // Also check for typical piston flag combinations
            // Flags 2, 18, or 82 without other flags often indicate piston movement
            boolean isLikelyPiston = (flags == 2 || flags == 18 || flags == 82);
            
            if (isPistonMove || isLikelyPiston) {
                // This is a piston movement, don't convert
                return state;
            }
            
            // Roll for ore generation (25% chance)
            if (CobbleoreGenerator.shouldGenerateOre()) {
                Block oreBlock = CobbleoreGenerator.getRandomBlock();
                CobbleoreGenerator.LOGGER.info("Converting cobblestone to {} at {} (flags: {})", oreBlock, pos, flags);
                return oreBlock.getDefaultState();
            }
        }
        
        return state;
    }
}
