package austeretony.oxygen_cp_ge.common.main;

import austeretony.oxygen_core.common.api.CommonReference;
import austeretony.oxygen_core.common.currency.CurrencyHelperServer;
import austeretony.oxygen_cp_ge.server.currency.GrandEconomyCurrencyProvider;
import austeretony.oxygen_cp_ge.server.event.CurrencyProviderEventsServer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(
        modid = CurrencyProviderMain.MODID, 
        name = CurrencyProviderMain.NAME, 
        version = CurrencyProviderMain.VERSION,
        acceptableRemoteVersions = "*",
        dependencies = "required-after:oxygen_core@[0.9.0,);required-after:grandeconomy@[1.3.0,);",
        certificateFingerprint = "@FINGERPRINT@",
        updateJSON = CurrencyProviderMain.VERSIONS_FORGE_URL)
public class CurrencyProviderMain {

    public static final String 
    MODID = "oxygen_cp_ge", 
    NAME = "Grand Economy Currency Provider", 
    VERSION = "0.9.1", 
    VERSION_CUSTOM = VERSION + ":beta:0",
    GAME_VERSION = "1.12.2",
    VERSIONS_FORGE_URL = "https://raw.githubusercontent.com/AustereTony-MCMods/Oxygen-Currency-Provider/info/grand_economy_versions.json";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        CurrencyHelperServer.registerCurrencyProvider(new GrandEconomyCurrencyProvider());
        CommonReference.registerEvent(new CurrencyProviderEventsServer());
    }
}
