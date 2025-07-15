package sh.catplu.scintilla.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import sh.catplu.scintilla.ScintillaMod;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ScintillaMod.MOD_ID);

    public static final RegistryObject<Item> SHATTERGLASS = ITEMS.register("shatterglass",
            () -> new ShatterglassItem(new Item.Properties()));

    public static final RegistryObject<Item> SCINTILLA = ITEMS.register("scintilla",
            () -> new ScintillaItem(new Item.Properties().stacksTo(1).durability(9)
                    .food(new FoodProperties.Builder()
                            .alwaysEat()
                            .nutrition(2)
                            .saturationMod(0.4F)
                            .build())));

    public static final RegistryObject<Item> DIAMOND_DUST = ITEMS.register("diamond_dust",() -> new BuildyItem(new Item.Properties(),"t1nutrition",null,Rarity.UNCOMMON));
    public static final RegistryObject<Item> QUARTZ_DUST = ITEMS.register("quartz_dust",() -> new BuildyItem(new Item.Properties(),"t2nutrition",null,Rarity.UNCOMMON));
    public static final RegistryObject<Item> ECHO_DUST = ITEMS.register("echo_dust",() -> new BuildyItem(new Item.Properties(),"t3nutrition",null,Rarity.RARE));
    public static final RegistryObject<Item> STAR_DUST = ITEMS.register("star_dust",() -> new BuildyItem(new Item.Properties(),"t4nutrition",true,Rarity.EPIC));

    public static final RegistryObject<Item> AMETHYST_DUST = ITEMS.register("amethyst_dust",() -> new BuildyItem(new Item.Properties(),"t1saturation",null, Rarity.UNCOMMON));
    public static final RegistryObject<Item> BLAZE_DUST = ITEMS.register("blaze_dust",() -> new BuildyItem(new Item.Properties(),"t2saturation",null,Rarity.UNCOMMON));
    public static final RegistryObject<Item> HOTS_DUST = ITEMS.register("hots_dust",() -> new BuildyItem(new Item.Properties(),"t3saturation",null,Rarity.RARE));
    public static final RegistryObject<Item> PRISMARINE_DUST = ITEMS.register("prismarine_dust",() -> new BuildyItem(new Item.Properties(),"t4saturation",null,Rarity.EPIC));

    public static final RegistryObject<Item> GLASS_DUST = ITEMS.register("glass_dust",() -> new BuildyItem(new Item.Properties(),"t0durability",null,Rarity.COMMON));
    public static final RegistryObject<Item> EMERALD_DUST = ITEMS.register("emerald_dust",() -> new BuildyItem(new Item.Properties(),"t1durability",null,Rarity.UNCOMMON));
    public static final RegistryObject<Item> PEARL_DUST = ITEMS.register("pearl_dust",() -> new BuildyItem(new Item.Properties(),"t2durability",null,Rarity.UNCOMMON));
    public static final RegistryObject<Item> EYE_DUST = ITEMS.register("eye_dust",() -> new BuildyItem(new Item.Properties(),"t3durability",null,Rarity.RARE));
    public static final RegistryObject<Item> CRYSTAL_DUST = ITEMS.register("crystal_dust",() -> new BuildyItem(new Item.Properties(),"t4durability",null,Rarity.RARE));
    public static final RegistryObject<Item> END_DUST = ITEMS.register("end_dust",() -> new BuildyItem(new Item.Properties(),"t5durability",null,Rarity.EPIC));

    public static final RegistryObject<Item> DIAMOND_GLASS_BOTTLE = ITEMS.register("diamond_glass_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUSTY_GLASS_BOTTLE = ITEMS.register("dusty_glass_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HEART_GLASS_BOTTLE = ITEMS.register("heart_glass_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HOLY_GLASS_BOTTLE = ITEMS.register("holy_glass_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> JAR_GLASS_BOTTLE = ITEMS.register("jar_glass_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STAR_GLASS_BOTTLE = ITEMS.register("star_glass_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VANILLA_GLASS_BOTTLE = ITEMS.register("vanilla_glass_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VIAL_GLASS_BOTTLE = ITEMS.register("vial_glass_bottle",()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DIAMOND_EMERALD_BOTTLE = ITEMS.register("diamond_emerald_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUSTY_EMERALD_BOTTLE = ITEMS.register("dusty_emerald_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HEART_EMERALD_BOTTLE = ITEMS.register("heart_emerald_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HOLY_EMERALD_BOTTLE = ITEMS.register("holy_emerald_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> JAR_EMERALD_BOTTLE = ITEMS.register("jar_emerald_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STAR_EMERALD_BOTTLE = ITEMS.register("star_emerald_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VANILLA_EMERALD_BOTTLE = ITEMS.register("vanilla_emerald_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VIAL_EMERALD_BOTTLE = ITEMS.register("vial_emerald_bottle",()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DIAMOND_PEARL_BOTTLE = ITEMS.register("diamond_pearl_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUSTY_PEARL_BOTTLE = ITEMS.register("dusty_pearl_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HEART_PEARL_BOTTLE = ITEMS.register("heart_pearl_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HOLY_PEARL_BOTTLE = ITEMS.register("holy_pearl_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> JAR_PEARL_BOTTLE = ITEMS.register("jar_pearl_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STAR_PEARL_BOTTLE = ITEMS.register("star_pearl_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VANILLA_PEARL_BOTTLE = ITEMS.register("vanilla_pearl_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VIAL_PEARL_BOTTLE = ITEMS.register("vial_pearl_bottle",()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DIAMOND_EYE_BOTTLE = ITEMS.register("diamond_eye_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUSTY_EYE_BOTTLE = ITEMS.register("dusty_eye_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HEART_EYE_BOTTLE = ITEMS.register("heart_eye_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HOLY_EYE_BOTTLE = ITEMS.register("holy_eye_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> JAR_EYE_BOTTLE = ITEMS.register("jar_eye_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STAR_EYE_BOTTLE = ITEMS.register("star_eye_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VANILLA_EYE_BOTTLE = ITEMS.register("vanilla_eye_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VIAL_EYE_BOTTLE = ITEMS.register("vial_eye_bottle",()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DIAMOND_CRYSTAL_BOTTLE = ITEMS.register("diamond_crystal_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUSTY_CRYSTAL_BOTTLE = ITEMS.register("dusty_crystal_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HEART_CRYSTAL_BOTTLE = ITEMS.register("heart_crystal_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HOLY_CRYSTAL_BOTTLE = ITEMS.register("holy_crystal_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> JAR_CRYSTAL_BOTTLE = ITEMS.register("jar_crystal_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STAR_CRYSTAL_BOTTLE = ITEMS.register("star_crystal_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VANILLA_CRYSTAL_BOTTLE = ITEMS.register("vanilla_crystal_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VIAL_CRYSTAL_BOTTLE = ITEMS.register("vial_crystal_bottle",()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DIAMOND_END_BOTTLE = ITEMS.register("diamond_end_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUSTY_END_BOTTLE = ITEMS.register("dusty_end_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HEART_END_BOTTLE = ITEMS.register("heart_end_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HOLY_END_BOTTLE = ITEMS.register("holy_end_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> JAR_END_BOTTLE = ITEMS.register("jar_end_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STAR_END_BOTTLE = ITEMS.register("star_end_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VANILLA_END_BOTTLE = ITEMS.register("vanilla_end_bottle",()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VIAL_END_BOTTLE = ITEMS.register("vial_end_bottle",()-> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }

}





