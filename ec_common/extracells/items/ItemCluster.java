package extracells.items;

import appeng.api.me.items.IStorageComponent;

import extracells.*;
import extracells.items.subitem.SubItemStorageCluster;
import net.minecraft.item.ItemStack;

public class ItemCluster extends ItemBase implements IStorageComponent {

    // Item Names
    public static final String[] localized_names = new String[] {
            "Kilo Storage Cluster", "Mega Storage Cluster",
            "Giga Storage Cluster", "Tera Storage Cluster" };
    public static final String[] meta_names = new String[] { "itemKiloCluster",
            "itemMegaCluster", "itemGigaCluster", "itemTeraCluster" };

    // Sizes
    public static final int[] size = new int[] { 262144, 1048576, 4194304,
            16777216 };

    public ItemCluster(int id) {
        super(id);
        this.setMaxStackSize(64);
        this.setMaxDamage(0);
        this.setCreativeTab(extracells.ModTab);
        this.setUnlocalizedName("extraCluster");
    }

    @Override
    public int getBytes(ItemStack is) {
        is = this.defaultSubItem(is);
        return ((SubItemStorageCluster) getSubItem(is.getTagCompound()
                .getString("SubItemName"))).getSize();
    }

    @Override
    public boolean isStorageComponent(ItemStack is) {
        return true;
    }
}
