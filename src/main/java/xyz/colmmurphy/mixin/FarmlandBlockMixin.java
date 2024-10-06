package xyz.colmmurphy.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.slf4j.Logger;
import xyz.colmmurphy.NoTrampleFarmland;

@Mixin(value = FarmlandBlock.class, priority = 1001)
public class FarmlandBlockMixin {
    @Unique
    private static final Logger LOGGER = NoTrampleFarmland.LOGGER;

    @Inject(
            at = @At("HEAD"),
            method = "onLandedUpon",
            cancellable = true
    )
    private void onLandedUpon(
            World world,
            BlockState state,
            BlockPos pos,
            Entity entity,
            float fallDistance,
            CallbackInfo ci
    ) {
        if (ci.isCancellable() && !ci.isCancelled()) {
            LOGGER.info("Cancelling FarmlandBlock::onLandedUpon");
            ci.cancel();
            // fallDistance <= 0 ensures farmland will not be turned to dirt
            state.getBlock().onLandedUpon(world, state, pos, entity, 0.0f);
        }
    }
}
