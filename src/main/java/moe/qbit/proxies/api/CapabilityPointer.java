package moe.qbit.proxies.api;

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
    final private Direction accessedSide;
    final private Direction actualSide;
    final private Function<T,T> wrapper;

    private CapabilityPointer() {
        this.isPresent = false;
        this.world = null;
        this.blockPos = null;
        this.accessedSide = null;
        this.actualSide = null;
        this.wrapper = null;
    }

    private CapabilityPointer(World world, BlockPos blockPos, @Nullable Direction side, Direction actualSide, @Nullable Function<T, T> wrapper) {
        this.isPresent = true;
        this.world = world;
        this.blockPos = blockPos;
        this.accessedSide = side;
        this.actualSide = actualSide;
        this.wrapper = wrapper;
    }

    public static <T> CapabilityPointer<T> empty(){
        return new CapabilityPointer<>();
    }

    public static <T> CapabilityPointer<T> of(World world, BlockPos blockPos, @Nullable Direction side) {
        return new CapabilityPointer<>(world, blockPos, side, side, null);
    }
    public static <T> CapabilityPointer<T> of(World world, BlockPos blockPos, @Nullable Direction side, Function<T,T> wrapper) {
        return new CapabilityPointer<>(world, blockPos, side, side, wrapper);
    }

    public static <T> CapabilityPointer<T> of(World world, BlockPos blockPos, @Nullable Direction accessedSide, @Nullable Direction actualSide) {
        return new CapabilityPointer<>(world, blockPos, accessedSide, actualSide, null);
    }
    public static <T> CapabilityPointer<T> of(World world, BlockPos blockPos, @Nullable Direction accessedSide, @Nullable Direction actualSide, Function<T,T> wrapper) {
        return new CapabilityPointer<>(world, blockPos, accessedSide, actualSide, wrapper);
    }

    public World getWorld() { return this.world; }
    public BlockPos getBlockPos() { return this.blockPos; }
    public Direction getAccessedSide() { return this.accessedSide; }
    public Direction getActualSide() { return this.actualSide; }
    public boolean isPresent() { return this.isPresent; }


    public T wrap(T unwrapped) { return this.wrapper==null ? unwrapped : this.wrapper.apply(unwrapped); }

    @Nullable
    public TileEntity getTileEntity(){
        return this.getWorld().getTileEntity(this.getBlockPos());
    }
}
