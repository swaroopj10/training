package com.fidelity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fidelity.business.Music;
import com.fidelity.integration.MusicDao;
import com.fidelity.integration.MusicJdbcDao;

@Component("musicService")
public class MusicService {
	private MusicDao dao;
	
	public MusicService() {}
	public MusicService(MusicDao dao) {
		this.dao = dao;
	}
	
	public List<Music> queryAllMusic() {
		List<Music> musics = dao.queryForAllMusic();
		
		return musics;
	}
	
	public MusicDao getMusicDao() {
		return dao;
	}

	@Autowired
	@Qualifier("jdbcDao")
	public void setDao(MusicDao dao) {
		this.dao = dao;
	}
	
	
}
