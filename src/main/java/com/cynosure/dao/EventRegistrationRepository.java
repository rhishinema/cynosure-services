package com.cynosure.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cynosure.pojo.EventRegisteration;

public interface EventRegistrationRepository  extends JpaRepository<EventRegisteration, Long>{ 

}
