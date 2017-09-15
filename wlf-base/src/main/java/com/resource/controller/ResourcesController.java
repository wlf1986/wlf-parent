package  com.resource.controller;


import com.common.MappingTest;
import com.resource.entity.Resources;
import com.resource.service.ResourcesService;
import com.utils.JsonUtil;
import com.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/resources")
public class ResourcesController {


    @Autowired
    private ResourcesService resourcesService;

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
    @GetMapping("/getResources")
    public String getResources(Page page) {
        return JsonUtil.success(resourcesService.getResources());
    }

    /**
     * 添加对象
     *
     * @param resources
     *
     * @return
     */
    @PutMapping("/addResources")
    public String addResources(Resources resources) {
        return JsonUtil.success(resourcesService.addResources(resources));
    }

    /**
     * 删除对象
     *
     * @param id
     *
     * @return
     */
    @DeleteMapping("/deleteresources/{id}")
    public String deleteResources(@PathVariable("id") Long id) {
        return JsonUtil.success(resourcesService.deleteResources(id));
    }

    /**
     * 更新对象
     *
     * @param resources
     *
     * @return
     */
    @PostMapping("/updateResources")
    public String updateResources(Resources resources) {
        return JsonUtil.success(resourcesService.updateResourcesById(resources));
    }

    /**
     * 获取对象
     *
     * @param id
     *
     * @return
     */
    @GetMapping("/getResources/{id}")
    public String getResourcesById(@PathVariable("id") Long id) {
        return JsonUtil.success(resourcesService.getResourcesById(id));
    }
}
