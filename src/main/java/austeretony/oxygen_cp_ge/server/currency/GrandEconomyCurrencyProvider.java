package austeretony.oxygen_cp_ge.server.currency;

import java.util.UUID;

import austeretony.oxygen_core.server.currency.CurrencyProvider;
import austeretony.oxygen_cp_ge.common.config.CurrencyProviderConfig;
import the_fireplace.grandeconomy.api.GrandEconomyApi;

public class GrandEconomyCurrencyProvider implements CurrencyProvider {

    @Override
    public String getDisplayName(){
        return "Grand Economy Currency";
    }

    @Override
    public int getIndex() {
        return CurrencyProviderConfig.PROVIDER_INDEX.asInt();
    }

    @Override
    public boolean forceSync() {
        return CurrencyProviderConfig.FORCE_BALANCE_SYNC.asBoolean();
    }

    @Override
    public long getCurrency(UUID playerUUID) {
        return GrandEconomyApi.getBalance(playerUUID, true);
    }

    @Override
    public void setCurrency(UUID playerUUID, long value) {
        GrandEconomyApi.setBalance(playerUUID, value, true);
    }

    @Override
    public void updated(UUID playerUUID) {}
}
