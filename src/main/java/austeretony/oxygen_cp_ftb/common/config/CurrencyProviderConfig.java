package austeretony.oxygen_cp_ftb.common.config;

import java.util.List;

import austeretony.oxygen_core.common.api.CommonReference;
import austeretony.oxygen_core.common.api.config.AbstractConfig;
import austeretony.oxygen_core.common.config.ConfigValue;
import austeretony.oxygen_core.common.config.ConfigValueUtils;
import austeretony.oxygen_cp_ftb.common.main.CurrencyProviderMain;

public class CurrencyProviderConfig extends AbstractConfig {

    public static final ConfigValue
    PROVIDER_INDEX = ConfigValueUtils.getValue("server", "provider_index", 0),
    FORCE_BALANCE_SYNC = ConfigValueUtils.getValue("server", "force_balance_sync", true);

    @Override
    public String getDomain() {
        return CurrencyProviderMain.MODID;
    }

    @Override
    public String getVersion() {
        return CurrencyProviderMain.VERSION_CUSTOM;
    }

    @Override
    public String getExternalPath() {
        return CommonReference.getGameFolder() + "/config/oxygen/currency providers/ftb-money.json";
    }

    @Override
    public void getValues(List<ConfigValue> values) {
        values.add(PROVIDER_INDEX);
        values.add(FORCE_BALANCE_SYNC);
    }
}
