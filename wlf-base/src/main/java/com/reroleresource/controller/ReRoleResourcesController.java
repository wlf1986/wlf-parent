package  com.reroleresource.controller;


import com.common.MappingTest;
import com.reroleresource.entity.ReRoleResources;
import com.reroleresource.service.ReRoleResourcesService;
import com.utils.JsonUtil;
import com.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/reRoleResources")
public class ReRoleResourcesController {


    @Autowired
    private ReRoleResourcesService reRoleResourcesService;

    @RequestMapping("/ann")
    public void getAnn() {
        MappingTest mappingTest = new MappingTest();
        mappingTest.Tset(this);
    }

    /**
     * 获取集合
     *
     * @return
     */
    @GetMapping("/getReRoleResources")
    public String getReRoleResources(Page page) {
        return JsonUtil.success(reRoleResourcesService.getReRoleResources());
    }

    /**
     * 添加对象
     *
     * @param reRoleResources
     *
     * @return
     */
    @PutMapping("/addReRoleResources")
    public String addReRoleResources(ReRoleResources reRoleResources) {
        return JsonUtil.success(reRoleResourcesService.addReRoleResources(reRoleResources));
    }

    /**
     * 删除对象
     *
     * @param id
     *
     * @return
     */
    @DeleteMapping("/deletereRoleResources/{id}")
    public String deleteReRoleResources(@PathVariable("id") Long id) {
        return JsonUtil.success(reRoleResourcesService.deleteReRoleResources(id));
    }

    /**
     * 更新对象
     *
     * @param reRoleResources
     *
     * @return
     */
    @PostMapping("/updateReRoleResources")
    public String updateReRoleResources(ReRoleResources reRoleResources) {
        return JsonUtil.success(reRoleResourcesService.updateReRoleResourcesById(reRoleResources));
    }

    /**
     * 获取对象
     *
     * @param id
     *
     * @return
     */
    @GetMapping("/getReRoleResources/{id}")
    public String getReRoleResourcesById(@PathVariable("id") Long id) {
        return JsonUtil.success(reRoleResourcesService.getReRoleResourcesById(id));
    }
}
