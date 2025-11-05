package in.jay.service;

import java.util.List;

import in.jay.entity.Roles;

public interface RolesService {
	
	public Roles createRole(Roles role);
	
	public List<Roles> getAllRoles();
	
	public Roles getRoleById(Integer rolesId);
	
	public Roles deleteRoleById(Integer rolesId);

}
