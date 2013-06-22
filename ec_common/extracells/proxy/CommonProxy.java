package extracells.proxy;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import extracells.*;
import extracells.blocks.BlockSolderingStation;
import extracells.items.ItemCell;
import extracells.items.ItemCluster;
import extracells.items.subitem.SubItemAdjustableCell;
import extracells.items.subitem.SubItemCell;
import extracells.items.subitem.SubItemContainerCell;
import extracells.items.subitem.SubItemStorageCluster;
import extracells.tile.TileEntitySolderingStation;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import extracells.container.ContainerSolderingStation;

public class CommonProxy implements IGuiHandler {
    public void addRecipes() {
        ItemStack solderingStation = new ItemStack(extracells.SolderingStation,
                1);

        // SolderingStation
        GameRegistry.addShapedRecipe(solderingStation, new Object[] { "III",
                "IDI", "I_I", 'I', Item.ingotIron, 'D', Item.diamond });
    }

    public void RegisterTileEntities() {
        GameRegistry.registerTileEntity(TileEntitySolderingStation.class,
                "tileEntitySolderingStation");
    }

    public void RegisterRenderers() {

    }

    public void RegisterSubItems() {
        // Cluster SubItems
        ((ItemCluster) extracells.Cluster).addSubItem("kilo",
                new SubItemStorageCluster("kilo", 262144,
                        "extracells:itemKiloCluster", new Object[] { "FPF",
                                "CDC", "FCF", 'P',
                                appeng.api.Materials.matProcessorAdvanced, 'F',
                                appeng.api.Materials.matFluxDust, 'D',
                                Item.diamond, 'C',
                                appeng.api.Materials.matStorageCluster }));
        ((ItemCluster) extracells.Cluster).addSubItem("mega",
                new SubItemStorageCluster("mega", 1048576,
                        "extracells:itemMegaCluster", new Object[] {}));
        ((ItemCluster) extracells.Cluster).addSubItem("giga",
                new SubItemStorageCluster("giga", 4194304,
                        "extracells:itemGigaCluster", new Object[] {}));
        ((ItemCluster) extracells.Cluster).addSubItem("tera",
                new SubItemStorageCluster("tera", 16777216,
                        "extracells:itemTeraCluster", new Object[] {}));

        // Cell SubItems
        ((ItemCell) extracells.Cell).addSubItem("256k", new SubItemCell("256k",
                262144, 63, "extracells:item256kCell", new Object[] {}));
        ((ItemCell) extracells.Cell).addSubItem("1024k", new SubItemCell(
                "1024k", 1048576, 63, "extracells:item1024kCell",
                new Object[] {}));
        ((ItemCell) extracells.Cell).addSubItem("4096k", new SubItemCell(
                "4096k", 4194304, 63, "extracells:item4096kCell",
                new Object[] {}));
        ((ItemCell) extracells.Cell).addSubItem("16348k", new SubItemCell(
                "16348k", 16777216, 63, "extracells:item16348kCell",
                new Object[] {}));
        ((ItemCell) extracells.Cell).addSubItem("container",
                new SubItemContainerCell(new Object[] {
                        appeng.api.Items.itemCell1k, Block.chest }));
        ((ItemCell) extracells.Cell).addSubItem("adjustable",
                new SubItemAdjustableCell(new Object[] { " P ", "SSS", " P ",
                        'P', appeng.api.Materials.matProcessorBasic, 'S',
                        appeng.api.Items.itemCell1k }));
    }

    public void RegisterItems() {
        extracells.Cluster = new ItemCluster(extracells.Cluster_ID)
                .setUnlocalizedName("cluster");
        extracells.Cell = new ItemCell(extracells.Cell_ID);
        RegisterSubItems();
        registerSubRecipes();
    }

    public void RegisterBlocks() {
        extracells.SolderingStation = new BlockSolderingStation(
                extracells.SolderingStation_ID, Material.rock);
        GameRegistry.registerBlock(extracells.SolderingStation,
                extracells.SolderingStation.getUnlocalizedName());
    }

    private void registerSubRecipes() {
        // Cluster SubItem recipes
        ((ItemCluster) extracells.Cluster).getSubItem("mega").setRecipe(
                new Object[] {
                        "FPF",
                        "CDC",
                        "FCF",
                        'P',
                        appeng.api.Materials.matProcessorAdvanced,
                        'F',
                        appeng.api.Materials.matFluxDust,
                        'D',
                        Item.diamond,
                        'C',
                        ((ItemCluster) extracells.Cluster).createStack("kilo",
                                1, 0) });
        ((ItemCluster) extracells.Cluster).getSubItem("giga").setRecipe(
                new Object[] {
                        "FPF",
                        "CDC",
                        "FCF",
                        'P',
                        appeng.api.Materials.matProcessorAdvanced,
                        'F',
                        appeng.api.Materials.matFluxDust,
                        'D',
                        Item.diamond,
                        'C',
                        ((ItemCluster) extracells.Cluster).createStack("mega",
                                1, 0) });
        ((ItemCluster) extracells.Cluster).getSubItem("tera").setRecipe(
                new Object[] {
                        "FPF",
                        "CDC",
                        "FCF",
                        'P',
                        appeng.api.Materials.matProcessorAdvanced,
                        'F',
                        appeng.api.Materials.matFluxDust,
                        'D',
                        Item.diamond,
                        'C',
                        ((ItemCluster) extracells.Cluster).createStack("giga",
                                1, 0) });

        // Cell SubItem recipes
        ((ItemCell) extracells.Cell).getSubItem("256k").setRecipe(
                new Object[] {
                        "GFG",
                        "FCF",
                        "DDD",
                        'G',
                        Block.glass,
                        'F',
                        appeng.api.Materials.matFluxDust,
                        'D',
                        Item.diamond,
                        'C',
                        ((ItemCluster) extracells.Cluster).createStack("kilo",
                                1, 0) });
        ((ItemCell) extracells.Cell).getSubItem("1024k").setRecipe(
                new Object[] {
                        "GFG",
                        "FCF",
                        "DDD",
                        'G',
                        Block.glass,
                        'F',
                        appeng.api.Materials.matFluxDust,
                        'D',
                        Item.diamond,
                        'C',
                        ((ItemCluster) extracells.Cluster).createStack("mega",
                                1, 0) });
        ((ItemCell) extracells.Cell).getSubItem("4096k").setRecipe(
                new Object[] {
                        "GFG",
                        "FCF",
                        "DDD",
                        'G',
                        Block.glass,
                        'F',
                        appeng.api.Materials.matFluxDust,
                        'D',
                        Item.diamond,
                        'C',
                        ((ItemCluster) extracells.Cluster).createStack("giga",
                                1, 0) });
        ((ItemCell) extracells.Cell).getSubItem("16348k").setRecipe(
                new Object[] {
                        "GFG",
                        "FCF",
                        "DDD",
                        'G',
                        Block.glass,
                        'F',
                        appeng.api.Materials.matFluxDust,
                        'D',
                        Item.diamond,
                        'C',
                        ((ItemCluster) extracells.Cluster).createStack("tera",
                                1, 0) });

        ((ItemCluster) extracells.Cluster).registerRecipes();
        ((ItemCell) extracells.Cell).registerRecipes();
    }

    public void RegisterNames() {
        // Items
        LanguageRegistry.instance().addStringLocalization(
                "item.extraCell.256k.name", "ME 256K Storage");
        LanguageRegistry.instance().addStringLocalization(
                "item.extraCell.1024k.name", "ME 1M Storage");
        LanguageRegistry.instance().addStringLocalization(
                "item.extraCell.4096k.name", "ME 4M Storage");
        LanguageRegistry.instance().addStringLocalization(
                "item.extraCell.16348k.name", "ME 16M Storage");
        LanguageRegistry.instance().addStringLocalization(
                "item.extraCell.adjustable.name", "Adjustable ME Storage");

        LanguageRegistry.instance().addStringLocalization(
                "item.extraCluster.kilo.name", "Kilo Storage Cluster");
        LanguageRegistry.instance().addStringLocalization(
                "item.extraCluster.mega.name", "Mega Storage Cluster");
        LanguageRegistry.instance().addStringLocalization(
                "item.extraCluster.giga.name", "Giga Storage Cluster");
        LanguageRegistry.instance().addStringLocalization(
                "item.extraCluster.tera.name", "Tera Storage Cluster");
        LanguageRegistry.instance().addStringLocalization(
                "itemGroup.Extra_Cells", "en_US", "Extra Cells");

        LanguageRegistry.instance();
        // Blocks
        LanguageRegistry.addName(extracells.SolderingStation,
                "Soldering-Station");
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,
            int x, int y, int z) {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (tileEntity != null) {
            switch (ID) {
                case 0: // GUI Soldering Station
                    return null;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world,
            int x, int y, int z) {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (tileEntity != null) {
            switch (ID) {
                case 0: // GUI Soldering Station
                    return new ContainerSolderingStation();
                default:
                    return false;
            }
        } else {
            return false;
        }
    }
}
