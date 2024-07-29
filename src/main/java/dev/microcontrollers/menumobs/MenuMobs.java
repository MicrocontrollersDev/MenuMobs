package dev.microcontrollers.menumobs;

import dev.microcontrollers.menumobs.config.MenuMobsConfig;
import dev.microcontrollers.menumobs.handler.CommonProxy;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = MenuMobs.MODID, name = MenuMobs.NAME, version = MenuMobs.VERSION)
public class MenuMobs {
    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";
    @Mod.Instance(MODID)
    public static MenuMobs INSTANCE;
    public static MenuMobsConfig config;
    @SidedProxy(clientSide = "dev.microcontrollers.menumobs.handler.ClientProxy", serverSide = "dev.microcontrollers.menumobs.handler.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        config = new MenuMobsConfig();
        FMLCommonHandler.instance().bus().register((Object) MenuMobs.INSTANCE);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        MenuMobs.proxy.registerMainMenuTickHandler();
    }

}
