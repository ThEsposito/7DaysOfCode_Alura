package IMDB.service;

import IMDB.domain.Content;

import java.util.List;

public interface ApiClient{
    List<? extends Content> getBody();
    Content type(); // declaro assim?
}
