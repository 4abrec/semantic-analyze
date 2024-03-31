package com.example.semanticanalyze.service;

import static java.lang.System.currentTimeMillis;
import static java.util.Arrays.stream;
import static java.util.Comparator.reverseOrder;
import static java.util.regex.Pattern.compile;
import static java.util.stream.Collectors.toMap;
import static lombok.AccessLevel.PRIVATE;

import cc.mallet.pipe.CharSequence2TokenSequence;
import cc.mallet.pipe.CharSequenceLowercase;
import cc.mallet.pipe.Pipe;
import cc.mallet.pipe.SerialPipes;
import cc.mallet.pipe.TokenSequence2FeatureSequence;
import cc.mallet.pipe.iterator.ArrayIterator;
import cc.mallet.topics.ParallelTopicModel;
import cc.mallet.types.InstanceList;
import com.example.semanticanalyze.dto.SemanticFrequencyAnalysisResponse;
import com.example.semanticanalyze.dto.SemanticFrequencyLDAResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class SemanticService {

  static String punctuationRegex = "\\p{Punct}";
  static String pronounsRegex = "\\b(он\\p{L}?|мы|я)\\b";

  public SemanticFrequencyAnalysisResponse frequencySemanticAnalyze(String text) {
    long startTime = currentTimeMillis();

    var frequencyMap = new HashMap<String, Integer>();
    var wordList = stream(
        text
            .toLowerCase()
            .replaceAll(punctuationRegex, "")
            .replaceAll(pronounsRegex, "")
            .split(" "))
        .filter(word -> word.length() > 3).toList();
    wordList.forEach(word -> frequencyMap.merge(word, 1, Integer::sum));

    long endTime = currentTimeMillis();

    return SemanticFrequencyAnalysisResponse.builder()
        .result(frequencyMap.entrySet().stream().
            sorted(Map.Entry.comparingByValue(reverseOrder()))
            .collect(
                toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new)))
        .durationInMs(endTime - startTime)
        .build();
  }

  @SneakyThrows
  public SemanticFrequencyLDAResponse LDASemanticAnalyze(List<String> texts) {
    long startTime = currentTimeMillis();

    var model = new ParallelTopicModel(10);

    var pipeList = new ArrayList<Pipe>();
    pipeList.add(new CharSequenceLowercase());
    pipeList.add(new CharSequence2TokenSequence(compile("\\p{L}+")));
    pipeList.add(new TokenSequence2FeatureSequence());

    var pipe = new SerialPipes(pipeList);
    InstanceList instances = new InstanceList(pipe);

    instances.addThruPipe(new ArrayIterator(texts));
    model.addInstances(instances);
    model.estimate();

    String topWords = model.displayTopWords(1, false);

    long endTime = currentTimeMillis();

    return SemanticFrequencyLDAResponse.builder()
        .durationInMs(endTime - startTime)
        .result(formatTopWordsString(topWords))
        .build();
  }

  private Map<String, Double> formatTopWordsString(String topWords) {

    var topWordsList = stream(topWords.split(" \\n"))
        .map(word -> {
          word = word.replaceAll("\\t", " ");
          var subStr = word.substring(0, word.indexOf(" ") + 1);
          return word.replaceFirst(subStr, "");
        })
        .toList();
    var map = new HashMap<String, Double>();
    for (var word : topWordsList) {
      String[] split = word.trim().split(" ");
      map.put(split[1], Double.valueOf(split[0]));
    }

    return map.entrySet().stream().
        sorted(Map.Entry.comparingByValue(reverseOrder()))
        .collect(
            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
  }
}
