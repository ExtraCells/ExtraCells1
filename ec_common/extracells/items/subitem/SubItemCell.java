package extracells.items.subitem;

import java.util.List;

import extracells.extracells;

import appeng.api.Materials;
import appeng.api.Util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * 
 * ExtraCells
 * 
 * SubItemCell
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class SubItemCell extends SubItem {
    private int byteAmount;
    private int totalTypes;

    public SubItemCell(String name, int bytes, int totalTypes,
            String texturePath, Object[] recipe) {
        super(name, "extraCell", texturePath, recipe);
        this.byteAmount = bytes;
        this.totalTypes = totalTypes;
    }

    public int getBytes(ItemStack i) {
        return byteAmount;
    }

    public int BytePerType(ItemStack i) {
        return getBytes(i) / 128;
    }

    public int getTotalTypes(ItemStack i) {
        return totalTypes;
    }

    @Override
    public String getDisplayName(ItemStack stack) {
        Boolean hasName = !Util.getCellRegistry().getHandlerForCell(stack)
                .getName().isEmpty();
        String partitionName = Util.getCellRegistry().getHandlerForCell(stack)
                .getName();
        if(hasName)
            return super.getDisplayName(stack) + " - " + partitionName;
        return super.getDisplayName(stack);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list,
            boolean par4) {
        boolean preformatted = Util.getCellRegistry().getHandlerForCell(stack)
                .isPreformatted();
        boolean fuzzy = Util.getCellRegistry().getHandlerForCell(stack)
                .isFuzzyPreformatted();
        long used_bytes = Util.getCellRegistry().getHandlerForCell(stack)
                .usedBytes();
        long total_bytes = Util.getCellRegistry().getHandlerForCell(stack)
                .totalBytes();
        long used_types = Util.getCellRegistry().getHandlerForCell(stack)
                .storedItemTypes();
        long total_types = Util.getCellRegistry().getHandlerForCell(stack)
                .getTotalItemTypes();
        list.add(used_bytes + " of " + total_bytes + " Bytes Used");
        list.add(used_types + " of " + total_types + " Types");
        if (preformatted) {
            if (fuzzy) {
                list.add("Preformatted - Fuzzy");
            } else {
                list.add("Preformatted - Precise");
            }
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p) {
        if (p.isSneaking()) {
            if (Util.getCellRegistry().getHandlerForCell(i).storedItemCount() == 0) {
                p.inventory.decrStackSize(p.inventory.currentItem, 1);
                p.inventory.addItemStackToInventory(new ItemStack(
                        extracells.Cluster, 1, i.getItemDamage()));
                p.inventory
                        .addItemStackToInventory(Materials.matStorageCellHouseing
                                .copy());
            }

        }
        return i;
    }
}
