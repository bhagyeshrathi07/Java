package com.practice.java.dsa.cisco;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* A sentence consists of a group of words. Each word is a sequence of letters (a-z, A-Z) and may contain one or more hyphens (-) that join two words into one.
* Words may end in punctuation marks such as a period (.), comma (,), question mark (?), or exclamation point (!).
* These punctuation marks should be stripped off. Words are separated by one or more whitespace characters.
* Hyphens are only valid if they appear between two letters; otherwise, they should not be considered part of a word.
* Numbers are not considered words.
* */
public class HowManyWords {
    public static int howMany(String sentence) {
        if(sentence == null || sentence.isEmpty()) return 0;

        Pattern wordPattern = Pattern.compile("\\b[a-zA-Z]+(?:-[a-zA-Z]+)?\\b");
        Matcher matcher = wordPattern.matcher(sentence);

        int count = 0;
        while(matcher.find()) {
            count++;
        }
        return count;
    }


    public static void main(String[] args) {
        String test1 = "How many eggs are in a half-dozen, 13?";
        String test2 = "Hello-world! This is a test.";
        String test3 = "One-word";
        String test4 = "Invalid -hyphen usage";
        String test5 = "";

        System.out.println("Test 1: " + howMany(test1)); // Output: 7
        System.out.println("Test 2: " + howMany(test2)); // Output: 5
        System.out.println("Test 3: " + howMany(test3)); // Output: 1
        System.out.println("Test 4: " + howMany(test4)); // Output: 3
        System.out.println("Test 5: " + howMany(test5)); // Output: 0
    }
}
