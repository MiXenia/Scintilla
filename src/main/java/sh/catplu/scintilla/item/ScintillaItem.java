package sh.catplu.scintilla.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class ScintillaItem extends Item implements DyeableLeatherItem {
    public ScintillaItem(Properties pProperties) {
        //super(pProperties.food(NON_MODULAR_FOOD));
        super(pProperties);
    }

    @Override
    public @Nullable FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity) {
        if (stack.hasTag() && (stack.getTag() != null && stack.getTag().contains("hv")) && stack.getTag().contains("sv")) {
            CompoundTag nbt        = stack.getOrCreateTag();
            int         hunger     = nbt.getInt("hv");
            float       saturation = nbt.getFloat("sv");
            return new FoodProperties.Builder()
                    .nutrition(hunger)
                    .saturationMod(saturation/hunger)
                    .alwaysEat()
                    .build();
        } else {
            return new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationMod(0.4f/2)
                    .alwaysEat()
                    .build();
        }

    }

    @Override
    public @NotNull SoundEvent getEatingSound() {
        return SoundEvents.AMETHYST_BLOCK_BREAK;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack pStack) {
        return 22;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        if (stack.hasTag() && (stack.getTag() != null && stack.getTag().contains("md"))) {
            return stack.getTag().getInt("md");
        } else {
            return super.getMaxDamage(stack);
        }
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        if (pStack.hasTag() && (pStack.getTag() != null && pStack.getTag().contains("Damage"))) {
            return pStack.getTag().getInt("Damage") > 0;
        } else {
            return super.isBarVisible(pStack);
        }
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        if (pStack.hasTag() && (pStack.getTag() != null && pStack.getTag().contains("Damage")) && pStack.getTag().contains("md")) {
            int cd = pStack.getTag().getInt("Damage");
            int md = pStack.getTag().getInt("md");
            return Math.round(13.0f - (float) cd * 13.0f / (float) md);
        } else {
            return super.getBarWidth(pStack);
        }
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        if (pStack.hasTag() && (pStack.getTag() != null && pStack.getTag().contains("Damage")) && pStack.getTag().contains("md")) {
            int   cd = pStack.getTag().getInt("Damage");
            int   md = pStack.getTag().getInt("md");
            float pc = (float) cd / (float) md;
            int   sr = 255;
            int   sg = 170;
            int   sb = 221;
            int   er = 170;
            int   eg = 238;
            int   eb = 255;
            int   ir = (int) (sr + (er - sr) * pc);
            int   ig = (int) (sg + (eg - sg) * pc);
            int   ib = (int) (sb + (eb - sb) * pc);
            return (ir << 16) | (ig << 8) | ib;
        } else {
            return super.getBarColor(pStack);
        }
    }

    @Override
    public boolean isFoil(@NotNull ItemStack pStack) {
        return false;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull LivingEntity pLivingEntity) {
        if (pLivingEntity instanceof Player player) {
            if (player.getFoodData().getFoodLevel() < 20) {
                if (pStack.getDamageValue() < pStack.getMaxDamage() - 1) {

                    FoodProperties nutrition = pStack.getFoodProperties(null);
                    if (nutrition != null) {
                        player.getFoodData().eat(nutrition.getNutrition(), nutrition.getSaturationModifier());
                    }
                    pStack.hurt(1, pLevel.getRandom(), player instanceof ServerPlayer ? (ServerPlayer) player : null);
                }
            }
        }
        return pStack;
    }

    @Override
    public boolean isValidRepairItem(@NotNull ItemStack pStack, ItemStack pRepairCandidate) {
        return pRepairCandidate.getItem() instanceof ShatterglassItem;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        if (pStack.hasTag()) {
            CompoundTag tag = pStack.getTag();
            if (tag != null && tag.contains("display")) {
                CompoundTag display = tag.getCompound("display");
                if (display.contains("dl")) {
                    MutableComponent l1 = Component.translatable("tooltip.scintilla.left_dust");
                    MutableComponent l2 = Component.translatable("item.scintilla." + display.getString("dl") + "_dust");
                    MutableComponent l3 = l1.append(l2);
                    pTooltipComponents.add(l3.withStyle(ChatFormatting.DARK_GRAY));
                }
                if (display.contains("dr")) {
                    MutableComponent r1 = Component.translatable("tooltip.scintilla.right_dust");
                    MutableComponent r2 = Component.translatable("item.scintilla." + display.getString("dr") + "_dust");
                    MutableComponent r3 = r1.append(r2);
                    pTooltipComponents.add(r3.withStyle(ChatFormatting.DARK_GRAY));
                }
                if (display.contains("bs") && display.contains("bm")) {
                    MutableComponent b1 = Component.translatable("tooltip.scintilla.bottle");
                    MutableComponent b2 = Component.translatable("item.scintilla."
                            + display.getString("bs")
                            + "_"
                            + display.getString("bm")
                            + "_bottle");
                    MutableComponent b3 = b1.append(b2);
                    pTooltipComponents.add(b3.withStyle(ChatFormatting.DARK_GRAY));
                }
            }
            if ((tag != null && tag.contains("hv")) || (tag != null && tag.contains("sv")) || (tag != null && tag.contains("md"))) {
                if (Screen.hasShiftDown()) {
                    if (tag.contains("hv")) {
                        MutableComponent h1 = Component.translatable("tooltip.scintilla.nutrition");
                        MutableComponent h2 = h1.append(String.valueOf(tag.getInt("hv")));
                        pTooltipComponents.add(h2.withStyle(ChatFormatting.DARK_GRAY));
                    }
                    if (tag.contains("sv")) {
                        MutableComponent s1 = Component.translatable("tooltip.scintilla.saturation");
                        MutableComponent s2 = s1.append(String.format("%.2f", tag.getFloat("sv")));
                        pTooltipComponents.add(s2.withStyle(ChatFormatting.DARK_GRAY));
                    }
                    if (tag.contains("md")) {
                        MutableComponent d1 = Component.translatable("tooltip.scintilla.max_durability");
                        MutableComponent d2 = d1.append(String.valueOf(tag.getInt("md") - 1));
                        pTooltipComponents.add(d2.withStyle(ChatFormatting.DARK_GRAY));
                    }
                } else {
                    MutableComponent e1 = Component.translatable("tooltip.scintilla.expandable");
                    pTooltipComponents.add(e1.withStyle(ChatFormatting.GRAY));
                }
            }
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public @NotNull Rarity getRarity(ItemStack pStack) {
        if (pStack.hasTag()) {
            CompoundTag tag = pStack.getTag();
            int         h   = (tag != null && tag.contains("hv")) ? tag.getInt("hv") : 2;
            float       s   = (tag != null && tag.contains("sv")) ? tag.getFloat("sv") : 0.4f;
            int         d   = (tag != null && tag.contains("md")) ? tag.getInt("md") : 9;
            if (h >= 3 && s >= 1.1f && d >= 16) {
                if (h >= 7 && s >= 12.7f && d >= 64) {
                    if (h >= 9 && s >= 19.9f && d >= 256) {
                        return Rarity.EPIC;
                    } else {
                        return Rarity.RARE;
                    }
                } else {
                    return Rarity.UNCOMMON;
                }
            } else {
                return Rarity.COMMON;
            }
        } else {
            return Rarity.COMMON;
        }
    }
}
