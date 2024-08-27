package xyz.colmmurphy.mixin;

//import net.minecraft.world.level.storage
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.FarmlandBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.points.MethodHead;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import xyz.colmmurphy.Notramplefarmland;

@Mixin(value=FarmlandBlock.class, priority=1001)
public class FarmlandBlockMixin {
    @Unique
    private static final String MOD_ID = Notramplefarmland.MOD_ID;
    @Unique
    private static final Logger LOGGER = Notramplefarmland.LOGGER;

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
