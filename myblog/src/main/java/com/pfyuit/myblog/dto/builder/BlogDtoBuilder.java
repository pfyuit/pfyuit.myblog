package com.pfyuit.myblog.dto.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.pfyuit.myblog.domain.Blog;
import com.pfyuit.myblog.dto.BlogDto;
import com.pfyuit.myblog.utils.BlogUtils;

public class BlogDtoBuilder {

	public static BlogDto buildBlogDto(Blog b) {
		BlogDto blogDto = new BlogDto();
		blogDto.setBlogid(b.getBlogid());
		blogDto.setAuthor(b.getAuthor());
		blogDto.setCategory(b.getCategory());
		blogDto.setComments(b.getComments());
		blogDto.setContent(b.getContent());
		blogDto.setCreateDate(b.getCreateDate());
		blogDto.setLastModified(b.getLastModified());
		blogDto.setOriginal(b.isOriginal());
		blogDto.setReadCount(b.getReadCount() == 0 ? 0 : b.getReadCount());
		blogDto.setTitle(b.getTitle());
		blogDto.setCommentCount(blogDto.getComments().size() == 0 ? "0" : String.valueOf(blogDto.getComments().size()));
		blogDto.setContentAbstract(BlogUtils.getAbstract(blogDto.getContent()));
		return blogDto;
	}

	public static List<BlogDto> buildBlogDtos(List<Blog> blogs) {
		List<BlogDto> blogDtos = new ArrayList<BlogDto>();
		for (Blog blog : blogs) {
			BlogDto blogDto = buildBlogDto(blog);
			blogDtos.add(blogDto);
		}

		Collections.sort(blogDtos, new Comparator<BlogDto>() {
			@Override
			public int compare(BlogDto o1, BlogDto o2) {
				return o2.getBlogid() - o1.getBlogid();
			}
		});
		return blogDtos;
	}

}
