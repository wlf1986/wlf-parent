package  com.reuserrole.service;

import  com.reuserrole.entity.ReUserRole;

import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */
public interface ReUserRoleService {

    int addReUserRole(ReUserRole reUserRole);

    int deleteReUserRole(Long id);

    int updateReUserRoleById(ReUserRole reUserRole);

    List<ReUserRole> getReUserRole();

    ReUserRole getReUserRoleById(Long id);
}
