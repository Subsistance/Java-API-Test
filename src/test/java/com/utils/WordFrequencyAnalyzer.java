package com.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyAnalyzer {

    public static void main(String[] args) {
        // Step 1: Fetch data
        List<Map<String, Object>> posts = fetchPosts();

        // Step 2: Extract words
        List<String> words = extractWords(posts);

        /* Here we can also add the step for normalizing words to remove non-alphabetic characters (except for newlines)
        and convert words to lowercase, but in this case it's not necessary */

        // Step 3: Count word frequency
        Map<String, Integer> wordFrequency = countWordFrequency(words);

        // Step 4: Sort words by frequency
        List<Map.Entry<String, Integer>> sortedWords = sortWordsByFrequency(wordFrequency);

        // Step 5: Select top 10 words
        List<Map.Entry<String, Integer>> top10Words = selectTop10Words(sortedWords);

        // Step 6: Format output
        formatOutput(top10Words);
    }

    // Fetches all posts from the JSONPlaceholder API.
    // @return a list of posts, where each post is represented as a map.

    private static List<Map<String, Object>> fetchPosts() {
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts");
        return response.jsonPath().getList("$");
    }

    // Extracts words from the bodies of all posts.
    private static List<String> extractWords(List<Map<String, Object>> posts) {
        List<String> words = new ArrayList<>();
        for (Map<String, Object> post : posts) {
            String body = (String) post.get("body");
            // Split the body into words considering \n without spaces
            String[] splitWords = body.split("[\\s\\n]+");
            words.addAll(Arrays.asList(splitWords));
        }
        return words;
    }

    // Counts the frequency of each word in the list.
    // @return a map where the keys are words and the values are their frequencies
    private static Map<String, Integer> countWordFrequency(List<String> words) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }
        return wordFrequency;
    }

    // Sorts the word frequency map by frequency in descending order.
    // @return a list of map entries sorted by frequency in descending order
    private static List<Map.Entry<String, Integer>> sortWordsByFrequency(Map<String, Integer> wordFrequency) {
        return wordFrequency.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .collect(Collectors.toList());
    }

    // Selects the top 10 words with the highest frequency.
    // @return a list of the top 10 word frequency entries
    private static List<Map.Entry<String, Integer>> selectTop10Words(List<Map.Entry<String, Integer>> sortedWords) {
        return sortedWords.stream().limit(10).collect(Collectors.toList());
    }

    private static void formatOutput(List<Map.Entry<String, Integer>> top10Words) {
        for (int i = 0; i < top10Words.size(); i++) {
            Map.Entry<String, Integer> entry = top10Words.get(i);
            System.out.printf("%d. %s - %d%n", i + 1, entry.getKey(), entry.getValue());
        }
    }
}