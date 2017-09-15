package com.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author 王岚枫
 * @Datetime 2017年08月20日 20:52
 */
@Controller
public class MappingTest {


    public void Tset(Object o){
        List<String> pathList = new ArrayList<String>();
        String path = null;
        Class c = o.getClass();
        RequestMapping requestMapping = (RequestMapping) c.getAnnotation(RequestMapping.class);
        boolean b = c.isAnnotationPresent(RequestMapping.class);
        String[] value = requestMapping.value();
//        RequestMethod[] requestMethods = requestMapping.method();
//        String[] consumes = requestMapping.consumes();
//        String[] path1 = requestMapping.path();
//        String[] params = requestMapping.params();
//        String[] produces = requestMapping.produces();


        path = value[0];
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            boolean b1 = method.isAnnotationPresent(RequestMapping.class);
            boolean b2 = method.isAnnotationPresent(GetMapping.class);
            boolean b3 = method.isAnnotationPresent(PutMapping.class);
            boolean b4 = method.isAnnotationPresent(DeleteMapping.class);
            boolean b5 = method.isAnnotationPresent(PostMapping.class);
            int i = b1 == true ? 1 : b2 == true ? 2 : b3 == true ? 3 : b4 == true ? 4 : b5 == true ? 5 : 0;
            if (b1||b2||b3||b4||b5){
                String[] value1 = null;
                switch (i){
                    case 1: RequestMapping mapping1 = method.getAnnotation(RequestMapping.class);
                            value1 = mapping1.value();
                            break;
                    case 2: GetMapping mapping2 = method.getAnnotation(GetMapping.class);
                            value1 = mapping2.value();
                            break;
                    case 3: PutMapping mapping3 = method.getAnnotation(PutMapping.class);
                            value1 = mapping3.value();
                            break;
                    case 4: DeleteMapping mapping4 = method.getAnnotation(DeleteMapping.class);
                            value1 = mapping4.value();
                            break;
                    case 5: PostMapping mapping5 = method.getAnnotation(PostMapping.class);
                            value1 = mapping5.value();
                            break;
                }

                System.out.println(Arrays.toString(value1));
                pathList.add(path+value1[0]);
            }
        }
        System.out.println(111111);

    }

}
