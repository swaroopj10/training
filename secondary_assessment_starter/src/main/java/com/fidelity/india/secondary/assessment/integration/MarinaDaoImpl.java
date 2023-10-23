package com.fidelity.india.secondary.assessment.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fidelity.india.secondary.assessment.integration.mapper.MarinaMapper;

@Repository("marine")
public class MarinaDaoImpl implements MarinaDao {
	
	@Autowired
	private MarinaMapper marinaMapper;

	@Override
	public String getMarinaName(String vesselName) {
		return marinaMapper.getMarinaName(vesselName);
	}
}
