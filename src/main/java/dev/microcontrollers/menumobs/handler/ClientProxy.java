package dev.microcontrollers.menumobs.handler;

import dev.microcontrollers.menumobs.config.MenuMobsConfig;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy {
    private MainMenuRenderer mainMenuTicker;

    @Override
    public void registerMainMenuTickHandler() {
        this.mainMenuTicker = new MainMenuRenderer();
        MinecraftForge.EVENT_BUS.register((Object) this);
    }

    @SubscribeEvent
    public void onGuiOpen(final GuiOpenEvent event) {
        if (MenuMobsConfig.enableMenuMobs) {
            if (event.gui instanceof GuiMainMenu && !this.mainMenuTicker.isRegistered()) {
                this.mainMenuTicker.register();
            }
            else if (this.mainMenuTicker.isRegistered()) {
                this.mainMenuTicker.unRegister();
            }
        }
    }
}
