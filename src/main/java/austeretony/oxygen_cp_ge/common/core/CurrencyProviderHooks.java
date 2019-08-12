package austeretony.oxygen_cp_ge.common.core;

import java.util.UUID;

import austeretony.oxygen.common.api.WatcherHelperServer;
import austeretony.oxygen.common.main.OxygenPlayerData;

public class CurrencyProviderHooks {

    //Hook to <the_fireplace.grandeconomy.economy.Account> class to <setBalance()> method.
    public static void updateBalanceWatcedValue(UUID playerUUID, long balance) {
        WatcherHelperServer.setValue(playerUUID, OxygenPlayerData.CURRENCY_COINS_WATCHER_ID, (int) balance);
    }
}