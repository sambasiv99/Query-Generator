package com.example.TableCheck.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.TableCheck.entity.RequestData;
import com.example.TableCheck.repository.TableRepository;



import reactor.core.publisher.Mono;


@Service
public class TableService {
	
	@Autowired
	private TableRepository tableRepository;
	
	@Autowired
	private WebClient webClient;
	
	public Boolean checkTableName(String tableName) {
		Integer tableNameRepo = tableRepository.findTableName(tableName);
		Boolean tableNameStatus = (1 == tableNameRepo );
		return tableNameStatus;
	}
	
	public Mono<String> generator(RequestData requestData) {
		String tableName = requestData.getTableName();
		requestData.setStatus(checkTableName(tableName));
		 Mono<String> userResponse = webClient.post().uri("/generator").body(Mono.just(requestData),RequestData.class)
				 .retrieve()
				 .bodyToMono(String.class);
		 return userResponse;
	}

}
