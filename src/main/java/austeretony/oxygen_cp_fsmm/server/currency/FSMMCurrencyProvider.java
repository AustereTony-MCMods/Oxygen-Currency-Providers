package austeretony.oxygen_cp_fsmm.server.currency;

import java.util.UUID;

import austeretony.oxygen_core.common.currency.CurrencyProvider;
import net.fexcraft.mod.fsmm.api.Account;
import net.fexcraft.mod.fsmm.util.DataManager;

/**
 * FSMM Currency Provider for <i>Oxygen API</i>.
 * Works with the FSMM "account" of the player, not the in-inventory items as money.
 * 
 * @author Ferdinand Calo' (FEX___96)
 *
 */
public class FSMMCurrencyProvider implements CurrencyProvider {

    @Override
    public String getName(){
        return "FSMM Currency Provider";
    }

    @Override
    public long getCurrency(UUID uuid){
        Account acc = getAccount(uuid); return acc.getBalance() / 1000;
    }

    @Override
    public boolean enoughCurrency(UUID uuid, long required){
        return getCurrency(uuid) >= required;
    }

    @Override
    public void setCurrency(UUID uuid, long value){
        Account acc = getAccount(uuid); acc.setBalance(value * 1000);
    }

    @Override
    public void addCurrency(UUID uuid, long value){
        Account acc = getAccount(uuid); acc.setBalance(acc.getBalance() + (value * 1000));
    }

    @Override
    public void removeCurrency(UUID uuid, long value){
        Account acc = getAccount(uuid);
        if((value * 1000) > acc.getBalance()) acc.setBalance(0);
        else acc.setBalance(acc.getBalance() - (value * 1000));
    }

    @Override
    public void save(UUID uuid){
        //Account acc = getAccount(uuid); if(acc != null) DataManager.save(acc);
        //Uncomment this if accounts would turn out not to save as they should.
    }

    /** Loads an account from disk if necessary, marked as "temporary", so it get's unloaded later.
     * NOTE: Online Players should have an "active" account linked to them which doesn't unload.
     * */
    private Account getAccount(UUID uuid){
        return DataManager.getAccount("player:" + uuid.toString(), true, true);
    }
}
