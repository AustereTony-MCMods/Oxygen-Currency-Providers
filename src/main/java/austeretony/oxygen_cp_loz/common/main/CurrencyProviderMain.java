package austeretony.oxygen_cp_loz.common.main;

import austeretony.oxygen_core.client.api.OxygenHelperClient;
import austeretony.oxygen_core.common.api.OxygenHelperCommon;
import austeretony.oxygen_core.server.api.CurrencyHelperServer;
import austeretony.oxygen_cp_loz.client.currency.LOZCurrencyProperties;
import austeretony.oxygen_cp_loz.common.config.CurrencyProviderConfig;
import austeretony.oxygen_cp_loz.server.currency.LOZCurrencyProvider;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(
        modid = CurrencyProviderMain.MODID, 
        name = CurrencyProviderMain.NAME, 
        version = CurrencyProviderMain.VERSION,
        dependencies = "required-after:oxygen_core@[0.11.0,);required-after:lozmod@[4.0.0-pre7,);",
        certificateFingerprint = "@FINGERPRINT@",
        updateJSON = CurrencyProviderMain.VERSIONS_FORGE_URL)
public class CurrencyProviderMain {

    public static final String 
    MODID = "oxygen_cp_loz", 
    NAME = "LOZ Currency Provider", 
    VERSION = "0.11.0", 
    VERSION_CUSTOM = VERSION + ":beta:0",
    GAME_VERSION = "1.12.2",
    VERSIONS_FORGE_URL = "https://raw.githubusercontent.com/AustereTony-MCMods/Oxygen-Currency-Provider/info/loz_versions.json";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        OxygenHelperCommon.registerConfig(new CurrencyProviderConfig());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        CurrencyHelperServer.registerCurrencyProvider(new LOZCurrencyProvider());
        if (event.getSide() == Side.CLIENT)
            OxygenHelperClient.registerCurrencyProperties(new LOZCurrencyProperties());
    }
}
