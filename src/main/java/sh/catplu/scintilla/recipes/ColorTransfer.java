package sh.catplu.scintilla.recipes;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import sh.catplu.scintilla.ScintillaMod;
import sh.catplu.scintilla.utils.NBTCopyUtil;

import java.util.Map;
import java.util.Set;

public class ColorTransfer extends ShapedRecipe {
    static        int                     MAX_WIDTH              = 3;
    static        int                     MAX_HEIGHT             = 3;
    private final String                  pGroup;
    private final CraftingBookCategory    pCategory;
    private final int                     pWidth;
    private final int                     pHeight;
    private final NonNullList<Ingredient> pRecipeItems;
    private final ItemStack               pResult;
    private final boolean                 pShowNotification;
    final         int                     colorSourceSlot;
    private       int                     matchedColorSourceSlot = -1;

    public ColorTransfer(ResourceLocation pId, String pGroup, CraftingBookCategory pCategory, int pWidth, int pHeight, NonNullList<Ingredient> pRecipeItems, ItemStack pResult, boolean pShowNotification, int colorSourceSlot) {
        super(pId, pGroup, pCategory, pWidth, pHeight, pRecipeItems, pResult, pShowNotification);
        this.pGroup            = pGroup;
        this.pCategory         = pCategory;
        this.pWidth            = pWidth;
        this.pHeight           = pHeight;
        this.pRecipeItems      = pRecipeItems;
        this.pResult           = pResult;
        this.pShowNotification = pShowNotification;
        this.colorSourceSlot   = colorSourceSlot;
    }

    @Override
    public boolean matches(CraftingContainer pInv, Level pLevel) {
        if (pLevel.isClientSide()) {
            return false;
        }
        for (int i = 0; i <= pInv.getWidth() - this.pWidth; ++i) {
            for (int j = 0; j <= pInv.getHeight() - this.pHeight; ++j) {
                int slot = getColorSlot(i, j, pInv.getWidth(), pInv.getHeight());
                if (this.matches(pInv, i, j, false)) {
                    if (pInv.getItem(slot).hasTag()) {
                        this.matchedColorSourceSlot = slot;
                        return true;
                    }
                }
            }
        }
        this.matchedColorSourceSlot = -1;
        return false;
    }

    private int getColorSlot(int i, int j, int width, int height) {
        int recipeX = this.colorSourceSlot % this.pWidth;
        int recipeY = this.colorSourceSlot / this.pWidth;
        int gridX   = i + recipeX;
        int gridY   = j + recipeY;
        return gridX + gridY * width;
    }

    private boolean matches(CraftingContainer pCraftingInventory, int pWidth, int pHeight, boolean pMirrored) {
        if (pMirrored) return false;
        for (int i = 0; i < pCraftingInventory.getWidth(); ++i) {
            for (int j = 0; j < pCraftingInventory.getHeight(); ++j) {
                int        k          = i - pWidth;
                int        l          = j - pHeight;
                Ingredient ingredient = Ingredient.EMPTY;
                if (k >= 0 && l >= 0 && k < this.pWidth && l < this.pHeight) {
                    ingredient = this.pRecipeItems.get(k + l * this.pWidth);
                }

                if (!ingredient.test(pCraftingInventory.getItem(i + j * pCraftingInventory.getWidth()))) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public ItemStack assemble(CraftingContainer pContainer, RegistryAccess pRegistryAccess) {
        ItemStack donor  = pContainer.getItem(this.matchedColorSourceSlot);
        ItemStack source = this.pResult.copy();
        ItemStack output = NBTCopyUtil.mergeNbtPreservingExisting(donor, source);
        return output;
    }

    static Map<String, Ingredient> keyFromJson(JsonObject pKeyEntry) {
        Map<String, Ingredient> map = Maps.newHashMap();

        for (Map.Entry<String, JsonElement> entry : pKeyEntry.entrySet()) {
            if (((String) entry.getKey()).length() != 1) {
                throw new JsonSyntaxException("Invalid key entry: '" + (String) entry.getKey() + "' is an invalid symbol (must be 1 character only).");
            }

            if (" ".equals(entry.getKey())) {
                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
            }

            map.put((String) entry.getKey(), Ingredient.fromJson((JsonElement) entry.getValue(), false));

        }

        map.put(" ", Ingredient.EMPTY);
        return map;
    }

    static String[] shrink(String... pToShrink) {
        int i = Integer.MAX_VALUE;
        int j = 0;
        int k = 0;
        int l = 0;

        for (int i1 = 0; i1 < pToShrink.length; ++i1) {
            String s = pToShrink[i1];
            i = Math.min(i, firstNonSpace(s));
            int j1 = lastNonSpace(s);
            j = Math.max(j, j1);
            if (j1 < 0) {
                if (k == i1) {
                    ++k;
                }

                ++l;
            } else {
                l = 0;
            }
        }

        if (pToShrink.length == l) {
            return new String[0];
        } else {
            String[] astring = new String[pToShrink.length - l - k];

            for (int k1 = 0; k1 < astring.length; ++k1) {
                astring[k1] = pToShrink[k1 + k].substring(i, j + 1);
            }

            return astring;
        }
    }

    private static int firstNonSpace(String pEntry) {
        int i;
        for (i = 0; i < pEntry.length() && pEntry.charAt(i) == ' '; ++i) {
        }

        return i;
    }

    private static int lastNonSpace(String pEntry) {
        int i;
        for (i = pEntry.length() - 1; i >= 0 && pEntry.charAt(i) == ' '; --i) {
        }

        return i;
    }

    static String[] patternFromJson(JsonArray pPatternArray) {
        String[] astring = new String[pPatternArray.size()];
        if (astring.length > MAX_HEIGHT) {
            throw new JsonSyntaxException("Invalid pattern: too many rows, " + MAX_HEIGHT + " is maximum");
        } else if (astring.length == 0) {
            throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
        } else {
            for (int i = 0; i < astring.length; ++i) {
                String s = GsonHelper.convertToString(pPatternArray.get(i), "pattern[" + i + "]");
                if (s.length() > MAX_WIDTH) {
                    throw new JsonSyntaxException("Invalid pattern: too many columns, " + MAX_WIDTH + " is maximum");
                }

                if (i > 0 && astring[0].length() != s.length()) {
                    throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
                }

                astring[i] = s;
            }

            return astring;
        }
    }

    static NonNullList<Ingredient> dissolvePattern(String[] pPattern, Map<String, Ingredient> pKeys, int pPatternWidth, int pPatternHeight) {
        NonNullList<Ingredient> nonnulllist = NonNullList.withSize(pPatternWidth * pPatternHeight, Ingredient.EMPTY);
        Set<String>             set         = Sets.newHashSet(pKeys.keySet());
        set.remove(" ");

        for (int i = 0; i < pPattern.length; ++i) {
            for (int j = 0; j < pPattern[i].length(); ++j) {
                String     s          = pPattern[i].substring(j, j + 1);
                Ingredient ingredient = (Ingredient) pKeys.get(s);
                if (ingredient == null) {
                    throw new JsonSyntaxException("Pattern references symbol '" + s + "' but it's not defined in the key");
                }

                set.remove(s);
                nonnulllist.set(j + pPatternWidth * i, ingredient);
            }
        }

        if (!set.isEmpty()) {
            throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + String.valueOf(set));
        } else {
            return nonnulllist;
        }
    }

    public static class Serializer implements RecipeSerializer<ColorTransfer> {
        public static final ColorTransfer.Serializer INSTANCE = new ColorTransfer.Serializer();

        @SuppressWarnings("removal")
        public static final ResourceLocation ID = new ResourceLocation(ScintillaMod.MOD_ID, "color");

        @Override
        public ColorTransfer fromJson(ResourceLocation pRecipeId, JsonObject pJson) {
            String                  s                    = GsonHelper.getAsString(pJson, "group", "");
            CraftingBookCategory    craftingbookcategory = (CraftingBookCategory) CraftingBookCategory.CODEC.byName(GsonHelper.getAsString(pJson, "category", (String) null), CraftingBookCategory.MISC);
            Map<String, Ingredient> map                  = ColorTransfer.keyFromJson(GsonHelper.getAsJsonObject(pJson, "key"));
            String[]                astring              = ColorTransfer.shrink(ColorTransfer.patternFromJson(GsonHelper.getAsJsonArray(pJson, "pattern")));
            int                     i                    = astring[0].length();
            int                     j                    = astring.length;
            NonNullList<Ingredient> nonnulllist          = ColorTransfer.dissolvePattern(astring, map, i, j);
            ItemStack               itemstack            = ColorTransfer.itemStackFromJson(GsonHelper.getAsJsonObject(pJson, "result"));
            boolean                 flag                 = GsonHelper.getAsBoolean(pJson, "show_notification", true);
            int                     colorslot            = GsonHelper.getAsInt(pJson, "color_slot", 0);
            return new ColorTransfer(pRecipeId, s, craftingbookcategory, i, j, nonnulllist, itemstack, flag, colorslot);
        }

        @Override
        public @Nullable ColorTransfer fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            int                     i                    = pBuffer.readVarInt();
            int                     j                    = pBuffer.readVarInt();
            String                  s                    = pBuffer.readUtf();
            CraftingBookCategory    craftingbookcategory = (CraftingBookCategory) pBuffer.readEnum(CraftingBookCategory.class);
            NonNullList<Ingredient> nonnulllist          = NonNullList.withSize(i * j, Ingredient.EMPTY);

            for (int k = 0; k < nonnulllist.size(); ++k) {
                nonnulllist.set(k, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack itemstack = pBuffer.readItem();
            boolean   flag      = pBuffer.readBoolean();
            int       colorslot = pBuffer.readInt();
            return new ColorTransfer(pRecipeId, s, craftingbookcategory, i, j, nonnulllist, itemstack, flag, colorslot);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, ColorTransfer pRecipe) {
            pBuffer.writeVarInt(pRecipe.pWidth);
            pBuffer.writeVarInt(pRecipe.pHeight);
            pBuffer.writeUtf(pRecipe.pGroup);
            pBuffer.writeEnum(pRecipe.pCategory);

            for (Ingredient ingredient : pRecipe.pRecipeItems) {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeItem(pRecipe.pResult);
            pBuffer.writeBoolean(pRecipe.pShowNotification);
            pBuffer.writeInt(pRecipe.colorSourceSlot);
        }
    }
}
