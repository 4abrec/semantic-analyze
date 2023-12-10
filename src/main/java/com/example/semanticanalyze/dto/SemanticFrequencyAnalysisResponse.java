package com.example.semanticanalyze.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class SemanticFrequencyAnalysisResponse {

  String word;
  Integer count;
}
