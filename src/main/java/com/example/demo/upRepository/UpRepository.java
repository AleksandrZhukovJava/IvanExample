package com.example.demo.upRepository;

import com.example.demo.data_base_emtity.Date_entity;
import org.springframework.data.jpa.repository.JpaRepository;

   public interface UpRepository extends JpaRepository<Date_entity, Integer> {

}
