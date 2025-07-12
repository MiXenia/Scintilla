package sh.catplu.scintilla.item;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;


public class ScintillaItem extends Item implements DyeableLeatherItem{
    public ScintillaItem(Properties pProperties) {
        //super(pProperties.food(NON_MODULAR_FOOD));
        super(pProperties);
    }

    private static FoodProperties NON_MODULAR_FOOD =
        new FoodProperties.Builder()
                .alwaysEat()
                .build();

    @Override
    public @Nullable FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity) {
        if (stack.hasTag() && stack.getTag().contains("hv") && stack.getTag().contains("sv")) {
            CompoundTag nbt = stack.getOrCreateTag();
            int hunger = nbt.getInt("hv");
            float saturation = nbt.getFloat("sv");
            return new FoodProperties.Builder()
                    .nutrition(hunger)
                    .saturationMod(saturation)
                    .alwaysEat()
                    .build();
        } else {
            return new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationMod(0.4f)
                    .alwaysEat()
                    .build();
        }

    }

    //@Override
    //public int getColor(ItemStack pStack) {
    //    CompoundTag $$1 = pStack.getTagElement("display");
    //    return $$1 != null && $$1.contains("color", 99) ? $$1.getInt("color") : 16777215;
    //}

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.AMETHYST_BLOCK_BREAK;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 22;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains("md")) {
            return stack.getTag().getInt("md");
        } else {
            return super.getMaxDamage(stack);
        }
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        if (pStack.hasTag() && pStack.getTag().contains("Damage")) {
            return pStack.getTag().getInt("Damage") > 0;
        } else {
            return super.isBarVisible(pStack);
        }
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        if (pStack.hasTag() && pStack.getTag().contains("Damage") && pStack.getTag().contains("md")) {
            int cd = pStack.getTag().getInt("Damage");
            int md = pStack.getTag().getInt("md");
            return Math.round(13.0f - (float) cd * 13.0f / (float) md);
        } else {
            return super.getBarWidth(pStack);
        }
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        if (pStack.hasTag() && pStack.getTag().contains("Damage") && pStack.getTag().contains("md")) {
            int cd = pStack.getTag().getInt("Damage");
            int md = pStack.getTag().getInt("md");
            float pc = (float) cd / (float) md;
            int sr = 255;
            int sg = 170;
            int sb = 221;
            int er = 170;
            int eg = 238;
            int eb = 255;
            int ir = (int) (sr + (er - sr) * pc);
            int ig = (int) (sg + (eg - sg) * pc);
            int ib = (int) (sb + (eb - sb) * pc);
            int color = (ir << 16) | (ig << 8) | ib;
            return color;
        } else {
            return super.getBarColor(pStack);
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (pLivingEntity instanceof Player player){
            if (player.getFoodData().getFoodLevel() < 20) {
                if (pStack.getDamageValue() < pStack.getMaxDamage() - 1) {
                    
                    FoodProperties nutrition = pStack.getFoodProperties(null);
                    if (nutrition != null) {
                        player.getFoodData().eat(nutrition.getNutrition(),nutrition.getSaturationModifier());
                    }
                    pStack.setDamageValue(pStack.getDamageValue() + 1);
                }
            }
        }
        return pStack;
    }
}
