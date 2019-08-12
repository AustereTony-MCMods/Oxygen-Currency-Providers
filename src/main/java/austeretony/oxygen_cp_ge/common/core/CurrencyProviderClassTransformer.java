package austeretony.oxygen_cp_ge.common.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import net.minecraft.launchwrapper.IClassTransformer;

public class CurrencyProviderClassTransformer implements IClassTransformer {

    public static final Logger CORE_LOGGER = LogManager.getLogger("Currency Provider Core Plugin");

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        switch (transformedName) {    
        case "the_fireplace.grandeconomy.economy.Account":                    
            return patch(basicClass, EnumInputClass.GRAND_ECONOMY_ACCOUNT);
        }
        return basicClass;
    }

    private byte[] patch(byte[] basicClass, EnumInputClass enumInput) {
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(basicClass);
        classReader.accept(classNode, enumInput.readerFlags);
        if (enumInput.patch(classNode))
            CORE_LOGGER.info("{} <{}.class> patched!", enumInput.domain, enumInput.clazz);
        else
            CORE_LOGGER.info("{} <{}.class> patch SKIPPED!", enumInput.domain, enumInput.clazz);
        ClassWriter writer = new ClassWriter(enumInput.writerFlags);        
        classNode.accept(writer);
        return writer.toByteArray();    
    }
}