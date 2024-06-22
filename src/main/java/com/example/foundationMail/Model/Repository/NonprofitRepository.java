package com.example.foundationMail.Model.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.foundationMail.Model.Nonprofit;


@Repository
public interface NonprofitRepository extends JpaRepository<Nonprofit, Long> {
	
	@Query("SELECT e.email FROM Nonprofit e")
    List<String> findemail();
	
}





