package austeretony.oxygen_cp_ge.common.core;

import java.util.Iterator;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public enum EnumInputClass {

    //Server
    GE_ACCOUNT("Grand Economy", "Account", 0, ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES),
    GE_SPONGE_ECON_HANDLER("Grand Economy", "SpongeEconHandler", 0, ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES),
    GE_ENDER_PAY_ECON_HANDLER("Grand Economy", "EnderPayEconHandler", 0, 0);

    private static final String HOOKS_CLASS = "austeretony/oxygen_cp_ge/common/core/CurrencyProviderHooks";

    public final String domain, clazz;

    public final int readerFlags, writerFlags;

    EnumInputClass(String domain, String clazz, int readerFlags, int writerFlags) {
        this.domain = domain;
        this.clazz = clazz;
        this.readerFlags = readerFlags;
        this.writerFlags = writerFlags;
    }

    public boolean patch(ClassNode classNode) {
        switch (this) {
        case GE_ACCOUNT:
            return this.pathcGrandEconomyAccount(classNode);
        case GE_SPONGE_ECON_HANDLER:
            return this.pathcGrandEconomySpongeEconHandler(classNode);
        case GE_ENDER_PAY_ECON_HANDLER:
            return this.pathcGrandEconomyEnderPayEconHandler(classNode);
        }
        return false;
    }

    private boolean pathcGrandEconomyAccount(ClassNode classNode) {
        String 
        setBalanceMethodName = "setBalance",
        uuidFieldName = "uuid",
        accountClassName = "the_fireplace/grandeconomy/econhandlers/ge/Account",
        uuidClassName = "java/util/UUID";
        boolean isSuccessful = false;   
        AbstractInsnNode currentInsn;

        for (MethodNode methodNode : classNode.methods) {               
            if (methodNode.name.equals(setBalanceMethodName) && methodNode.desc.equals("(JZ)V")) {                         
                Iterator<AbstractInsnNode> insnIterator = methodNode.instructions.iterator();              
                while (insnIterator.hasNext()) {                        
                    currentInsn = insnIterator.next();                  
                    if (currentInsn.getOpcode() == Opcodes.ALOAD) {    
                        InsnList nodesList = new InsnList();   
                        nodesList.add(new VarInsnNode(Opcodes.ALOAD, 0));
                        nodesList.add(new FieldInsnNode(Opcodes.GETFIELD, accountClassName, uuidFieldName, "L" + uuidClassName + ";"));
                        nodesList.add(new VarInsnNode(Opcodes.LLOAD, 1));
                        nodesList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, HOOKS_CLASS, "updateBalanceWatchedValue", "(L" + uuidClassName + ";J)V", false));
                        methodNode.instructions.insertBefore(currentInsn, nodesList); 
                        isSuccessful = true;                        
                        break;
                    }
                }    
                break;
            }
        }
        return isSuccessful;
    }

    private boolean pathcGrandEconomySpongeEconHandler(ClassNode classNode) {
        String 
        addToBalanceMethodName = "addToBalance",
        takeFromBalanceMethodName = "takeFromBalance",
        setBalanceMethodName = "setBalance",
        uuidClassName = "java/util/UUID";
        boolean isSuccessful = false;   
        AbstractInsnNode currentInsn;

        for (MethodNode methodNode : classNode.methods) {               
            if ((methodNode.name.equals(addToBalanceMethodName) || methodNode.name.equals(takeFromBalanceMethodName) || methodNode.name.equals(setBalanceMethodName)) 
                    && methodNode.desc.equals("(L" + uuidClassName + ";JZ)Z")) {                         
                Iterator<AbstractInsnNode> insnIterator = methodNode.instructions.iterator();              
                while (insnIterator.hasNext()) {                        
                    currentInsn = insnIterator.next();                  
                    if (currentInsn.getOpcode() == Opcodes.IRETURN) {    
                        InsnList nodesList = new InsnList();   
                        nodesList.add(new VarInsnNode(Opcodes.ALOAD, 1));
                        nodesList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, HOOKS_CLASS, "updateBalanceWatchedValue", "(ZL" + uuidClassName + ";)Z", false));
                        methodNode.instructions.insertBefore(currentInsn, nodesList); 
                        isSuccessful = true;                        
                        break;
                    }
                }    
                if (methodNode.name.equals(setBalanceMethodName))
                    break;
            }
        }
        return isSuccessful;
    }

    private boolean pathcGrandEconomyEnderPayEconHandler(ClassNode classNode) {
        String 
        addToBalanceMethodName = "addToBalance",
        takeFromBalanceMethodName = "takeFromBalance",
        setBalanceMethodName = "setBalance",
        uuidClassName = "java/util/UUID";
        boolean isSuccessful = false;   
        AbstractInsnNode currentInsn;

        for (MethodNode methodNode : classNode.methods) {               
            if ((methodNode.name.equals(addToBalanceMethodName) || methodNode.name.equals(takeFromBalanceMethodName) || methodNode.name.equals(setBalanceMethodName)) 
                    && methodNode.desc.equals("(L" + uuidClassName + ";JZ)Z")) {                         
                Iterator<AbstractInsnNode> insnIterator = methodNode.instructions.iterator();              
                while (insnIterator.hasNext()) {                        
                    currentInsn = insnIterator.next();                  
                    if (currentInsn.getOpcode() == Opcodes.LLOAD) {    
                        InsnList nodesList = new InsnList();   
                        nodesList.add(new VarInsnNode(Opcodes.ALOAD, 1));
                        nodesList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, HOOKS_CLASS, "updateBalanceWatchedValue", "(L" + uuidClassName + ";)V", false));
                        methodNode.instructions.insert(currentInsn.getNext(), nodesList); 
                        isSuccessful = true;                        
                        break;
                    }
                }    
                if (methodNode.name.equals(setBalanceMethodName))
                    break;
            }
        }
        return isSuccessful;
    }
}
