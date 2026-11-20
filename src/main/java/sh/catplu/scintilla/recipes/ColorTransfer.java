package sh.catplu.scintilla.recipes;

import com.google.gson.JsonObject;

import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import sh.catplu.scintilla.ScintillaMod;
import sh.catplu.scintilla.utils.NBTCopyUtil;

public class ColorTransfer extends ShapedRecipe {
    final         int                     colorSourceSlot;

    public ColorTransfer(ShapedRecipe base, int colorSourceSlot) {
        super(
            base.getId(),
            base.getGroup(),
            base.category(),
            base.getWidth(),
            base.getHeight(),
            base.getIngredients(),
            base.getResultItem(null),
            base.showNotification()
        );
        this.colorSourceSlot   = colorSourceSlot;
    }

    @Override 
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.COLOR_RECIPE.get();
    }

    private int findColorSlot(CraftingContainer pInv) {
        for (int i = 0; i <= pInv.getWidth() - getWidth(); ++i) {
            for (int j = 0; j <= pInv. getHeight() - getHeight(); ++j) {
                if (matchesAt(pInv,i,j)) {
                    int slot = getColorSlot(i,j,pInv.getWidth());
                    if (pInv.getItem(slot).hasTag()) {
                        return slot;
                    }
                }
            }
        }
        return -1;
    }

    private int getColorSlot(int i, int j, int width) {
        int recipeX = this.colorSourceSlot % getWidth();
        int recipeY = this.colorSourceSlot / getWidth();
        return (i + recipeX) + (j + recipeY) * width;
    }

    private boolean matchesAt(CraftingContainer inv, int offX, int offY) {
        for (int x = 0; x < inv.getWidth(); ++x) {
            for (int y = 0; y < inv.getHeight(); ++y) {
                int k = x - offX;
                int l = y - offY;
                Ingredient ingredient = Ingredient.EMPTY;
                if (k >=0 && l >= 0 && k < getWidth() && l < getHeight()) {
                    ingredient = getIngredients().get(k+l * getWidth());
                }
                if (!ingredient.test(inv.getItem(x + y * inv.getWidth()))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override 
    public boolean matches(CraftingContainer inv, Level level) {
        return findColorSlot(inv) != -1;
    }

    @Override
    public ItemStack assemble(CraftingContainer inv, RegistryAccess registryAccess) {
        int slot = findColorSlot(inv);
        if (slot == -1) return ItemStack.EMPTY;
        return NBTCopyUtil.mergeNbtPreservingExisting(inv.getItem(slot),getResultItem(registryAccess).copy());
    }

    public static class Serializer implements RecipeSerializer<ColorTransfer> {
        public static final Serializer INSTANCE = new Serializer();

        @SuppressWarnings("removal")
        public static final ResourceLocation ID = new ResourceLocation(ScintillaMod.MOD_ID, "color");

        @Override
        public ColorTransfer fromJson(ResourceLocation pRecipeId, JsonObject pJson) {
            int                     colorslot            = GsonHelper.getAsInt(pJson, "color_slot", 0);
            ShapedRecipe base = RecipeSerializer.SHAPED_RECIPE.fromJson(pRecipeId,pJson);
            return new ColorTransfer(base, colorslot);
        }

        @Override
        public ColorTransfer fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            ShapedRecipe base = RecipeSerializer.SHAPED_RECIPE.fromNetwork(pRecipeId, pBuffer);
            return new ColorTransfer(base,pBuffer.readVarInt());
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, ColorTransfer pRecipe) {
            RecipeSerializer.SHAPED_RECIPE.toNetwork(pBuffer,pRecipe);
            pBuffer.writeVarInt(pRecipe.colorSourceSlot);
        }
    }
}
