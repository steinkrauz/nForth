package org.supremus.nforth.core;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.supremus.nforth.INWord;

public class CompiledWord implements INWord{

    private List<Object> wordCode; 

    @Override
    public boolean isNative() {
        return false;
    }

    @Override
    public boolean isImmediate() {
        return false;
    }


    @Override
    public List<Object> getText() {
        return wordCode;
    }

    public void setText(List<Object> code)
    {
        wordCode = code;
    }

    @Override
    public void execute(Stack<Object> theStack, Object[] theMemory, Map<String, Integer> vars, Map<String, INWord> theDict ) {
        throw new UnsupportedOperationException("Cannot execute compiled word");     
    }

}
