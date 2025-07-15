package sh.catplu.scintilla.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import sh.catplu.scintilla.ScintillaMod;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ScintillaMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SCINTILLA_TAB = CREATIVE_MODE_TABS.register("scintilla_tab",
            () -> CreativeModeTab.builder().icon(() -> {
                        CompoundTag s5 = new CompoundTag();
                        CompoundTag d5 = new CompoundTag();
                        ItemStack i5 = new ItemStack(ModItems.SCINTILLA.get());
                        d5.putString("bs","heart");
                        d5.putString("bm","end");
                        d5.putString("dl","snow");
                        d5.putString("dr","diamond");
                        d5.putInt("color",16755421);
                        s5.putInt("md",257);
                        s5.putInt("hv",10);
                        s5.putFloat("sv",20.0f);
                        s5.put("display",d5);
                        i5.setTag(s5);
                        return i5;
                    })
                    .title(Component.translatable("creativetab.scintilla_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        CompoundTag s0 = new CompoundTag();
                        CompoundTag s1 = new CompoundTag();
                        CompoundTag s2 = new CompoundTag();
                        CompoundTag s3 = new CompoundTag();
                        CompoundTag s4 = new CompoundTag();
                        CompoundTag s5 = new CompoundTag();
                        CompoundTag s6 = new CompoundTag();
                        CompoundTag s7 = new CompoundTag();
                        CompoundTag s8 = new CompoundTag();

                        CompoundTag d0 = new CompoundTag();
                        CompoundTag d1 = new CompoundTag();
                        CompoundTag d2 = new CompoundTag();
                        CompoundTag d3 = new CompoundTag();
                        CompoundTag d4 = new CompoundTag();
                        CompoundTag d5 = new CompoundTag();
                        CompoundTag d6 = new CompoundTag();
                        CompoundTag d7 = new CompoundTag();
                        CompoundTag d8 = new CompoundTag();

                        ItemStack i0 = new ItemStack(ModItems.SCINTILLA.get());
                        ItemStack i1 = new ItemStack(ModItems.SCINTILLA.get());
                        ItemStack i2 = new ItemStack(ModItems.SCINTILLA.get());
                        ItemStack i3 = new ItemStack(ModItems.SCINTILLA.get());
                        ItemStack i4 = new ItemStack(ModItems.SCINTILLA.get());
                        ItemStack i5 = new ItemStack(ModItems.SCINTILLA.get());
                        ItemStack i6 = new ItemStack(ModItems.SCINTILLA.get());
                        ItemStack i7 = new ItemStack(ModItems.SCINTILLA.get());
                        ItemStack i8 = new ItemStack(ModItems.SCINTILLA.get());

                        d0.putString("bs","vanilla");
                        d0.putString("bm","glass");
                        d0.putString("dl","sugar");
                        d0.putString("dr","snow");
                        d0.putInt("color",3296176);
                        s0.putInt("md",9);
                        s0.putInt("hv",2);
                        s0.putFloat("sv",0.4f);
                        s0.put("display",d0);

                        d1.putString("bs","dusty");
                        d1.putString("bm","emerald");
                        d1.putString("dl","diamond");
                        d1.putString("dr","amethyst");
                        d1.putInt("color",11553749);
                        s1.putInt("md",17);
                        s1.putInt("hv",4);
                        s1.putFloat("sv",1.2f);
                        s1.put("display",d1);

                        d2.putString("bs","jar");
                        d2.putString("bm","pearl");
                        d2.putString("dl","quartz");
                        d2.putString("dr","blaze");
                        d2.putInt("color",9908786);
                        s2.putInt("md",33);
                        s2.putInt("hv",6);
                        s2.putFloat("sv",6.0f);
                        s2.put("display",d2);

                        d3.putString("bs","holy");
                        d3.putString("bm","eye");
                        d3.putString("dl","echo");
                        d3.putString("dr","hots");
                        d3.putInt("color",4947351);
                        s3.putInt("md",65);
                        s3.putInt("hv",8);
                        s3.putFloat("sv",12.8f);
                        s3.put("display",d3);

                        d4.putString("bs","star");
                        d4.putString("bm","crystal");
                        d4.putString("dl","star");
                        d4.putString("dr","prismarine");
                        d4.putInt("color",8243737);
                        s4.putInt("md",129);
                        s4.putInt("hv",10);
                        s4.putFloat("sv",20.0f);
                        s4.put("display",d4);

                        d5.putString("bs","heart");
                        d5.putString("bm","end");
                        d5.putString("dl","snow");
                        d5.putString("dr","diamond");
                        d5.putInt("color",16755421);
                        s5.putInt("md",257);
                        s5.putInt("hv",10);
                        s5.putFloat("sv",20.0f);
                        s5.put("display",d5);

                        d6.putString("bs","dusty");
                        d6.putString("bm","glass");
                        d6.putString("dl","redstone");
                        d6.putString("dr","glowstone");
                        d6.putInt("color",16777215);
                        s6.putInt("md",257);
                        s6.putInt("hv",10);
                        s6.putFloat("sv",20.0f);
                        s6.put("display",d6);

                        d7.putString("bs","vial");
                        d7.putString("bm","crystal");
                        d7.putString("dl","crystal");
                        d7.putString("dr","crystal");
                        d7.putInt("color",9784763);
                        s7.putInt("md",257);
                        s7.putInt("hv",10);
                        s7.putFloat("sv",20.0f);
                        s7.put("display",d7);

                        d8.putString("bs","diamond");
                        d8.putString("bm","end");
                        d8.putString("dl","glass");
                        d8.putString("dr","glass");
                        d8.putInt("color",12886696);
                        s8.putInt("md",257);
                        s8.putInt("hv",10);
                        s8.putFloat("sv",20.0f);
                        s8.put("display",d8);

                        i0.setTag(s0);
                        i1.setTag(s1);
                        i2.setTag(s2);
                        i3.setTag(s3);
                        i4.setTag(s4);
                        i5.setTag(s5);
                        i6.setTag(s6);
                        i7.setTag(s7);
                        i8.setTag(s8);

                        pOutput.accept(i0);
                        pOutput.accept(i1);
                        pOutput.accept(i2);
                        pOutput.accept(i3);
                        pOutput.accept(i4);
                        pOutput.accept(i5);
                        pOutput.accept(i6);
                        pOutput.accept(i7);
                        pOutput.accept(i8);

                        //CompoundTag sgd = new CompoundTag();
                        //CompoundTag sgt = new CompoundTag();
                        //sgd.putInt("color",16777215);
                        //sgt.putBoolean("tut",true);
                        //sgt.put("display",sgd);
                        //ItemStack sg = new ItemStack(ModItems.SHATTERGLASS.get());
                        //sg.setTag(sgt);

                        //pOutput.accept(sg);
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

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
