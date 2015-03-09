package com.pfyuit.myblog.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pfyuit.myblog.domain.Blog;
import com.pfyuit.myblog.domain.Category;
import com.pfyuit.myblog.dto.ArchiveDto;
import com.pfyuit.myblog.dto.BlogDto;
import com.pfyuit.myblog.dto.CategoryDto;
import com.pfyuit.myblog.dto.ReadDto;
import com.pfyuit.myblog.dto.builder.BlogDtoBuilder;
import com.pfyuit.myblog.service.BlogService;
import com.pfyuit.myblog.service.CategoryService;
import com.pfyuit.myblog.service.LinkService;
import com.pfyuit.myblog.service.UserService;

@Controller
public class PageController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	@Autowired
	private LinkService linkService;

	@RequestMapping("/index")
	public ModelAndView index() {
		List<Blog> blogs = blogService.findAll();
		List<BlogDto> blogDtos = BlogDtoBuilder.buildBlogDtos(blogs);

		ModelAndView view = new ModelAndView();
		view.addObject("menu", "Home");
		view.addObject("blogs", blogDtos);
		view.setViewName("/business/content");
		return view;
	}

	@RequestMapping("/about")
	public ModelAndView about() {
		ModelAndView view = new ModelAndView();
		view.addObject("menu", "About");
		view.setViewName("/business/about");
		return view;
	}

	@RequestMapping("/newblog")
	public ModelAndView newBlog() {
		List<Category> categories = categoryService.findAll();

		ModelAndView view = new ModelAndView();
		view.addObject("menu", "NewBlog");
		view.addObject("categories", categories);
		view.setViewName("/business/newblog");
		return view;
	}

	@RequestMapping("/updateblog")
	public ModelAndView updateBlog(@RequestParam String blogid) {
		Blog blog = blogService.find(Integer.parseInt(blogid));
		List<Category> categories = categoryService.findAll();

		ModelAndView view = new ModelAndView();
		view.addObject("blog", blog);
		view.addObject("categories", categories);
		view.setViewName("/business/updateblog");
		return view;
	}

	@RequestMapping("/viewblog")
	public ModelAndView viewBlog(@RequestParam String blogid) {
		Blog blog = blogService.find(Integer.parseInt(blogid));
		blog.setReadCount(blog.getReadCount() + 1);
		blogService.update(blog);

		BlogDto blogDto = BlogDtoBuilder.buildBlogDto(blog);

		ModelAndView view = new ModelAndView();
		view.addObject("blog", blogDto);
		view.setViewName("/business/viewblog");
		return view;
	}

	@RequestMapping("/navigator")
	public ModelAndView navigator() {
		List<Category> categories = categoryService.findAll();
		List<Blog> blogs = blogService.findAll();

		List<ArchiveDto> archives = new ArrayList<ArchiveDto>();

		Collections.sort(categories, new Comparator<Category>() {
			@Override
			public int compare(Category o1, Category o2) {
				return o1.getSortId() - o2.getSortId();
			}
		});
		List<CategoryDto> categoryDtos = new LinkedList<CategoryDto>();
		for (Category category : categories) {
			CategoryDto categoryDto = new CategoryDto();
			categoryDto.setCategoryid(category.getCategoryid());
			categoryDto.setName(category.getName());
			categoryDto.setCreateTime(category.getCreateTime());
			for (Blog blog : blogs) {
				if (blog.getCategory().getName().equals(category.getName())) {
					categoryDto.setBlogCount(categoryDto.getBlogCount() + 1);
				}
			}
			categoryDtos.add(categoryDto);
		}

		Map<String, Integer> map = new TreeMap<String, Integer>();
		for (Blog blog : blogs) {
			String month = new SimpleDateFormat("yyyy-MM").format(blog.getCreateDate());
			if (!map.containsKey(month)) {
				map.put(month, 0);
			}
			int oldCount = map.get(month);
			map.put(month, oldCount + 1);
		}
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			archives.add(new ArchiveDto(entry.getKey(), entry.getValue()));
		}

		List<ReadDto> readDtos = new ArrayList<ReadDto>();
		List<ReadDto> readDtos1 = new ArrayList<ReadDto>();
		for (Blog blog : blogs) {
			ReadDto readDto = new ReadDto();
			readDto.setBlogid(blog.getBlogid());
			readDto.setTitle(blog.getTitle());
			readDto.setCount(blog.getReadCount());
			readDto.setCommentCount(blog.getComments().size());
			readDtos.add(readDto);
			readDtos1.add(readDto);
		}
		Collections.sort(readDtos, new Comparator<ReadDto>() {
			@Override
			public int compare(ReadDto o1, ReadDto o2) {
				return (int) (o2.getCount() - o1.getCount());
			}
		});
		if (readDtos.size() <= 10) {
			readDtos = readDtos.subList(0, readDtos.size());
			readDtos1 = readDtos1.subList(0, readDtos1.size());
		} else {
			readDtos = readDtos.subList(0, 10);
			readDtos1 = readDtos1.subList(0, 10);
		}

		Collections.sort(readDtos1, new Comparator<ReadDto>() {
			@Override
			public int compare(ReadDto o1, ReadDto o2) {
				return (int) (o2.getCommentCount() - o1.getCommentCount());
			}
		});

		ModelAndView view = new ModelAndView();
		view.addObject("user", userService.findAll().get(0));
		view.addObject("categories", categoryDtos);
		view.addObject("archives", archives);
		view.addObject("reads", readDtos);
		view.addObject("comments", readDtos1);
		view.addObject("links", linkService.findAll());
		view.setViewName("/business/navigator");
		return view;
	}

	@RequestMapping("/content")
	public ModelAndView content(@RequestParam(value = "categoryid", required = false) Integer categoryid, @RequestParam(value = "createdate", required = false) String createdate) {
		Category category = null;
		String createDate = null;
		if (categoryid != null && categoryid != 0) {
			category = categoryService.find(categoryid);
		}
		if (createdate != null && !createdate.equals("")) {
			createDate = createdate;
		}

		List<Blog> blogs = blogService.findAll();
		List<BlogDto> blogDtos = new ArrayList<BlogDto>();
		for (Blog blog : blogs) {
			if (category != null && blog.getCategory().getCategoryid() != category.getCategoryid()) {
				continue;
			}
			if (createDate != null && !new SimpleDateFormat("yyyy-MM").format(blog.getCreateDate()).equals(createDate)) {
				continue;
			}
			BlogDto blogDto = BlogDtoBuilder.buildBlogDto(blog);
			blogDtos.add(blogDto);
		}

		ModelAndView view = new ModelAndView();
		view.addObject("menu", "Home");
		view.addObject("blogs", blogDtos);
		view.setViewName("/business/content");
		return view;
	}

	@RequestMapping("/authen")
	public ModelAndView login(@RequestParam(value = "messages", required = false) String messages) {
		ModelAndView view = new ModelAndView();
		if (messages != null && !messages.isEmpty()) {
			view.addObject("messages", messages);
		}
		view.setViewName("/business/authen");
		return view;
	}

}
