package sh.catplu.scintilla.model;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import sh.catplu.scintilla.utils.ClientSpriteGetter;

public class ScintillantOverrider extends ItemOverrides {
    private final ScintillantGeometry.LayeredItemModel parentModel;

    public ScintillantOverrider(ScintillantGeometry.LayeredItemModel parentModel) {
        this.parentModel = parentModel;
    }

    @Override
    public BakedModel resolve(BakedModel pModel, ItemStack pStack, @Nullable ClientLevel pLevel, @Nullable LivingEntity pEntity, int pSeed) {
        String bs = "vanilla";
        String bm = "glass";
        String dl = "sugar";
        String dr = "snow";
        if (pStack.hasTag()) {
            CompoundTag tag = pStack.getTagElement("display");
            bs = tag != null && tag.contains("bs") ? tag.getString("bs") : "vanilla";
            bm = tag != null && tag.contains("bm") ? tag.getString("bm") : "glass";
            dl = tag != null && tag.contains("dl") ? tag.getString("dl") : "sugar";
            dr = tag != null && tag.contains("dr") ? tag.getString("dr") : "snow";
        }
        String cacheKey = "c_" + bs + "_" + bm + "_" + dl + "_" + dr;
        BakedModel model = ScintillantGeometry.MODEL_CACHE.get(cacheKey);
        if (model == null) {
            // Bake on the fly using the parent model's ModelBaker and ModelState
            model = ScintillantGeometry.bakeModel(bs, bm, dl, dr, parentModel.getModelBaker(), parentModel.getModelState(), new ScintillantOverrider(parentModel),parentModel.getSpriteGetter());
            ScintillantGeometry.MODEL_CACHE.put(cacheKey, model);
        }
        return model != null ? model : pModel;
    }
}
