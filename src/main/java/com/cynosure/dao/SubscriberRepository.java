package com.cynosure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cynosure.pojo.Subscriber;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, String>{

}
