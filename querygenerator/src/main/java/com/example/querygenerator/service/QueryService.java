package com.example.querygenerator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.querygenerator.repository.ColumnRepository;
import com.example.querygenerator.repository.QueryRepository;

@Service
public class QueryService {
	
	@Autowired
	private QueryRepository queryRepository;
	
	@Autowired
	private ColumnRepository columnRepository;

	public Boolean checkType(String type) {
		Integer typeRepo = queryRepository.findTypeByRequestType(type);
		Boolean typeStatus = (1 == typeRepo);
		return typeStatus;
	}
	
	public Boolean checkColumn(String tableName) {
		Integer columnRepo = columnRepository.findColumn(tableName);
		Boolean typeStatus = (1 == columnRepo);
		return typeStatus;
	}
	
	public Boolean checkSchema(String schema) {
		Integer schemaRepo = queryRepository.findSchema(schema);
		Boolean schemaStatus = (1 == schemaRepo);
		return schemaStatus;
	}
	
	public List<String> listColumn(String tableName){
		List<String> columnList= columnRepository.findColumnName(tableName);
		return columnList;
	}
	
	public StringBuffer generateQuery(String schema,String tableName,String type,String input,Boolean status) {
		StringBuffer query = new StringBuffer();
		if(checkType(type) && status && checkSchema(schema)) {
			if(!input.isEmpty()) {
				String[] words = input.split(" ");
				if(type.equalsIgnoreCase("select")) {
					query.append("SELECT * ");
					query.append(" From ").append(schema).append(".").append(tableName);
					boolean wherePresent = false;
					for(String word : words) {
						if(word.equalsIgnoreCase("where")) {
							wherePresent=true;
						}
						else if(word.contains("=") && wherePresent) {
							String[] values=word.split("=");
							String key = values[0];
							String value = values[1];
							query.append(" WHERE ").append(key).append(" = '").append(value).append("' ");
						}
					}
				}
				else if(type.equalsIgnoreCase("update")) {
					query.append("UPDATE ").append(schema).append(".").append(tableName);
					boolean setPresent = false;
					for(String word : words) {
						if(word.equalsIgnoreCase("set")) {
							setPresent=true;
						}
						else if(word.contains("=") && setPresent) {
							String[] values=word.split("=");
							String key = values[0];
							String value = values[1];
							query.append(" SET ").append(key).append(" = '").append(value).append("' ");
							break;
						}
					}
					boolean wherePresent = false;
					for(String word : words) {
						if(word.equalsIgnoreCase("where")) {
							wherePresent=true;
						}
						if(word.contains("=") && wherePresent) {
							String[] values=word.split("=");
							String key = values[0];
							String value = values[1];
							query.append(" WHERE ").append(key).append(" = '").append(value).append("' ");
						}
					}
				}
				else if(type.equalsIgnoreCase("DELETE")) {
					query.append("DELETE FROM ").append(schema).append(".").append(tableName);
					boolean wherePresent = false;
					for(String word : words) {
						if(word.equalsIgnoreCase("where")) {
							wherePresent=true;
						}
						if(word.contains("=") && wherePresent) {
							String[] values=word.split("=");
							String key = values[0];
							String value = values[1];
							query.append(" WHERE ").append(key).append(" = '").append(value).append("' ");
						}
					}
				}
				else if(type.equalsIgnoreCase("INSERT")) {
					query.append("INSERT INTO ").append(schema).append(".").append(tableName).append("(");
					if(checkColumn(tableName)) {
						List<String> list = listColumn(tableName);
						String stringList = String.join(",", list);
						query.append(stringList);
					}
					query.append(") VALUES (");
					for(String word : words) {
						if(word.startsWith("(")) {
							String values = word.substring(1);
							if(values.endsWith(")")) {
								values = values.substring(0,values.length()-1);
							}
							String[] valueList = values.split(",");
							for(int i=0;i<valueList.length;i++) {
								if(i==valueList.length-1) {
									query.append(valueList[i]);
								}
								else {
									query.append(valueList[i]).append(", ");
								}
							}
						}
					}
					query.append(")");
				}
			}
		}
		else if(checkSchema(schema)==false) {
			query.append("User inputed schema is not found");
		}
		else if(checkType(type)==true) {
			query.append("User inputed table name is not found");
		}
		else if(status == true) {
			query.append("User inputed type doesnot match with the conditions");
		}
		else {
			query.append("User inputed table name as well as conditions doesnot match with the data available");
		}
		return query;
	}
}
