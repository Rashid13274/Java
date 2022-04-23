package sliding_Windows;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Count_Occurences_OF_Anagram {
	
	    // Two words are anagrams of each other if the count of every character in both the words are same.
	    private static boolean isAnagram(Map<Character, Integer> word1CharCount, Map<Character, Integer> word2CharCount) {
	        for(char c : word1CharCount.keySet()) {
	            if(word1CharCount.get(c) != word2CharCount.get(c)) {
	                return false;
	            }
	        }	   
	        return true;
	    }

	    private static int countOccurrenceOfAnagram(String text, String word) {
	        int n = text.length();
	        int k = word.length();
	        int count = 0;

	        // Calculate the count of each character for the given word
	        Map<Character, Integer> wordCharCount = new HashMap<>();
	        for(int i = 0; i < k; i++) {
	            char c = word.charAt(i);
	            wordCharCount.put(c, wordCharCount.getOrDefault(c, 0)+1);
	        }

	        for(int i = 0; i <= n-k; i++) {
	            // Find the substring starting from i with length equal to the length of the word. 
	            // Calculate its char count to easily determine if it is an anagram of the word or not.
	            Map<Character, Integer> substrCharCount = new HashMap<>();
	            for(int j = i; j < i+k; j++) {
	                char c = text.charAt(j);
	                substrCharCount.put(c, substrCharCount.getOrDefault(c, 0)+1);
	            }

	            // Check if the current substring is an anagram of the given word
	            if(isAnagram(wordCharCount, substrCharCount)) {
	                count++;
	            }
	        }

	        return count;
	    }

	    public static void main(String[] args) {
	        Scanner keyboard = new Scanner(System.in);
	        String text = keyboard.next();
	        String word = keyboard.next();
	        keyboard.close();

	        System.out.printf("Count of occurrences of anagram = %d%n", countOccurrenceOfAnagram(text, word));
	        
	      //  aabaabaa
	      //  aaba
	      //  Count of occurrences of anagram = 4
	    }
	}

	 
	


