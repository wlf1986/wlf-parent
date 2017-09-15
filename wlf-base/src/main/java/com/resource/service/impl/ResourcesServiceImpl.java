
package  com.resource.service.impl;

import com.common.PageUtil;
import  com.resource.entity.Resources;
import  com.resource.entity.ResourcesExample;
import  com.resource.mapper.ResourcesMapper;
import  com.resource.service.ResourcesService;
import com.utils.BlankUtil;
import com.utils.Page;
import com.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourcesServiceImpl implements ResourcesService {


    @Autowired
    private ResourcesMapper resourcesMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public int addResources(Resources resources) {
        Resources u = RedisUtil.add(resources, redisTemplate, "id");
        return resourcesMapper.insert(u);
    }

    @Override
    public int deleteResources(Long id) {
        RedisUtil.delete(redisTemplate, id, Resources.class);
        return resourcesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateResourcesById(Resources resources) {
        RedisUtil.update(resources, redisTemplate, "id");
        return resourcesMapper.updateByPrimaryKey(resources);
    }

    @Override
    public List<Resources> getResources() {
        List<Resources> list = RedisUtil.getList(redisTemplate, Resources.class);
        if (BlankUtil.isNotBlank(list))
            return  list;
        Page page = PageUtil.getPage();
        ResourcesExample resourcesExample = new ResourcesExample();
        resourcesExample.setPageEnd(page.getPageStar()+page.getPageSize()-1);
        resourcesExample.setPageStar(page.getPageStar());
        return  resourcesMapper.selectByExample(resourcesExample);
//        return resourcesMapper.getResources();
    }


    @Override
    public Resources getResourcesById(Long id) {
        /**
         * @author wlf
         * @date 2017年08月21日 18:32
         * @param [id]
         * @return  com.resource.entity.Resources
         */
        Resources resources = RedisUtil.getOne(id, redisTemplate, Resources.class);
        return resources == null ? resourcesMapper.selectByPrimaryKey(id) : resources;
    }
}
