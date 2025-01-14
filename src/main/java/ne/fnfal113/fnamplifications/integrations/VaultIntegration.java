package ne.fnfal113.fnamplifications.integrations;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.Optional;

public class VaultIntegration {

    @Getter
    private final SlimefunAddon slimefunAddon;
    @Getter
    private boolean isVaultInstalled;
    @Getter
    private Economy economy = null;

    public VaultIntegration(SlimefunAddon addon){
        this.slimefunAddon = addon;

        if(!setupEconomy()){
            getSlimefunAddon().getLogger().info("未检测到 Vault! 需要 Vault 的物品将不会被加载.");
        } else {
            getSlimefunAddon().getLogger().info("检测到 Vault! 成功注册了需要 Vault 的物品.");
        }

    }

    public boolean setupEconomy(){
        if(!getSlimefunAddon().getJavaPlugin().getServer().getPluginManager().isPluginEnabled("Vault")){
            this.isVaultInstalled = false;
            return false;
        }

        try {
            Optional<RegisteredServiceProvider<Economy>> economyRegisteredServiceProvider =
                    Optional.ofNullable(getSlimefunAddon().getJavaPlugin().getServer().getServicesManager().getRegistration(Economy.class));

            if (economyRegisteredServiceProvider.isPresent()) {
                this.economy = economyRegisteredServiceProvider.get().getProvider();
                this.isVaultInstalled = true;
                return true;
            }
        } catch (NoClassDefFoundError e){
            this.isVaultInstalled = false;
            return false;
        }

        this.isVaultInstalled = false;
        return false;
    }


}
