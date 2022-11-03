package com.cm.user.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cm.user.entity.Roles;



public interface RolesRepository extends JpaRepository<Roles,Long> {

   public Roles findByTitle(String role);
   public Roles findById(long id);
   
   @Query("select new com.cm.user.entity.Roles(r.id,r.title) from Roles r") 
   List<Roles> findRolesIndex();
   
  
}