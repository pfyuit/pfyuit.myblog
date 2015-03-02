package com.pfyuit.myblog.service;

import java.util.List;

import com.pfyuit.myblog.domain.Link;

public interface LinkService {

	public abstract void save(Link Link);

	public abstract void delete(Link Link);

	public abstract void update(Link Link);

	public abstract List<Link> findAll();

}