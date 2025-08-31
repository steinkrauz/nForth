package org.supremus.nforth.core;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.supremus.nforth.INWord;

public class Plus implements INWord{

    @Override
    public boolean isNative() {
        return true;
    }

    @Override
    public boolean isImmediate() {
        return false;
    }


    @Override
    public List<Object> getText() {
        throw new UnsupportedOperationException("Unimplemented method 'getText'");
    }

    @Override
    public void execute(Stack<Object> theStack, Object[] theMemory, Map<String, Integer> vars, Map<String, INWord> theDict ) {
        Integer int1 = (Integer) theStack.pop();
        Integer int2 = (Integer) theStack.pop();
        
        theStack.push(int1+int2);

    }

}
