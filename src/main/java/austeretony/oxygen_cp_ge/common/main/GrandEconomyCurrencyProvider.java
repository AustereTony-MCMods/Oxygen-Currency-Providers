package austeretony.oxygen_cp_ge.common.main;

import java.util.UUID;

import austeretony.oxygen.common.currency.ICurrencyProvider;
import the_fireplace.grandeconomy.api.GrandEconomyApi;
import the_fireplace.grandeconomy.economy.Account;

public class GrandEconomyCurrencyProvider implements ICurrencyProvider {

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
        Account.get(playerUUID).setBalance(value, false);
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
    public void save(UUID playerUUID) {
        //unused
    }
}