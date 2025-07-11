package sh.catplu.scintilla.model;

import com.google.common.collect.ImmutableList;
import com.mojang.math.Transformation;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraftforge.client.model.geometry.IGeometryBakingContext;
import net.minecraftforge.client.model.geometry.IUnbakedGeometry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("removal")
public class ScintillantGeometry implements IUnbakedGeometry<ScintillantGeometry> {
    private static final Map<String, BakedModel> MODEL_CACHE = new ConcurrentHashMap<>();

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
        ResourceLocation layer0 = new ResourceLocation("scintilla","item/s_" + bs + "shatterglass");
        ResourceLocation layer1 = new ResourceLocation("scintilla","item/l_" + bs + dl);
        ResourceLocation layer2 = new ResourceLocation("scintilla","item/r_" + bs + dr);
        ResourceLocation layer3 = new ResourceLocation("scintilla","item/b_" + bs + "_" + bm);

        ImmutableList<Material> materials = ImmutableList.of(
                new Material(net.minecraft.client.renderer.texture.TextureAtlas.LOCATION_BLOCKS, layer0),
                new Material(net.minecraft.client.renderer.texture.TextureAtlas.LOCATION_BLOCKS, layer1),
                new Material(net.minecraft.client.renderer.texture.TextureAtlas.LOCATION_BLOCKS, layer2),
                new Material(net.minecraft.client.renderer.texture.TextureAtlas.LOCATION_BLOCKS, layer3)
        );

        ScintillantModel ilm = new ScintillantModel(materials, null, null);

        // Use a simple IGeometryBakingContext
        IGeometryBakingContext fakeContext = new IGeometryBakingContext() {
            @Override
            public String getModelName() {
                return "";
            }

            @Override public boolean hasMaterial(String name) { return name.startsWith("layer") && Integer.parseInt(name.substring(5)) < 4; }
            @Override public Material getMaterial(String name) { int idx = Integer.parseInt(name.substring(5)); return materials.get(idx); }

            @Override
            public boolean isGui3d() {
                return false;
            }

            @Override
            public boolean useBlockLight() {
                return false;
            }

            @Override
            public boolean useAmbientOcclusion() {
                return false;
            }

            @Override
            public ItemTransforms getTransforms() {
                return null;
            }

            @Override
            public Transformation getRootTransform() {
                return null;
            }

            @Override
            public @Nullable ResourceLocation getRenderTypeHint() {
                return null;
            }

            @Override
            public boolean isComponentVisible(String s, boolean b) {
                return false;
            }
        };

        return ilm.bake(fakeContext, baker, spriteGetter, modelState, overrides, modelLocation);
    }

    // Static method to get or bake a model for a given set of textures
    public static BakedModel getOrBake(String bs, String bm, String dl, String dr, ModelBaker baker, ModelState state, ItemOverrides overrides) {
        String cacheKey = "c_" + bs + "_" + bm + "_" + dl + "_" + dr;
        return MODEL_CACHE.computeIfAbsent(cacheKey, k -> {
            ScintillantGeometry geom = new ScintillantGeometry(bs, bm, dl, dr);
            // Use the vanilla block atlas sprite getter
            Function<Material, TextureAtlasSprite> spriteGetter = mat -> net.minecraft.client.Minecraft.getInstance().getModelManager().getAtlas(mat.texture()).getSprite(mat.texture());
            ResourceLocation modelLoc = new ResourceLocation("scintilla", "dynamic_item/" + cacheKey.hashCode());
            return geom.bake(null, baker, spriteGetter, state, overrides, modelLoc);
        });
    }
}
