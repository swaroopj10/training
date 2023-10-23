package com.fidelity.india.secondary.assessment.restcontroller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerErrorException;

import com.fidelity.india.secondary.assessment.business.Bill;
import com.fidelity.india.secondary.assessment.services.MarinaException;
import com.fidelity.india.secondary.assessment.services.MarinaService;

@RestController
@RequestMapping("/marina")
public class MarinaController {
	
public static final String baseUrl = "http://localhost:8888/marina/";
	
	@Autowired
	private Logger logger;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MarinaService marinaService;
	
	@GetMapping(value="/ping", produces=MediaType.ALL_VALUE)
	public String ping() {
		return "Marina web service is alive at " + LocalDateTime.now();
	}
	
	@GetMapping("/bill/{vesselName}")
	public ResponseEntity<Bill> getBill(@PathVariable String vesselName) {
		
		try {
			ResponseEntity<Bill> vesselBill;
			String marinaName = marinaService.getMarinaName(vesselName);
			
			if(marinaName != null) {
				Bill bill = getBillFromServer(marinaName, vesselName);
				vesselBill = ResponseEntity.ok(bill);
			} else {
				logger.warn("getBill: " + vesselName + "not found");
				vesselBill = ResponseEntity.badRequest().build();
			}
			return vesselBill;
		} catch (MarinaException e) {
			logger.error("getBill", vesselName, e.getMessage());
			throw new ServerErrorException("Server Error", e);
		}
	}

	private Bill getBillFromServer(String marinaName, String vesselName) {
		
		String url = baseUrl + marinaName + "/" + vesselName;
		ResponseEntity<Bill> billResponse = restTemplate.getForEntity(url, Bill.class);
		if (billResponse.getStatusCode() != HttpStatus.OK) {
			String message = "Error getting bill for " + marinaName + "," + vesselName;
			throw new MarinaException(message);
		}
		Bill bill = billResponse.getBody();
		return bill;
	}
}
