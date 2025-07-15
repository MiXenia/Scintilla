package sh.catplu.scintilla;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import sh.catplu.scintilla.item.ScintillaItem;
import sh.catplu.scintilla.item.ShatterglassItem;

import static sh.catplu.scintilla.ScintillaMod.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        System.out.println("MEOWMEOWMEOWMEOW");
        ItemStack leftStack = event.getLeft();
        System.out.println(leftStack.getDisplayName());
        ItemStack rightStack = event.getRight();
        System.out.println(rightStack.getDisplayName());
        System.out.println(leftStack.getItem() instanceof ScintillaItem);
        System.out.println(rightStack.getItem() instanceof ShatterglassItem);

        // Check if the left item is your custom item and the right is your repair item
        if (leftStack.getItem() instanceof ScintillaItem && rightStack.getItem() instanceof ShatterglassItem) {
            ItemStack outputStack = leftStack.copy(); // Create a copy of the item to be repaired
            int startDamage = outputStack.getDamageValue();
            int glassCount = rightStack.getCount();
            int repairMod = startDamage % 8;
            int maxRepairs = (startDamage / 8) + ((repairMod != 0) ? 1 : 0);
            int repairMult = Math.min(glassCount,maxRepairs);

            // Calculate the new durability (repair by 8)
            int newDamage = Math.max(0, startDamage - (8 * repairMult));
            outputStack.setDamageValue(newDamage);
            event.setMaterialCost(repairMult);
            event.setOutput(outputStack); // Set the output item
            event.setCost(1);
        }
    }
}
