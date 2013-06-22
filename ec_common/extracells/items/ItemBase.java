package extracells.items;

import java.util.LinkedHashMap;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extracells.items.subitem.SubItem;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

/**
 * 
 * PaleoMachineFramework
 * 
 * ItemBase
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemBase extends Item {

    private LinkedHashMap<String, SubItem> subItems;

    public ItemBase(int id) {
        super(id);
        subItems = new LinkedHashMap<String, SubItem>();
    }

    public void addSubItem(String name, SubItem subItem) {
        subItems.put(name, subItem);
    }

    public SubItem getSubItem(String name) {
        return subItems.get(name);
    }

    @Override
    public String getUnlocalizedName(ItemStack is) {
        is = defaultSubItem(is);
        String name = getSubItem(is.getTagCompound().getString("SubItemName"))
                .getUnlocalizedName(is);
        return name;
    }

    public void registerRecipes() {
        ItemStack output = new ItemStack(this, 1, 0);
        output.setTagCompound(new NBTTagCompound());

        for (String name : subItems.keySet()) {
            output.getTagCompound().setString("SubItemName", name);
            subItems.get(name).registerRecipe(output);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        for (SubItem item : subItems.values()) {
            item.registerIcons(iconRegister);
        }
    }

    @Override
    public Icon getIcon(ItemStack stack, int pass) {
        defaultSubItem(stack);
        return getSubItem(stack.getTagCompound().getString("SubItemName"))
                .getIcon(stack);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs tabs, List list) {
        for (String name : subItems.keySet()) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("SubItemName", name);
            ItemStack is = new ItemStack(this, 1);
            is.setTagCompound(tag);
            list.add(subItems.get(name).onCreativeTabAdding(is));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @Override
    public int getRenderPasses(int damage) {
        return 1;
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        defaultSubItem(stack);
        subItems.get(stack.getTagCompound().getString("SubItemName"))
                .onCreated(stack, world, player);
    }

    public ItemStack defaultSubItem(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            NBTTagCompound tag = new NBTTagCompound();
            if (subItems.isEmpty())
                throw new IllegalArgumentException(
                        "There has to be at least one SubItem added to "
                                + this.getClass().getName());
            tag.setString("SubItemName", subItems.keySet().iterator().next());
            stack.setTagCompound(tag);
        }
        return stack;
    }

    public ItemStack createStack(String subItem, int amount, int damage) {
        ItemStack stack = new ItemStack(this, amount, damage);
        if (getSubItem(subItem) != null) {
            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setString("SubItemName", subItem);
            return stack;
        }
        return defaultSubItem(stack);
    }
}
