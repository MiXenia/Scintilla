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
        MODEL_CACHE.clear();
        BakedModel model = bakeModel(bs, bm, dl, dr, modelState, spriteGetter, context.getTransforms());
        MODEL_CACHE.put("c_" + bs + "_" + bm + "_" + dl + "_" + dr, model);
        return model;
    }

    public static BakedModel bakeModel(
        String bs, String bm, String dl, String dr,
        ModelState state,
        Function<Material, TextureAtlasSprite> spriteGetter,
        ItemTransforms transforms
    ) {

        ImmutableList<Material> materials = ImmutableList.of(
            new Material(InventoryMenu.BLOCK_ATLAS, new ResourceLocation("scintilla","item/s_" + bs + "_shatterglass")),
            new Material(InventoryMenu.BLOCK_ATLAS, new ResourceLocation("scintilla","item/l_" + bs + "_" + dl)),
            new Material(InventoryMenu.BLOCK_ATLAS, new ResourceLocation("scintilla","item/r_" + bs + "_" + dr)),
            new Material(InventoryMenu.BLOCK_ATLAS, new ResourceLocation("scintilla","item/b_" + bs + "_" + bm))
        );

        return new LayeredItemModel(materials, spriteGetter, state, transforms);
    }

    // Add a static cache and a getOrBake method:
    static final Map<String, BakedModel> MODEL_CACHE = new ConcurrentHashMap<>();

    // Simple layered item model implementation
    public static class LayeredItemModel implements BakedModel {
        private final ImmutableList<Material> materials;
        private final Function<Material, TextureAtlasSprite> spriteGetter;
        private final ModelState modelState;
        private final ItemOverrides overrides;
        private final ItemTransforms transforms;
        private final List<BakedQuad> quads;

        public LayeredItemModel(ImmutableList<Material> materials, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemTransforms transforms) {
            this.materials = materials;
            this.spriteGetter = spriteGetter;
            this.modelState = modelState;
            this.overrides = new ScintillantOverrider(this); // Pass self to overrider
            this.transforms = transforms;
            ImmutableList.Builder<BakedQuad> builder = ImmutableList.builder();
            for (int i = 0; i < materials.size(); ++i) {
                Material mat = materials.get(i);
                //System.out.println("MEOWMEOWMEOW Loading texture: " + mat.texture());
                TextureAtlasSprite sprite = spriteGetter.apply(mat);

                // If you have ForgeFaceData, pass it here, otherwise use ForgeFaceData.DEFAULT
                List<BlockElement> elements = UnbakedGeometryHelper.createUnbakedItemElements(i, sprite.contents(), ForgeFaceData.DEFAULT);

                // The spriteGetter for bakeElements expects a String (texture name), so wrap your Material lookup
                builder.addAll(UnbakedGeometryHelper.bakeElements(
                        elements,
                        name -> sprite,
                        modelState,
                        mat.texture()
                ));
            }
            this.quads = builder.build();
        }

        @Override
        public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable net.minecraft.core.Direction side, net.minecraft.util.RandomSource rand) {
            if (side != null) return Collections.emptyList();
            return quads;
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
        public ModelState getModelState() { return modelState; }
        public Function<Material, TextureAtlasSprite> getSpriteGetter() { return spriteGetter; }
    }
}
