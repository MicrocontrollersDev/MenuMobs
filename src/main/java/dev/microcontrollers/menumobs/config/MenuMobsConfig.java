package dev.microcontrollers.menumobs.config;

import dev.microcontrollers.menumobs.MenuMobs;
import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Dropdown;
import cc.polyfrost.oneconfig.config.annotations.Slider;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.config.data.OptionSize;

public class MenuMobsConfig extends Config {
    @Switch(
            name = "Enable Menu Mobs"
    )
    public static boolean enableMenuMobs = true;

//    @Slider(
//            name = "Example Slider",
//            min = 0f, max = 100f, // Minimum and maximum values for the slider.
//            step = 10 // The amount of steps that the slider should have.
//    )
//    public static float exampleSlider = 50f; // The default value for the float Slider.
//
//    @Dropdown(
//            name = "Example Dropdown", // Name of the Dropdown
//            options = {"Option 1", "Option 2", "Option 3", "Option 4"} // Options available.
//    )
//    public static int exampleDropdown = 1; // Default option (in this case "Option 2")

    public MenuMobsConfig() {
        super(new Mod(MenuMobs.NAME, ModType.UTIL_QOL), MenuMobs.MODID + ".json");
        initialize();
    }
}

