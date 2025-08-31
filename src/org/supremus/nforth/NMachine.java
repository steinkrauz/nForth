package org.supremus.nforth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class NMachine {
    final static int MEM_SIZE = 1024;
    public final static Integer MODE_EXEC = 0;
    public final static Integer MODE_COMPILE = 1;
    private Stack<Object> theStack = new Stack<>();

    private Object[] theMemory;
    /*
     *MEMORY MAP
     0 -- machine mode
     1 -- stack top on compile start
     */

    private Map<String, Integer> vars = new HashMap<String, Integer>();

    private Map<String, INWord> theDict = new HashMap<String, INWord>();

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

    public void addWord(String name, INWord aWord)
    {
        theDict.put(name,aWord);
    }

    public INWord getWord(String name)
    {
        return theDict.get(name);
    }

    void eval(List<Object> input) {
        for (Object o: input) {
            //dumpStack(o);            
            if (o instanceof INWord) {
                INWord aWord = (INWord) o;
                if (theMemory[0]==MODE_COMPILE&&!aWord.isImmediate()) {
                    theStack.push(o);
                    continue;
                }
                if (aWord.isNative()) {
                    aWord.execute(theStack, theMemory, vars, theDict);
                } else {
                    eval(aWord.getText());
                }
            } else {
                if (theDict.containsKey(o)) {
                    INWord oWord = theDict.get(o);
                    eval(oWord.getText());
                }else {
                    theStack.push(o);
                }
            }
        }
    }

    public void dumpStack(Object o) {
        System.out.println("-----> "+ o.toString());
        for (int i = theStack.size()-1;theStack.size()>0 && i >= 0; i--)
            System.out.println(theStack.get(i));
        System.out.println("<-----");
    }

}
