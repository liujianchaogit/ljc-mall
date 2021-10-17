package com.ljc.search.controller;

import com.ljc.search.service.IElasticSearchService;
import com.ljc.search.vo.SearchParam;
import com.ljc.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/elasticSearch")
@RestController
public class ElasticSearchController {

    @Autowired
    private IElasticSearchService elasticSearchService;

    @GetMapping("/page")
    public SearchResult listPage(SearchParam searchParam) {
        return elasticSearchService.search(searchParam);
    }
}
