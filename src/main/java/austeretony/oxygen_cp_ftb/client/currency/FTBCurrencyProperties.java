package austeretony.oxygen_cp_ftb.client.currency;

import austeretony.oxygen_core.client.currency.AbstractCurrencyProperties;
import austeretony.oxygen_cp_ftb.common.config.CurrencyProviderConfig;
import austeretony.oxygen_cp_ftb.common.main.CurrencyProviderMain;
import net.minecraft.util.ResourceLocation;

public class FTBCurrencyProperties extends AbstractCurrencyProperties {

    public FTBCurrencyProperties() {
        super(
                "oxygen_core.currency.money", 
                new ResourceLocation(CurrencyProviderMain.MODID, "textures/gui/currency/beastcoin.png"), 
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
