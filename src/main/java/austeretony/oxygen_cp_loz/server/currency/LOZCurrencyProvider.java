package austeretony.oxygen_cp_loz.server.currency;

import java.util.UUID;

import austeretony.oxygen_core.common.api.CommonReference;
import austeretony.oxygen_core.server.currency.CurrencyProvider;
import austeretony.oxygen_cp_loz.common.config.CurrencyProviderConfig;

public class LOZCurrencyProvider implements CurrencyProvider {

    public static final String CURRENCY_KEY = "rupeeCount";

    @Override
    public String getDisplayName(){
        return "Legend of Zelda Mod Currency";
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
        return CommonReference.playerByUUID(playerUUID).getEntityData().getLong(CURRENCY_KEY);
    }

    @Override
    public void setCurrency(UUID playerUUID, long value) {
        CommonReference.playerByUUID(playerUUID).getEntityData().setLong(CURRENCY_KEY, value);
    }

    @Override
    public void updated(UUID playerUUID) {}
}
