
package  com.reroleresource.service.impl;

import com.common.PageUtil;
import  com.reroleresource.entity.ReRoleResources;
import  com.reroleresource.entity.ReRoleResourcesExample;
import  com.reroleresource.mapper.ReRoleResourcesMapper;
import  com.reroleresource.service.ReRoleResourcesService;
import com.utils.BlankUtil;
import com.utils.Page;
import com.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReRoleResourcesServiceImpl implements ReRoleResourcesService {


    @Autowired
    private ReRoleResourcesMapper reRoleResourcesMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public int addReRoleResources(ReRoleResources reRoleResources) {
        ReRoleResources u = RedisUtil.add(reRoleResources, redisTemplate, "id");
        return reRoleResourcesMapper.insert(u);
    }

    @Override
    public int deleteReRoleResources(Long id) {
        RedisUtil.delete(redisTemplate, id, ReRoleResources.class);
        return reRoleResourcesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateReRoleResourcesById(ReRoleResources reRoleResources) {
        RedisUtil.update(reRoleResources, redisTemplate, "id");
        return reRoleResourcesMapper.updateByPrimaryKey(reRoleResources);
    }

    @Override
    public List<ReRoleResources> getReRoleResources() {
        List<ReRoleResources> list = RedisUtil.getList(redisTemplate, ReRoleResources.class);
        if (BlankUtil.isNotBlank(list))
            return  list;
        Page page = PageUtil.getPage();
        ReRoleResourcesExample reRoleResourcesExample = new ReRoleResourcesExample();
        reRoleResourcesExample.setPageEnd(page.getPageStar()+page.getPageSize()-1);
        reRoleResourcesExample.setPageStar(page.getPageStar());
        return  reRoleResourcesMapper.selectByExample(reRoleResourcesExample);
//        return reRoleResourcesMapper.getReRoleResources();
    }


    @Override
    public ReRoleResources getReRoleResourcesById(Long id) {
        /**
         * @author wlf
         * @date 2017年08月21日 18:32
         * @param [id]
         * @return  com.reroleresource.entity.ReRoleResources
         */
        ReRoleResources reRoleResources = RedisUtil.getOne(id, redisTemplate, ReRoleResources.class);
        return reRoleResources == null ? reRoleResourcesMapper.selectByPrimaryKey(id) : reRoleResources;
    }
}
