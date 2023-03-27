package com.example.querygenerator.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.querygenerator.entity.RequestData;
import com.example.querygenerator.repository.ColumnRepository;
import com.example.querygenerator.service.QueryService;

@RestController
public class QueryController {
	
	@Autowired
	private QueryService queryService;
	
	@Autowired
	private ColumnRepository columnRepository;
	
	@PostMapping("/generator")
	public String queryGenerator(@RequestBody RequestData requestData) {
		String schema = requestData.getSchema();
		String tableName = requestData.getTableName();
		String type = requestData.getType();
		String input = requestData.getInput();
		Boolean status = requestData.getStatus();
		
		StringBuffer query = queryService.generateQuery(schema,tableName,type,input,status);
		return new String(query);
	}

}
