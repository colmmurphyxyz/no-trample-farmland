package xyz.colmmurphy.mixin;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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

    public FarmlandBlockMixin(Settings settings) {
        super(settings);
    }

    @Redirect(method = "onLandedUpon",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/FarmlandBlock;setToDirt(Lnet/minecraft/entity/Entity;Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V"
            )
    )
    private static void redirectedSetToDirt(@Nullable Entity entity, BlockState state, World world, BlockPos pos) {
        LOGGER.trace("Redirected call to FarmlandBlock::setToDirt");
    }
}
