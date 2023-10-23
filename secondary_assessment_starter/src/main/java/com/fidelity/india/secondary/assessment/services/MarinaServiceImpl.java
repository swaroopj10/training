package com.fidelity.india.secondary.assessment.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.india.secondary.assessment.integration.MarinaDao;

@Service
@Transactional
public class MarinaServiceImpl implements MarinaService {
	
	@Autowired
	private Logger logger;
	
	@Autowired
	private MarinaDao marinaDao;

	@Override
	public String getMarinaName(String vesselName) {
		
		String marinaName = null;
		
		if(vesselName == null || vesselName.isEmpty()) {
			logger.error("Invalid vessel name");
			throw new IllegalArgumentException("Vessel Name cannot be null");
		}
		try {
			marinaName = marinaDao.getMarinaName(vesselName);
		} catch (DataAccessException e) {
			logger.error("DataAccessException found", e);
			throw new MarinaException("DataAccessException found",e);
			
		} catch (RuntimeException e) {
			logger.error("RunTimeException found", e);
			throw new MarinaException("RunTimeException found",e);
		}
		
		return marinaName;
	}
}
