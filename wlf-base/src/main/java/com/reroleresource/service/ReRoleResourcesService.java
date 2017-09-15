package  com.reroleresource.service;

import  com.reroleresource.entity.ReRoleResources;

import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */
public interface ReRoleResourcesService {

    int addReRoleResources(ReRoleResources reRoleResources);

    int deleteReRoleResources(Long id);

    int updateReRoleResourcesById(ReRoleResources reRoleResources);

    List<ReRoleResources> getReRoleResources();

    ReRoleResources getReRoleResourcesById(Long id);
}
