
package  com.role.service.impl;

import com.common.PageUtil;
import com.role.entity.Role;
import com.role.entity.RoleExample;
import com.role.mapper.RoleMapper;
import com.role.service.RoleService;
import com.utils.BlankUtil;
import com.utils.Page;
import com.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public int addRole(Role role) {
        Role u = RedisUtil.add(role, redisTemplate, "id");
        return roleMapper.insert(u);
    }

    @Override
    public int deleteRole(Long id) {
        RedisUtil.delete(redisTemplate, id, Role.class);
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateRoleById(Role role) {
        RedisUtil.update(role, redisTemplate, "id");
        return roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public List<Role> getRole() {
        List<Role> list = RedisUtil.getList(redisTemplate, Role.class);
        if (BlankUtil.isNotBlank(list))
            return  list;
        Page page = PageUtil.getPage();
        RoleExample roleExample = new RoleExample();
        roleExample.setPageEnd(page.getPageStar()+page.getPageSize()-1);
        roleExample.setPageStar(page.getPageStar());
        return  roleMapper.selectByExample(roleExample);
//        return roleMapper.getRole();
    }


    @Override
    public Role getRoleById(Long id) {
        /**
         * @author wlf
         * @date 2017年08月21日 18:32
         * @param [id]
         * @return  com.role.entity.entity.Role
         */
        Role role = RedisUtil.getOne(id, redisTemplate, Role.class);
        return role == null ? roleMapper.selectByPrimaryKey(id) : role;
    }
}
