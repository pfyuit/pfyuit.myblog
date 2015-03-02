package com.pfyuit.myblog.search;

import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;

public interface ISearchService {

	public List<Integer> searchBlog(String key) throws SolrServerException;

}
