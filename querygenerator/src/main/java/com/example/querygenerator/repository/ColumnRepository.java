package com.example.querygenerator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.querygenerator.entity.ColumnName;

public interface ColumnRepository extends JpaRepository<ColumnName,Integer>{

	@Query(value="SELECT columnName FROM query_column WHERE tableName=:tableName",nativeQuery=true)
	public List<String> findColumnName(@Param("tableName") String tableName);
	
	@Query(value="SELECT CASE WHEN COUNT(*)>0 THEN true ELSE false END FROM query_column WHERE tableName=:tableName",nativeQuery=true)
	public Integer findColumn(@Param("tableName") String tableName);
	
}
