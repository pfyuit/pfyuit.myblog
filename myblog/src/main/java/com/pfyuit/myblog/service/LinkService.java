package com.pfyuit.myblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfyuit.myblog.dao.LinkDAO;
import com.pfyuit.myblog.domain.Link;

@Service
public class LinkService {
	
	@Autowired
	private LinkDAO linkDAO;

	public void save(Link Link) {
		linkDAO.save(Link);
	}

	public void delete(Link Link) {
		linkDAO.delete(Link);
	}

	public void update(Link Link) {
		linkDAO.update(Link);
	}

	public List<Link> findAll() {
		return linkDAO.findAll();
	}

}
