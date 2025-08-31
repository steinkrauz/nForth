package org.supremus.nforth;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public interface INWord {
    boolean isNative();

    boolean isImmediate();

    List<Object> getText();

    void execute(Stack<Object> theStack, Object[] theMemory, Map<String, Integer> vars, Map<String, INWord> theDict );
}
