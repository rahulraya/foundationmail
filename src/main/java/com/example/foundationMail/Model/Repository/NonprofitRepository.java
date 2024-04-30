package com.example.foundationMail.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.foundationMail.Model.Nonprofit;


@Repository
public interface NonprofitRepository extends JpaRepository<Nonprofit, Long> {
}



