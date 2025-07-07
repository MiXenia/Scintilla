package sh.catplu.scintilla.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
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

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
