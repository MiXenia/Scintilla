package sh.catplu.scintilla.block;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import sh.catplu.scintilla.ScintillaMod;
import sh.catplu.scintilla.item.ModItems;
import sh.catplu.scintilla.item.ScintillaItem;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
    DeferredRegister.create(ForgeRegistries.BLOCKS, ScintillaMod.MOD_ID);

    public static final RegistryObject<Block> FRAILILY = registerBlock("frailily",
            () -> new FlowerBlock(() -> MobEffects.LUCK, Integer.MAX_VALUE,
                    BlockBehaviour.Properties.copy(Blocks.ALLIUM).sound(SoundType.MEDIUM_AMETHYST_BUD).noOcclusion().noCollission()));

    public static final RegistryObject<Block> POTTED_FRAILILY = BLOCKS.register("potted_frailily",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.FRAILILY,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).sound(SoundType.LARGE_AMETHYST_BUD).noOcclusion()));




    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
