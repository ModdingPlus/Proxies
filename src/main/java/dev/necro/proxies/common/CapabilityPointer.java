package dev.necro.proxies.common;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.util.function.Function;

@Immutable
public class CapabilityPointer<T>{
    final private World world;
    final private BlockPos blockPos;
    final private Direction direction;
    final private Function<T,T> wrapper;

    public CapabilityPointer(World world, BlockPos blockPos, Direction direction) {
        this.world = world;
        this.blockPos = blockPos;
        this.direction = direction;
        this.wrapper = null;
    }

    public CapabilityPointer(World world, BlockPos blockPos, Direction direction, Function<T,T> wrapper) {
        this.world = world;
        this.blockPos = blockPos;
        this.direction = direction;
        this.wrapper = wrapper;
    }

    public World getWorld() { return this.world; }
    public BlockPos getBlockPos() { return this.blockPos; }
    public Direction getDirection() { return this.direction; }


    public T wrap(T unwrapped) { return this.wrapper==null ? unwrapped : this.wrapper.apply(unwrapped); }

    @Nullable
    public TileEntity getTileEntity(){
        this.world.getTileEntity(this.blockPos);
    }
}
