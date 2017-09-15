package  com.reuserrole.controller;


import com.common.MappingTest;
import com.reuserrole.entity.ReUserRole;
import com.reuserrole.service.ReUserRoleService;
import com.utils.JsonUtil;
import com.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/reUserRole")
public class ReUserRoleController {


    @Autowired
    private ReUserRoleService reUserRoleService;

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
    @GetMapping("/getReUserRole")
    public String getReUserRole(Page page) {
        return JsonUtil.success(reUserRoleService.getReUserRole());
    }

    /**
     * 添加对象
     *
     * @param reUserRole
     *
     * @return
     */
    @PutMapping("/addReUserRole")
    public String addReUserRole(ReUserRole reUserRole) {
        return JsonUtil.success(reUserRoleService.addReUserRole(reUserRole));
    }

    /**
     * 删除对象
     *
     * @param id
     *
     * @return
     */
    @DeleteMapping("/deletereUserRole/{id}")
    public String deleteReUserRole(@PathVariable("id") Long id) {
        return JsonUtil.success(reUserRoleService.deleteReUserRole(id));
    }

    /**
     * 更新对象
     *
     * @param reUserRole
     *
     * @return
     */
    @PostMapping("/updateReUserRole")
    public String updateReUserRole(ReUserRole reUserRole) {
        return JsonUtil.success(reUserRoleService.updateReUserRoleById(reUserRole));
    }

    /**
     * 获取对象
     *
     * @param id
     *
     * @return
     */
    @GetMapping("/getReUserRole/{id}")
    public String getReUserRoleById(@PathVariable("id") Long id) {
        return JsonUtil.success(reUserRoleService.getReUserRoleById(id));
    }
}
