package com.ljc.search.service;

import com.ljc.search.vo.SearchParam;
import com.ljc.search.vo.SearchResult;

public interface IMallSearchService {
    SearchResult search(SearchParam param);
}
