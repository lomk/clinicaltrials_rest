package ua.com.clinicaltrials.services;

import ua.com.clinicaltrials.domain.Role;

/**
 * Created by Igor on 07-Jul-16.
 */
public interface RoleService {
    Iterable<Role> listAllRoles();
    Role getRoleById(Integer id);
    void deleteRole(Integer id);
    void save(Role role);
    public Role findByName(String name);
}
