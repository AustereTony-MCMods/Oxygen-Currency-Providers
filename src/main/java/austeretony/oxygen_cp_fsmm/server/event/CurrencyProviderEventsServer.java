package austeretony.oxygen_cp_fsmm.server.event;

import java.util.UUID;

import austeretony.oxygen_core.server.api.OxygenHelperServer;
import austeretony.oxygen_cp_fsmm.common.config.CurrencyProviderConfig;
import net.fexcraft.mod.fsmm.events.AccountEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CurrencyProviderEventsServer {

    @SubscribeEvent
    public void onBalanceUpdated(AccountEvent.BalanceUpdated event) {
        UUID playerUUID = UUID.fromString(event.getAccount().getId());
        if (OxygenHelperServer.isPlayerOnline(playerUUID))
            OxygenHelperServer.setWatchedValueLong(playerUUID, CurrencyProviderConfig.PROVIDER_INDEX.asInt(), event.getNewBalance() / 1000);
    }
}
