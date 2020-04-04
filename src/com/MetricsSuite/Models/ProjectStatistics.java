package com.MetricsSuite.Models;

import com.MetricsSuite.grammar.JavaJavaLexer;
import com.MetricsSuite.grammar.JavaJavaParser;
import com.MetricsSuite.grammar.JavaMetrics;
import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import java.io.File;
import java.io.IOException;

public class ProjectStatistics {

    File file;
    public ProjectStatistics(File file){
        this.file = file;
    }

    public String calculateStatistics(){

        try {

            JavaMetrics.resetVariable();
            JavaJavaLexer lexer = new JavaJavaLexer(new ANTLRFileStream(this.file.getAbsolutePath()));
            CommonTokenStream token = new CommonTokenStream(lexer);
            JavaJavaParser parser = new JavaJavaParser(token);
            parser.compilationUnit();

            System.out.println("uniqueKeywords:"+ JavaMetrics.uniqueKeywords);
            System.out.println("uniqueConstants: "+JavaMetrics.uniqueConstants);
            System.out.println("uniqueIdentifiers: "+JavaMetrics.uniqueIdentifiers);
            System.out.println("uniqueSpecial: "+JavaMetrics.uniqueSpecial);
            System.out.println("uIDSym: "+JavaMetrics.uIDSym);

            System.out.println("identcount: "+parser.identcount);
            System.out.println("keywordCount: "+parser.keywordCount);
            System.out.println("specialcount: "+parser.specialcount);

            String joined = String.join("<br>", JavaMetrics.mccabeValues);

            System.out.println("\n\n"+joined);
            return joined;
        } catch (IOException io){

        } catch (RecognitionException rec){

        }

        return null;

    }
}
