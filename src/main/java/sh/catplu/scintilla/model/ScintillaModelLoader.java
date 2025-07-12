package sh.catplu.scintilla.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraftforge.client.model.geometry.IGeometryLoader;

// This loader is registered as "scintilla:scintilla_model" in your mod event
public class ScintillaModelLoader implements IGeometryLoader<ScintillantGeometry> {
    @Override
    public ScintillantGeometry read(JsonObject jsonObject, JsonDeserializationContext context) {
        // You can parse custom JSON properties here if needed.
        // For now, just return a default ScintillantModel (with nulls for extra data).
        String bs = "vanilla";
        String bm = "glass";
        String dl = "sugar";
        String dr = "snow";
        return new ScintillantGeometry(bs,bm,dl,dr);
    }
}
