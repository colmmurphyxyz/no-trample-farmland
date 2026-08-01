package xyz.colmmurphy.mixin;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.slf4j.Logger;
import xyz.colmmurphy.NoTrampleFarmland;

@Mixin(value = FarmlandBlock.class, priority = 1001)
public abstract class FarmlandBlockMixin extends Block {
    @Unique
    private static final Logger LOGGER = NoTrampleFarmland.LOGGER;

    public FarmlandBlockMixin(Properties properties) {
        super(properties);
    }

    @Redirect(method = "fallOn",
            at = @At(
                    value = "INVOKE",
                    target="Lnet/minecraft/world/level/block/FarmlandBlock;turnToDirt(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V"
            )
    )
    private static void turnToDirt(final @Nullable Entity sourceEntity, final BlockState state, final Level level, final BlockPos pos) {
        LOGGER.trace("Redirected call to FarmlandBlock::setToDirt");
    }

}
