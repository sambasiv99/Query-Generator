package com.example.TableCheck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TableCheck.entity.Queries;

public interface TableRepository extends JpaRepository<Queries,Integer>{
	
	@Query(value="SELECT CASE WHEN COUNT(*)>0 THEN true ELSE false END from query where table_name=:tableName",nativeQuery=true)
	public Integer findTableName(@Param("tableName") String tableName);

}
