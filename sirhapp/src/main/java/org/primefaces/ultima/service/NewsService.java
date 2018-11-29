package org.primefaces.ultima.service;

import java.util.List;

import org.primefaces.ultima.domain.NewsGroup;

public interface NewsService {

	public List<NewsGroup> fetchNews();
}
