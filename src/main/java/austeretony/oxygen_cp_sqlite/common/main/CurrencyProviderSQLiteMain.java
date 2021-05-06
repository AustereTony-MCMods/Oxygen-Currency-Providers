package austeretony.oxygen_cp_sqlite.common.main;

import austeretony.oxygen_core.common.api.OxygenCommon;
import austeretony.oxygen_core.common.main.OxygenMain;
import austeretony.oxygen_core.server.api.OxygenServer;
import austeretony.oxygen_cp_sqlite.common.config.CurrencyProviderConfig;
import austeretony.oxygen_cp_sqlite.server.currency.SQLiteCurrencyProvider;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = CurrencyProviderSQLiteMain.MOD_ID,
        name = CurrencyProviderSQLiteMain.NAME,
        version = CurrencyProviderSQLiteMain.VERSION,
        acceptableRemoteVersions = "*",
        dependencies = "required-after:oxygen_core@[0.12.0,);",
        certificateFingerprint = "@FINGERPRINT@",
        updateJSON = CurrencyProviderSQLiteMain.VERSIONS_FORGE_URL)
public class CurrencyProviderSQLiteMain {

    public static final String
            MOD_ID = "oxygen_cp_sqlite",
            NAME = "DB SQLite Currency Provider",
            VERSION = "0.12.0",
            VERSION_CUSTOM = VERSION + ":beta:0",
            GAME_VERSION = "1.12.2",
            VERSIONS_FORGE_URL = "https://raw.githubusercontent.com/AustereTony-MCMods/Oxygen-Currency-Provider/info/db_splite_versions.json";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        OxygenCommon.registerConfig(new CurrencyProviderConfig());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if (CurrencyProviderConfig.DATA_BASE_URL.asString().isEmpty()) {
            OxygenMain.logInfo(1, "[SQLite Currency Provider] Data base URL not specified. Provider(s) will not be registered!");
            return;
        }

        String configurationStr = CurrencyProviderConfig.CONFIGURATION.asString();
        String[] array = configurationStr.split("[;]");
        for (String providerConfig : array) {
            String[] paramsArray = providerConfig.split("[$]");
            if (paramsArray.length != 3) continue;

            int currencyIndex = Integer.parseInt(paramsArray[0].trim());
            String tableName = paramsArray[1].trim();
            String currencyName = paramsArray[2].trim();

            OxygenServer.registerCurrencyProvider(new SQLiteCurrencyProvider(currencyIndex, tableName, currencyName));
        }
    }
}
