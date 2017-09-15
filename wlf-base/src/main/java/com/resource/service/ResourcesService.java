package  com.resource.service;

import  com.resource.entity.Resources;

import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */
public interface ResourcesService {

    int addResources(Resources resources);

    int deleteResources(Long id);

    int updateResourcesById(Resources resources);

    List<Resources> getResources();

    Resources getResourcesById(Long id);
}
