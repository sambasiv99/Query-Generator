package com.example.querygenerator.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.querygenerator.entity.Queries;


public interface QueryRepository extends JpaRepository<Queries,Integer>{
	
	@Query(value="SELECT CASE WHEN COUNT(*)>0 THEN true ELSE false END from query where ddl_command=:type",nativeQuery=true)
	public Integer findTypeByRequestType(@Param("type") String type);

	@Query(value="SELECT CASE WHEN COUNT(*)>0 THEN true ELSE false END from query where table_name=:tableName",nativeQuery=true)
	public Integer findTableName(@Param("tableName") String tableName);
	
	@Query(value="SELECT CASE WHEN COUNT(*)>0 THEN true ELSE false END from query where query_schema=:query_schema",nativeQuery=true)
	public Integer findSchema(@Param("query_schema") String query_schema);
	
}
