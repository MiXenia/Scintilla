package sh.catplu.scintilla.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.minecraftforge.client.model.geometry.IGeometryBakingContext;

@SuppressWarnings("removal")
public class ScintillantOverrider extends ItemOverrides {

    // Cache: key is a string of your four texture names
    private static final Map<String, BakedModel> MODEL_CACHE = new ConcurrentHashMap<>();

    @Override
    public BakedModel resolve(BakedModel pModel, ItemStack pStack, @Nullable ClientLevel pLevel, @Nullable LivingEntity pEntity, int pSeed) {
        String bs = "vanilla";
        String bm = "glass";
        String dl = "sugar";
        String dr = "snow";
        if (pStack.hasTag()) {
            CompoundTag tag = pStack.getTagElement("display");
            if (tag == null) tag = pStack.getTag();
            bs = tag != null && tag.contains("bs") ? tag.getString("bs") : "vanilla";
            bm = tag != null && tag.contains("bm") ? tag.getString("bm") : "glass";
            dl = tag != null && tag.contains("dl") ? tag.getString("dl") : "sugar";
            dr = tag != null && tag.contains("dr") ? tag.getString("dr") : "snow";
        }

        // Compose the texture paths
        ResourceLocation layer0 = new ResourceLocation("scintilla","item/s_" + bs + "shatterglass");
        ResourceLocation layer1 = new ResourceLocation("scintilla","item/l_" + bs + dl);
        ResourceLocation layer2 = new ResourceLocation("scintilla","item/r_" + bs + dr);
        ResourceLocation layer3 = new ResourceLocation("scintilla","item/b_" + bs + "_" + bm);

        // Key for caching
        String cacheKey = "c_" + bs + "_" + bm + "_" + dl + "_" + dr;

        // Check cache
        BakedModel cached = MODEL_CACHE.get(cacheKey);
        if (cached != null) return cached;

        // Build materials for ItemLayerModel
        ImmutableList<Material> materials = ImmutableList.of(
                new Material(net.minecraft.client.renderer.texture.TextureAtlas.LOCATION_BLOCKS, layer0),
                new Material(net.minecraft.client.renderer.texture.TextureAtlas.LOCATION_BLOCKS, layer1),
                new Material(net.minecraft.client.renderer.texture.TextureAtlas.LOCATION_BLOCKS, layer2),
                new Material(net.minecraft.client.renderer.texture.TextureAtlas.LOCATION_BLOCKS, layer3)
        );

        // Create ItemLayerModel
        ScintillantModel ilm = new ScintillantModel(materials, null, null);

        // Bake the model
        ModelBaker baker = Minecraft.getInstance().getBa();
        ModelState state = pModel.getOverrides() != null ? null : null; // Use null or get from context if needed
        ItemOverrides overrides = ItemOverrides.EMPTY;
        ResourceLocation modelLoc = new ResourceLocation("scintilla", "dynamic_item/" + cacheKey.hashCode());

        // Use a simple IGeometryBakingContext (can be improved for transforms, etc.)
        IGeometryBakingContext context = new IGeometryBakingContext() {
            @Override public boolean hasMaterial(String name) { return name.startsWith("layer") && Integer.parseInt(name.substring(5)) < 4; }
            @Override public Material getMaterial(String name) { int idx = Integer.parseInt(name.substring(5)); return materials.get(idx); }
            // ...implement other methods as needed, or use a real context if available...
        };

        BakedModel baked = ilm.bake(context, baker, mat -> Minecraft.getInstance().getModelManager().getAtlas(mat.texture()).getSprite(mat.texture()), state, overrides, modelLoc);

        // Cache and return
        MODEL_CACHE.put(cacheKey, baked);
        return baked;
    }
}
