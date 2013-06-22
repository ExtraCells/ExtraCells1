package extracells.items.subitem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * 
 * ExtraCells
 * 
 * SubItemAdjustableCell
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class SubItemAdjustableCell extends SubItemCell {
    public SubItemAdjustableCell(Object[] recipe) {
        super("adjustable", 0, 0, "extracells:itemAdjustableCell", recipe);
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        stack.getTagCompound().setInteger("costum_size", 4096);
        stack.getTagCompound().setInteger("costum_types", 27);
    }

    @Override
    public int getBytes(ItemStack i) {
        if (i.hasTagCompound()) {
            return i.getTagCompound().getInteger("costum_size");
        } else {
            return 0;
        }
    }

    @Override
    public int BytePerType(ItemStack i) {
        if (i.hasTagCompound()) {
            if (Math.round(i.getTagCompound().getInteger("costum_types") / 128) == 0) {
                return 1;
            } else {
                return Math
                        .round(i.getTagCompound().getInteger("costum_types") / 128);
            }
        } else {
            return 1;
        }
    }

    public int getTotalTypes(ItemStack i) {
        if (i.hasTagCompound()) {
            return i.getTagCompound().getInteger("costum_types");
        } else {
            return 0;
        }
    }

    @Override
    public ItemStack onCreativeTabAdding(ItemStack stack) {
        stack.getTagCompound().setInteger("costum_types", 1);
        stack.getTagCompound().setInteger("costum_size", 4096);
        return stack;
    }
}
