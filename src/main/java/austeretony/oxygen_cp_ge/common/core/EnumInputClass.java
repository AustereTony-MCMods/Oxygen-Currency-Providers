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
    GRAND_ECONOMY_ACCOUNT("Grand Economy", "Account", 0, ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

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
        case GRAND_ECONOMY_ACCOUNT:
            return this.pathcGrandEconomyAccount(classNode);
        }
        return false;
    }

    private boolean pathcGrandEconomyAccount(ClassNode classNode) {
        String 
        setBalanceMethodName = "setBalance",
        uuidFieldName = "uuid",
        accountClassName = "the_fireplace/grandeconomy/economy/Account",
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
                        nodesList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, HOOKS_CLASS, "updateBalanceWatcedValue", "(L" + uuidClassName + ";J)V", false));
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
}
