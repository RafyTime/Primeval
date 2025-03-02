package net.cr24.primeval.block;

import net.cr24.primeval.PrimevalMain;
import net.cr24.primeval.block.entity.*;
import net.cr24.primeval.block.functional.*;
import net.cr24.primeval.fluid.PrimevalFluids;
import net.cr24.primeval.item.Size;
import net.cr24.primeval.item.Weight;
import net.cr24.primeval.item.WeightedBlockItem;
import net.cr24.primeval.screen.Primeval3x5ContainerScreen;
import net.cr24.primeval.screen.PrimevalContainerScreenHandler;
import net.cr24.primeval.world.gen.trunker.BirchTrunker;
import net.cr24.primeval.world.gen.trunker.OakTrunker;
import net.cr24.primeval.world.gen.trunker.SpruceTrunker;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;

import static net.cr24.primeval.item.PrimevalItems.PRIMEVAL_BLOCKS;

@SuppressWarnings("unused")
public class PrimevalBlocks {

    // Block Settings
    private static final FabricBlockSettings SETTINGS_SOIL = FabricBlockSettings.of(Material.SOIL, MapColor.DIRT_BROWN).strength(2.1f, 2.0f).sounds(BlockSoundGroup.GRAVEL);
    private static final FabricBlockSettings SETTINGS_TOUGH_SOIL = FabricBlockSettings.of(Material.SOIL, MapColor.DIRT_BROWN).strength(3f, 2.0f).sounds(BlockSoundGroup.GRAVEL);
    private static final FabricBlockSettings SETTINGS_GRASSY = FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.TERRACOTTA_GREEN).strength(2.5f, 2.0f).sounds(BlockSoundGroup.GRASS);
    private static final FabricBlockSettings SETTINGS_SAND = FabricBlockSettings.of(Material.AGGREGATE, MapColor.PALE_YELLOW).strength(1.8f, 2.0f).sounds(BlockSoundGroup.SAND);
    private static final FabricBlockSettings SETTINGS_STONE = FabricBlockSettings.of(Material.STONE, MapColor.DEEPSLATE_GRAY).strength(4.5f, 6.0f).requiresTool();
    private static final FabricBlockSettings SETTINGS_PLANT = FabricBlockSettings.of(Material.REPLACEABLE_PLANT, MapColor.GREEN).strength(0.05f, 0f).sounds(BlockSoundGroup.GRASS).noCollision();
    private static final FabricBlockSettings SETTINGS_LOG = FabricBlockSettings.of(Material.WOOD, MapColor.BROWN).strength(5.0f, 6.0f).sounds(BlockSoundGroup.WOOD).requiresTool();
    private static final FabricBlockSettings SETTINGS_TRUNK = FabricBlockSettings.of(Material.WOOD, MapColor.BROWN).strength(8.0f, 8.0f).sounds(BlockSoundGroup.WOOD).requiresTool();
    private static final FabricBlockSettings SETTINGS_FIRED_CLAY = FabricBlockSettings.of(Material.STONE, MapColor.ORANGE).strength(4.0f, 6.0f).sounds(BlockSoundGroup.STONE).requiresTool();
    private static final FabricBlockSettings SETTINGS_REFINED_WOOD = FabricBlockSettings.of(Material.WOOD, MapColor.OAK_TAN).strength(3.0f, 4.0f).sounds(BlockSoundGroup.WOOD).requiresTool();

    // Terrain blocks
    public static final Block DIRT = registerBlock("dirt", new SemiSupportedBlock(SETTINGS_SOIL, 0.2f), Weight.NORMAL, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final Block COARSE_DIRT = registerBlock("coarse_dirt", new SemiSupportedBlock(SETTINGS_SOIL, 0.2f), Weight.NORMAL, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final Block MUD = registerBlock("mud", new MudBlock(SETTINGS_SOIL.velocityMultiplier(0.4f), 0.25f), Weight.NORMAL, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final Block GRASSY_DIRT = registerBlock("grassy_dirt", new GrassyDirtBlock(SETTINGS_GRASSY.ticksRandomly(), 0.35f), Weight.NORMAL, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final Block SAND = registerBlock("sand", new SemiSupportedBlock(SETTINGS_SAND, 0.1f), Weight.NORMAL, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final Block GRAVEL = registerBlock("gravel", new SemiSupportedBlock(SETTINGS_SAND, 0.1f), Weight.NORMAL, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final Block CLAY_BLOCK = registerBlock("block_of_clay", new SemiSupportedBlock(SETTINGS_SOIL, 0.3f), Weight.NORMAL, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final Block COBBLESTONE = registerBlock("cobblestone", new SemiSupportedBlock(SETTINGS_STONE.strength(5.0f, 6.0f), 0.1f), Weight.HEAVY, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final Block STONE = registerBlock("stone", new CascadingBlock(SETTINGS_STONE, 0.35f, COBBLESTONE), Weight.HEAVY, Size.MEDIUM, PRIMEVAL_BLOCKS);

    //// Plant blocks
    // Oak Trees
    public static final Block OAK_LOG = registerBlock("oak_log", new PillarBlock(SETTINGS_LOG), Weight.HEAVY, Size.LARGE, PRIMEVAL_BLOCKS);
    public static final Block OAK_TRUNK = registerBlockWithoutItem("oak_trunk", new TrunkBlock(SETTINGS_TRUNK.nonOpaque(), OakTrunker.INSTANCE));
    public static final Block OAK_LEAVES = registerBlockWithoutItem("oak_leaves", new LeafBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES)));
    // Birch Trees
    public static final Block BIRCH_LOG = registerBlock("birch_log", new PillarBlock(SETTINGS_LOG), Weight.HEAVY, Size.LARGE, PRIMEVAL_BLOCKS);
    public static final Block BIRCH_TRUNK = registerBlockWithoutItem("birch_trunk", new TrunkBlock(SETTINGS_TRUNK.nonOpaque(), BirchTrunker.INSTANCE));
    public static final Block BIRCH_LEAVES = registerBlockWithoutItem("birch_leaves", new LeafBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES)));
    // Spruce Trees
    public static final Block SPRUCE_LOG = registerBlock("spruce_log", new PillarBlock(SETTINGS_LOG), Weight.HEAVY, Size.LARGE, PRIMEVAL_BLOCKS);
    public static final Block SPRUCE_TRUNK = registerBlockWithoutItem("spruce_trunk", new TrunkBlock(SETTINGS_TRUNK.nonOpaque(), SpruceTrunker.INSTANCE));
    public static final Block SPRUCE_LEAVES = registerBlockWithoutItem("spruce_leaves", new LeafBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES)));
    // Saplings+
    public static final Block OAK_SAPLING = registerBlock("oak_sapling", new GrowingSaplingBlock(SETTINGS_PLANT.ticksRandomly(), OakTrunker.INSTANCE), Weight.HEAVY, Size.LARGE, PRIMEVAL_BLOCKS);
    public static final Block BIRCH_SAPLING = registerBlock("birch_sapling", new GrowingSaplingBlock(SETTINGS_PLANT.ticksRandomly(), BirchTrunker.INSTANCE), Weight.HEAVY, Size.LARGE, PRIMEVAL_BLOCKS);
    public static final Block SPRUCE_SAPLING = registerBlock("spruce_sapling", new GrowingSaplingBlock(SETTINGS_PLANT.ticksRandomly(), SpruceTrunker.INSTANCE), Weight.HEAVY, Size.LARGE, PRIMEVAL_BLOCKS);
    public static final Block GRASS = registerBlock("grass", new GrowingGrassBlock(SETTINGS_PLANT.ticksRandomly()), Weight.VERY_LIGHT, Size.SMALL, PRIMEVAL_BLOCKS);
    public static final Block BUSH = registerBlock("bush", new PrimevalPlantBlock(SETTINGS_PLANT), Weight.VERY_LIGHT, Size.SMALL, PRIMEVAL_BLOCKS);
    public static final Block SHRUB = registerBlock("shrub", new PrimevalPlantBlock(SETTINGS_PLANT), Weight.LIGHT, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final Block MOSS = registerBlock("moss", new SpreadingMossBlock(SETTINGS_PLANT.ticksRandomly()), Weight.VERY_LIGHT, Size.SMALL, PRIMEVAL_BLOCKS);
    // Flowers
    public static final Block POPPY = registerBlock("poppy", new PrimevalPlantBlock(SETTINGS_PLANT), Weight.VERY_LIGHT, Size.SMALL, PRIMEVAL_BLOCKS);
    public static final Block DANDELION = registerBlock("dandelion", new PrimevalPlantBlock(SETTINGS_PLANT), Weight.VERY_LIGHT, Size.SMALL, PRIMEVAL_BLOCKS);
    public static final Block OXEYE_DAISY = registerBlock("oxeye_daisy", new PrimevalPlantBlock(SETTINGS_PLANT), Weight.VERY_LIGHT, Size.SMALL, PRIMEVAL_BLOCKS);

    public static final Block WILD_CARROTS = registerBlockWithoutItem("wild_carrots", new PrimevalPlantBlock(SETTINGS_PLANT));

    // Ore blocks
    public static final OreBlockSet COPPER_MALACHITE_ORE = registerOreBlockSet("copper_malachite_ore", SETTINGS_STONE, Weight.HEAVY, Size.LARGE, PRIMEVAL_BLOCKS);
    public static final OreBlockSet COPPER_NATIVE_ORE = registerOreBlockSet("copper_native_ore", SETTINGS_STONE, Weight.HEAVY, Size.LARGE, PRIMEVAL_BLOCKS);
    public static final OreBlockSet TIN_CASSITERITE_ORE = registerOreBlockSet("tin_cassiterite_ore", SETTINGS_STONE, Weight.HEAVY, Size.LARGE, PRIMEVAL_BLOCKS);
    public static final OreBlockSet ZINC_SPHALERITE_ORE = registerOreBlockSet("zinc_sphalerite_ore", SETTINGS_STONE, Weight.HEAVY, Size.LARGE, PRIMEVAL_BLOCKS);
    public static final OreBlockSet IRON_HEMATITE_ORE = registerOreBlockSet("iron_hematite_ore", SETTINGS_STONE, Weight.HEAVY, Size.LARGE, PRIMEVAL_BLOCKS);

    // Crafted Blocks
    public static final Block STRAW_BLOCK = registerBlock("straw_block", new PillarBlock(FabricBlockSettings.of(Material.PLANT).strength(0.5F).sounds(BlockSoundGroup.GRASS)), Weight.LIGHT, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final Block STRAW_STAIRS = registerBlock("straw_stairs", new PrimevalStairsBlock(STRAW_BLOCK.getDefaultState(), FabricBlockSettings.of(Material.PLANT).strength(0.5F).sounds(BlockSoundGroup.GRASS)), Weight.LIGHT, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final Block STRAW_SLAB = registerBlock("straw_slab", new SlabBlock(FabricBlockSettings.of(Material.PLANT).strength(0.5F).sounds(BlockSoundGroup.GRASS)), Weight.LIGHT, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final Block STRAW_MESH = registerBlock("straw_mesh", new Block(FabricBlockSettings.of(Material.PLANT).strength(0.5F).sounds(BlockSoundGroup.GRASS)), Weight.LIGHT, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final Block STRAW_MAT = registerBlock("straw_mat", new CarpetBlock(FabricBlockSettings.of(Material.PLANT).strength(0.3F).sounds(BlockSoundGroup.GRASS)), Weight.LIGHT, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final Block TERRACOTTA = registerBlock("terracotta", new Block(SETTINGS_FIRED_CLAY), Weight.HEAVY, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final BlockSet FIRED_CLAY_SHINGLE_BLOCKS = registerBlockSet("fired_clay_shingles", SETTINGS_FIRED_CLAY, Weight.HEAVY, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final BlockSet FIRED_CLAY_BRICK_BLOCKS = registerBlockSet("fired_clay_bricks", SETTINGS_FIRED_CLAY, Weight.HEAVY, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final BlockSet FIRED_CLAY_TILES_BLOCKS = registerBlockSet("fired_clay_tiles", SETTINGS_FIRED_CLAY, Weight.HEAVY, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final BlockSet DRIED_BRICK_BLOCKS = registerBlockSet("dried_bricks", SETTINGS_FIRED_CLAY, Weight.HEAVY, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final BlockSet MUD_BRICKS = registerBlockSet("mud_bricks", SETTINGS_TOUGH_SOIL, Weight.NORMAL, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final BlockSet STONE_BRICKS = registerBlockSet("stone_bricks", SETTINGS_STONE, Weight.HEAVY, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final BlockSet SMOOTH_STONE = registerBlockSet("smooth_stone", SETTINGS_STONE, Weight.HEAVY, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final Block DAUB = registerBlock("daub", new Block(SETTINGS_REFINED_WOOD), Weight.NORMAL, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final Block FRAMED_DAUB = registerBlock("framed_daub", new Block(SETTINGS_REFINED_WOOD), Weight.NORMAL, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final Block FRAMED_PILLAR_DAUB = registerBlock("framed_pillar_daub", new PillarBlock(SETTINGS_REFINED_WOOD), Weight.NORMAL, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final Block FRAMED_CROSS_DAUB = registerBlock("framed_cross_daub", new Block(SETTINGS_REFINED_WOOD), Weight.NORMAL, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final Block FRAMED_INVERTED_CROSS_DAUB = registerBlock("framed_inverted_cross_daub", new Block(SETTINGS_REFINED_WOOD), Weight.NORMAL, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final Block FRAMED_X_DAUB = registerBlock("framed_x_daub", new Block(SETTINGS_REFINED_WOOD), Weight.NORMAL, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final Block FRAMED_PLUS_DAUB = registerBlock("framed_plus_daub", new Block(SETTINGS_REFINED_WOOD), Weight.NORMAL, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final Block FRAMED_DIVIDED_DAUB = registerBlock("framed_divided_daub", new Block(SETTINGS_REFINED_WOOD), Weight.NORMAL, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final WoodBlockSet OAK_PLANK_BLOCKS = registerWoodBlockSet("oak", FabricBlockSettings.of(Material.WOOD, MapColor.OAK_TAN).strength(3.0f, 4.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), Weight.NORMAL, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final WoodBlockSet BIRCH_PLANK_BLOCKS = registerWoodBlockSet("birch", FabricBlockSettings.of(Material.WOOD, MapColor.YELLOW).strength(3.0f, 4.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), Weight.NORMAL, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final WoodBlockSet SPRUCE_PLANK_BLOCKS = registerWoodBlockSet("spruce", FabricBlockSettings.of(Material.WOOD, MapColor.DIRT_BROWN).strength(3.0f, 4.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), Weight.NORMAL, Size.MEDIUM, PRIMEVAL_BLOCKS);
    public static final Block ROPE = registerBlock("rope", new ChainBlock(FabricBlockSettings.of(Material.PLANT).strength(0.2F).sounds(BlockSoundGroup.GRASS)), Weight.LIGHT, Size.SMALL, PRIMEVAL_BLOCKS);
    public static final Block ROPE_LADDER = registerBlock("rope_ladder", new SuspendedLadderBlock(FabricBlockSettings.of(Material.WOOD, MapColor.OAK_TAN).strength(0.3F).sounds(BlockSoundGroup.WOOD).nonOpaque()), Weight.NORMAL, Size.MEDIUM, PRIMEVAL_BLOCKS);

    // Technical Blocks or Blocks with other BlockItems than themselves
    public static final Block STRAW_PILE = registerBlockWithoutItem("straw", new StrawLayeredBlock(FabricBlockSettings.of(Material.PLANT).strength(0.5F).sounds(BlockSoundGroup.GRASS)));
    public static final Block ASH_PILE = registerBlockWithoutItem("ash_pile", new AshPileBlock(FabricBlockSettings.of(Material.AGGREGATE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block LAYING_ITEM = registerBlockWithoutItem("laying_item", new LayingItemBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().nonOpaque().breakInstantly()));
    public static final Block OAK_CRATE = registerBlock("oak_crate", new CrateBlock(SETTINGS_REFINED_WOOD), Weight.HEAVY, Size.LARGE, PRIMEVAL_BLOCKS);
    public static final Block BIRCH_CRATE = registerBlock("birch_crate", new CrateBlock(SETTINGS_REFINED_WOOD), Weight.HEAVY, Size.LARGE, PRIMEVAL_BLOCKS);
    public static final Block SPRUCE_CRATE = registerBlock("spruce_crate", new CrateBlock(SETTINGS_REFINED_WOOD), Weight.HEAVY, Size.LARGE, PRIMEVAL_BLOCKS);

    public static final Block LARGE_CLAY_POT = registerBlock("large_clay_pot", new DecorativePotBlock(SETTINGS_SOIL.nonOpaque()), Weight.HEAVY, Size.LARGE, PRIMEVAL_BLOCKS);
    public static final Block LARGE_FIRED_CLAY_POT = registerBlock("fired_large_clay_pot", new StoragePotBlock(SETTINGS_FIRED_CLAY.nonOpaque()), Weight.HEAVY, Size.LARGE, PRIMEVAL_BLOCKS);
    public static final Block LARGE_DECORATIVE_FIRED_CLAY_POT = registerBlock("fired_large_decorative_clay_pot", new DecorativePotBlock(SETTINGS_FIRED_CLAY.nonOpaque()), Weight.HEAVY, Size.LARGE, PRIMEVAL_BLOCKS);


    // Intractable Blocks
    public static final Block PIT_KILN = registerBlockWithoutItem("pit_kiln", new PitKilnBlock(FabricBlockSettings.of(Material.PLANT).strength(1.0F).sounds(BlockSoundGroup.GRASS).nonOpaque()));
    public static final Block CRUDE_CRAFTING_BENCH = registerBlock("crude_crafting_bench", new PrimevalCraftingTableBlock(SETTINGS_REFINED_WOOD), Weight.HEAVY, Size.LARGE, PRIMEVAL_BLOCKS);
    public static final Block CRUDE_TORCH = registerBlock("crude_torch", new TimedTorchBlock(FabricBlockSettings.of(Material.WOOD, MapColor.OAK_TAN).sounds(BlockSoundGroup.WOOD).breakInstantly().noCollision().luminance(TimedTorchBlock::getLuminanceFromState).ticksRandomly()), Weight.LIGHT, Size.SMALL, PRIMEVAL_BLOCKS);
    public static final Item LIT_CRUDE_TORCH = Registry.register(Registry.ITEM, PrimevalMain.getId("crude_torch_lit"), new WeightedBlockItem(CRUDE_TORCH, new FabricItemSettings().maxCount(Size.SMALL.getStackSize()), Weight.LIGHT, Size.SMALL));
    public static final Block CAMPFIRE = registerBlock("campfire", new PrimevalCampfireBlock(SETTINGS_STONE.luminance(PrimevalCampfireBlock::getLuminanceFromState).nonOpaque()), Weight.HEAVY, Size.LARGE, null);

    // Fluid Blocks
    public static final Block MOLTEN_COPPER = registerMoltenFluid("molten_copper", PrimevalFluids.MOLTEN_COPPER);
    public static final Block MOLTEN_TIN = registerMoltenFluid("molten_tin", PrimevalFluids.MOLTEN_TIN);
    public static final Block MOLTEN_ZINC = registerMoltenFluid("molten_zinc", PrimevalFluids.MOLTEN_ZINC);

    public static final Block MOLTEN_BRONZE = registerMoltenFluid("molten_bronze", PrimevalFluids.MOLTEN_BRONZE);
    public static final Block MOLTEN_BRASS = registerMoltenFluid("molten_brass", PrimevalFluids.MOLTEN_BRASS);
    public static final Block MOLTEN_BOTCHED_ALLOY = registerMoltenFluid("molten_botched_alloy", PrimevalFluids.MOLTEN_BOTCHED_ALLOY);


    // Block entities
    public static final BlockEntityType<PitKilnBlockEntity> PIT_KILN_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, PrimevalMain.getId("pit_kiln_block_entity"), FabricBlockEntityTypeBuilder.create(PitKilnBlockEntity::new, PIT_KILN).build());
    public static final BlockEntityType<AshPileBlockEntity> ASH_PILE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, PrimevalMain.getId("ash_pile_block_entity"), FabricBlockEntityTypeBuilder.create(AshPileBlockEntity::new, ASH_PILE).build());
    public static final BlockEntityType<LayingItemBlockEntity> LAYING_ITEM_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, PrimevalMain.getId("laying_item_block_entity"), FabricBlockEntityTypeBuilder.create(LayingItemBlockEntity::new, LAYING_ITEM).build());
    public static final BlockEntityType<CrateBlockEntity> CRATE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, PrimevalMain.getId("crate_block_entity"), FabricBlockEntityTypeBuilder.create(CrateBlockEntity::new, OAK_CRATE, BIRCH_CRATE, SPRUCE_CRATE).build());
    public static final BlockEntityType<StoragePotBlockEntity> LARGE_POT_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, PrimevalMain.getId("large_pot_block_entity"), FabricBlockEntityTypeBuilder.create(StoragePotBlockEntity::new, LARGE_FIRED_CLAY_POT).build());
    public static final BlockEntityType<PrimevalCampfireBlockEntity> CAMPFIRE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, PrimevalMain.getId("campfire_block_entity"), FabricBlockEntityTypeBuilder.create(PrimevalCampfireBlockEntity::new, CAMPFIRE).build());

    // Screen Handlers
    public static final ScreenHandlerType<PrimevalContainerScreenHandler> CRATE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(PrimevalMain.getId("crate_screen_handler"), PrimevalContainerScreenHandler::new);


    public static void init() {
        OakTrunker.INSTANCE.build();
        BirchTrunker.INSTANCE.build();
        SpruceTrunker.INSTANCE.build();

        registerFlammables();
    }

    @Environment(EnvType.CLIENT)
    public static void initClient() {
        // Render Layers
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                GRASSY_DIRT,
                GRASS, BUSH, SHRUB,
                POPPY, DANDELION, OXEYE_DAISY,
                WILD_CARROTS,
                MOSS,
                /* Tree */
                OAK_SAPLING,
                OAK_LEAVES,
                BIRCH_SAPLING,
                BIRCH_LEAVES,
                SPRUCE_SAPLING,
                SPRUCE_LEAVES,
                /* Misc */
                CRUDE_TORCH,
                CAMPFIRE,
                ROPE,
                ROPE_LADDER,
                OAK_PLANK_BLOCKS.door,   // Door
                OAK_PLANK_BLOCKS.trapdoor);  // Trapdoor

        // Color registry on items
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x91BD59,
                GRASSY_DIRT.asItem(),
                GRASS.asItem(),
                BUSH.asItem());

        // Block Renderers
        BlockEntityRendererRegistry.register(PIT_KILN_BLOCK_ENTITY, PitKilnBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(ASH_PILE_BLOCK_ENTITY, AshPileBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(LAYING_ITEM_BLOCK_ENTITY, LayingItemBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(CAMPFIRE_BLOCK_ENTITY, PrimevalCampfireBlockEntityRenderer::new);

        ScreenRegistry.register(CRATE_SCREEN_HANDLER, Primeval3x5ContainerScreen::new);
    }


    private static void registerFlammables() {
        FlammableBlockRegistry.getDefaultInstance().add(OAK_LOG, 5, 10);
        FlammableBlockRegistry.getDefaultInstance().add(OAK_TRUNK, 1, 3);
        FlammableBlockRegistry.getDefaultInstance().add(OAK_LEAVES, 50, 60);
        FlammableBlockRegistry.getDefaultInstance().add(BIRCH_LOG, 5, 10);
        FlammableBlockRegistry.getDefaultInstance().add(BIRCH_TRUNK, 1, 3);
        FlammableBlockRegistry.getDefaultInstance().add(BIRCH_LEAVES, 50, 60);
        FlammableBlockRegistry.getDefaultInstance().add(SPRUCE_LOG, 5, 10);
        FlammableBlockRegistry.getDefaultInstance().add(SPRUCE_TRUNK, 1, 3);
        FlammableBlockRegistry.getDefaultInstance().add(SPRUCE_LEAVES, 50, 60);

        FlammableBlockRegistry.getDefaultInstance().add(OAK_SAPLING, 50, 80);
        FlammableBlockRegistry.getDefaultInstance().add(BIRCH_SAPLING, 50, 80);
        FlammableBlockRegistry.getDefaultInstance().add(SPRUCE_SAPLING, 50, 80);

        FlammableBlockRegistry.getDefaultInstance().add(GRASS, 60, 100);
        FlammableBlockRegistry.getDefaultInstance().add(BUSH, 60, 100);
        FlammableBlockRegistry.getDefaultInstance().add(POPPY, 60, 100);
        FlammableBlockRegistry.getDefaultInstance().add(DANDELION, 60, 100);
        FlammableBlockRegistry.getDefaultInstance().add(OXEYE_DAISY, 60, 100);

        FlammableBlockRegistry.getDefaultInstance().add(STRAW_BLOCK, 10, 40);
        FlammableBlockRegistry.getDefaultInstance().add(STRAW_STAIRS, 10, 40);
        FlammableBlockRegistry.getDefaultInstance().add(STRAW_SLAB, 10, 40);

        FlammableBlockRegistry.getDefaultInstance().add(STRAW_MESH, 10, 40);
        FlammableBlockRegistry.getDefaultInstance().add(STRAW_MAT, 10, 40);
        FlammableBlockRegistry.getDefaultInstance().add(STRAW_PILE, 10, 40);

        FlammableBlockRegistry.getDefaultInstance().add(DAUB, 2, 6);
        FlammableBlockRegistry.getDefaultInstance().add(FRAMED_DAUB, 2, 6);
        FlammableBlockRegistry.getDefaultInstance().add(FRAMED_PILLAR_DAUB, 2, 6);
        FlammableBlockRegistry.getDefaultInstance().add(FRAMED_CROSS_DAUB, 2, 6);
        FlammableBlockRegistry.getDefaultInstance().add(FRAMED_INVERTED_CROSS_DAUB, 2, 6);
        FlammableBlockRegistry.getDefaultInstance().add(FRAMED_X_DAUB, 2, 6);
        FlammableBlockRegistry.getDefaultInstance().add(FRAMED_PLUS_DAUB, 2, 6);
        FlammableBlockRegistry.getDefaultInstance().add(FRAMED_DIVIDED_DAUB, 2, 6);

        for (Block b : OAK_PLANK_BLOCKS) {
            FlammableBlockRegistry.getDefaultInstance().add(b, 5, 20);
        }
        for (Block b : BIRCH_PLANK_BLOCKS) {
            FlammableBlockRegistry.getDefaultInstance().add(b, 5, 20);
        }
        for (Block b : SPRUCE_PLANK_BLOCKS) {
            FlammableBlockRegistry.getDefaultInstance().add(b, 5, 20);
        }

        FlammableBlockRegistry.getDefaultInstance().add(CRUDE_CRAFTING_BENCH, 2, 6);
    }


    private static Block registerBlockWithoutItem(String id, Block block) {
        return Registry.register(Registry.BLOCK, PrimevalMain.getId(id), block);
    }


    private static <V extends Block> V registerBlock(String id, V block, Weight weight, Size size, ItemGroup itemgroup) {
        if (itemgroup == null) {
            Registry.register(Registry.ITEM, PrimevalMain.getId(id), new WeightedBlockItem(block, new FabricItemSettings().maxCount(size.getStackSize()), weight, size));
        } else {
            Registry.register(Registry.ITEM, PrimevalMain.getId(id), new WeightedBlockItem(block, new FabricItemSettings().group(itemgroup).maxCount(size.getStackSize()), weight, size));
        }
        return Registry.register(Registry.BLOCK, PrimevalMain.getId(id), block);
    }

    private static Block registerMoltenFluid(String id, FlowableFluid fluid) {
        return registerBlockWithoutItem(id, new UnprotectedFluidBlock(fluid, AbstractBlock.Settings.of(Material.LAVA).noCollision().ticksRandomly().strength(100.0F).luminance((state) -> 15).dropsNothing()));
    }

    private static BlockSet registerBlockSet(String id, AbstractBlock.Settings settings, Weight weight, Size size, ItemGroup itemgroup) {
        return registerBlockSet(id, id+"_stairs", id+"_slab", settings, weight, size, itemgroup);
    }
    private static BlockSet registerBlockSet(String base_id, String stairs_id, String slab_id, AbstractBlock.Settings settings, Weight weight, Size size, ItemGroup itemgroup) {
        Block base = registerBlock(base_id, new Block(settings), Weight.LIGHT, Size.MEDIUM, PRIMEVAL_BLOCKS);
        return new BlockSet(
                base,
                registerBlock(stairs_id, new PrimevalStairsBlock(base.getDefaultState(), settings), Weight.LIGHT, Size.MEDIUM, PRIMEVAL_BLOCKS),
                registerBlock(slab_id, new SlabBlock(settings), Weight.LIGHT, Size.MEDIUM, PRIMEVAL_BLOCKS)
        );
    }

    private static WoodBlockSet registerWoodBlockSet(String id, AbstractBlock.Settings settings, Weight weight, Size size, ItemGroup itemgroup) {
        return registerWoodBlockSet(id+"_planks", id+"_stairs", id+"_slab", id+"_fence", id+"_log_fence", id+"_fence_gate", id+"_door", id+"_trapdoor", settings, weight, size, itemgroup);
    }
    private static WoodBlockSet registerWoodBlockSet(String block_id, String stairs_id, String slab_id, String fence_id, String log_fence_id, String fence_gate_id, String door_id, String trapdoor_id, AbstractBlock.Settings settings, Weight weight, Size size, ItemGroup itemgroup) {
        Block base = registerBlock(block_id, new Block(settings), Weight.LIGHT, Size.MEDIUM, PRIMEVAL_BLOCKS);

        return new WoodBlockSet(
                base,
                registerBlock(stairs_id, new PrimevalStairsBlock(base.getDefaultState(), settings), Weight.LIGHT, Size.MEDIUM, PRIMEVAL_BLOCKS),
                registerBlock(slab_id, new SlabBlock(settings), Weight.LIGHT, Size.MEDIUM, PRIMEVAL_BLOCKS),
                registerBlock(fence_id, new FenceBlock(settings), Weight.LIGHT, Size.MEDIUM, PRIMEVAL_BLOCKS),
                registerBlock(log_fence_id, new FenceBlock(settings), Weight.LIGHT, Size.MEDIUM, PRIMEVAL_BLOCKS),
                registerBlock(fence_gate_id, new FenceGateBlock(settings), Weight.LIGHT, Size.MEDIUM, PRIMEVAL_BLOCKS),
                registerBlock(door_id, new PrimevalDoorBlock(settings.nonOpaque()), Weight.LIGHT, Size.MEDIUM, PRIMEVAL_BLOCKS),
                registerBlock(trapdoor_id, new PrimevalTrapdoorBlock(settings.nonOpaque()), Weight.LIGHT, Size.MEDIUM, PRIMEVAL_BLOCKS)
        );
    }

    private static OreBlockSet registerOreBlockSet(String ore_id, AbstractBlock.Settings settings, Weight weight, Size size, ItemGroup itemgroup) {
        return new OreBlockSet(
                registerBlock(ore_id + "_small", new SemiSupportedBlock(settings, 0.35f, COBBLESTONE), weight, size, itemgroup),
                registerBlock(ore_id + "_medium", new SemiSupportedBlock(settings, 0.35f, COBBLESTONE), weight, size, itemgroup),
                registerBlock(ore_id + "_large", new SemiSupportedBlock(settings, 0.35f, COBBLESTONE), weight, size, itemgroup)
        );
    }

    private static final record BlockSet(Block block, StairsBlock stairs, SlabBlock slab) {
        public @NotNull Iterator<Block> iterator() {
            return Arrays.stream(new Block[]{block, stairs, slab}).iterator();
        }
    }
    private static final record WoodBlockSet(Block block, StairsBlock stairs, SlabBlock slab, FenceBlock fence, FenceBlock logFence, FenceGateBlock fenceGate, DoorBlock door, TrapdoorBlock trapdoor) implements Iterable<Block> {
        public @NotNull Iterator<Block> iterator() {
            return Arrays.stream(new Block[]{block, stairs, slab, fence, logFence, fenceGate, door, trapdoor}).iterator();
        }
    }
    public static final record OreBlockSet(Block small, Block medium, Block large) {
        public @NotNull Iterator<Block> iterator() {
            return Arrays.stream(new Block[]{small, medium, large}).iterator();
        }
    }
}
