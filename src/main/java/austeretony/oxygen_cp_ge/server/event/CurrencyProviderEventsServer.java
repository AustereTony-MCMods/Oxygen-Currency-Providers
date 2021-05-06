package austeretony.oxygen_cp_ge.server.event;

import austeretony.oxygen_core.server.api.OxygenServer;
import austeretony.oxygen_cp_ge.common.config.CurrencyProviderConfig;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.grandeconomy.api.event.BalanceChangeEvent;

public class CurrencyProviderEventsServer {

    @SubscribeEvent
    public void onBalanceUpdated(BalanceChangeEvent event) {
        if (OxygenServer.isPlayerOnline(event.getAccountId())) {
            OxygenServer.updateWatcherValue(event.getAccountId(), CurrencyProviderConfig.PROVIDER_INDEX.asInt(),
                    (long) event.getNewBalance());
        }
    }
}
