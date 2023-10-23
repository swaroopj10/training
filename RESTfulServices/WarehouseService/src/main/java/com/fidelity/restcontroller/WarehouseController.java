package com.fidelity.restcontroller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebInputException;

import com.fidelity.business.Gadget;
import com.fidelity.business.Widget;
import com.fidelity.business.service.WarehouseBusinessService;

/**
 * WarehouseController is a RESTful web service.
 * It provides web methods to manage Widgets and Gadgets 
 * in the Warehouse database.
 * 
 * There is no business logic in this class; all business rules in
 * terms of validating data, defining transaction boundaries, etc.,
 * are implemented in the business service.
 * 
 * @author ROI Instructor
 *
 */

// TODO: add the required Spring annotations:
//       1. identify this class as a RESTful controller
//       2. configure the URL that will trigger method calls
// HINT: see slide 1-22
@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
	
	// TODO: note the autowired business service field. The RESTful controller
	//       will delegate all input validation and database operations to the 
	//       business service.
	//       (no code changes required)
	@Autowired
	private WarehouseBusinessService service;

	@GetMapping(value="/ping",
				produces=MediaType.ALL_VALUE)
	public String ping() {
		return "Warehouse web service is alive at " + LocalDateTime.now();
	}
	
	// **** Widget methods ****
	
	// TODO: define CRUD operations for widgets
	
	// Get all widgets
	@GetMapping("/widgets")
	public ResponseEntity<List<Widget>> getAllWidgets() {
	    try {
	        List<Widget> widgetList = service.findAllWidgets();

	        if (widgetList.isEmpty()) {
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.ok(widgetList);
	        }
	    } catch (RuntimeException e) {
	        // Handle the exception and return an error response
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .build();
	    }
	}

	
	// Get one widget by ID
	@GetMapping("/widgets/{id}")
	public ResponseEntity<Widget> getWidgetById(@PathVariable int id) {
		try {
	        Widget widget = service.findWidgetById(id);

	        if (widget == null) {
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.ok(widget);
	        }
	    } catch (RuntimeException e) {
	        // Handle the exception and return an error response
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .build();
	    }
	}
	// Insert a widget
	@PostMapping("/widgets")
	public ResponseEntity<DatabaseRequestResult> addWidget (@RequestBody Widget widget) {
		return ResponseEntity.accepted().body(new DatabaseRequestResult(service.addWidget(widget)));
	}
	// Update a widget
	@PutMapping("/widgets")
	public ResponseEntity<DatabaseRequestResult> updateWidget(@RequestBody Widget widget) {
		int rowCount = service.modifyWidget(widget);
		if (rowCount == 0) {
            throw new ServerWebInputException("Cannot update widget");
        }
        return ResponseEntity.accepted().body(new DatabaseRequestResult(rowCount));
	}
	// Delete a widget
	@DeleteMapping("/widgets/{id}")
	public DatabaseRequestResult deleteWidget(@PathVariable int id) {
		int rowCount = service.removeWidget(id);
		if(rowCount == 0) {
			throw new ServerWebInputException("Cannot delete widget");
		}
		return new DatabaseRequestResult(rowCount);
	}
	// **** Gadget methods ****

	// BONUS TODO: define CRUD operations for gadgets
	
	
}

