package extracells.items.subitem;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

/**
 * 
 * PaleoMachineFramework
 * 
 * SubItem
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class SubItem {
    private String name;
    private String superName;
    private String unlocalizedName;
    public Icon itemIcon;
    private Object[] recipe;
    private String texturePath;

    public SubItem(String name, String superName, String texturePath,
            Object[] recipe) {
        this.name = name;
        this.unlocalizedName = name;
        this.superName = superName;
        this.recipe = recipe;
        this.texturePath = texturePath;
    }

    public String getName() {
        return name;
    }

    public String getUnlocalizedName(ItemStack stack) {
        return "item." + superName + "." + unlocalizedName;
    }

    public void setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
    }

    public Icon getIcon(ItemStack stack) {
        return itemIcon;
    }

    public void registerIcons(IconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(texturePath);
    }

    public void registerRecipe(ItemStack output) {
        GameRegistry.addRecipe(output, recipe);
    }

    public void onCreated(ItemStack stack, World world, EntityPlayer player) {

    }

    public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p) {
        return i;
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer player,
            World world, int x, int y, int z, int side, float xOffset,
            float yOffset, float zOffset) {
        return false;
    }

    public String getDisplayName(ItemStack stack) {
        return StatCollector.translateToLocal(getUnlocalizedName(stack)
                + ".name");
    }

    @SuppressWarnings("rawtypes")
    public void addInformation(ItemStack stack, EntityPlayer player, List list,
            boolean par4) {
    }

    public ItemStack onCreativeTabAdding(ItemStack stack) {
        return stack;
    }

    public Object[] getRecipe() {
        return recipe;
    }

    public void setRecipe(Object[] recipe) {
        this.recipe = recipe;
    }
}
