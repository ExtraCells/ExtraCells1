package extracells.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import appeng.api.IAEItemStack;
import appeng.api.me.items.IStorageCell;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extracells.extracells;
import extracells.items.subitem.SubItemCell;

public class ItemCell extends ItemBase implements IStorageCell {

    // Item Names
    public static final String[] localized_names = new String[] {
            "ME 256K Storage", "ME 1M Storage", "ME 4M Storage",
            "ME 16M Storage", "ME Block Container", "Adjustable ME Storage" };

    public static final String[] meta_names = new String[] { "item256kCell",
            "item1024kCell", "item4096kCell", "item16348kCell",
            "itemBlockContainer", "itemAdjustableCell" };

    // Bytes
    public static final int[] bytes_cell = new int[] { 262144, 1048576,
            4194304, 16777216, 65536 };

    public static final int[] types_cell = new int[] { 63, 63, 63, 63, 1 };

    // Icons
    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    public ItemCell(int id) {
        super(id);
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
        this.setUnlocalizedName("extraCell");
        this.setCreativeTab(extracells.ModTab);
    }

    @Override
    public String getItemDisplayName(ItemStack stack) {
        stack = this.defaultSubItem(stack);
        SubItemCell cell = (SubItemCell) getSubItem(stack.getTagCompound()
                .getString("SubItemName"));
        return cell.getDisplayName(stack);
    }

    @SuppressWarnings({ "rawtypes" })
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list,
            boolean par4) {
        stack = this.defaultSubItem(stack);
        SubItemCell cell = (SubItemCell) getSubItem(stack.getTagCompound()
                .getString("SubItemName"));
        cell.addInformation(stack, player, list, par4);

    }

    @Override
    public int getBytes(ItemStack i) {
        i = this.defaultSubItem(i);
        SubItemCell cell = (SubItemCell) getSubItem(i.getTagCompound()
                .getString("SubItemName"));
        return cell.getBytes(i);
    }

    @Override
    public int BytePerType(ItemStack i) {
        i = this.defaultSubItem(i);
        SubItemCell cell = (SubItemCell) getSubItem(i.getTagCompound()
                .getString("SubItemName"));
        return cell.BytePerType(i);
    }

    public int getTotalTypes(ItemStack i) {
        i = this.defaultSubItem(i);
        SubItemCell cell = (SubItemCell) getSubItem(i.getTagCompound()
                .getString("SubItemName"));
        return cell.getTotalTypes(i);
    }

    public boolean isBlackListed(ItemStack cellItem,
            IAEItemStack requsetedAddition) {
        return false;
    }

    public EnumRarity getRarity(ItemStack par1) {
        return EnumRarity.epic;
    }

    @ForgeSubscribe
    @Override
    public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p) {
        i = this.defaultSubItem(i);
        SubItemCell cell = (SubItemCell) getSubItem(i.getTagCompound()
                .getString("SubItemName"));
        return cell.onItemRightClick(i, w, p);
    }

    int sleep = 0;

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player,
            World world, int x, int y, int z, int side, float xOffset,
            float yOffset, float zOffset) {
        itemstack = this.defaultSubItem(itemstack);
        SubItemCell cell = (SubItemCell) getSubItem(itemstack.getTagCompound()
                .getString("SubItemName"));
        return cell.onItemUse(itemstack, player, world, x, y, z, side, xOffset,
                yOffset, zOffset);
    }

    @Override
    public boolean storableInStorageCell() {
        return false;
    }
}
