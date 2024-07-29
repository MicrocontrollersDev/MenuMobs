package dev.microcontrollers.menumobs.util;

import net.minecraft.profiler.*;
import net.minecraft.world.biome.*;
import net.minecraft.block.state.*;
import net.minecraft.init.*;
import net.minecraft.tileentity.*;
import net.minecraft.block.*;
import net.minecraftforge.client.event.sound.*;
import net.minecraft.client.audio.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraft.block.material.*;
import net.minecraft.world.chunk.*;
import net.minecraft.world.*;
import javax.annotation.*;
import net.minecraft.crash.*;
import java.util.*;
import net.minecraft.nbt.*;
import net.minecraft.scoreboard.*;
import net.minecraft.entity.*;
import net.minecraftforge.client.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.chunk.storage.*;
import net.minecraft.world.storage.*;
import java.io.*;
import net.minecraft.util.*;

public class FakeWorld extends World
{
    public FakeWorld(final WorldInfo worldInfo) {
        super((ISaveHandler) new FakeSaveHandler(), worldInfo, (WorldProvider)new FakeWorldProvider(), new Profiler(), true);
    }

    public BiomeGenBase getBiomeGenForCoords(final BlockPos pos) {
        return BiomeGenBase.plains;
    }

    public BiomeGenBase getBiomeForCoordsBody(final BlockPos pos) {
        return BiomeGenBase.plains;
    }

    protected boolean isChunkLoaded(final int i, final int i1, final boolean b) {
        return false;
    }

    public BlockPos getTopSolidOrLiquidBlock(final BlockPos pos) {
        return new BlockPos(pos.getX(), 63, pos.getZ());
    }

    public boolean isAirBlock(final BlockPos pos) {
        return pos.getY() > 63;
    }

    public boolean setBlockState(final BlockPos pos, final IBlockState newState, final int flags) {
        return super.setBlockState(pos, newState, flags);
    }

    public IBlockState getBlockState(final BlockPos pos) {
        return (pos.getY() > 63) ? Blocks.air.getDefaultState() : Blocks.grass.getDefaultState();
    }

    public boolean setBlockState(final BlockPos pos, final IBlockState state) {
        return true;
    }

    public boolean setBlockToAir(final BlockPos pos) {
        return true;
    }

    public void markChunkDirty(final BlockPos pos, final TileEntity unusedTileEntity) {
    }

    public void notifyBlockUpdate(final BlockPos pos, final IBlockState oldState, final IBlockState newState, final int flags) {
    }

    public boolean destroyBlock(final BlockPos pos, final boolean dropBlock) {
        return this.isAirBlock(pos);
    }

    public void notifyNeighborsOfStateChange(final BlockPos pos, final Block blockType) {
    }

    public void notifyNeighborsOfStateExcept(final BlockPos pos, final Block blockType, final EnumFacing skipSide) {
    }

    public void notifyBlockOfStateChange(final BlockPos pos, final Block blockIn) {
    }

    public void markAndNotifyBlock(final BlockPos pos, final Chunk chunk, final IBlockState iblockstate, final IBlockState newState, final int flags) {
    }

    public void markBlocksDirtyVertical(final int par1, final int par2, final int par3, final int par4) {
    }

    public void markBlockRangeForRenderUpdate(final int x1, final int y1, final int z1, final int x2, final int y2, final int z2) {
    }

    public boolean isBlockTickPending(final BlockPos pos, final Block blockType) {
        return false;
    }

    public int getLightFromNeighbors(final BlockPos pos) {
        return 14;
    }

    public int getLight(final BlockPos pos, final boolean checkNeighbors) {
        return 14;
    }

    public int getLight(final BlockPos pos) {
        return 14;
    }

    public int getLightFor(final EnumSkyBlock type, final BlockPos pos) {
        return 14;
    }

    public int getLightFromNeighborsFor(final EnumSkyBlock type, final BlockPos pos) {
        return 14;
    }

    public boolean canBlockSeeSky(final BlockPos pos) {
        return pos.getY() > 62;
    }

    public BlockPos getHeight(final BlockPos pos) {
        return new BlockPos(pos.getX(), 63, pos.getZ());
    }

    public int getChunksLowestHorizon(final int x, final int z) {
        return 63;
    }

    protected void updateBlocks() {
    }

    public void markBlockRangeForRenderUpdate(final BlockPos rangeMin, final BlockPos rangeMax) {
    }

    public void setLightFor(final EnumSkyBlock type, final BlockPos pos, final int lightValue) {
    }

    public boolean isDaytime() {
        return true;
    }

    public void playSound(final EntityPlayer player, final BlockPos pos, final SoundEvent soundIn, final SoundCategory category, final float volume, final float pitch) {
    }

    public void playSound(final EntityPlayer player, final double x, final double y, final double z, final SoundEvent soundIn, final SoundCategory category, final float volume, final float pitch) {
    }

    public void playSound(final double x, final double y, final double z, final SoundEvent soundIn, final SoundCategory category, final float volume, final float pitch, final boolean distanceDelay) {
    }

    public void spawnParticle(final EnumParticleTypes particleType, final boolean p_175682_2_, final double xCoord, final double yCoord, final double zCoord, final double xOffset, final double yOffset, final double zOffset, final int... p_175682_15_) {
    }

    public void spawnParticle(final EnumParticleTypes particleType, final double xCoord, final double yCoord, final double zCoord, final double xOffset, final double yOffset, final double zOffset, final int... p_175688_14_) {
    }

    public void playRecord(final BlockPos blockPositionIn, final SoundEvent soundEventIn) {
    }

    public boolean addWeatherEffect(final Entity par1Entity) {
        return false;
    }

    public boolean spawnEntityInWorld(final Entity par1Entity) {
        return false;
    }

    public void onEntityAdded(final Entity par1Entity) {
    }

    public void onEntityRemoved(final Entity par1Entity) {
    }

    public void removeEntity(final Entity par1Entity) {
    }

    public void removeEntityDangerously(final Entity entityIn) {
    }

    public int calculateSkylightSubtracted(final float par1) {
        return 6;
    }

    @SideOnly(Side.CLIENT)
    public int getMoonPhase() {
        return super.getMoonPhase();
    }

    public float getCurrentMoonPhaseFactor() {
        return super.getCurrentMoonPhaseFactor();
    }

    public float getCelestialAngleRadians(final float par1) {
        return super.getCelestialAngleRadians(par1);
    }

    public void scheduleBlockUpdate(final BlockPos pos, final Block blockIn, final int delay, final int priority) {
    }

    @SideOnly(Side.CLIENT)
    public float getStarBrightness(final float par1) {
        return super.getStarBrightness(par1);
    }

    @SideOnly(Side.CLIENT)
    public float getStarBrightnessBody(final float par1) {
        return super.getStarBrightnessBody(par1);
    }

    public void updateEntities() {
    }

    public void updateEntity(final Entity par1Entity) {
    }

    public void updateEntityWithOptionalForce(final Entity par1Entity, final boolean par2) {
    }

    public boolean checkNoEntityCollision(final AxisAlignedBB bb) {
        return true;
    }

    public boolean checkNoEntityCollision(final AxisAlignedBB bb, final Entity entityIn) {
        return true;
    }

    public boolean checkBlockCollision(final AxisAlignedBB bb) {
        return false;
    }

    public boolean containsAnyLiquid(final AxisAlignedBB bb) {
        return false;
    }

    public boolean handleMaterialAcceleration(final AxisAlignedBB par1AxisAlignedBB, final Material par2Material, final Entity par3Entity) {
        return false;
    }

    public boolean isMaterialInBB(final AxisAlignedBB par1AxisAlignedBB, final Material par2Material) {
        return false;
    }

    public boolean isAABBInMaterial(final AxisAlignedBB par1AxisAlignedBB, final Material par2Material) {
        return false;
    }

    public Explosion createExplosion(final Entity par1Entity, final double par2, final double par4, final double par6, final float par8, final boolean par9) {
        return super.createExplosion(par1Entity, par2, par4, par6, par8, par9);
    }

    public Explosion newExplosion(final Entity par1Entity, final double par2, final double par4, final double par6, final float par8, final boolean par9, final boolean par10) {
        return super.newExplosion(par1Entity, par2, par4, par6, par8, par9, par10);
    }

    public float getBlockDensity(final Vec3 vec, final AxisAlignedBB bb) {
        return super.getBlockDensity(vec, bb);
    }

    public TileEntity getTileEntity(final BlockPos pos) {
        return null;
    }

    public boolean extinguishFire(final EntityPlayer player, final BlockPos pos, final EnumFacing side) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public String getDebugLoadedEntities() {
        return "";
    }

    @SideOnly(Side.CLIENT)
    public String getProviderName() {
        return "";
    }

    public void setTileEntity(final BlockPos pos, final TileEntity tileEntityIn) {
    }

    public void removeTileEntity(final BlockPos pos) {
    }

    public void markTileEntityForRemoval(final TileEntity tileEntityIn) {
    }

    public boolean isBlockFullCube(final BlockPos pos) {
        return super.isBlockFullCube(pos);
    }

    public boolean isBlockNormalCube(final BlockPos pos, final boolean _default) {
        return true;
    }

    public void calculateInitialSkylight() {
        super.calculateInitialSkylight();
    }

    public void setAllowedSpawnTypes(final boolean par1, final boolean par2) {
        super.setAllowedSpawnTypes(par1, par2);
    }

    public void tick() {
    }

    public void calculateInitialWeatherBody() {
        super.calculateInitialWeatherBody();
    }

    protected void updateWeather() {
    }

    public void updateWeatherBody() {
    }

    public boolean canBlockFreezeWater(final BlockPos pos) {
        return false;
    }

    public boolean canBlockFreezeNoWater(final BlockPos pos) {
        return false;
    }

    public boolean canBlockFreeze(final BlockPos pos, final boolean noWaterAdj) {
        return false;
    }

    public boolean canBlockFreezeBody(final BlockPos pos, final boolean noWaterAdj) {
        return false;
    }

    public boolean canSnowAt(final BlockPos pos, final boolean checkLight) {
        return false;
    }

    public boolean canSnowAtBody(final BlockPos pos, final boolean checkLight) {
        return false;
    }

    public boolean tickUpdates(final boolean par1) {
        return false;
    }

    public List getPendingBlockUpdates(final Chunk par1Chunk, final boolean par2) {
        return null;
    }

    public List getEntitiesWithinAABBExcludingEntity(final Entity par1Entity, final AxisAlignedBB par2AxisAlignedBB) {
        return super.getEntitiesWithinAABBExcludingEntity(par1Entity, par2AxisAlignedBB);
    }

    public List getEntitiesWithinAABB(final Class par1Class, final AxisAlignedBB par2AxisAlignedBB) {
        return super.getEntitiesWithinAABB(par1Class, par2AxisAlignedBB);
    }

    public Entity findNearestEntityWithinAABB(final Class par1Class, final AxisAlignedBB par2AxisAlignedBB, final Entity par3Entity) {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public List getLoadedEntityList() {
        return super.getLoadedEntityList();
    }

    public void loadEntities(final Collection<Entity> entityCollection) {
    }

    public void unloadEntities(final Collection<Entity> entityCollection) {
    }

    public int countEntities(final Class par1Class) {
        return 0;
    }

    public int getStrongPower(final BlockPos pos) {
        return 0;
    }

    public int getStrongPower(final BlockPos pos, final EnumFacing direction) {
        return 0;
    }

    public boolean isSidePowered(final BlockPos pos, final EnumFacing side) {
        return false;
    }

    public int getRedstonePower(final BlockPos pos, final EnumFacing facing) {
        return 0;
    }

    public boolean isBlockPowered(final BlockPos pos) {
        return false;
    }

    public int isBlockIndirectlyGettingPowered(final BlockPos pos) {
        return 0;
    }

    public EntityPlayer getClosestPlayerToEntity(final Entity par1Entity, final double par2) {
        return super.getClosestPlayerToEntity(par1Entity, par2);
    }

    public EntityPlayer getPlayerEntityByName(final String par1Str) {
        return super.getPlayerEntityByName(par1Str);
    }

    @SideOnly(Side.CLIENT)
    public void sendQuittingDisconnectingPacket() {
        super.sendQuittingDisconnectingPacket();
    }

    public void checkSessionLock() throws MinecraftException {
    }

    public long getSeed() {
        return 1L;
    }

    public long getTotalWorldTime() {
        return 1L;
    }

    public long getWorldTime() {
        return 1L;
    }

    public void setWorldTime(final long par1) {
    }

    public BlockPos getSpawnPoint() {
        return new BlockPos(0, 64, 0);
    }

    @SideOnly(Side.CLIENT)
    public void joinEntityInSurroundings(final Entity par1Entity) {
    }

    public boolean canSeeSky(final BlockPos pos) {
        return pos.getY() > 63;
    }

    public boolean canMineBlockBody(final EntityPlayer player, final BlockPos pos) {
        return false;
    }

    public void setEntityState(final Entity par1Entity, final byte par2) {
    }

    public IChunkProvider getChunkProvider() {
        return super.getChunkProvider();
    }

    public float getThunderStrength(final float delta) {
        return 0.0f;
    }

    public void addBlockEvent(final BlockPos pos, final Block blockIn, final int eventID, final int eventParam) {
    }

    public ISaveHandler getSaveHandler() {
        return super.getSaveHandler();
    }

    public WorldInfo getWorldInfo() {
        return super.getWorldInfo();
    }

    public GameRules getGameRules() {
        return super.getGameRules();
    }

    public void updateAllPlayersSleepingFlag() {
    }

    public boolean isRainingAt(final BlockPos strikePosition) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void setThunderStrength(final float strength) {
    }

    public float getRainStrength(final float par1) {
        return 0.0f;
    }

    @SideOnly(Side.CLIENT)
    public void setRainStrength(final float par1) {
    }

    public boolean isThundering() {
        return false;
    }

    public boolean isRaining() {
        return false;
    }

    public boolean isBlockinHighHumidity(final BlockPos pos) {
        return false;
    }

    public void setItemData(final String par1Str, final WorldSavedData par2WorldSavedData) {
    }

    public WorldSavedData loadItemData(final Class par1Class, final String par2Str) {
        return super.loadItemData(par1Class, par2Str);
    }

    public int getUniqueDataId(final String par1Str) {
        return super.getUniqueDataId(par1Str);
    }

    public void playBroadcastSound(final int p_175669_1_, final BlockPos pos, final int p_175669_3_) {
    }

    public void playEvent(@Nullable final EntityPlayer player, final int type, final BlockPos pos, final int data) {
    }

    public void playEvent(final int type, final BlockPos pos, final int data) {
    }

    public int getHeight() {
        return 256;
    }

    public int getActualHeight() {
        return 256;
    }

    public Random setRandomSeed(final int par1, final int par2, final int par3) {
        return super.setRandomSeed(par1, par2, par3);
    }

    @SideOnly(Side.CLIENT)
    public boolean extendedLevelsInChunkCache() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public double getHorizon() {
        return super.getHorizon();
    }

    public CrashReportCategory addWorldInfoToCrashReport(final CrashReport par1CrashReport) {
        return super.addWorldInfoToCrashReport(par1CrashReport);
    }

    public Calendar getCurrentDate() {
        return super.getCurrentDate();
    }

    @SideOnly(Side.CLIENT)
    public void makeFireworks(final double par1, final double par3, final double par5, final double par7, final double par9, final double par11, final NBTTagCompound par13nbtTagCompound) {
    }

    public Scoreboard getScoreboard() {
        return super.getScoreboard();
    }

    public boolean addTileEntity(final TileEntity tile) {
        return true;
    }

    public void addTileEntities(final Collection<TileEntity> tileEntityCollection) {
    }

    public boolean isSideSolid(final BlockPos pos, final EnumFacing side) {
        return pos.getY() <= 63;
    }

    public boolean isSideSolid(final BlockPos pos, final EnumFacing side, final boolean _default) {
        return pos.getY() <= 63;
    }

    public int countEntities(final EnumCreatureType type, final boolean forSpawnCount) {
        return 0;
    }

    protected IChunkProvider createChunkProvider() {
        return (IChunkProvider)new FakeChunkProvider();
    }

    public Entity getEntityByID(final int i) {
        return EntityList.createEntityByID(i, (World)this);
    }

    public Chunk getChunkFromChunkCoords(final int par1, final int par2) {
        return null;
    }

    protected int getRenderDistanceChunks() {
        return 0;
    }

    protected static class FakeWorldProvider extends WorldProvider
    {
        protected void generateLightBrightnessTable() {
        }

        public boolean isSurfaceWorld() {
            return true;
        }

        public boolean canRespawnHere() {
            return true;
        }

        public int getAverageGroundLevel() {
            return 63;
        }

        @SideOnly(Side.CLIENT)
        public double getVoidFogYFactor() {
            return super.getVoidFogYFactor();
        }

        @SideOnly(Side.CLIENT)
        public boolean doesXZShowFog(final int par1, final int par2) {
            return false;
        }

        public void setDimension(final int dim) {
        }

        public String getSaveFolder() {
            return null;
        }

        public String getWelcomeMessage() {
            return "";
        }

        public String getDepartMessage() {
            return "";
        }

        public double getMovementFactor() {
            return super.getMovementFactor();
        }

        @SideOnly(Side.CLIENT)
        public IRenderHandler getSkyRenderer() {
            return super.getSkyRenderer();
        }

        @SideOnly(Side.CLIENT)
        public void setSkyRenderer(final IRenderHandler skyRenderer) {
            super.setSkyRenderer(skyRenderer);
        }

        @SideOnly(Side.CLIENT)
        public IRenderHandler getCloudRenderer() {
            return super.getCloudRenderer();
        }

        @SideOnly(Side.CLIENT)
        public void setCloudRenderer(final IRenderHandler renderer) {
            super.setCloudRenderer(renderer);
        }

        @SideOnly(Side.CLIENT)
        public IRenderHandler getWeatherRenderer() {
            return super.getWeatherRenderer();
        }

        @SideOnly(Side.CLIENT)
        public void setWeatherRenderer(final IRenderHandler renderer) {
            super.setWeatherRenderer(renderer);
        }

        public BlockPos getRandomizedSpawnPoint() {
            return new BlockPos(0, 64, 0);
        }

        public boolean shouldMapSpin(final String entity, final double x, final double y, final double z) {
            return false;
        }

        public int getRespawnDimension(final EntityPlayerMP player) {
            return 0;
        }

        public boolean isDaytime() {
            return true;
        }

        @SideOnly(Side.CLIENT)
        public float getStarBrightness(final float par1) {
            return super.getStarBrightness(par1);
        }

        public void setAllowedSpawnTypes(final boolean allowHostile, final boolean allowPeaceful) {
        }

        public void calculateInitialWeather() {
        }

        public void updateWeather() {
        }

        public boolean canBlockFreeze(final BlockPos pos, final boolean byWater) {
            return false;
        }

        public boolean canSnowAt(final BlockPos pos, final boolean checkLight) {
            return false;
        }

        public long getSeed() {
            return 1L;
        }

        public long getWorldTime() {
            return 1L;
        }

        public void setWorldTime(final long time) {
        }

        public boolean canMineBlock(final EntityPlayer player, final BlockPos pos) {
            return false;
        }

        public boolean isBlockHighHumidity(final BlockPos pos) {
            return false;
        }

        public int getHeight() {
            return 256;
        }

        public int getActualHeight() {
            return 256;
        }

        public double getHorizon() {
            return super.getHorizon();
        }

        public void resetRainAndThunder() {
        }

        public boolean canDoLightning(final Chunk chunk) {
            return false;
        }

        public boolean canDoRainSnowIce(final Chunk chunk) {
            return false;
        }

        public BlockPos getSpawnPoint() {
            return new BlockPos(0, 64, 0);
        }

        public boolean canCoordinateBeSpawn(final int par1, final int par2) {
            return true;
        }

        public String getDimensionName() {
            return null;
        }

        public String getInternalNameSuffix() {
            return null;
        }
    }

    protected static class FakeSaveHandler implements ISaveHandler
    {
        public WorldInfo loadWorldInfo() {
            return null;
        }

        public void checkSessionLock() {
        }

        public IChunkLoader getChunkLoader(final WorldProvider var1) {
            return null;
        }

        public void saveWorldInfoWithPlayer(final WorldInfo var1, final NBTTagCompound var2) {
        }

        public void saveWorldInfo(final WorldInfo var1) {
        }

        public IPlayerFileData getPlayerNBTManager() {
            return null;
        }

        public void flush() {
        }

        public File getWorldDirectory() {
            return null;
        }

        public File getMapFileFromName(final String var1) {
            return null;
        }

        public String getWorldDirectoryName() {
            return null;
        }
    }

    public static class FakeChunkProvider implements IChunkProvider
    {
        public Chunk getLoadedChunk(final int x, final int z) {
            return null;
        }

        public Chunk provideChunk(final int var1, final int var2) {
            return null;
        }

        public boolean unloadQueuedChunks() {
            return false;
        }

        public String makeString() {
            return null;
        }

        public boolean chunkExists(final int x, final int z) {
            return false;
        }

        public Chunk provideChunk(final BlockPos blockPosIn) {
            return null;
        }

        public void populate(final IChunkProvider chunkProvider, final int x, final int z) {
        }

        public boolean populateChunk(final IChunkProvider chunkProvider, final Chunk chunkIn, final int x, final int z) {
            return false;
        }

        public boolean saveChunks(final boolean saveAllChunks, final IProgressUpdate progressCallback) {
            return false;
        }

        public boolean canSave() {
            return false;
        }

        public List<BiomeGenBase.SpawnListEntry> getPossibleCreatures(final EnumCreatureType creatureType, final BlockPos pos) {
            return null;
        }

        public BlockPos getStrongholdGen(final World worldIn, final String structureName, final BlockPos position) {
            return null;
        }

        public int getLoadedChunkCount() {
            return 0;
        }

        public void recreateStructures(final Chunk chunkIn, final int x, final int z) {
        }

        public void saveExtraData() {
        }
    }
}
