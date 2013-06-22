package extracells.items.subitem;

import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import appeng.api.IAEItemStack;
import appeng.api.Util;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraft.item.ItemBlock;

;

/**
 * 
 * ExtraCells
 * 
 * SubItemContainerCell
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class SubItemContainerCell extends SubItemCell {

    public SubItemContainerCell(Object[] recipe) {
        super("container", 65536, 1, "extracells:itemBlockContainer", recipe);
    }

    @Override
    public String getDisplayName(ItemStack stack) {
        long used_bytes = Util.getCellRegistry().getHandlerForCell(stack)
                .usedBytes();
        if (used_bytes != 0) {
            return "ME Block Container"
                    + " - "
                    + Util.getCellRegistry().getHandlerForCell(stack)
                            .getAvailableItems().getItems().get(0)
                            .getDisplayName();
        } else {
            return "Empty ME Block Container";
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list,
            boolean par4) {
        long used_bytes = Util.getCellRegistry().getHandlerForCell(stack)
                .usedBytes();
        long total_bytes = Util.getCellRegistry().getHandlerForCell(stack)
                .totalBytes();
        if (used_bytes != 0) {
            list.add("Block: "
                    + Util.getCellRegistry().getHandlerForCell(stack)
                            .getAvailableItems().getItems().get(0)
                            .getDisplayName());
        } else {
            list.add("Block: -");
        }
        list.add(used_bytes + " of " + total_bytes + " Bytes Used");

    }

    @Override
    public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p) {
        if (p.isSneaking()) {
            if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
                switch (i.getTagCompound().getInteger("mode")) {
                    case 0:
                        System.out.println(i.getTagCompound()
                                .getInteger("mode"));
                        i.getTagCompound().setInteger("mode", 1);
                        p.sendChatToPlayer("Mode: Equal Trade Mode (1*1)");
                        break;
                    case 1:
                        System.out.println(i.getTagCompound()
                                .getInteger("mode"));
                        i.getTagCompound().setInteger("mode", 2);
                        p.sendChatToPlayer("Mode: Equal Trade Mode (3*3)");
                        break;
                    case 2:
                        System.out.println(i.getTagCompound()
                                .getInteger("mode"));
                        i.getTagCompound().setInteger("mode", 0);
                        p.sendChatToPlayer("Mode: Placement Mode");
                        break;
                }
            }
        }
        return i;
    }

    int sleep = 0;

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player,
            World world, int x, int y, int z, int side, float xOffset,
            float yOffset, float zOffset) {
        ForgeDirection face = ForgeDirection.getOrientation(side);
        if (world.getBlockId(x + face.offsetX, y + face.offsetY, z
                + face.offsetZ) == 0
                && Util.getCellRegistry().getHandlerForCell(itemstack)
                        .storedItemTypes() != 0) {
            if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
                IAEItemStack request = Util.createItemStack(Util
                        .getCellRegistry().getHandlerForCell(itemstack)
                        .getAvailableItems().getItems().get(0).copy());
                ItemStack block = request.getItemStack();
                if (block.getItem() instanceof ItemBlock) {
                    switch (itemstack.getTagCompound().getInteger("mode")) {
                        case 0:
                            request.setStackSize(1);
                            player.worldObj.setBlock(
                                    x + face.offsetX,
                                    y + face.offsetY,
                                    z + face.offsetZ,
                                    Util.getCellRegistry()
                                            .getHandlerForCell(itemstack)
                                            .getAvailableItems().getItems()
                                            .get(0).itemID, appeng.api.Util
                                            .getCellRegistry()
                                            .getHandlerForCell(itemstack)
                                            .getAvailableItems().getItems()
                                            .get(0).getItemDamage(), 3);
                            Util.getCellRegistry().getHandlerForCell(itemstack)
                                    .extractItems(request);
                            break;
                        case 1:
                            request.setStackSize(1);
                            world.destroyBlock(x, y, z, true);
                            player.worldObj.setBlock(
                                    x,
                                    y,
                                    z,
                                    Util.getCellRegistry()
                                            .getHandlerForCell(itemstack)
                                            .getAvailableItems().getItems()
                                            .get(0).itemID, appeng.api.Util
                                            .getCellRegistry()
                                            .getHandlerForCell(itemstack)
                                            .getAvailableItems().getItems()
                                            .get(0).getItemDamage(), 3);
                            Util.getCellRegistry().getHandlerForCell(itemstack)
                                    .extractItems(request);
                            break;
                        case 2:

                            request.setStackSize(9);
                            if (Util.getCellRegistry()
                                    .getHandlerForCell(itemstack)
                                    .storedItemCount() > 9) {
                                switch (ForgeDirection.getOrientation(side)) {
                                    case DOWN:
                                        for (int posX = x - 1; posX < x + 2; posX++) {
                                            for (int posZ = z - 1; posZ < z + 2; posZ++) {
                                                world.destroyBlock(posX, y,
                                                        posZ, true);
                                                player.worldObj
                                                        .setBlock(
                                                                posX,
                                                                y,
                                                                posZ,
                                                                Util.getCellRegistry()
                                                                        .getHandlerForCell(
                                                                                itemstack)
                                                                        .getAvailableItems()
                                                                        .getItems()
                                                                        .get(0).itemID,
                                                                appeng.api.Util
                                                                        .getCellRegistry()
                                                                        .getHandlerForCell(
                                                                                itemstack)
                                                                        .getAvailableItems()
                                                                        .getItems()
                                                                        .get(0)
                                                                        .getItemDamage(),
                                                                3);
                                            }
                                        }
                                        Util.getCellRegistry()
                                                .getHandlerForCell(itemstack)
                                                .extractItems(request);
                                        break;
                                    case EAST:
                                        for (int posZ = z - 1; posZ < z + 2; posZ++) {
                                            for (int posY = y - 1; posY < y + 2; posY++) {
                                                world.destroyBlock(x, posY,
                                                        posZ, true);
                                                player.worldObj
                                                        .setBlock(
                                                                x,
                                                                posY,
                                                                posZ,
                                                                Util.getCellRegistry()
                                                                        .getHandlerForCell(
                                                                                itemstack)
                                                                        .getAvailableItems()
                                                                        .getItems()
                                                                        .get(0).itemID,
                                                                appeng.api.Util
                                                                        .getCellRegistry()
                                                                        .getHandlerForCell(
                                                                                itemstack)
                                                                        .getAvailableItems()
                                                                        .getItems()
                                                                        .get(0)
                                                                        .getItemDamage(),
                                                                3);
                                            }
                                        }
                                        Util.getCellRegistry()
                                                .getHandlerForCell(itemstack)
                                                .extractItems(request);
                                        break;
                                    case NORTH:
                                        for (int posX = x - 1; posX < x + 2; posX++) {
                                            for (int posY = y - 1; posY < y + 2; posY++) {
                                                world.destroyBlock(posX, posY,
                                                        z, true);
                                                player.worldObj
                                                        .setBlock(
                                                                posX,
                                                                posY,
                                                                z,
                                                                Util.getCellRegistry()
                                                                        .getHandlerForCell(
                                                                                itemstack)
                                                                        .getAvailableItems()
                                                                        .getItems()
                                                                        .get(0).itemID,
                                                                appeng.api.Util
                                                                        .getCellRegistry()
                                                                        .getHandlerForCell(
                                                                                itemstack)
                                                                        .getAvailableItems()
                                                                        .getItems()
                                                                        .get(0)
                                                                        .getItemDamage(),
                                                                3);
                                            }
                                        }
                                        Util.getCellRegistry()
                                                .getHandlerForCell(itemstack)
                                                .extractItems(request);
                                        break;
                                    case SOUTH:
                                        for (int posX = x - 1; posX < x + 2; posX++) {
                                            for (int posY = y - 1; posY < y + 2; posY++) {
                                                world.destroyBlock(posX, posY,
                                                        z, true);
                                                player.worldObj
                                                        .setBlock(
                                                                posX,
                                                                posY,
                                                                z,
                                                                Util.getCellRegistry()
                                                                        .getHandlerForCell(
                                                                                itemstack)
                                                                        .getAvailableItems()
                                                                        .getItems()
                                                                        .get(0).itemID,
                                                                appeng.api.Util
                                                                        .getCellRegistry()
                                                                        .getHandlerForCell(
                                                                                itemstack)
                                                                        .getAvailableItems()
                                                                        .getItems()
                                                                        .get(0)
                                                                        .getItemDamage(),
                                                                3);
                                            }
                                        }
                                        Util.getCellRegistry()
                                                .getHandlerForCell(itemstack)
                                                .extractItems(request);
                                        break;
                                    case UNKNOWN:
                                        break;
                                    case UP:
                                        for (int posX = x - 1; posX < x + 2; posX++) {
                                            for (int posZ = z - 1; posZ < z + 2; posZ++) {
                                                world.destroyBlock(posX, y,
                                                        posZ, true);
                                                player.worldObj
                                                        .setBlock(
                                                                posX,
                                                                y,
                                                                posZ,
                                                                Util.getCellRegistry()
                                                                        .getHandlerForCell(
                                                                                itemstack)
                                                                        .getAvailableItems()
                                                                        .getItems()
                                                                        .get(0).itemID,
                                                                appeng.api.Util
                                                                        .getCellRegistry()
                                                                        .getHandlerForCell(
                                                                                itemstack)
                                                                        .getAvailableItems()
                                                                        .getItems()
                                                                        .get(0)
                                                                        .getItemDamage(),
                                                                3);
                                            }
                                        }
                                        Util.getCellRegistry()
                                                .getHandlerForCell(itemstack)
                                                .extractItems(request);
                                        break;
                                    case WEST:
                                        for (int posZ = z - 1; posZ < z + 2; posZ++) {
                                            for (int posY = y - 1; posY < y + 2; posY++) {
                                                world.destroyBlock(x, posY,
                                                        posZ, true);
                                                player.worldObj
                                                        .setBlock(
                                                                x,
                                                                posY,
                                                                posZ,
                                                                Util.getCellRegistry()
                                                                        .getHandlerForCell(
                                                                                itemstack)
                                                                        .getAvailableItems()
                                                                        .getItems()
                                                                        .get(0).itemID,
                                                                appeng.api.Util
                                                                        .getCellRegistry()
                                                                        .getHandlerForCell(
                                                                                itemstack)
                                                                        .getAvailableItems()
                                                                        .getItems()
                                                                        .get(0)
                                                                        .getItemDamage(),
                                                                3);
                                            }
                                        }
                                        Util.getCellRegistry()
                                                .getHandlerForCell(itemstack)
                                                .extractItems(request);
                                        break;
                                    default:
                                        break;
                                }
                            }
                    }
                    return true;
                } else {
                    if (sleep == 0) {
                        player.sendChatToPlayer("You can't place Items! Put a Block into the BLOCK-Container");
                        sleep++;
                    } else if (sleep == 10) {
                        sleep = 0;
                    } else {
                        sleep++;
                    }
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    @Override
    public void registerRecipe(ItemStack output) {
        GameRegistry.addShapelessRecipe(output, getRecipe());
    }

}
