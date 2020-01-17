package austeretony.oxygen_cp_ge.common.main;

import austeretony.oxygen_core.client.api.OxygenHelperClient;
import austeretony.oxygen_core.common.api.CommonReference;
import austeretony.oxygen_core.common.api.OxygenHelperCommon;
import austeretony.oxygen_core.server.api.CurrencyHelperServer;
import austeretony.oxygen_cp_ge.client.currency.GrandEconomyCurrencyProperties;
import austeretony.oxygen_cp_ge.common.config.CurrencyProviderConfig;
import austeretony.oxygen_cp_ge.server.currency.GrandEconomyCurrencyProvider;
import austeretony.oxygen_cp_ge.server.event.CurrencyProviderEventsServer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(
        modid = CurrencyProviderMain.MODID, 
        name = CurrencyProviderMain.NAME, 
        version = CurrencyProviderMain.VERSION,
        dependencies = "required-after:oxygen_core@[0.10.0,);required-after:grandeconomy@[1.3.0,);",
        certificateFingerprint = "@FINGERPRINT@",
        updateJSON = CurrencyProviderMain.VERSIONS_FORGE_URL)
public class CurrencyProviderMain {

    public static final String 
    MODID = "oxygen_cp_ge", 
    NAME = "Grand Economy Currency Provider", 
    VERSION = "0.10.0", 
    VERSION_CUSTOM = VERSION + ":beta:0",
    GAME_VERSION = "1.12.2",
    VERSIONS_FORGE_URL = "https://raw.githubusercontent.com/AustereTony-MCMods/Oxygen-Currency-Provider/info/grand_economy_versions.json";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        OxygenHelperCommon.registerConfig(new CurrencyProviderConfig());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        CurrencyHelperServer.registerCurrencyProvider(new GrandEconomyCurrencyProvider());
        CommonReference.registerEvent(new CurrencyProviderEventsServer());
        if (event.getSide() == Side.CLIENT)
            OxygenHelperClient.registerCurrencyProperties(new GrandEconomyCurrencyProperties());
    }
}
