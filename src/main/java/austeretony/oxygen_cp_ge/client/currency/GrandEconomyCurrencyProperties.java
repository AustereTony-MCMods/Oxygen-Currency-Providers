package austeretony.oxygen_cp_ge.client.currency;

import austeretony.oxygen_core.client.currency.AbstractCurrencyProperties;
import austeretony.oxygen_core.common.main.OxygenMain;
import austeretony.oxygen_cp_ge.common.config.CurrencyProviderConfig;
import net.minecraft.util.ResourceLocation;

public class GrandEconomyCurrencyProperties extends AbstractCurrencyProperties {

    public GrandEconomyCurrencyProperties() {
        super(
                "oxygen_core.currency.money", 
                new ResourceLocation(OxygenMain.MODID, "textures/gui/currency/coin.png"), 
                8, 
                8, 
                0, 
                0);
    }

    @Override
    public int getIndex() {
        return CurrencyProviderConfig.PROVIDER_INDEX.asInt();
    }
}
