package com.oroarmor.config.testmod;

import java.io.File;
import java.util.List;

import com.oroarmor.config.Config;
import com.oroarmor.config.ConfigItem;
import com.oroarmor.config.ConfigItemGroup;
import net.minecraftforge.fml.loading.FMLLoader;

import static com.google.common.collect.ImmutableList.of;

public class TestConfig extends Config {
    public static final ConfigItemGroup mainGroup = new ConfigGroupLevel1();

    public static final List<ConfigItemGroup> configs = of(mainGroup);

    public TestConfig() {
        super(configs, new File(FMLLoader.getGamePath().resolve("config").toFile(), "oroarmor_config_testmod.json"), "oroarmor_config_testmod");
    }

    public static class ConfigGroupLevel1 extends ConfigItemGroup {
        public static final ConfigItem<EnumTest> testEnum = new ConfigItem<>("test_enum", EnumTest.A, "test_enum");
        public static final ConfigItem<Boolean> testItem = new ConfigItem<>("test_boolean", true, "test_boolean");

        public ConfigGroupLevel1() {
            super(of(new NestedGroup(), testItem, testEnum), "group");
        }

        public static class NestedGroup extends ConfigItemGroup {
            public static final ConfigItem<Integer> nestedItem = new ConfigItem<>("test_int", 0, "test_integer");

            public NestedGroup() {
                super(of(nestedItem, new TripleNested()), "nested");
            }

            public static class TripleNested extends ConfigItemGroup {
                public static final ConfigItem<String> testString = new ConfigItem<>("test_string", "Default", "test_string");

                public TripleNested() {
                    super(of(testString), "triple");
                }
            }
        }
    }
}
