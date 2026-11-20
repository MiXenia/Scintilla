package sh.catplu.scintilla.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import sh.catplu.scintilla.ScintillaMod;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, ScintillaMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SCINTILLA_TAB = CREATIVE_MODE_TABS.register("scintilla_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> scintilla("heart", "end", "snow", "diamond", 0xFFAADD, 257, 10, 20.0f))
                    .title(Component.translatable("creativetab.scintilla_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(scintilla("vanilla", "glass", "sugar", "snow", 0x324BB0, 9, 2, 0.4f));
                        pOutput.accept(scintilla("dusty", "emerald", "diamond", "amethyst", 0xB04BD5, 17, 4, 1.2f));
                        pOutput.accept(scintilla("jar", "pearl", "quartz", "blaze", 0x973232, 33, 6, 6.0f));
                        pOutput.accept(scintilla("holy", "eye", "echo", "hots", 0x4B7D97, 65, 8, 12.8f));
                        pOutput.accept(scintilla("star", "crystal", "star", "prismarine", 0x7DCA19, 129, 10, 20.0f));
                        pOutput.accept(scintilla("heart", "end", "snow", "diamond", 0xFFAADD, 257, 10, 20.0f));
                        pOutput.accept(scintilla("dusty", "glass", "redstone", "glowstone", 0xFFFFFF, 257, 10, 20.0f));
                        pOutput.accept(scintilla("vial", "crystal", "crystal", "crystal", 0x954DBB, 257, 10, 20.0f));
                        pOutput.accept(scintilla("diamond", "end", "glass", "glass", 0xC4A2A8, 257, 10, 20.0f));

                        pOutput.accept(ModItems.SHATTERGLASS.get());

                        pOutput.accept(ModItems.DIAMOND_DUST.get());
                        pOutput.accept(ModItems.QUARTZ_DUST.get());
                        pOutput.accept(ModItems.ECHO_DUST.get());
                        pOutput.accept(ModItems.STAR_DUST.get());

                        pOutput.accept(ModItems.AMETHYST_DUST.get());
                        pOutput.accept(ModItems.BLAZE_DUST.get());
                        pOutput.accept(ModItems.HOTS_DUST.get());
                        pOutput.accept(ModItems.PRISMARINE_DUST.get());

                        pOutput.accept(ModItems.GLASS_DUST.get());
                        pOutput.accept(ModItems.DIAMOND_GLASS_BOTTLE.get());
                        pOutput.accept(ModItems.DUSTY_GLASS_BOTTLE.get());
                        pOutput.accept(ModItems.HEART_GLASS_BOTTLE.get());
                        pOutput.accept(ModItems.HOLY_GLASS_BOTTLE.get());
                        pOutput.accept(ModItems.JAR_GLASS_BOTTLE.get());
                        pOutput.accept(ModItems.STAR_GLASS_BOTTLE.get());
                        pOutput.accept(ModItems.VANILLA_GLASS_BOTTLE.get());
                        pOutput.accept(ModItems.VIAL_GLASS_BOTTLE.get());

                        pOutput.accept(ModItems.EMERALD_DUST.get());
                        pOutput.accept(ModItems.DIAMOND_EMERALD_BOTTLE.get());
                        pOutput.accept(ModItems.DUSTY_EMERALD_BOTTLE.get());
                        pOutput.accept(ModItems.HEART_EMERALD_BOTTLE.get());
                        pOutput.accept(ModItems.HOLY_EMERALD_BOTTLE.get());
                        pOutput.accept(ModItems.JAR_EMERALD_BOTTLE.get());
                        pOutput.accept(ModItems.STAR_EMERALD_BOTTLE.get());
                        pOutput.accept(ModItems.VANILLA_EMERALD_BOTTLE.get());
                        pOutput.accept(ModItems.VIAL_EMERALD_BOTTLE.get());

                        pOutput.accept(ModItems.PEARL_DUST.get());
                        pOutput.accept(ModItems.DIAMOND_PEARL_BOTTLE.get());
                        pOutput.accept(ModItems.DUSTY_PEARL_BOTTLE.get());
                        pOutput.accept(ModItems.HEART_PEARL_BOTTLE.get());
                        pOutput.accept(ModItems.HOLY_PEARL_BOTTLE.get());
                        pOutput.accept(ModItems.JAR_PEARL_BOTTLE.get());
                        pOutput.accept(ModItems.STAR_PEARL_BOTTLE.get());
                        pOutput.accept(ModItems.VANILLA_PEARL_BOTTLE.get());
                        pOutput.accept(ModItems.VIAL_PEARL_BOTTLE.get());

                        pOutput.accept(ModItems.EYE_DUST.get());
                        pOutput.accept(ModItems.DIAMOND_EYE_BOTTLE.get());
                        pOutput.accept(ModItems.DUSTY_EYE_BOTTLE.get());
                        pOutput.accept(ModItems.HEART_EYE_BOTTLE.get());
                        pOutput.accept(ModItems.HOLY_EYE_BOTTLE.get());
                        pOutput.accept(ModItems.JAR_EYE_BOTTLE.get());
                        pOutput.accept(ModItems.STAR_EYE_BOTTLE.get());
                        pOutput.accept(ModItems.VANILLA_EYE_BOTTLE.get());
                        pOutput.accept(ModItems.VIAL_EYE_BOTTLE.get());

                        pOutput.accept(ModItems.CRYSTAL_DUST.get());
                        pOutput.accept(ModItems.DIAMOND_CRYSTAL_BOTTLE.get());
                        pOutput.accept(ModItems.DUSTY_CRYSTAL_BOTTLE.get());
                        pOutput.accept(ModItems.HEART_CRYSTAL_BOTTLE.get());
                        pOutput.accept(ModItems.HOLY_CRYSTAL_BOTTLE.get());
                        pOutput.accept(ModItems.JAR_CRYSTAL_BOTTLE.get());
                        pOutput.accept(ModItems.STAR_CRYSTAL_BOTTLE.get());
                        pOutput.accept(ModItems.VANILLA_CRYSTAL_BOTTLE.get());
                        pOutput.accept(ModItems.VIAL_CRYSTAL_BOTTLE.get());

                        pOutput.accept(ModItems.END_DUST.get());
                        pOutput.accept(ModItems.DIAMOND_END_BOTTLE.get());
                        pOutput.accept(ModItems.DUSTY_END_BOTTLE.get());
                        pOutput.accept(ModItems.HEART_END_BOTTLE.get());
                        pOutput.accept(ModItems.HOLY_END_BOTTLE.get());
                        pOutput.accept(ModItems.JAR_END_BOTTLE.get());
                        pOutput.accept(ModItems.STAR_END_BOTTLE.get());
                        pOutput.accept(ModItems.VANILLA_END_BOTTLE.get());
                        pOutput.accept(ModItems.VIAL_END_BOTTLE.get());

                        // pOutput.accept(ModBlocks.FRAILILY.get());
                        // pOutput.accept(ModBlocks.POTTED_FRAILILY.get());

                    })
                    .build());

    private static ItemStack scintilla(
            String bs,
            String bm,
            String dl,
            String dr,
            int color,
            int md,
            int hv,
            float sv) {
        CompoundTag display = new CompoundTag();
        display.putString("bs", bs);
        display.putString("bm", bm);
        display.putString("dl", dl);
        display.putString("dr", dr);
        display.putInt("color", color);

        CompoundTag tag = new CompoundTag();
        tag.putInt("md", md);
        tag.putInt("hv", hv);
        tag.putFloat("sv", sv);
        tag.put("display", display);

        ItemStack stack = new ItemStack(ModItems.SCINTILLA.get());
        stack.setTag(tag);
        return stack;
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
