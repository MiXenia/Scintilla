package sh.catplu.scintilla.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class ShatterglassItem extends Item implements DyeableLeatherItem {
    public ShatterglassItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public int getColor(ItemStack pStack) {
        CompoundTag $$1 = pStack.getTagElement("display");
        return $$1 != null && $$1.contains("color", 99) ? $$1.getInt("color") : 16777215;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (!pStack.hasTag()) {
            pTooltipComponents.add(Component.translatable("tooltip.scintilla.shatterglass_guide"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
