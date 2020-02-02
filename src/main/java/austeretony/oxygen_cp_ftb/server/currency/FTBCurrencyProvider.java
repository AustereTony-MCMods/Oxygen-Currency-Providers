package austeretony.oxygen_cp_ftb.server.currency;

import java.util.UUID;

import com.feed_the_beast.mods.money.FTBMoney;

import austeretony.oxygen_core.common.api.CommonReference;
import austeretony.oxygen_core.server.currency.CurrencyProvider;
import austeretony.oxygen_cp_ftb.common.config.CurrencyProviderConfig;

public class FTBCurrencyProvider implements CurrencyProvider {

    @Override
    public String getDisplayName(){
        return "FTB Money";
    }

    @Override
    public int getIndex() {
        return CurrencyProviderConfig.PROVIDER_INDEX.asInt();
    }

    @Override
    public boolean forceSync() {
        return CurrencyProviderConfig.FORCE_BALANCE_SYNC.asBoolean();
    }

    @Override
    public long getCurrency(UUID playerUUID) {
        return FTBMoney.getMoney(CommonReference.playerByUUID(playerUUID));
    }

    @Override
    public void setCurrency(UUID playerUUID, long value) {
        FTBMoney.setMoney(CommonReference.playerByUUID(playerUUID), value);
    }

    @Override
    public void updated(UUID playerUUID) {}
}
