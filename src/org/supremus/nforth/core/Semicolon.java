package org.supremus.nforth.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.supremus.nforth.INWord;
import org.supremus.nforth.NMachine;

public class Semicolon implements INWord{

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
        
        int oldTop = (Integer)theMemory[1];        
        String name = (String) theStack.get(oldTop);
        List<Object> wordCode = new ArrayList<>();
        for (int i=oldTop+1; i<theStack.size(); i++)
            wordCode.add(theStack.get(i));
        while (theStack.size()>oldTop)
            theStack.pop();
        
        CompiledWord newWord = new CompiledWord();
        newWord.setText(wordCode);
        theDict.put(name, newWord);
        theMemory[0] = NMachine.MODE_EXEC;
    }

}
