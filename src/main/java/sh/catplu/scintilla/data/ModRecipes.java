package sh.catplu.scintilla.data;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import sh.catplu.scintilla.ScintillaMod;

import java.util.Map;
import java.util.Set;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ScintillaMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<InheritColorShapedRecipe>> COLOR_RECIPE_SERIALIZER =
            RECIPES.register("color_copy_shaped", () -> new RecipeSerializer<InheritColorShapedRecipe>() {
                @Override
                public InheritColorShapedRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
                    String group = GsonHelper.getAsString(jsonObject, "group", "");
                    CraftingBookCategory craftingbookcategory = (CraftingBookCategory)CraftingBookCategory.CODEC.byName(
                            GsonHelper.getAsString(jsonObject, "category", (String)null), CraftingBookCategory.MISC
                    );
                    Map<String, Ingredient> map = Maps.newHashMap();

                    for(Map.Entry<String, JsonElement> entry : GsonHelper.getAsJsonObject(jsonObject, "key").entrySet()) {
                        if (((String)entry.getKey()).length() != 1) {
                            throw new JsonSyntaxException("Invalid key entry: '" + (String)entry.getKey() + "' is an invalid symbol (must be 1 character only).");
                        }

                        if (" ".equals(entry.getKey())) {
                            throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
                        }

                        map.put((String)entry.getKey(), Ingredient.fromJson((JsonElement)entry.getValue(), false));
                    }

                    map.put(" ", Ingredient.EMPTY);
                    String[] patterns = new String[GsonHelper.getAsJsonArray(jsonObject, "pattern").size()];
                    if (patterns.length > 3) {
                        throw new JsonSyntaxException("Invalid pattern: too many rows, " + 3 + " is maximum");
                    } else if (patterns.length == 0) {
                        throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
                    } else {
                        for (int i = 0; i < patterns.length; ++i) {
                            String st = GsonHelper.convertToString(GsonHelper.getAsJsonArray(jsonObject, "pattern").get(i), "pattern[" + i + "]");
                            if (st.length() > 3) {
                                throw new JsonSyntaxException("Invalid pattern: too many columns, " + 3 + " is maximum");
                            }

                            if (i > 0 && patterns[0].length() != st.length()) {
                                throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
                            }

                            patterns[i] = st;
                        }
                    }
                    String[] astring;
                    int i = Integer.MAX_VALUE;
                    int j = 0;
                    int k = 0;
                    int l = 0;

                    for(int i1 = 0; i1 < patterns.length; ++i1) {
                        String s = patterns[i1];
                        int jj;
                        for(jj = 0; jj < s.length() && s.charAt(jj) == ' '; ++jj) {}
                        i = Math.min(i, jj);
                        int jkl;
                        for(jkl = s.length() - 1; jkl >= 0 && s.charAt(i) == ' '; --jkl) {
                        }
                        int j1 = jkl;
                        jj = Math.max(jj, j1);
                        if (j1 < 0) {
                            if (k == i1) {
                                ++k;
                            }

                            ++l;
                        } else {
                            l = 0;
                        }
                    }

                    if (patterns.length == l) {
                        astring = new String[0];
                    } else {
                        astring = new String[patterns.length - l - k];

                        for(int k1 = 0; k1 < astring.length; ++k1) {
                            astring[k1] = patterns[k1 + k].substring(i, j + 1);
                        }

                    }

                    int rwidth = astring[0].length();
                    int rheight = astring.length;
                    NonNullList<Ingredient> nonnulllist = NonNullList.withSize(rwidth * rheight, Ingredient.EMPTY);
                    Set<String> set = Sets.newHashSet(map.keySet());
                    set.remove(" ");

                    for(int ith = 0; ith < astring.length; ++ith) {
                        for(int jth = 0; jth < astring[i].length(); ++jth) {
                            String s = astring[ith].substring(jth, jth + 1);
                            Ingredient ingredient = (Ingredient)map.get(s);
                            if (ingredient == null) {
                                throw new JsonSyntaxException("Pattern references symbol '" + s + "' but it's not defined in the key");
                            }

                            set.remove(s);
                            nonnulllist.set(j + rwidth * ith, ingredient);
                        }
                    }

                    if (!set.isEmpty()) {
                        throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + String.valueOf(set));
                    }
                    ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
                    boolean flag = GsonHelper.getAsBoolean(jsonObject, "show_notification", true);
                    return new InheritColorShapedRecipe(resourceLocation, group, craftingbookcategory, rwidth, rheight, nonnulllist, itemstack, flag);
                }

                @Override
                public @Nullable InheritColorShapedRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
                    int i = friendlyByteBuf.readVarInt();
                    int j = friendlyByteBuf.readVarInt();
                    String s = friendlyByteBuf.readUtf();
                    CraftingBookCategory craftingbookcategory = (CraftingBookCategory)friendlyByteBuf.readEnum(CraftingBookCategory.class);
                    NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i * j, Ingredient.EMPTY);

                    for(int k = 0; k < nonnulllist.size(); ++k) {
                        nonnulllist.set(k, Ingredient.fromNetwork(friendlyByteBuf));
                    }

                    ItemStack itemstack = friendlyByteBuf.readItem();
                    boolean flag = friendlyByteBuf.readBoolean();
                    return new InheritColorShapedRecipe(resourceLocation, s, craftingbookcategory, i, j, nonnulllist, itemstack, flag);
                }

                @Override
                public void toNetwork(FriendlyByteBuf friendlyByteBuf, InheritColorShapedRecipe inheritColorShapedRecipe) {
                    friendlyByteBuf.writeVarInt(inheritColorShapedRecipe.getWidth());
                    friendlyByteBuf.writeVarInt(inheritColorShapedRecipe.getHeight());
                    friendlyByteBuf.writeUtf(inheritColorShapedRecipe.getGroup());
                    friendlyByteBuf.writeEnum(inheritColorShapedRecipe.category());

                    NonNullList<Ingredient> ingredients = inheritColorShapedRecipe.getIngredients();
                    friendlyByteBuf.writeInt(ingredients.size());
                    for(Ingredient ingredient : ingredients) {
                        ingredient.toNetwork(friendlyByteBuf);
                    }

                    friendlyByteBuf.writeItem(inheritColorShapedRecipe.getResultItemStack());
                    friendlyByteBuf.writeBoolean(inheritColorShapedRecipe.showNotification());
                }
            });
    public static final RegistryObject<RecipeType<InheritColorShapedRecipe>> COLOR_RECIPE_TYPE =
            RecipeType.register("color_copy_shaped");
}
