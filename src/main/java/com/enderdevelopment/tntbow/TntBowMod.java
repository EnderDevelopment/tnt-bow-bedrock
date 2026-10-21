package com.enderdevelopment.tntbow;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipeBuilder;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(TntBowMod.MODID)
public class TntBowMod {
    public static final String MODID = "tntbow";
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> TNT_BOW = ITEMS.register("tnt_bow", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    public TntBowMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static void registerRecipes() {
        ShapedRecipeBuilder.shaped(TNT_BOW.get())
                .pattern("T T")
                .pattern(" T ")
                .pattern("B")
                .define('T', Items.TNT)
                .define('B', Items.BOW)
                .unlockedBy("has_tnt", has(Items.TNT))
                .unlockedBy("has_bow", has(Items.BOW))
                .save(Consumer<FinishedRecipe> consumer, "tnt_bow");
    }

    private static InventoryChangeTrigger.Instance has(ItemLike item) {
        return InventoryChangeTrigger.Instance.hasItems(ItemPredicate.Builder.item().of(item).build());
    }
}