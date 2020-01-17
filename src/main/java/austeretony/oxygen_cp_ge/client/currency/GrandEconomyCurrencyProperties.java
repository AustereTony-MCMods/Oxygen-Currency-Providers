package austeretony.oxygen_cp_ge.client.currency;

import austeretony.oxygen_core.client.currency.AbstractCurrencyProperties;
import austeretony.oxygen_cp_ge.common.config.CurrencyProviderConfig;
import austeretony.oxygen_cp_ge.common.main.CurrencyProviderMain;
import net.minecraft.util.ResourceLocation;

public class GrandEconomyCurrencyProperties extends AbstractCurrencyProperties {

    public GrandEconomyCurrencyProperties() {
        super(
                "oxygen_core.currency.money", 
                new ResourceLocation(CurrencyProviderMain.MODID, "textures/gui/currency/coin.png"), 
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
