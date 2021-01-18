package moe.qbit.proxies.api;

import jdk.nashorn.internal.ir.annotations.Immutable;
import net.minecraft.util.Direction;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;

public enum SideMapping {
    REGULAR,
    SIDED,
    NULLSIDED,
    JUNCTION,
    SIDED_JUNCTION;

    public MappedSide getMapping(@Nullable Direction facing, @Nullable Direction accessedSide, @Nullable Direction actualSide){
        switch (this){
            case REGULAR:
            default:
                //noinspection ConstantConditions
                return new MappedSide(facing, facing.getOpposite(), -1);
            case SIDED:
                return new MappedSide(facing, accessedSide, accessedSide!=null?accessedSide.ordinal():-1);
            case NULLSIDED:
                return new MappedSide(facing, null, -1);
            case JUNCTION:
                return actualSide==null ? new MappedSide(null, null, -1) : new MappedSide(actualSide.getOpposite(), actualSide, actualSide.ordinal());
            case SIDED_JUNCTION:
                return actualSide==null ? new MappedSide(null, null, -1) : new MappedSide(actualSide.getOpposite(), accessedSide, actualSide.ordinal()<<3+(accessedSide==null?7:accessedSide.ordinal()));
        }
    }

    @Immutable
    public static class MappedSide {
        private final Direction traversalDirection;
        private final Direction accessDirection;
        private final int cachingKey;

        public MappedSide(Direction traversalDirection, Direction accessDirection, int cachingKey) {
            this.traversalDirection = traversalDirection;
            this.accessDirection = accessDirection;
            this.cachingKey = cachingKey;
        }

        public Direction getTraversalDirection() {
            return traversalDirection;
        }

        public Direction getAccessDirection() {
            return accessDirection;
        }

        public int getCachingKey() {
            return this.cachingKey;
        }
    }
}
