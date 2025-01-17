package ne.fnfal113.fnamplifications.gems;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import ne.fnfal113.fnamplifications.gems.abstracts.AbstractGem;
import ne.fnfal113.fnamplifications.utils.Keys;
import ne.fnfal113.fnamplifications.utils.WeaponArmorEnum;
import ne.fnfal113.fnamplifications.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class RetaliateGem extends AbstractGem {

    public RetaliateGem(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void onDrag(Player player, SlimefunItem slimefunGemItem, ItemStack gemItem, ItemStack itemStackToSocket){
        if (WeaponArmorEnum.SWORDS.isTagged(itemStackToSocket.getType()) || WeaponArmorEnum.AXES.isTagged(itemStackToSocket.getType())) {
            if (hasNeededGem(itemStackToSocket.getItemMeta().getPersistentDataContainer())) {
                bindGem(slimefunGemItem, itemStackToSocket, player);
                retaliateWeapon(itemStackToSocket);
            } else {
                player.sendMessage(Utils.colorTranslator("&e武器中缺少所需的宝石, 请先阅读宝石的介绍!"));
            }
        } else {
            player.sendMessage(Utils.colorTranslator("&e这个物品不能绑定! 此宝石只能绑定在剑和斧上"));
        }
    }

    public boolean hasNeededGem(PersistentDataContainer pdc){
        return pdc.has(Keys.RETURN_DAMNATION_KEY, PersistentDataType.STRING) ||
                pdc.has(Keys.RETURN_TRISWORD_KEY, PersistentDataType.STRING) ||
                pdc.has(Keys.RETURN_AXE_KEY, PersistentDataType.STRING);
    }

    
    /**
     * adding the needed pdc for checking whether it has retaliate gem
     */
    public void retaliateWeapon(ItemStack itemStackToSocket){
        ItemMeta meta = itemStackToSocket.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        pdc.set(Keys.RETURN_WEAPON_KEY, PersistentDataType.STRING, "true");
        itemStackToSocket.setItemMeta(meta);
    }
}