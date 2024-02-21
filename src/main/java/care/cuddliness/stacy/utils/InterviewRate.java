package care.cuddliness.stacy.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.diff.EditScript;
import org.apache.commons.text.diff.StringsComparator;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.apache.commons.text.similarity.LongestCommonSubsequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class InterviewRate {
    List<String> badwords = new ArrayList<>();

    public InterviewRate(){
        if(!badwords.isEmpty()){
            return;
        }
        try (Stream<String> stream = Files.lines(Paths.get("./data/badwords.txt"))) {
            stream.forEach(s -> badwords.add(s));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean rate(List<String> answers) {
        int level = 0;
        LevenshteinDistance distance = new LevenshteinDistance(100);
        for (String ans : answers) {
            for(String split : ans.split(" ")){
                for (String badword : badwords) {
                    if(getLevenshteinScore(split, badword) >= 0.6){
                        level ++;
                    }



                }
            }
        }
        return (level >= 1);

    }

    public static double getLevenshteinScore(String inputStr, String outputColumnName) {
        double LevenshteinScore = 0.0;

        double maxLength = (inputStr.length() > outputColumnName.length()) ? inputStr.length()
                : outputColumnName.length();
        LevenshteinDistance ld = new LevenshteinDistance();
        double LevenshteinDistance = ld.apply(outputColumnName, inputStr);

        // one can overwrite to have his own version
        if (inputStr.contains(outputColumnName) || outputColumnName.contains(inputStr)) {
            LevenshteinScore = (maxLength - LevenshteinDistance + 1) / (maxLength + 1);
        } else {//w w w .j  ava  2s. c o  m
            LevenshteinScore = 1 - (LevenshteinDistance / maxLength);
        }
        return LevenshteinScore;
    }
}
