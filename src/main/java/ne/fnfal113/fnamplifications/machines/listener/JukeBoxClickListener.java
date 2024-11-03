package ne.fnfal113.fnamplifications.machines.listener;

import ne.fnfal113.fnamplifications.utils.Utils;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.Locale;

public class JukeBoxClickListener implements Listener {

    @EventHandler
    public void onDiscClick(InventoryClickEvent event){
        if(event.getView().getTitle().contains(Utils.colorTranslator("&5F&dN &f唱&b片&e机"))){
            if(event.getCurrentItem() != null) {
                ItemStack itemStack = event.getCurrentItem();
                if (isDisc(itemStack) && itemStack.getItemMeta().hasEnchant(Enchantment.BINDING_CURSE)) {
                    event.setCancelled(true);
                }
                if(itemStack.getType() == Material.PINK_STAINED_GLASS_PANE){
                    event.setCancelled(true);
                }
                if(!isDisc(itemStack)){
                    event.setCancelled(true);
                }
            }
        }

    }

    boolean isDisc(@Nonnull ItemStack itemStack) {
        return itemStack.getType().name().toLowerCase(Locale.ROOT).startsWith("music_disc_");
    }
}
