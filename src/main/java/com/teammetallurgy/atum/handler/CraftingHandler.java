package com.teammetallurgy.atum.handler;

import com.teammetallurgy.atum.blocks.BlockAtumLog;
import com.teammetallurgy.atum.blocks.BlockAtumPlank;
import com.teammetallurgy.atum.blocks.BlockAtumStainedGlass;
import com.teammetallurgy.atum.blocks.BlockAtumStainedGlassPane;
import com.teammetallurgy.atum.init.AtumBlocks;
import com.teammetallurgy.atum.utils.Constants;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import org.apache.commons.lang3.StringUtils;

@Mod.EventBusSubscriber
public class CraftingHandler {
    private static void register() {
        addSmeltingRecipes();
    }

    private static void addSmeltingRecipes() {
        GameRegistry.addSmelting(AtumBlocks.IRON_ORE, new ItemStack(Items.IRON_INGOT), 0.7F);
        GameRegistry.addSmelting(AtumBlocks.COAL_ORE, new ItemStack(Items.COAL), 0.1F);
        GameRegistry.addSmelting(AtumBlocks.REDSTONE_ORE, new ItemStack(Items.REDSTONE), 0.7F);
        GameRegistry.addSmelting(AtumBlocks.LAPIS_ORE, new ItemStack(Items.DYE, 1, 4), 0.2F);
        GameRegistry.addSmelting(AtumBlocks.GOLD_ORE, new ItemStack(Items.GOLD_INGOT), 1.0F);
        GameRegistry.addSmelting(AtumBlocks.DIAMOND_ORE, new ItemStack(Items.DIAMOND), 1.0F);
        for (BlockAtumPlank.WoodType type : BlockAtumPlank.WoodType.values()) {
            GameRegistry.addSmelting(BlockAtumLog.getLog(type), new ItemStack(Items.COAL, 1, 1), 0.15F);
        }
        GameRegistry.addSmelting(AtumBlocks.LIMESTONE_CRACKED, new ItemStack(AtumBlocks.LIMESTONE), 0.1F);
        GameRegistry.addSmelting(AtumBlocks.SAND, new ItemStack(AtumBlocks.CRYSTAL_GLASS), 0.1F);
        GameRegistry.addSmelting(AtumBlocks.SAND, new ItemStack(AtumBlocks.CRYSTAL_GLASS), 0.1F);
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) { //TODO Do this in a cleaner way
        final ResourceLocation crystal = new ResourceLocation(Constants.MOD_ID, "crystal_glass");
        final ResourceLocation framed = new ResourceLocation(Constants.MOD_ID, "framed_glass");
        for (EnumDyeColor color : EnumDyeColor.values()) {
            String colorName = StringUtils.capitalize(color.getUnlocalizedName().replace("silver", "lightGray"));
            event.getRegistry().register(new ShapedOreRecipe(crystal, new ItemStack(BlockAtumStainedGlass.getGlass(AtumBlocks.CRYSTAL_GLASS, color), 8), "GGG", "GDG", "GGG", 'G', AtumBlocks.CRYSTAL_GLASS, 'D', "dye" + colorName).setRegistryName(new ResourceLocation(Constants.MOD_ID, "crysal_" + colorName)));
            event.getRegistry().register(new ShapedOreRecipe(framed, new ItemStack(BlockAtumStainedGlass.getGlass(AtumBlocks.FRAMED_GLASS, color), 8), "GGG", "GDG", "GGG", 'G', AtumBlocks.FRAMED_GLASS, 'D', "dye" + colorName).setRegistryName(new ResourceLocation(Constants.MOD_ID, "framed_" + colorName)));
            event.getRegistry().register(new ShapedOreRecipe(framed, BlockAtumStainedGlass.getGlass(AtumBlocks.FRAMED_GLASS, color), " S ", "SGS", " S ", 'S', Items.STICK, 'G', BlockAtumStainedGlass.getGlass(AtumBlocks.CRYSTAL_GLASS, color)).setRegistryName(new ResourceLocation(Constants.MOD_ID, "crytal_to_framed_" + colorName)));
            event.getRegistry().register(new ShapedOreRecipe(crystal, new ItemStack(BlockAtumStainedGlassPane.getGlass(AtumBlocks.CRYSTAL_GLASS, color), 16), "GGG", "GGG", 'G', BlockAtumStainedGlass.getGlass(AtumBlocks.CRYSTAL_GLASS, color)).setRegistryName(new ResourceLocation(Constants.MOD_ID, "thin_crystal_" + colorName)));
            event.getRegistry().register(new ShapedOreRecipe(framed, new ItemStack(BlockAtumStainedGlassPane.getGlass(AtumBlocks.FRAMED_GLASS, color), 16), "GGG", "GGG", 'G', BlockAtumStainedGlass.getGlass(AtumBlocks.FRAMED_GLASS, color)).setRegistryName(new ResourceLocation(Constants.MOD_ID, "thin_framed_" + colorName)));
        }
        CraftingHandler.register();
    }
}