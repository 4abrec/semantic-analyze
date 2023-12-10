package com.example.semanticanalyze.service;

import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toMap;
import static lombok.AccessLevel.PRIVATE;

@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class SemanticService {

  static String punctuationRegex = "\\p{Punct}";
  static String pronounsRegex = "\\b(он\\p{L}?|мы|я)\\b";

  public Map<String, Integer> frequencySemanticAnalyze(String text) {
    var frequencyMap = new HashMap<String, Integer>();
    var wordList = stream(
        text
            .toLowerCase()
            .replaceAll(punctuationRegex, "")
            .replaceAll(pronounsRegex, "")
            .split(" "))
        .filter(word -> word.length() > 3).toList();
    wordList.forEach(word -> frequencyMap.merge(word, 1, Integer::sum));
    return frequencyMap.entrySet().stream().
        sorted(Map.Entry.comparingByValue(reverseOrder()))
        .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
  }
}
