package dev.necro.proxies.common;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import java.util.function.Function;

@Immutable
@ParametersAreNonnullByDefault
public class CapabilityPointer<T>{
    final private boolean isPresent;
    final private World world;
    final private BlockPos blockPos;
    final private Direction side;
    final private Function<T,T> wrapper;

    private CapabilityPointer() {
        this.isPresent = false;
        this.world = null;
        this.blockPos = null;
        this.side = null;
        this.wrapper = null;
    }

    private CapabilityPointer(World world, BlockPos blockPos, Direction side, @Nullable Function<T,T> wrapper) {
        this.isPresent = true;
        this.world = world;
        this.blockPos = blockPos;
        this.side = side;
        this.wrapper = wrapper;
    }

    public static <T> CapabilityPointer<T> empty(){
        return new CapabilityPointer<>();
    }

    public static <T> CapabilityPointer<T> of(World world, BlockPos blockPos, Direction side) {
        return new CapabilityPointer<>(world, blockPos, side, null);
    }
    public static <T> CapabilityPointer<T> of(World world, BlockPos blockPos, Direction side, Function<T,T> wrapper) {
        return new CapabilityPointer<>(world, blockPos, side, wrapper);
    }

    public World getWorld() { return this.world; }
    public BlockPos getBlockPos() { return this.blockPos; }
    public Direction getSide() { return this.side; }
    public boolean isPresent() { return this.isPresent; }


    public T wrap(T unwrapped) { return this.wrapper==null ? unwrapped : this.wrapper.apply(unwrapped); }

    @Nullable
    public TileEntity getTileEntity(){
        return this.getWorld().getTileEntity(this.getBlockPos());
    }
}
