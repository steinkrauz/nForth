package org.supremus.nforth.core;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.supremus.nforth.INWord;
import org.supremus.nforth.NMachine;

public class Colon implements INWord{

    @Override
    public boolean isNative() {
        return true;
    }

    @Override
    public boolean isImmediate() {
        return true;
    }


    @Override
    public List<Object> getText() {
        throw new UnsupportedOperationException("Unimplemented method 'getText'");
    }

    @Override
    public void execute(Stack<Object> theStack, Object[] theMemory, Map<String, Integer> vars, Map<String, INWord> theDict ) {
        theMemory[0] = NMachine.MODE_COMPILE;
        theMemory[1] = theStack.size();        
    }

}
