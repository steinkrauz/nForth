package org.supremus.nforth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.supremus.nforth.core.*;

public class NForth {
    private Map<String, INWord> theDict = new HashMap<String, INWord>();
    private NMachine vm = new NMachine();

    public NForth() {
        theDict.put("+", new Plus());
        theDict.put("-", new Sub());
        theDict.put("*", new Mul());
        theDict.put("/", new Div());
    }

    private List<Object> parse(String str) {
        List<Object> res = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();
        int mode = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '"') {
                mode = 1;
            } else if (Character.isWhitespace(c) && mode != 1) {
                mode = 10;
            } else if (c == '\\')
                mode += 8;

            switch (mode) {
                case 0:
                case 1:
                    currentToken.append(c);
                    break;
                case 10:
                    if (currentToken.length() > 0) {
                        Object o = tokenToObject(currentToken.toString());
                        res.add(o);
                        currentToken.setLength(0);
                    }
                    mode = 0;
                    break;
                case 8:
                case 9:
                    c = str.charAt(i++);
                    currentToken.append(c);
                    mode -= 8;
                    break;
            }
        }

        // Add the last token if it exists.
        if (currentToken.length() > 0) {
            Object o = tokenToObject(currentToken.toString());
            res.add(o);
        }
        return res;
    }

    private Object tokenToObject(String token) {
        if (theDict.containsKey(token)) {
            return theDict.get(token);
        }
        try {
            return Integer.valueOf(token);
        } catch (NumberFormatException ex) {

        }
        return token;
    }

    public void addWord(String name, INWord impl) {
        theDict.put(name, impl);
    }

    public void run(String source) {
        List<Object> code = parse(source);
        vm.eval(code);
    }

    public Object retVal() {
        return vm.peek();
    }
}
