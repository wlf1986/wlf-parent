package  com.role.service;

import  com.role.entity.Role;

import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */
public interface RoleService {

    int addRole(Role role);

    int deleteRole(Long id);

    int updateRoleById(Role role);

    List<Role> getRole();

    Role getRoleById(Long id);
}
