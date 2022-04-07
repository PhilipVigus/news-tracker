package com.philvigus.newstracker.server.services;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleUrlFinderServiceImpl implements ArticleUrlFinderService {
  private final SyndFeedInput input;

  public ArticleUrlFinderServiceImpl(final SyndFeedInput input) {
    this.input = input;
  }

  @Override
  public List<String> getUrls(final String source) {
    try {
      final URL feedUrl = new URL(source);
      final SyndFeed feed = input.build(new XmlReader(feedUrl));

      return feed.getEntries().stream()
          .map((entry) -> StringUtils.substringBefore(entry.getLink(), "?"))
          .toList();

      // TODO - implement proper error handling here
    } catch (MalformedURLException e) {
      System.out.println("Malformed URL");
      return new ArrayList<>();
    } catch (IOException | FeedException e) {
      System.out.println("Unable to access rss feed");
      return new ArrayList<>();
    }
  }
}
