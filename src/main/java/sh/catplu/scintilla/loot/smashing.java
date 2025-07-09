package sh.catplu.scintilla.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class smashing extends LootModifier {

    private final LootItemCondition[] conditionsIn;
    private final Item prop1;
    private final int prop2;

    protected smashing(LootItemCondition[] conditionsIn, Item prop1, int prop2) {
        super(conditionsIn);
        this.conditionsIn = conditionsIn;
        this.prop1 = prop1;
        this.prop2 = prop2;
    }

    public LootItemCondition[] getConditions() {
        return conditionsIn;
    }

    public Item getProp1() {
        return prop1;
    }

    public int getProp2() {
        return prop2;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> objectArrayList, LootContext lootContext) {
        ItemStack shatterglass = new ItemStack(prop1, 1);
        shatterglass.getOrCreateTagElement("display").putInt("color", prop2);
        objectArrayList.add(shatterglass);
        return objectArrayList;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

    public static final Codec<smashing> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    LOOT_CONDITIONS_CODEC.fieldOf("conditions").forGetter(smashing -> smashing.conditionsIn),
                    ForgeRegistries.ITEMS.getCodec().fieldOf("prop1").forGetter(smashing -> smashing.prop1),
                    Codec.INT.fieldOf("prop2").forGetter(smashing -> smashing.prop2)
            ).apply(instance, smashing::new));
}
