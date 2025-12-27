package me.teychman.coordcopymod.Settings;

import net.minecraftforge.common.ForgeConfigSpec;

public class ModConfigs {

    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<String> COORDS_FORMAT;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("general");

        COORDS_FORMAT = builder
                .comment("Формат отображения координат")
                .define("coords_format", "X: {x}, Y: {y}, Z: {z}");

        builder.pop();
        SPEC = builder.build();
    }

}
