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

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }

}













