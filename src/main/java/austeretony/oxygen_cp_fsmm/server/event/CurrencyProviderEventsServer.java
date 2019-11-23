package austeretony.oxygen_cp_fsmm.server.event;

import java.util.UUID;

import austeretony.oxygen_core.common.main.OxygenMain;
import austeretony.oxygen_core.server.OxygenPlayerData;
import austeretony.oxygen_core.server.api.OxygenHelperServer;
import austeretony.oxygen_core.server.api.WatcherHelperServer;
import net.fexcraft.mod.fsmm.events.AccountEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CurrencyProviderEventsServer {

    @SubscribeEvent
    public void onBalanceUpdated(AccountEvent.BalanceUpdated event) {
        try {
            UUID playerUUID = UUID.fromString(event.getAccount().getId());
            if (OxygenHelperServer.isPlayerOnline(playerUUID))
                WatcherHelperServer.setValue(playerUUID, OxygenPlayerData.CURRENCY_COINS_WATCHER_ID, event.getNewBalance() / 1000);
        } catch (Exception exception) {
            OxygenMain.LOGGER.error("Failed to update player balance watched value!", exception);
        }
    }
}
