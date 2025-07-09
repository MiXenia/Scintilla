package sh.catplu.scintilla.model;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = "scintilla", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT) // IMPORTANT: value = Dist.CLIENT
public class BakedScintilla extends SimpleBakedModel {
    private final ItemOverrides pOverrides;

    public BakedScintilla(List<BakedQuad> pUnculledFaces, Map<Direction, List<BakedQuad>> pCulledFaces, boolean pHasAmbientOcclusion, boolean pUsesBlockLight, boolean pIsGui3d, TextureAtlasSprite pParticleIcon, ItemTransforms pTransforms, ItemOverrides pOverrides) {
        super(pUnculledFaces, pCulledFaces, pHasAmbientOcclusion, pUsesBlockLight, pIsGui3d, pParticleIcon, pTransforms, pOverrides);
        this.pOverrides = new ScintillantOverrider();
    }

    // --- NEW: Override getRenderTypes ---
    @Override
    public List<RenderType> getRenderTypes(ItemStack stack, boolean forDynamicRender) {
        // For simple layered items like yours, itemEntityTranslucentCull is typically the correct RenderType
        // It's used for items that have transparency and don't need a custom renderer.
        return Collections.singletonList(RenderType.itemEntityTranslucentCull(TextureAtlas.LOCATION_BLOCKS));
    }

    // --- NEW: Override getRenderType ---
    // This is called when rendering without a specific item stack context, like in a GUI.

    public RenderType getRenderType(ItemStack stack, boolean forDynamicRender) {
        return RenderType.itemEntityTranslucentCull(TextureAtlas.LOCATION_BLOCKS);
    }

    @Override
    public ItemOverrides getOverrides() {
        return pOverrides;
    };
}
