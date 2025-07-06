package sh.catplu.scintilla.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import sh.catplu.scintilla.ScintillaMod;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ScintillaMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SCINTILLA_TAB = CREATIVE_MODE_TABS.register("scintilla_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SHATTERGLASS.get()))
                    .title(Component.translatable("creativetab.scintilla_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.SHATTERGLASS.get());
                        pOutput.accept(ModItems.SCINTILLA.get());


                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
