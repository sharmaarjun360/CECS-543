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
    private long fileSize;
    private int ws;
    private long commentSpace;
    private float commentPercentage;
    private long uniqueOperators;
    private long uniqueOperands;
    private long totalOperators;
    private long totalOperands;
    private long programLength;
    private long programVocabulary;
    private double volumn;
    private double difficulty;
    private double effort;
    private double time;
    private double bugs;
    private String mccabeValues;

    public ProjectStatistics(File file){
        this.file = file;
    }

    public long getFileSize() {
        return fileSize;
    }

    public int getWs() {
        return ws;
    }

    public long getCommentSpace() {
        return commentSpace;
    }

    public float getCommentPercentage() {
        return commentPercentage;
    }

    public long getUniqueOperators() {
        return uniqueOperators;
    }

    public long getUniqueOperands() {
        return uniqueOperands;
    }

    public long getTotalOperators() {
        return totalOperators;
    }

    public long getTotalOperands() {
        return totalOperands;
    }

    public long getProgramLength() {
        return programLength;
    }

    public long getProgramVocabulary() {
        return programVocabulary;
    }

    public double getVolumn() {
        return volumn;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public double getEffort() {
        return effort;
    }

    public double getTime() {
        return time;
    }

    public double getBugs() {
        return bugs;
    }

    public String getMccabeValues() {
        return mccabeValues;
    }

    public void calculateStatistics(){

        try {

            String filePath = this.file.getAbsolutePath();
            JavaMetrics.resetVariable();
            JavaJavaLexer lexer = new JavaJavaLexer(new ANTLRFileStream(filePath));
            CommonTokenStream token = new CommonTokenStream(lexer);
            JavaJavaParser parser = new JavaJavaParser(token);
            parser.compilationUnit();

//            System.out.println("uniqueIdentifiers: "+JavaMetrics.uniqueIdentifiers);
//            System.out.println("uniqueConstants: "+JavaMetrics.uniqueConstants);
//
//            System.out.println("uniqueKeywords:"+ JavaMetrics.uniqueKeywords);
//            System.out.println("uniqueSpecial: "+JavaMetrics.uniqueSpecial);
//
//            System.out.println("identcount: "+parser.identcount);
//            System.out.println("constant: "+lexer.constantcount);
//
//            System.out.println("keywordCount: "+parser.keywordCount);
//            System.out.println("specialcount: "+parser.specialcount);

            this.fileSize = this.file.length();
            this.ws = lexer.ws;
            this.commentSpace = lexer.commentcount;
            this.commentPercentage = ((float)this.commentSpace*100/this.fileSize);

            this.uniqueOperators = JavaMetrics.uniqueKeywords.size() + JavaMetrics.uniqueSpecial.size();
            this.uniqueOperands =JavaMetrics.uniqueIdentifiers.size() + JavaMetrics.uniqueConstants.size();

            this.totalOperators = parser.keywordCount + parser.specialcount;
            this.totalOperands = parser.identcount+ lexer.constantcount;

            this.programLength = this.totalOperands+this.totalOperators;
            this.programVocabulary = this.uniqueOperands+this.uniqueOperators;
            this.volumn = programLength * (Math.log(programVocabulary)/Math.log(2));
            this.difficulty = ((float)this.uniqueOperators/2)*((float)this.totalOperands/this.uniqueOperands);
            this.effort = difficulty*volumn;
            this.time = effort/18;
            this.bugs = volumn/3000;

            this.mccabeValues = String.join("<br>", JavaMetrics.mccabeValues);
        } catch (IOException io){

        } catch (RecognitionException rec){

        }

    }
}
