package austeretony.oxygen_cp_ge.server.event;

import austeretony.oxygen_core.common.main.OxygenMain;
import austeretony.oxygen_core.server.OxygenPlayerData;
import austeretony.oxygen_core.server.api.OxygenHelperServer;
import austeretony.oxygen_core.server.api.WatcherHelperServer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.grandeconomy.api.event.BalanceChangeEvent;

public class CurrencyProviderEventsServer {

    @SubscribeEvent
    public void onBalanceUpdated(BalanceChangeEvent event) {
        try {
            if (OxygenHelperServer.isPlayerOnline(event.getAccountId()))
                WatcherHelperServer.setValue(event.getAccountId(), OxygenPlayerData.CURRENCY_COINS_WATCHER_ID, event.getNewBalance());
        } catch (Exception exception) {
            OxygenMain.LOGGER.error("Failed to update player balance watched value!", exception);
        }
    }
}
