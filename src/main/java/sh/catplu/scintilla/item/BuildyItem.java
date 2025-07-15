package sh.catplu.scintilla.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Objects;

@ParametersAreNonnullByDefault
public class BuildyItem extends Item {
    private final String  loreID;
    private final Boolean foil;
    private final Rarity  rarity;

    public BuildyItem(Properties pProperties, @Nullable String loreID, @Nullable Boolean foil, @Nullable Rarity rarity) {
        super(pProperties);
        this.loreID = loreID;
        this.foil   = foil;
        this.rarity = rarity;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (loreID != null) {
            pTooltipComponents.add(Component.translatable("tooltip.scintilla." + loreID));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return Objects.requireNonNullElseGet(foil, () -> super.isFoil(pStack));
    }

    @Override
    public @NotNull Rarity getRarity(ItemStack pStack) {
        return Objects.requireNonNullElseGet(rarity, () -> super.getRarity(pStack));
    }
}
