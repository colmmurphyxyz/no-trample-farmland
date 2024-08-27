package xyz.colmmurphy.mixin;

import net.minecraft.block.FarmlandBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.slf4j.Logger;
import xyz.colmmurphy.NoTrampleFarmland;

@Mixin(value=FarmlandBlock.class, priority=1001)
public class FarmlandBlockMixin {
    @Unique
    private static final Logger LOGGER = NoTrampleFarmland.LOGGER;

    @Inject(
            at = @At("HEAD"),
            method = "setToDirt",
            cancellable = true
    )
    private static void setToDirt(CallbackInfo callbackInfo) {
        LOGGER.info("FarmLand Mixin Called");
        if (callbackInfo.isCancellable() && !callbackInfo.isCancelled()) {
            LOGGER.info("Cancelling");
            callbackInfo.cancel();
        } else {
            LOGGER.info("Could Not Cancel");
        }
    }
}
