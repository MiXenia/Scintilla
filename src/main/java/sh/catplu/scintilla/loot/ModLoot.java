package sh.catplu.scintilla.loot;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import sh.catplu.scintilla.ScintillaMod;

public class ModLoot {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> SERIALIZER =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ScintillaMod.MOD_ID);

    public static final RegistryObject<Codec<smashing>> SMASHING =
            SERIALIZER.register("smashing", () -> smashing.CODEC);

    public static void register(IEventBus eventBus) {SERIALIZER.register(eventBus);}
}
