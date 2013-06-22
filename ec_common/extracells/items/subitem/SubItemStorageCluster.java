package extracells.items.subitem;

/**
 * 
 * ExtraCells
 * 
 * SubItemStorageCluster
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class SubItemStorageCluster extends SubItem {
    private int size;

    public SubItemStorageCluster(String name, int size, String texturePath,
            Object[] recipe) {
        super(name, "extraCluster", texturePath, recipe);
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
