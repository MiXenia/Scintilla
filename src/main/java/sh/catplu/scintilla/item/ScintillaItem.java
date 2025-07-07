package sh.catplu.scintilla.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ScintillaItem extends Item implements DyeableLeatherItem {
    public ScintillaItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public int getColor(ItemStack pStack) {
        CompoundTag $$1 = pStack.getTagElement("display");
        return $$1 != null && $$1.contains("color", 99) ? $$1.getInt("color") : 16777215;
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.AMETHYST_BLOCK_BREAK;
    }


}
