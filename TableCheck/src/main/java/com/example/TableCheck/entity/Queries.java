package com.example.TableCheck.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="query")
public class Queries {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="table_name")
	private String tableName;
	
	@Column(name="ddl_command")
	private String ddlCommand;
	
	@Column(name="query_schema")
	private String schema;

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDdlCommand() {
		return ddlCommand;
	}

	public void setDdlCommand(String ddlCommand) {
		this.ddlCommand = ddlCommand;
	}

}
