package austeretony.oxygen_cp_ge.common.config;

import java.util.List;

import austeretony.oxygen_core.common.config.AbstractConfig;
import austeretony.oxygen_core.common.config.ConfigValue;
import austeretony.oxygen_core.common.config.ConfigValueUtils;
import austeretony.oxygen_core.common.main.OxygenMain;
import austeretony.oxygen_cp_ge.common.main.CurrencyProviderGrandEconomyMain;

public class CurrencyProviderConfig extends AbstractConfig {

    public static final ConfigValue
            PROVIDER_INDEX = ConfigValueUtils.getInt("server", "provider_index", OxygenMain.CURRENCY_COINS),
            FORCE_BALANCE_SYNC = ConfigValueUtils.getBoolean("server", "force_balance_sync", false);

    @Override
    public String getDomain() {
        return CurrencyProviderGrandEconomyMain.MODID;
    }

    @Override
    public String getVersion() {
        return CurrencyProviderGrandEconomyMain.VERSION_CUSTOM;
    }

    @Override
    public String getFileName() {
        return "currency providers/grand_economy.json";
    }

    @Override
    public void getValues(List<ConfigValue> values) {
        values.add(PROVIDER_INDEX);
        values.add(FORCE_BALANCE_SYNC);
    }
}
