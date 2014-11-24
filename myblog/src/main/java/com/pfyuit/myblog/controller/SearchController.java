package com.pfyuit.myblog.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pfyuit.myblog.domain.Blog;
import com.pfyuit.myblog.dto.BlogDto;
import com.pfyuit.myblog.search.ISearchService;
import com.pfyuit.myblog.service.BlogService;

@Controller
public class SearchController {
	@Autowired
	private ISearchService searchService;

	@Autowired
	private BlogService blogService;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(String key) {
		List<Integer> blogIds = null;
		try {
			blogIds = searchService.searchBlog(key);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}

		if (blogIds.isEmpty()) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/index.html");
			return mav;
		}

		List<Blog> blogs = blogService.findAll();
		List<BlogDto> blogDtos = new ArrayList<BlogDto>();
		for (int blogId : blogIds) {
			for (Blog blog : blogs) {
				if (blog.getBlogid() == blogId) {
					BlogDto blogDto = new BlogDto();
					blogDto.setBlogid(blog.getBlogid());
					blogDto.setAuthor(blog.getAuthor());
					blogDto.setCategory(blog.getCategory());
					blogDto.setComments(blog.getComments());
					blogDto.setContent(blog.getContent());
					blogDto.setCreateDate(blog.getCreateDate());
					blogDto.setLastModified(blog.getLastModified());
					blogDto.setOriginal(blog.isOriginal());
					blogDto.setReadCount(blog.getReadCount() == 0 ? 0 : blog.getReadCount());
					blogDto.setTitle(blog.getTitle());
					blogDto.setCommentCount(blogDto.getComments().size() == 0 ? "0" : String.valueOf(blogDto.getComments().size()));
					blogDto.setContentAbstract(PageController.getAbstract(blogDto.getContent()));
					blogDtos.add(blogDto);
				}
			}
		}

		ModelAndView view = new ModelAndView();
		view.addObject("menu", "Home");
		view.addObject("blogs", blogDtos);
		view.setViewName("/business/content");
		return view;
	}

}
