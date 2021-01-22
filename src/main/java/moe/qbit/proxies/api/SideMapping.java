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
            case SIDED:
                return new MappedSide(facing, accessedSide, accessedSide);
            case NULLSIDED:
                return new MappedSide(facing, null, 0);
            case JUNCTION:
                return new MappedSide(actualSide.getOpposite(), actualSide, actualSide);
            case SIDED_JUNCTION:
                return new MappedSide(actualSide.getOpposite(), accessedSide, accessedSide, actualSide);
            case REGULAR:
            default:
                //noinspection ConstantConditions
                return new MappedSide(facing, facing.getOpposite(), 0);
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

        public MappedSide(Direction traversalDirection, Direction accessDirection, Direction... cachingDirections) {
            this(traversalDirection, accessDirection, directionsToNumber(cachingDirections));
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

        public static int directionsToNumber(Direction... directions){
            int ret = 0;

            for(Direction dir : directions){
                ret += dir!=null?dir.ordinal():6;
                ret <<= 3;
            }
            ret >>= 3;
            return ret;
        }
    }
}
