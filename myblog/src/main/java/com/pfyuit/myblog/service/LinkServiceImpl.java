package com.pfyuit.myblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfyuit.myblog.dao.LinkDao;
import com.pfyuit.myblog.domain.Link;

@Service
@Transactional(rollbackFor = Exception.class)
public class LinkServiceImpl implements LinkService {
	
	@Autowired
	private LinkDao linkDAO;

	@Override
	public void save(Link Link) {
		linkDAO.save(Link);
	}

	@Override
	public void delete(Link Link) {
		linkDAO.delete(Link);
	}

	@Override
	public void update(Link Link) {
		linkDAO.update(Link);
	}

	@Override
	public List<Link> findAll() {
		return linkDAO.findAll();
	}

}
