package com.example.semanticanalyze.dto;

import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SemanticFrequencyLDAResponse {

  Long durationInMs;
  Map<String, Double> result;
}
