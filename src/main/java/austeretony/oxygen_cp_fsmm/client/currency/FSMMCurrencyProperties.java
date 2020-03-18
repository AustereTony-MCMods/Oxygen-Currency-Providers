package austeretony.oxygen_cp_fsmm.client.currency;

import austeretony.oxygen_core.client.currency.AbstractCurrencyProperties;
import austeretony.oxygen_core.common.main.OxygenMain;
import austeretony.oxygen_cp_fsmm.common.config.CurrencyProviderConfig;
import net.minecraft.util.ResourceLocation;

public class FSMMCurrencyProperties extends AbstractCurrencyProperties {

    public FSMMCurrencyProperties() {
        super(
                "oxygen_core.currency.foney", 
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
