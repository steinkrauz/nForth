package org.supremus.nforth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class NMachine {
    final static int MEM_SIZE = 1024;
    private Stack<Object> theStack = new Stack<>();

    private Object[] theMemory;

    private Map<String, Integer> vars = new HashMap<String, Integer>();

    public NMachine() {
        theMemory = new Object[MEM_SIZE];
    }

    public NMachine(int memSize) {
        theMemory = new Object[memSize];
    }

    public Object peek() {
        return theStack.peek();
    }

    public void poke(Object o) {
        theStack.push(o);
    }

    void eval(List<Object> input) {
        for (Object o: input) {
            if (o instanceof INWord) {
                INWord aWord = (INWord) o;
                if (aWord.isNative()) {
                    aWord.execute(theStack, theMemory, vars);
                } else {
                    eval(aWord.getText());
                }
            } else {
                theStack.push(o);
            }
        }
    }

}
