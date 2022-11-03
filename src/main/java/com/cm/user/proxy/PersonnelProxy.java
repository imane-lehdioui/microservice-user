package com.cm.user.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cm.user.bein.PersonnelBein;




@FeignClient(name="microservice-personnel")
public interface PersonnelProxy {

	@GetMapping(value = "${personnels.show.url}")
    PersonnelBein getPersonnelById(@PathVariable("id") long id);
}
