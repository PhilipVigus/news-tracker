package com.philvigus.newstracker.server.services;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@DisplayName("ArticleUrlFinderServiceImpl Test")
class ArticleUrlFinderServiceImplTest {
  private ArticleUrlFinderService service;

  @Mock private SyndEntry syndEntryMock;
  @Mock private SyndFeed syndFeedMock;
  @Mock private SyndFeedInput syndFeedInputMock;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("it returns the correct number of links from the feed")
  void itReturnsTheCorrectNumberOfLinksFromTheFeed() throws FeedException, IOException {
    final String returnedLink = "www.test.com";

    setupMocks(returnedLink);

    service = new ArticleUrlFinderServiceImpl(syndFeedInputMock);

    final List<String> urls = service.getUrls("http://feeds.bbci.co.uk/news/rss.xml?edition=uk");

    assertEquals("getUrls does not return the correct number of links", 1, urls.size());
  }

  @Test
  @DisplayName("it returns the correct links from the feed")
  void itReturnsTheCorrectLinksFromTheFeed() throws FeedException, IOException {
    final String returnedLink = "www.test.com";

    setupMocks(returnedLink);

    service = new ArticleUrlFinderServiceImpl(syndFeedInputMock);

    final List<String> urls = service.getUrls("http://feeds.bbci.co.uk/news/rss.xml?edition=uk");

    assertEquals(
        "getUrls does not return the correct link", returnedLink, urls.stream().findFirst().get());
  }

  @Test
  @DisplayName("it removes the query string from returned urls")
  void itReturnsRemovesTheQueryStringFromReturnedUrls() throws FeedException, IOException {
    final String rssLink = "www.test.com?query=string";
    final String returnedLink = "www.test.com";

    setupMocks(rssLink);

    service = new ArticleUrlFinderServiceImpl(syndFeedInputMock);

    final List<String> urls = service.getUrls("http://feeds.bbci.co.uk/news/rss.xml?edition=uk");

    assertEquals(
        "getUrls does not return the correct link", returnedLink, urls.stream().findFirst().get());
  }

  private void setupMocks(final String returnUrl) throws FeedException {
    when(syndEntryMock.getLink()).thenReturn(returnUrl);

    final List<SyndEntry> entries = Stream.of(syndEntryMock).collect(Collectors.toList());

    when(syndFeedMock.getEntries()).thenReturn(entries);
    when(syndFeedInputMock.build((Reader) any())).thenReturn(syndFeedMock);
  }
}
