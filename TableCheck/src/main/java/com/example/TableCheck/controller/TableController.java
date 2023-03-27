package com.example.TableCheck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.TableCheck.entity.RequestData;
import com.example.TableCheck.entity.Response;
import com.example.TableCheck.service.TableService;

import reactor.core.publisher.Mono;

@RestController
public class TableController {
	
	@Autowired
	private TableService tableService;
	
	@PostMapping("/table-check")
	public Mono<String> tableChecker(@RequestBody RequestData requestData) {
		
		Mono<String> status = tableService.generator(requestData);
		
		return status;
	}

}
