package austeretony.oxygen_cp_sqlite.common.config;

import austeretony.oxygen_core.common.config.AbstractConfig;
import austeretony.oxygen_core.common.config.ConfigValue;
import austeretony.oxygen_core.common.config.ConfigValueUtils;
import austeretony.oxygen_cp_sqlite.common.main.CurrencyProviderSQLiteMain;

import java.util.List;

public class CurrencyProviderConfig extends AbstractConfig {

    public static final ConfigValue
            DATA_BASE_URL = ConfigValueUtils.getString("server", "data_base_url", ""),
            CONFIGURATION = ConfigValueUtils.getString("server", "configuration", "");

    @Override
    public String getDomain() {
        return CurrencyProviderSQLiteMain.MOD_ID;
    }

    @Override
    public String getVersion() {
        return CurrencyProviderSQLiteMain.VERSION_CUSTOM;
    }

    @Override
    public String getFileName() {
        return "currency providers/db_sqlite.json";
    }

    @Override
    public void getValues(List<ConfigValue> values) {
        values.add(DATA_BASE_URL);
        values.add(CONFIGURATION);
    }
}
