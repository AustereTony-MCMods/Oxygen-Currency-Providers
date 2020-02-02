package austeretony.oxygen_cp_loz.client.currency;

import austeretony.oxygen_core.client.currency.AbstractCurrencyProperties;
import austeretony.oxygen_cp_loz.common.config.CurrencyProviderConfig;
import austeretony.oxygen_cp_loz.common.main.CurrencyProviderMain;
import net.minecraft.util.ResourceLocation;

public class LOZCurrencyProperties extends AbstractCurrencyProperties {

    public LOZCurrencyProperties() {
        super(
                "oxygen_core.currency.rupees", 
                new ResourceLocation(CurrencyProviderMain.MODID, "textures/gui/currency/rupee.png"), 
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
