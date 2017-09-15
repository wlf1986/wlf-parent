
package  com.reuserrole.service.impl;

import com.common.PageUtil;
import  com.reuserrole.entity.ReUserRole;
import  com.reuserrole.entity.ReUserRoleExample;
import  com.reuserrole.mapper.ReUserRoleMapper;
import  com.reuserrole.service.ReUserRoleService;
import com.utils.BlankUtil;
import com.utils.Page;
import com.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReUserRoleServiceImpl implements ReUserRoleService {


    @Autowired
    private ReUserRoleMapper reUserRoleMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public int addReUserRole(ReUserRole reUserRole) {
        ReUserRole u = RedisUtil.add(reUserRole, redisTemplate, "id");
        return reUserRoleMapper.insert(u);
    }

    @Override
    public int deleteReUserRole(Long id) {
        RedisUtil.delete(redisTemplate, id, ReUserRole.class);
        return reUserRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateReUserRoleById(ReUserRole reUserRole) {
        RedisUtil.update(reUserRole, redisTemplate, "id");
        return reUserRoleMapper.updateByPrimaryKey(reUserRole);
    }

    @Override
    public List<ReUserRole> getReUserRole() {
        List<ReUserRole> list = RedisUtil.getList(redisTemplate, ReUserRole.class);
        if (BlankUtil.isNotBlank(list))
            return  list;
        Page page = PageUtil.getPage();
        ReUserRoleExample reUserRoleExample = new ReUserRoleExample();
        reUserRoleExample.setPageEnd(page.getPageStar()+page.getPageSize()-1);
        reUserRoleExample.setPageStar(page.getPageStar());
        return  reUserRoleMapper.selectByExample(reUserRoleExample);
//        return reUserRoleMapper.getReUserRole();
    }


    @Override
    public ReUserRole getReUserRoleById(Long id) {
        /**
         * @author wlf
         * @date 2017年08月21日 18:32
         * @param [id]
         * @return  com.reuserrole.entity.ReUserRole
         */
        ReUserRole reUserRole = RedisUtil.getOne(id, redisTemplate, ReUserRole.class);
        return reUserRole == null ? reUserRoleMapper.selectByPrimaryKey(id) : reUserRole;
    }
}
