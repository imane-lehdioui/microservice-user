package com.cm.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cm.user.entity.Fonctionnalite;
import com.cm.user.entity.Roles;
import com.cm.user.exception.ResourceNotFoundException;
import com.cm.user.payload.RoleResponse;
import com.cm.user.repository.RolesRepository;

@RestController
@RequestMapping("/Users")
public class RolesController {
	@Autowired
	private RolesRepository rolesRepository;

	@GetMapping("/roles/index")
	public List<Roles> getIndexRoles() {
		return rolesRepository.findRolesIndex();
	}
	/*
	 * @GetMapping("/Users/roleproxy") public List<LignePermission>
	 * getAllRolesproxy() {
	 * 
	 * }
	 */

	@GetMapping("/roles/show/{id}")
	public ResponseEntity<Roles> getRoleById(@PathVariable(value = "id") long roleId) {
		Roles roles = rolesRepository.findById(roleId);

		return ResponseEntity.ok().body(roles);
	}

	@PostMapping("/roles/new")
	public Roles createRole(@Valid @RequestBody Roles roles) {
		return rolesRepository.save(roles);
	}

	@PutMapping("/roles/{id}")
	public ResponseEntity<Roles> updateRole(@PathVariable(value = "id") long roleId,
			@Valid @RequestBody Roles typePersonnelDetails) throws ResourceNotFoundException {
		Roles roles = rolesRepository.findById(roleId);

		roles.setTitle(typePersonnelDetails.getTitle());
		final Roles updatedRole = rolesRepository.save(roles);
		return ResponseEntity.ok(updatedRole);
	}

	@DeleteMapping("/roles/{id}")
	public Map<String, Boolean> deleteRole(@PathVariable(value = "id") long roleId) throws ResourceNotFoundException {
		Roles roles = rolesRepository.findById(roleId);
		rolesRepository.delete(roles);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@GetMapping("/roles/all")
	@Transactional
	public List<RoleResponse> getAllRolesn() {

		List<Roles> roles = rolesRepository.findAll();
		List<RoleResponse> liste = new ArrayList<>();
		for (Roles r : roles) {
			RoleResponse reponse = new RoleResponse();
			List<Long> permissions = new ArrayList<>();
			for (Fonctionnalite p : r.getPermissions()) {
				permissions.add((long) p.getId());
			}
			reponse.setId(r.getId());
			reponse.setCoreRole(r.isCoreRole());
			reponse.setTitle(r.getTitle());
			reponse.setPermissions(permissions);
			liste.add(reponse);
		}

		return liste;
	}
}