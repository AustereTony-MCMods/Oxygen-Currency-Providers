package austeretony.oxygen_cp_ge.common.main;

import austeretony.oxygen_core.common.api.OxygenCommon;
import austeretony.oxygen_core.common.util.MinecraftCommon;
import austeretony.oxygen_core.server.api.OxygenServer;
import austeretony.oxygen_cp_ge.common.config.CurrencyProviderConfig;
import austeretony.oxygen_cp_ge.server.currency.GrandEconomyCurrencyProvider;
import austeretony.oxygen_cp_ge.server.event.CurrencyProviderEventsServer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = CurrencyProviderGrandEconomyMain.MODID,
        name = CurrencyProviderGrandEconomyMain.NAME,
        version = CurrencyProviderGrandEconomyMain.VERSION,
        acceptableRemoteVersions = "*",
        dependencies = "required-after:oxygen_core@[0.12.0,);required-after:grandeconomy@[2.1.2,);",
        certificateFingerprint = "@FINGERPRINT@",
        updateJSON = CurrencyProviderGrandEconomyMain.VERSIONS_FORGE_URL)
public class CurrencyProviderGrandEconomyMain {

    public static final String 
    MODID = "oxygen_cp_ge", 
    NAME = "Grand Economy Currency Provider", 
    VERSION = "0.11.1", 
    VERSION_CUSTOM = VERSION + ":beta:0",
    GAME_VERSION = "1.12.2",
    VERSIONS_FORGE_URL = "https://raw.githubusercontent.com/AustereTony-MCMods/Oxygen-Currency-Provider/info/grand_economy_versions.json";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        OxygenCommon.registerConfig(new CurrencyProviderConfig());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        OxygenServer.registerCurrencyProvider(new GrandEconomyCurrencyProvider());
        MinecraftCommon.registerEventHandler(new CurrencyProviderEventsServer());
    }
}
