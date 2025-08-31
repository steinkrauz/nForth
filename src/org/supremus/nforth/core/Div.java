package org.supremus.nforth.core;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.supremus.nforth.INWord;

public class Div implements INWord{

    @Override
    public boolean isNative() {
        return true;
    }

    @Override
    public List<Object> getText() {
        throw new UnsupportedOperationException("Unimplemented method 'getText'");
    }

    @Override
    public void execute(Stack<Object> theStack, Object[] theMemory, Map<String, Integer> vars, Map<String, INWord> theDict ) {
        Integer divisor = (Integer) theStack.pop();
        Integer dividend = (Integer) theStack.pop();
        Object res;
        if (dividend % divisor == 0) {
            res =  dividend / divisor; // Returns an Integer
        } else {
            res =  1.0 * dividend / divisor; // Returns a Double
        }

        theStack.push(res);

    }

    @Override
    public boolean isImmediate() {
        return false;
    }

}
