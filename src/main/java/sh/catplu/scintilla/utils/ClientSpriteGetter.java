package sh.catplu.scintilla.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import java.util.function.Function;

public class ClientSpriteGetter {
    /**
     * Returns a sprite getter function for the block atlas.
     * Usage: spriteGetter.apply(new ResourceLocation("namespace", "path/to/texture"))
     */
    public static Function<ResourceLocation, TextureAtlasSprite> getBlockAtlasSpriteGetter() {
        return Minecraft.getInstance()
                .getModelManager()
                .getAtlas(net.minecraft.client.renderer.texture.TextureAtlas.LOCATION_BLOCKS)
                ::getSprite;
    }
}
