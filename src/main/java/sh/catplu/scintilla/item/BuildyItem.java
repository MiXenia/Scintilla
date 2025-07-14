package sh.catplu.scintilla.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class BuildyItem extends Item {
    private final String loreID;
    private final Boolean foil;
    private final Rarity rarity;
    public BuildyItem(Properties pProperties, @Nullable String loreID, @Nullable Boolean foil, @Nullable Rarity rarity) {
        super(pProperties);
        this.loreID = loreID;
        this.foil = foil;
        this.rarity = rarity;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @org.jetbrains.annotations.Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (loreID != null) {
            pTooltipComponents.add(Component.translatable("tooltip.scintilla." + loreID));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        if (foil != null) {
            return foil;
        } else {
            return super.isFoil(pStack);
        }
    }

    @Override
    public Rarity getRarity(ItemStack pStack) {
        if (rarity != null) {
            return rarity;
        } else {
            return super.getRarity(pStack);
        }
    }
}
