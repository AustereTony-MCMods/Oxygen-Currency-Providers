package austeretony.oxygen_cp_ge.server.event;

import austeretony.oxygen_core.server.api.OxygenHelperServer;
import austeretony.oxygen_cp_ge.common.config.CurrencyProviderConfig;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.grandeconomy.api.event.BalanceChangeEvent;

public class CurrencyProviderEventsServer {

    @SubscribeEvent
    public void onBalanceUpdated(BalanceChangeEvent event) {
        if (OxygenHelperServer.isPlayerOnline(event.getAccountId()))
            OxygenHelperServer.setWatchedValueLong(event.getAccountId(), CurrencyProviderConfig.PROVIDER_INDEX.asInt(), (long) event.getNewBalance());
    }
}
