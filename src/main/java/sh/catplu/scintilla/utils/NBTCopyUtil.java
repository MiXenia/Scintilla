package sh.catplu.scintilla.utils;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

public class NBTCopyUtil {

    public static ItemStack mergeNbtPreservingExisting(ItemStack donor, ItemStack source) {
        CompoundTag donorTag = donor.getTag();
        if (donorTag == null) return source;
        
        CompoundTag sourceTag = source.getTag();
        if (sourceTag == null) {
            source.setTag(donorTag.copy());
        } else {
            source.setTag(mergeCompounds(donorTag, sourceTag));
        }
        return source;
    }

    private static CompoundTag mergeCompounds(CompoundTag compound1, CompoundTag compound2) {
        for (String key : compound1.getAllKeys()) {
            if (!compound2.contains(key)) {
                compound2.put(key, compound1.get(key).copy());
            } else {
                Tag tag1 = compound1.get(key);
                Tag tag2 = compound2.get(key);

                if (tag1 instanceof CompoundTag && tag2 instanceof CompoundTag) {
                    compound2.put(key, mergeCompounds((CompoundTag) tag1, (CompoundTag) tag2));
                }
            }
        }
        return compound2;
    }
}
