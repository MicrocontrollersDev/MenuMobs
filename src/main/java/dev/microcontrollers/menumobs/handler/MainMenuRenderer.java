package dev.microcontrollers.menumobs.handler;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import dev.microcontrollers.menumobs.config.MenuMobsConfig;
import dev.microcontrollers.menumobs.util.EntityUtils;
import dev.microcontrollers.menumobs.util.FakeWorld;
import net.minecraftforge.fml.relauncher.*;
import net.minecraft.client.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.client.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.gui.*;
import org.lwjgl.input.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.nbt.*;
import net.minecraft.world.storage.*;
import net.minecraft.network.*;
import net.minecraft.stats.*;
import net.minecraft.entity.*;
import com.mojang.authlib.*;
import net.minecraft.client.network.*;
import javax.annotation.*;
import net.minecraft.client.entity.*;
import net.minecraft.entity.monster.*;
import net.minecraft.init.*;
import net.minecraftforge.fml.common.*;
import com.mojang.util.*;
import net.minecraft.item.*;
import java.util.*;
import net.minecraft.util.*;

@SideOnly(Side.CLIENT)
public class MainMenuRenderer {
    private static Minecraft mcClient;
    private static boolean isRegistered;
    private World world;
    private EntityLivingBase randMob;
    private GuiScreen savedScreen;
    private static List entityBlacklist;
    private static List<AbstractMap.SimpleEntry<UUID, String>> fallbackPlayerNames;
    private static ItemStack[] playerItems;
    private static ItemStack[] zombieItems;
    private static ItemStack[] skelItems;
    private static ItemStack[] pigzombItems;
    private static Random random;
    private static Set entities;
    private static Object[] entStrings;
    private static int id;
    private static boolean erroredOut;

    public MainMenuRenderer() {
        MainMenuRenderer.mcClient = FMLClientHandler.instance().getClient();
    }

    @SubscribeEvent
    public void onTick(final TickEvent.RenderTickEvent event) {
        if (MenuMobsConfig.enableMenuMobs && !MainMenuRenderer.erroredOut && MainMenuRenderer.mcClient.currentScreen instanceof GuiMainMenu) {
            if (MainMenuRenderer.mcClient.thePlayer == null || MainMenuRenderer.mcClient.thePlayer.worldObj == null || this.randMob == null) {
                this.init();
            }
            if (this.world != null && MainMenuRenderer.mcClient.thePlayer != null && this.randMob != null) {
                final ScaledResolution sr = new ScaledResolution(MainMenuRenderer.mcClient);
                final int mouseX = Mouse.getX() * sr.getScaledWidth() / MainMenuRenderer.mcClient.displayWidth;
                final int mouseY = sr.getScaledHeight() - Mouse.getY() * sr.getScaledHeight() / MainMenuRenderer.mcClient.displayHeight - 1;
                final int distanceToSide = (MainMenuRenderer.mcClient.currentScreen.width / 2 - 98) / 2;
                final float targetHeight = (float)(sr.getScaledHeight_double() / 5.0) / 1.8f;
                final float scale = EntityUtils.getEntityScale(this.randMob, targetHeight, 1.8f);
                EntityUtils.drawEntityOnScreen(distanceToSide, (int)(sr.getScaledHeight() / 2 + this.randMob.height * scale), scale, distanceToSide - mouseX, sr.getScaledHeight() / 2 + this.randMob.height * scale - this.randMob.height * scale * (this.randMob.getEyeHeight() / this.randMob.height) - mouseY, this.randMob);
                EntityUtils.drawEntityOnScreen(sr.getScaledWidth() - distanceToSide, (int)(sr.getScaledHeight() / 2 + MainMenuRenderer.mcClient.thePlayer.height * targetHeight), targetHeight, sr.getScaledWidth() - distanceToSide - mouseX, sr.getScaledHeight() / 2 + MainMenuRenderer.mcClient.thePlayer.height * targetHeight - MainMenuRenderer.mcClient.thePlayer.height * targetHeight * (MainMenuRenderer.mcClient.thePlayer.getEyeHeight() / MainMenuRenderer.mcClient.thePlayer.height) - mouseY, (EntityLivingBase) MainMenuRenderer.mcClient.thePlayer);
            }
        }
    }

    private void init() {
        try {
            final boolean createNewWorld = this.world == null;
            final WorldInfo worldInfo = new WorldInfo(new NBTTagCompound());
            if (createNewWorld) {
                this.world = new FakeWorld(worldInfo);
            }
            if (createNewWorld || MainMenuRenderer.mcClient.thePlayer == null) {
                MainMenuRenderer.mcClient.thePlayer = new EntityPlayerSP(MainMenuRenderer.mcClient, this.world, new NetHandlerPlayClient(MainMenuRenderer.mcClient, MainMenuRenderer.mcClient.currentScreen, new NetworkManager(EnumPacketDirection.CLIENTBOUND), MainMenuRenderer.mcClient.getSession().getProfile()), (StatFileWriter) null);
                MainMenuRenderer.mcClient.thePlayer.dimension = 0;
                MainMenuRenderer.mcClient.thePlayer.movementInput = (MovementInput) new MovementInputFromOptions(MainMenuRenderer.mcClient.gameSettings);
                MainMenuRenderer.mcClient.thePlayer.eyeHeight = 1.82f;
                setRandomMobItem((EntityLivingBase) MainMenuRenderer.mcClient.thePlayer);
            }
            if (createNewWorld || this.randMob == null) {
                this.randMob = EntityUtils.getRandomLivingEntity(this.world, MainMenuRenderer.entityBlacklist, 4, MainMenuRenderer.fallbackPlayerNames);
                setRandomMobItem(this.randMob);
            }
            MainMenuRenderer.mcClient.getRenderManager().cacheActiveRenderInfo(this.world, MainMenuRenderer.mcClient.fontRendererObj, (Entity) MainMenuRenderer.mcClient.thePlayer, (Entity) MainMenuRenderer.mcClient.thePlayer, MainMenuRenderer.mcClient.gameSettings, 0.0f);
            this.savedScreen = MainMenuRenderer.mcClient.currentScreen;
        }
        catch (Throwable e) {
            e.printStackTrace();
            MainMenuRenderer.erroredOut = true;
            MainMenuRenderer.mcClient.thePlayer = null;
            this.randMob = null;
            this.world = null;
        }
    }

    private static EntityLivingBase getNextEntity(final World world) {
        int tries = 0;
        Class clazz;
        do {
            if (++MainMenuRenderer.id >= MainMenuRenderer.entStrings.length) {
                MainMenuRenderer.id = 0;
            }
            clazz = EntityList.stringToClassMapping.get(MainMenuRenderer.entStrings[MainMenuRenderer.id]);
        } while (!EntityLivingBase.class.isAssignableFrom(clazz) && ++tries <= 5);
        if (!EntityLivingBase.class.isAssignableFrom(clazz)) {
            final AbstractMap.SimpleEntry<UUID, String> entry = MainMenuRenderer.fallbackPlayerNames.get(MainMenuRenderer.random.nextInt(MainMenuRenderer.fallbackPlayerNames.size()));
            final GameProfile gameProfile = MainMenuRenderer.mcClient.getSessionService().fillProfileProperties(new GameProfile((UUID)entry.getKey(), (String)entry.getValue()), true);
            final NetworkPlayerInfo networkPlayerInfo = new NetworkPlayerInfo(gameProfile);
            return (EntityLivingBase)new EntityOtherPlayerMP(world, gameProfile) {
                @Nullable
                protected NetworkPlayerInfo getPlayerInfo() {
                    return networkPlayerInfo;
                }
            };
        }
        return (EntityLivingBase) EntityList.createEntityByName((String) MainMenuRenderer.entStrings[MainMenuRenderer.id], world);
    }

    private static void setRandomMobItem(final EntityLivingBase ent) {
        try {
            if (ent instanceof AbstractClientPlayer) {
                ent.setCurrentItemOrArmor(0, MainMenuRenderer.playerItems[MainMenuRenderer.random.nextInt(MainMenuRenderer.playerItems.length)]);
            }
            else if (ent instanceof EntityZombie) {
                ent.setCurrentItemOrArmor(0, MainMenuRenderer.zombieItems[MainMenuRenderer.random.nextInt(MainMenuRenderer.zombieItems.length)]);
            }
            else if (ent instanceof EntitySkeleton) {
                if (MainMenuRenderer.random.nextBoolean()) {
                    ((EntitySkeleton) ent).setSkeletonType(1);
                    ent.setCurrentItemOrArmor(0, new ItemStack(Items.stone_sword));
                }
                else {
                    ent.setCurrentItemOrArmor(0, MainMenuRenderer.skelItems[MainMenuRenderer.random.nextInt(MainMenuRenderer.skelItems.length)]);
                }
            }
            else if (ent instanceof EntityPigZombie) {
                ent.setCurrentItemOrArmor(0, new ItemStack(Items.golden_sword));
            }
            else if (ent instanceof EntityEnderman) {
                ((EntityEnderman) ent).setHeldBlockState(Blocks.grass.getDefaultState());
            }
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void register() {
        if (!MainMenuRenderer.isRegistered) {
            FMLCommonHandler.instance().bus().register((Object)this);
            MainMenuRenderer.isRegistered = true;
        }
    }

    public void unRegister() {
        if (MainMenuRenderer.isRegistered) {
            FMLCommonHandler.instance().bus().unregister((Object)this);
            MainMenuRenderer.isRegistered = false;
            this.randMob = null;
            this.world = null;
            MainMenuRenderer.mcClient.thePlayer = null;
        }
    }

    public boolean isRegistered() {
        return MainMenuRenderer.isRegistered;
    }

    static {
        MainMenuRenderer.isRegistered = false;
        MainMenuRenderer.random = new Random();
        MainMenuRenderer.erroredOut = false;
        (MainMenuRenderer.entityBlacklist = new ArrayList()).add("Mob");
        MainMenuRenderer.entityBlacklist.add("Monster");
        MainMenuRenderer.entityBlacklist.add("EnderDragon");
        MainMenuRenderer.entityBlacklist.add("Squid");
        MainMenuRenderer.entityBlacklist.add("Ghast");
        MainMenuRenderer.entityBlacklist.add("Bat");
        MainMenuRenderer.entityBlacklist.add("CaveSpider");
        MainMenuRenderer.entityBlacklist.add("Giant");
        MainMenuRenderer.entityBlacklist.add("ArmorStand");
        MainMenuRenderer.entityBlacklist.add("MillBlaze");
        MainMenuRenderer.entityBlacklist.add("MillGhast");
        MainMenuRenderer.entityBlacklist.add("MillWitherSkeleton");
        MainMenuRenderer.entityBlacklist.add("ml_GenericAsimmFemale");
        MainMenuRenderer.entityBlacklist.add("ml_GenericSimmFemale");
        MainMenuRenderer.entityBlacklist.add("ml_GenericVillager");
        MainMenuRenderer.entityBlacklist.add("BiomesOPlenty.Phantom");
        MainMenuRenderer.entityBlacklist.add("Forestry.butterflyGE");
        MainMenuRenderer.entityBlacklist.add("TConstruct.Crystal");
        MainMenuRenderer.entityBlacklist.add("Thaumcraft.Firebat");
        MainMenuRenderer.entityBlacklist.add("Thaumcraft.TaintSpore");
        MainMenuRenderer.entityBlacklist.add("Thaumcraft.TaintSwarm");
        MainMenuRenderer.entityBlacklist.add("Thaumcraft.Taintacle");
        MainMenuRenderer.entityBlacklist.add("Thaumcraft.TaintacleTiny");
        MainMenuRenderer.entityBlacklist.add("Thaumcraft.Wisp");
        MainMenuRenderer.entityBlacklist.add("TwilightForest.Boggard");
        MainMenuRenderer.entityBlacklist.add("TwilightForest.Firefly");
        MainMenuRenderer.entityBlacklist.add("TwilightForest.Helmet Crab");
        MainMenuRenderer.entityBlacklist.add("TwilightForest.Hydra");
        MainMenuRenderer.entityBlacklist.add("TwilightForest.HydraHead");
        MainMenuRenderer.entityBlacklist.add("TwilightForest.Lower Goblin Knight");
        MainMenuRenderer.entityBlacklist.add("TwilightForest.Mist Wolf");
        MainMenuRenderer.entityBlacklist.add("TwilightForest.Mosquito Swarm");
        MainMenuRenderer.entityBlacklist.add("TwilightForest.Upper Goblin Knight");
        (MainMenuRenderer.fallbackPlayerNames = new ArrayList<AbstractMap.SimpleEntry<UUID, String>>()).add(new AbstractMap.SimpleEntry<UUID, String>(UUIDTypeAdapter.fromString("92d459067a50474285b6b079db9dc189"), "bspkrs"));
        MainMenuRenderer.fallbackPlayerNames.add(new AbstractMap.SimpleEntry<UUID, String>(UUIDTypeAdapter.fromString("069a79f444e94726a5befca90e38aaf5"), "Notch"));
        MainMenuRenderer.fallbackPlayerNames.add(new AbstractMap.SimpleEntry<UUID, String>(UUIDTypeAdapter.fromString("ad371f407c8144c0bea559fdf019384f"), "Microcontrollers"));
        MainMenuRenderer.fallbackPlayerNames.add(new AbstractMap.SimpleEntry<UUID, String>(UUIDTypeAdapter.fromString("9605c62ea04c4e6c92f38b952af799b2"), "Squidjamin"));
        MainMenuRenderer.fallbackPlayerNames.add(new AbstractMap.SimpleEntry<UUID, String>(UUIDTypeAdapter.fromString("c4d341cbb16f42f5ae38511f5bc1bce5"), "taffyyyyy"));
        MainMenuRenderer.fallbackPlayerNames.add(new AbstractMap.SimpleEntry<UUID, String>(UUIDTypeAdapter.fromString("8eccd5bd00a741be83a9692c205f7334"), "Neptune42069"));
        MainMenuRenderer.fallbackPlayerNames.add(new AbstractMap.SimpleEntry<UUID, String>(UUIDTypeAdapter.fromString("9d291aceb55d4974b78f677739e82578"), "hsauqS"));
        MainMenuRenderer.fallbackPlayerNames.add(new AbstractMap.SimpleEntry<UUID, String>(UUIDTypeAdapter.fromString("91881ccd63304703883ae6fc8b5ec572"), "pilac"));
        MainMenuRenderer.fallbackPlayerNames.add(new AbstractMap.SimpleEntry<UUID, String>(UUIDTypeAdapter.fromString("42cded91e45143c9bf7035ca06e36aa4"), "Aurobee"));
        MainMenuRenderer.fallbackPlayerNames.add(new AbstractMap.SimpleEntry<UUID, String>(UUIDTypeAdapter.fromString("661d27ec0d4e4128809aa218697523b2"), "_P4ND4B34R_"));
        MainMenuRenderer.playerItems = new ItemStack[] { new ItemStack(Items.iron_sword), new ItemStack(Items.diamond_sword), new ItemStack(Items.golden_sword), new ItemStack(Items.diamond_pickaxe), new ItemStack(Items.iron_pickaxe), new ItemStack(Items.iron_axe) };
        MainMenuRenderer.zombieItems = new ItemStack[] { new ItemStack(Items.iron_sword), new ItemStack(Items.diamond_sword), new ItemStack(Items.golden_sword), new ItemStack(Items.iron_axe) };
        MainMenuRenderer.skelItems = new ItemStack[] { new ItemStack((Item)Items.bow), new ItemStack(Items.golden_sword), new ItemStack((Item)Items.bow), new ItemStack((Item)Items.bow), new ItemStack((Item)Items.bow), new ItemStack((Item)Items.bow) };
        MainMenuRenderer.pigzombItems = new ItemStack[] { new ItemStack(Items.golden_sword) };
        (MainMenuRenderer.entities = new TreeSet(EntityList.stringToClassMapping.keySet())).removeAll(MainMenuRenderer.entityBlacklist);
        MainMenuRenderer.entStrings = MainMenuRenderer.entities.toArray(new Object[0]);
        MainMenuRenderer.id = -1;
    }
}
