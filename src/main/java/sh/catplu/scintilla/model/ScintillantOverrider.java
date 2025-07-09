package sh.catplu.scintilla.model;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import sh.catplu.scintilla.utils.ClientUtils;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = "scintilla", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT) // IMPORTANT: value = Dist.CLIENT
public class ScintillantOverrider extends ItemOverrides {

    public ScintillantOverrider() {
    }

    @SuppressWarnings("removal")
    @Override
    public @Nullable BakedModel resolve(BakedModel pModel, ItemStack pStack, @Nullable ClientLevel pLevel, @Nullable LivingEntity pEntity, int pSeed) {
        CompoundTag tag = pStack.getTag();
        String bottleShape;
        String bottleMat;
        String dustLeftMat;
        String dustRightMat;
        if (tag != null) {
            CompoundTag display = tag.getCompound("Display");

            bottleShape  = (display.contains("bs")) ? display.getString("bs") : "vanilla";
            bottleMat    = (display.contains("bm")) ? display.getString("bm") : "glass";
            dustLeftMat  = (display.contains("dl")) ? display.getString("dl") : "sugar";
            dustRightMat = (display.contains("dr")) ? display.getString("dr") : "snow";
        } else {
            bottleShape = "vanilla";
            bottleMat = "glass";
            dustLeftMat = "sugar";
            dustRightMat = "snow";
        }

        ResourceLocation bottleTex       = new ResourceLocation("scintilla","item/" + bottleShape + "_" + bottleMat);
        ResourceLocation dustLeftTex     = new ResourceLocation("scintilla","item/" + bottleShape + "_" + dustLeftMat);
        ResourceLocation dustRightTex    = new ResourceLocation("scintilla","item/" + bottleShape + "_" + dustRightMat);
        ResourceLocation shatterglassTex = new ResourceLocation("scintilla","item/" + bottleShape + "_shatterglass");

        TextureAtlasSprite bottleSprite       = ClientUtils.getSprite(bottleTex);
        TextureAtlasSprite dustLeftSprite     = ClientUtils.getSprite(dustLeftTex);
        TextureAtlasSprite dustRightSprite    = ClientUtils.getSprite(dustRightTex);
        TextureAtlasSprite shatterglassSprite = ClientUtils.getSprite(shatterglassTex);

        // Handling Non-Culled Quads
        List<RenderType> renderTypes = pModel.getRenderTypes(pStack, false); // Get render types used by the model for this item stack
        List<BakedQuad> dynamicGeneralQuads = new ArrayList<>();
        Map<Direction, List<BakedQuad>> dynamicCulledFaces = new EnumMap<>(Direction.class);


            List<BakedQuad> baseGeneralQuads = pModel.getQuads(null, null, RandomSource.create(pSeed), ModelData.EMPTY, null);

            if (baseGeneralQuads != null) {
                System.out.println("Processing " + baseGeneralQuads.size() + " general quads."); // Debug
                for (BakedQuad baseQuad : baseGeneralQuads) {
                    TextureAtlasSprite newSprite = baseQuad.getSprite();

                    int colorIndex = baseQuad.getTintIndex();

                    if (colorIndex == 0) {
                        newSprite = shatterglassSprite;
                    } else if (colorIndex == 1) {
                        newSprite = dustRightSprite;
                    } else if (colorIndex == 2) {
                        newSprite = dustLeftSprite;
                    } else if (colorIndex == 3) {
                        newSprite = bottleSprite;
                    }

                    if (newSprite == null) {
                        // This should theoretically not happen if ClientSpriteHelper.getSprite() always returns non-null.
                        // But as a final fallback, use the missing sprite if it somehow slipped through.
                        newSprite = ClientUtils.getSprite(new ResourceLocation("missingno"));
                    }

                    System.out.println("--- Debugging General Quad Vertex Data (ColorIndex: " + baseQuad.getTintIndex() + ", Face: " + baseQuad.getDirection() + ") ---");
                    int[] vertexData = baseQuad.getVertices();
                    for (int i = 0; i < vertexData.length; i++) {
                        //System.out.println("  Vertex Data [" + i + "]: " + vertexData[i] + " (float: " + Float.intBitsToFloat(vertexData[i]) + ")");
                    }
                    System.out.println("  Sprite Name: " + newSprite.contents().name());
                    System.out.println("-----------------------------------------------------------------");
                    BakedQuad dynamicQuad = new BakedQuad(baseQuad.getVertices().clone(), baseQuad.getTintIndex(), baseQuad.getDirection(), newSprite, baseQuad.isShade());
                    dynamicGeneralQuads.add(dynamicQuad);
                }
            }

            // Handling Culled Quads

            for (Direction direction : Direction.values()) {
                List<BakedQuad> baseCulledQuads = pModel.getQuads(null, direction, RandomSource.create(pSeed), ModelData.EMPTY, null);
                List<BakedQuad> directionSpecificQuads = new ArrayList<>();

                if (baseCulledQuads != null) {
                    System.out.println("Processing " + baseCulledQuads.size() + " culled quads for direction " + direction + "."); // Debug
                    for (BakedQuad baseQuad : baseCulledQuads) {
                        TextureAtlasSprite newSprite = baseQuad.getSprite();

                        int colorIndex = baseQuad.getTintIndex();

                        if (colorIndex == 0) {
                            newSprite = shatterglassSprite;
                        } else if (colorIndex == 1) {
                            newSprite = dustRightSprite;
                        } else if (colorIndex == 2) {
                            newSprite = dustLeftSprite;
                        } else if (colorIndex == 3) {
                            newSprite = bottleSprite;
                        }

                        if (newSprite == null) {
                            // This should theoretically not happen if ClientSpriteHelper.getSprite() always returns non-null.
                            // But as a final fallback, use the missing sprite if it somehow slipped through.
                            newSprite = ClientUtils.getSprite(new ResourceLocation("missingno"));
                        }

                        System.out.println("--- Debugging Culled Quad Vertex Data (Direction: " + direction + ", ColorIndex: " + baseQuad.getTintIndex() + ", Face: " + baseQuad.getDirection() + ") ---");
                        int[] vertexData = baseQuad.getVertices();
                        for (int i = 0; i < vertexData.length; i++) {
                            //System.out.println("  Vertex Data [" + i + "]: " + vertexData[i] + " (float: " + Float.intBitsToFloat(vertexData[i]) + ")");
                        }
                        System.out.println("  Sprite Name: " + newSprite.contents().name());
                        System.out.println("-----------------------------------------------------------------");
                        BakedQuad dynamicQuad = new BakedQuad(baseQuad.getVertices().clone(), baseQuad.getTintIndex(), baseQuad.getDirection(), newSprite, baseQuad.isShade());
                        directionSpecificQuads.add(dynamicQuad);
                    }
                }
                if (!directionSpecificQuads.isEmpty()) { // Only put directions with quads
                    dynamicCulledFaces.put(direction, directionSpecificQuads);
                }
            }

        // --- Get safe values for constructor parameters ---
        TextureAtlasSprite particleIcon = pModel.getParticleIcon();
        if (particleIcon == null) {
            particleIcon = ClientUtils.getSprite(new ResourceLocation("missingno")); // Fallback to missing sprite
            System.err.println("WARNING: Base model returned null particle icon. Using missingno."); // Debug
        }

        ItemTransforms transforms = pModel.getTransforms();
        if (transforms == null) {
            transforms = ItemTransforms.NO_TRANSFORMS; // Fallback to no transformations
            System.err.println("WARNING: Base model returned null transformations. Using NO_TRANSFORMS."); // Debug
        }

        System.out.println("DEBUG: Constructing SimpleBakedModel with values:");
        System.out.println("  dynamicGeneralQuads size: " + dynamicGeneralQuads.size());
        System.out.println("  dynamicCulledFaces size: " + dynamicCulledFaces.size());
        System.out.println("  useAmbientOcclusion: " + pModel.useAmbientOcclusion());
        System.out.println("  isGui3d: " + pModel.isGui3d());
        System.out.println("  usesBlockLight: " + pModel.usesBlockLight());
        System.out.println("  particleIcon: " + (particleIcon != null ? particleIcon.contents().name() : "NULL"));
        System.out.println("  transforms: " + (transforms != null ? transforms.getClass().getSimpleName() : "NULL"));
        System.out.println("  overrides: " + (pModel.getOverrides() != null ? pModel.getOverrides().getClass().getSimpleName() : "NULL"));

        // --- Create and return a new SimpleBakedModel ---
        BakedScintilla newBakedModel = new BakedScintilla(
                dynamicGeneralQuads,
                dynamicCulledFaces,
                pModel.useAmbientOcclusion(),
                pModel.isGui3d(),
                pModel.usesBlockLight(),
                particleIcon,
                transforms,
                ItemOverrides.EMPTY
        );


        System.out.println("DEBUG: Successfully constructed SimpleBakedModel."); // Debug after successful construction
        System.out.println(newBakedModel.getQuads(null, null, RandomSource.create(pSeed), ModelData.EMPTY, null));
        return newBakedModel;
    }
}
