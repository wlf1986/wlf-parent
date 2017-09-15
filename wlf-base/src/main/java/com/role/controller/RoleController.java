package  com.role.controller;

import com.common.MappingTest;
import com.role.entity.Role;
import com.role.service.RoleService;
import com.utils.JsonUtil;
import com.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/role")
public class RoleController {


    @Autowired
    private RoleService roleService;

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
    @GetMapping("/getRole")
    public String getRole(Page page) {
        return JsonUtil.success(roleService.getRole());
    }

    /**
     * 添加对象
     *
     * @param role
     *
     * @return
     */
    @PutMapping("/addRole")
    public String addRole(Role role) {
        return JsonUtil.success(roleService.addRole(role));
    }

    /**
     * 删除对象
     *
     * @param id
     *
     * @return
     */
    @DeleteMapping("/deleterole/{id}")
    public String deleteRole(@PathVariable("id") Long id) {
        return JsonUtil.success(roleService.deleteRole(id));
    }

    /**
     * 更新对象
     *
     * @param role
     *
     * @return
     */
    @PostMapping("/updateRole")
    public String updateRole(Role role) {
        return JsonUtil.success(roleService.updateRoleById(role));
    }

    /**
     * 获取对象
     *
     * @param id
     *
     * @return
     */
    @GetMapping("/getRole/{id}")
    public String getRoleById(@PathVariable("id") Long id) {
        return JsonUtil.success(roleService.getRoleById(id));
    }
}
