package com.fidelity.restcontroller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fidelity.business.Ship;
import com.fidelity.restservices.ShipDatabaseException;
import com.fidelity.restservices.ShipService;

@RestController
@RequestMapping("/ships")
public class ShipController {
	
	@Autowired
	private ShipService service;
	
	@GetMapping(value="/ping",
			produces=MediaType.ALL_VALUE)
	public String ping() {
		return "Ship web service is alive at " + LocalDateTime.now();
	}
	
	@GetMapping
	public ResponseEntity<List<Ship>> getAllShips() {
		try {
			List<Ship> ships = service.queryAllShips();
			
			if (ships.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	        } else {
	            return ResponseEntity.ok(ships);
	        }
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		            .build();
		}
	}
	
	@GetMapping("/byId/{id}")
	public ResponseEntity<Ship> getShipById(@PathVariable int id) {
		if (id <= 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		try {
			Ship ship = service.queryShipById(id);
			
			if (ship == null) {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	        } else {
	            return ResponseEntity.ok(ship);
	        }
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		            .build();
		}
	}
	
	@GetMapping("/byName/{name}")
	public ResponseEntity<ShipCaptainResponse> getCaptainByShip(@PathVariable String name) {
	    if (name == null || name.isEmpty()) { 
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ShipCaptainResponse("Name cannot be null or empty"));
	    }
	    
	    String captain = null;
	    try {
	        captain = service.queryCaptainByShipName(name);

	        if (captain == null) {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	        }
	    } catch (ShipDatabaseException ex) {
	        String errorMessage = "Error retrieving captain for ship name: " + name;
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body(new ShipCaptainResponse(errorMessage));
	    }
	    return ResponseEntity.ok().body(new ShipCaptainResponse(captain));
	}

}
