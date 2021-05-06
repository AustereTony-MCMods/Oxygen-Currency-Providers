package austeretony.oxygen_cp_ge.server.currency;

import java.util.UUID;

import austeretony.oxygen_core.server.currency.CurrencyProvider;
import austeretony.oxygen_core.server.currency.CurrencySource;
import austeretony.oxygen_cp_ge.common.config.CurrencyProviderConfig;
import the_fireplace.grandeconomy.api.GrandEconomyApi;

import javax.annotation.Nullable;

public class GrandEconomyCurrencyProvider implements CurrencyProvider {

    @Override
    public String getName() {
        return "grand_economy";
    }

    @Override
    public int getIndex() {
        return CurrencyProviderConfig.PROVIDER_INDEX.asInt();
    }

    @Override
    public CurrencySource getSource() {
        return CurrencySource.MINECRAFT;
    }

    @Override
    public boolean isForcedSync() {
        return CurrencyProviderConfig.FORCE_BALANCE_SYNC.asBoolean();
    }

    @Nullable
    @Override
    public Long getBalance(UUID playerUUID, String username) {
        return (long) GrandEconomyApi.getBalance(playerUUID, true);
    }

    @Nullable
    @Override
    public Long setBalance(UUID playerUUID, String username, long value) {
        boolean success = GrandEconomyApi.setBalance(playerUUID, value, true);
        return success ? (long) GrandEconomyApi.getBalance(playerUUID, true) : null;
    }

    @Nullable
    @Override
    public Long incrementBalance(UUID playerUUID, String username, long increment) {
        boolean success = GrandEconomyApi.addToBalance(playerUUID, increment, true);
        return success ? (long) GrandEconomyApi.getBalance(playerUUID, true) : null;
    }

    @Nullable
    @Override
    public Long decrementBalance(UUID playerUUID, String username, long decrement) {
        boolean success = GrandEconomyApi.takeFromBalance(playerUUID, decrement, true);
        return success ? (long) GrandEconomyApi.getBalance(playerUUID, true) : null;
    }

    @Override
    public void updated(UUID playerUUID, String username, @Nullable Long balance) {}
}
