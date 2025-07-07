package sh.catplu.scintilla.data;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class InheritColorShapedRecipe extends ShapedRecipe {
    private ItemStack result;

    public InheritColorShapedRecipe(ResourceLocation pId, String pGroup, CraftingBookCategory pCategory, int pWidth, int pHeight, NonNullList<Ingredient> pRecipeItems, ItemStack pResult, boolean pShowNotification) {
        super(pId, pGroup, pCategory, pWidth, pHeight, pRecipeItems, pResult, pShowNotification);
        this.result = pResult;
    }

    @Override
    public ItemStack assemble(CraftingContainer pContainer, RegistryAccess pRegistryAccess) {

        ItemStack result = super.assemble(pContainer, pRegistryAccess);

        for (int i = 0; i < pContainer.getContainerSize(); i++) {
            ItemStack ingredientStack = pContainer.getItem(i);
            if (!ingredientStack.isEmpty() && ingredientStack.hasTag()) {
                CompoundTag ingredientTag = ingredientStack.getTag();
                if (ingredientTag.contains("display") && ingredientTag.getCompound("display").contains("color")) {
                    CompoundTag resultTag = result.getOrCreateTagElement("display");
                    resultTag.putInt("color", ingredientTag.getCompound("display").getInt("color"));
                    break;
                }
            }
        }
        this.result = result;
        return result;
    }

    public ItemStack getResultItemStack() {
        return this.result;
    }
}
