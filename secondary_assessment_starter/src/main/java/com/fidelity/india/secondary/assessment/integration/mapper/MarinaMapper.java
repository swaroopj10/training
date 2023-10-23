package com.fidelity.india.secondary.assessment.integration.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MarinaMapper {
	
	String getMarinaName(@Param("vesselName") String vesselName);
}
