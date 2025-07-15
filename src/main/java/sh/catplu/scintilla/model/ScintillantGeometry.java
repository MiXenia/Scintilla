package sh.catplu.scintilla.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockElement;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.SpriteContents;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.ForgeFaceData;
import net.minecraftforge.client.model.geometry.IGeometryBakingContext;
import net.minecraftforge.client.model.geometry.IUnbakedGeometry;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.client.model.geometry.UnbakedGeometryHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;


@SuppressWarnings("removal")
public class ScintillantGeometry implements IUnbakedGeometry<ScintillantGeometry> {
    private final String bs, bm, dl, dr;

    public ScintillantGeometry(String bs, String bm, String dl, String dr) {
        this.bs = bs;
        this.bm = bm;
        this.dl = dl;
        this.dr = dr;
    }

    @Override
    public BakedModel bake(IGeometryBakingContext context, ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides, ResourceLocation modelLocation) {
        // Compose the texture paths
        ResourceLocation layer0 = new ResourceLocation("scintilla","item/s_" + bs + "_shatterglass");
        ResourceLocation layer1 = new ResourceLocation("scintilla","item/l_" + bs + "_" + dl);
        ResourceLocation layer2 = new ResourceLocation("scintilla","item/r_" + bs + "_" + dr);
        ResourceLocation layer3 = new ResourceLocation("scintilla","item/b_" + bs + "_" + bm);

        ImmutableList<Material> materials = ImmutableList.of(
                new Material(InventoryMenu.BLOCK_ATLAS, layer0),
                new Material(InventoryMenu.BLOCK_ATLAS, layer1),
                new Material(InventoryMenu.BLOCK_ATLAS, layer2),
                new Material(InventoryMenu.BLOCK_ATLAS, layer3)
        );

        // Build a layered item model (like vanilla's ItemLayerModel)
        BakedModel model = bakeModel(bs, bm, dl, dr, baker, modelState, overrides, spriteGetter, context.getTransforms());
        // Populate the cache for the default key
        String cacheKey = "c_" + bs + "_" + bm + "_" + dl + "_" + dr;
        MODEL_CACHE.put(cacheKey, model);
        return model;
    }

    public static BakedModel bakeModel(
        String bs, String bm, String dl, String dr,
        ModelBaker baker, ModelState state, ItemOverrides overrides,
        Function<Material, TextureAtlasSprite> spriteGetter,
        ItemTransforms transforms
    ) {
        ResourceLocation layer0 = new ResourceLocation("scintilla","item/s_" + bs + "_shatterglass");
        ResourceLocation layer1 = new ResourceLocation("scintilla","item/l_" + bs + "_" + dl);
        ResourceLocation layer2 = new ResourceLocation("scintilla","item/r_" + bs + "_" + dr);
        ResourceLocation layer3 = new ResourceLocation("scintilla","item/b_" + bs + "_" + bm);

        ImmutableList<Material> materials = ImmutableList.of(
            new Material(net.minecraft.world.inventory.InventoryMenu.BLOCK_ATLAS, layer0),
            new Material(net.minecraft.world.inventory.InventoryMenu.BLOCK_ATLAS, layer1),
            new Material(net.minecraft.world.inventory.InventoryMenu.BLOCK_ATLAS, layer2),
            new Material(net.minecraft.world.inventory.InventoryMenu.BLOCK_ATLAS, layer3)
        );

        return new LayeredItemModel(materials, spriteGetter, state, overrides, baker, transforms);
    }

    // Add a static cache and a getOrBake method:
    static final Map<String, BakedModel> MODEL_CACHE = new ConcurrentHashMap<>();

    //public static BakedModel getOrBake(String bs, String bm, String dl, String dr, ModelBaker baker, ModelState state, ItemOverrides overrides) {
    //    String cacheKey = "c_" + bs + "_" + bm + "_" + dl + "_" + dr;
    //    return MODEL_CACHE.computeIfAbsent(cacheKey, k -> bakeModel(bs, bm, dl, dr, baker, state, overrides, spriteGetter));
    //}

    // Simple layered item model implementation
    public static class LayeredItemModel implements BakedModel {
        private final ImmutableList<Material> materials;
        private final Function<Material, TextureAtlasSprite> spriteGetter;
        private final ModelState modelState;
        private final ItemOverrides overrides;
        private final ModelBaker modelBaker;
        private final ItemTransforms transforms;

        public LayeredItemModel(ImmutableList<Material> materials, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides, ModelBaker modelBaker, ItemTransforms transforms) {
            this.materials = materials;
            this.spriteGetter = spriteGetter;
            this.modelState = modelState;
            this.overrides = new ScintillantOverrider(this); // Pass self to overrider
            this.modelBaker = modelBaker;
            this.transforms = transforms;
        }

        @Override
        public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable net.minecraft.core.Direction side, net.minecraft.util.RandomSource rand) {
            if (side != null) return Collections.emptyList();

            ImmutableList.Builder<BakedQuad> quads = ImmutableList.builder();

            for (int i = 0; i < materials.size(); ++i) {
                Material mat = materials.get(i);
                //System.out.println("MEOWMEOWMEOW Loading texture: " + mat.texture());
                TextureAtlasSprite sprite = spriteGetter.apply(mat);
                SpriteContents contents = sprite.contents();

                // If you have ForgeFaceData, pass it here, otherwise use ForgeFaceData.DEFAULT
                List<BlockElement> elements = UnbakedGeometryHelper.createUnbakedItemElements(i, contents, ForgeFaceData.DEFAULT);

                // The spriteGetter for bakeElements expects a String (texture name), so wrap your Material lookup
                List<BakedQuad> baked = UnbakedGeometryHelper.bakeElements(
                        elements,
                        name -> sprite,
                        modelState,
                        mat.texture()
                );
                quads.addAll(baked);
            }

            return quads.build();
        }

        @Override
        public ItemTransforms getTransforms() {
            return transforms;
        }


        @Override
        public boolean useAmbientOcclusion() { return false; }
        @Override
        public boolean isGui3d() { return false; }
        @Override
        public boolean usesBlockLight() { return false; }
        @Override
        public boolean isCustomRenderer() { return false; }
        @Override
        public TextureAtlasSprite getParticleIcon() {
            // Use the first layer as the particle
            return spriteGetter.apply(materials.get(0));
        }
        @Override
        public ItemOverrides getOverrides() { return overrides; }

        // Add getters for modelBaker and modelState
        public ModelBaker getModelBaker() { return modelBaker; }
        public ModelState getModelState() { return modelState; }
        public Function<Material, TextureAtlasSprite> getSpriteGetter() { return spriteGetter; }
    }
}
