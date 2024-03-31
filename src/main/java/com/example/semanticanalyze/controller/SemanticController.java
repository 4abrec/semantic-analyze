package com.example.semanticanalyze.controller;

import com.example.semanticanalyze.dto.SemanticAnalyzeDtoRequest;
import com.example.semanticanalyze.dto.SemanticFrequencyAnalysisResponse;
import com.example.semanticanalyze.dto.SemanticFrequencyLDAResponse;
import com.example.semanticanalyze.service.SemanticService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/api/v1/semantic")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class SemanticController {

  SemanticService semanticService;

  @GetMapping("/frequency")
  public ResponseEntity<SemanticFrequencyAnalysisResponse> getWordFrequency(
      @RequestBody SemanticAnalyzeDtoRequest request) {
    return ResponseEntity.ok(semanticService.frequencySemanticAnalyze(request.getText()));
  }

  @GetMapping("/frequencyLDA")
  public ResponseEntity<SemanticFrequencyLDAResponse> getWordFrequency(@RequestBody List<String> request) {
    return ResponseEntity.ok(semanticService.LDASemanticAnalyze(request)  );
  }

}
