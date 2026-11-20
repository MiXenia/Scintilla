package sh.catplu.scintilla.block;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import sh.catplu.scintilla.ScintillaMod;
import sh.catplu.scintilla.item.ModItems;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
    DeferredRegister.create(ForgeRegistries.BLOCKS, ScintillaMod.MOD_ID);

    public static final RegistryObject<Block> FRAILILY = registerBlock("frailily",
            () -> new FlowerBlock(() -> MobEffects.LUCK, Integer.MAX_VALUE,
                    BlockBehaviour.Properties.copy(Blocks.ALLIUM).sound(SoundType.MEDIUM_AMETHYST_BUD).noOcclusion().noCollission()),
                "frailily_lore");

    public static final RegistryObject<Block> POTTED_FRAILILY = BLOCKS.register("potted_frailily",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.FRAILILY,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).sound(SoundType.LARGE_AMETHYST_BUD).noOcclusion()));




    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, @Nullable String loreID) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()) {
            @Override
            public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                if (loreID != null) {
                    tooltip.add(Component.translatable("tooltip.scintilla."+loreID+".1")
                    .withStyle(ChatFormatting.DARK_GRAY,ChatFormatting.ITALIC));
                    tooltip.add(Component.translatable("tooltip.scintilla."+loreID+".2")
                    .withStyle(ChatFormatting.DARK_GRAY,ChatFormatting.ITALIC));
                    tooltip.add(Component.translatable("tooltip.scintilla."+loreID+".3")
                    .withStyle(ChatFormatting.GRAY,ChatFormatting.ITALIC));
                }
                super.appendHoverText(stack,level,tooltip,flag);
            }
        });
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        return registerBlock(name,block,null);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, @Nullable String loreID) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn,loreID);
        return toReturn;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
