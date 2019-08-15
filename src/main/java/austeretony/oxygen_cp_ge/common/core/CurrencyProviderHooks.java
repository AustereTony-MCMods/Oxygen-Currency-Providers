package austeretony.oxygen_cp_ge.common.core;

import java.util.UUID;

import austeretony.oxygen.common.api.WatcherHelperServer;
import austeretony.oxygen.common.main.OxygenPlayerData;
import the_fireplace.grandeconomy.api.GrandEconomyApi;

public class CurrencyProviderHooks {

    //Hook to <the_fireplace.grandeconomy.econhandlers.ge.Account> class to <setBalance()> method.
    public static void updateBalanceWatchedValue(UUID playerUUID, long balance) {
        WatcherHelperServer.setValue(playerUUID, OxygenPlayerData.CURRENCY_COINS_WATCHER_ID, (int) balance);
    }

    //Hook to <the_fireplace.grandeconomy.econhandlers.sponge.SpongeEconHandler> class to <addToBalance(), takeFromBalance(), setBalance()> methods.
    public static boolean updateBalanceWatchedValue(boolean successful, UUID playerUUID) {
        if (successful)
            WatcherHelperServer.setValue(playerUUID, OxygenPlayerData.CURRENCY_COINS_WATCHER_ID, (int) GrandEconomyApi.getBalance(playerUUID));
        return successful;
    }

    //Hook to <the_fireplace.grandeconomy.econhandlers.ep.EnderPayEconHandler> class to <addToBalance(), takeFromBalance(), setBalance()> methods.
    public static void updateBalanceWatchedValue(UUID playerUUID) {
        WatcherHelperServer.setValue(playerUUID, OxygenPlayerData.CURRENCY_COINS_WATCHER_ID, (int) GrandEconomyApi.getBalance(playerUUID));
    }
}