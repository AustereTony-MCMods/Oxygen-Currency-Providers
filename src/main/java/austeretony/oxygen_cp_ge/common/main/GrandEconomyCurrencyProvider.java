package austeretony.oxygen_cp_ge.common.main;

import java.util.UUID;

import austeretony.oxygen_core.common.currency.CurrencyProvider;
import the_fireplace.grandeconomy.api.GrandEconomyApi;

public class GrandEconomyCurrencyProvider implements CurrencyProvider {

    @Override
    public String getName() {
        return "Grand Economy Currency Provider";
    }

    @Override
    public long getCurrency(UUID playerUUID) {
        return GrandEconomyApi.getBalance(playerUUID);
    }

    @Override
    public boolean enoughCurrency(UUID playerUUID, long required) {
        return GrandEconomyApi.getBalance(playerUUID) >= required;
    }

    @Override
    public void setCurrency(UUID playerUUID, long value) {
        GrandEconomyApi.setBalance(playerUUID, value, false);
    }

    @Override
    public void addCurrency(UUID playerUUID, long value) {
        GrandEconomyApi.addToBalance(playerUUID, value, false);
    }

    @Override
    public void removeCurrency(UUID playerUUID, long value) {
        GrandEconomyApi.takeFromBalance(playerUUID, value, false);
    }

    @Override
    public void save(UUID playerUUID) {}
}
