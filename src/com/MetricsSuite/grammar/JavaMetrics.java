package com.MetricsSuite.grammar;

import java.util.LinkedHashSet;
import java.util.Set;

public class JavaMetrics {

    public static Set<String> uniqueKeywords = new LinkedHashSet<String>();
    public static Set<String> uniqueIdentifiers= new LinkedHashSet<String>();

    public static Set<Symbol> uIDSym=new LinkedHashSet<Symbol>();

    public static Set<String> uniqueConstants = new LinkedHashSet<String>();
    public static Set<String> uniqueSpecial = new LinkedHashSet<String>();

    public static Set<String> mccabeValues = new LinkedHashSet<String>();

    public static void resetVariable(){
        uniqueKeywords = new LinkedHashSet<String>();
        uniqueIdentifiers= new LinkedHashSet<String>();

        uIDSym=new LinkedHashSet<Symbol>();

        uniqueConstants = new LinkedHashSet<String>();
        uniqueSpecial = new LinkedHashSet<String>();

        mccabeValues = new LinkedHashSet<String>();
    }

}

class Symbol{
    String name;
    String type;
    String className;

    public Symbol(String name, String type, String className){
        this.name = name;
        this.type = type;
        this.className = className;
    }

    public String toString(){
        return this.name+" "+this.type+" "+this.className;
    }
}