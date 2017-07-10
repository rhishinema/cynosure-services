package com.cynosure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cynosure.pojo.Event;

@Repository
public interface EventRepository  extends JpaRepository<Event, Long>{ 

}
