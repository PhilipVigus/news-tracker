package com.philvigus.newstracker.server.services;

import com.rometools.rome.io.FeedException;

import java.io.IOException;
import java.util.List;

public interface ArticleUrlFinderService {
  List<String> getUrls(String source) throws IOException, FeedException;
}
