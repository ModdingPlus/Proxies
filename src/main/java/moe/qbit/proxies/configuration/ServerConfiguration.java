package moe.qbit.proxies.configuration;

import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class ServerConfiguration {

    public static class Configuration {
        public final ForgeConfigSpec.IntValue chain_length_limit;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> banned_proxy_targets;

        public Configuration(ForgeConfigSpec.Builder builder) {
            chain_length_limit = builder
                    .comment("The maximum length of proxy chains, lower values can save resources")
                    .defineInRange(
                            "chain_length_limit",
                            256,
                            0,
                            Integer.MAX_VALUE
                    );

            banned_proxy_targets = builder
                    .comment("IDs of tile entities that proxies can't interact with")
                    .defineList(
                            "banned_proxy_targets",
                            Lists::newArrayList,
                            o -> true
                    );
        }
    }

    public static final Configuration CONFIGURATION;
    public static final ForgeConfigSpec CONFIGURATION_SPEC;
    static {
        final Pair<Configuration, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Configuration::new);
        CONFIGURATION = specPair.getLeft();
        CONFIGURATION_SPEC = specPair.getRight();
    }
}
