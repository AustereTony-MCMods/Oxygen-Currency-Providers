package austeretony.oxygen_cp_fsmm.server.currency;

import java.util.UUID;

import austeretony.oxygen_core.server.currency.CurrencyProvider;
import austeretony.oxygen_cp_fsmm.common.config.CurrencyProviderConfig;
import net.fexcraft.mod.fsmm.api.Account;
import net.fexcraft.mod.fsmm.util.DataManager;

/**
 * FSMM Currency Provider for <i>Oxygen API</i>.
 * Works with the FSMM "account" of the player, not the in-inventory items as money.
 * 
 * @author Ferdinand Calo' (FEX___96)
 *
 */
public class FSMMCurrencyProvider implements CurrencyProvider {

    @Override
    public String getDisplayName(){
        return "FSMM Currency";
    }

    @Override
    public int getIndex() {
        return CurrencyProviderConfig.PROVIDER_INDEX.asInt();
    }

    @Override
    public boolean forceSync() {
        return CurrencyProviderConfig.FORCE_BALANCE_SYNC.asBoolean();
    }

    private static Account getAccount(UUID uuid){
        return DataManager.getAccount("player:" + uuid.toString(), true, true);
    }

    @Override
    public long getCurrency(UUID playerUUID) {
        return getAccount(playerUUID).getBalance() / 1000;
    }

    @Override
    public void setCurrency(UUID playerUUID, long value) {
        getAccount(playerUUID).setBalance(value * 1000);
    }

    @Override
    public void updated(UUID playerUUID) {}
}
