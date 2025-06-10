package com.project.demo.controller;

import com.project.demo.entity.Adopter;
import com.project.demo.service.AdopterService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *领养者：(Adopter)表控制层
 *
 */
@RestController
@RequestMapping("/adopter")
public class AdopterController extends BaseController<Adopter,AdopterService> {

    /**
     *领养者对象
     */
    @Autowired
    public AdopterController(AdopterService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> mapadopter_number = new HashMap<>();
        mapadopter_number.put("adopter_number",String.valueOf(paramMap.get("adopter_number")));
        List listadopter_number = service.selectBaseList(service.select(mapadopter_number, new HashMap<>()));
        if (listadopter_number.size()>0){
            return error(30000, "字段领养者编号内容不能重复");
        }
        this.addMap(paramMap);
        return success(1);
    }

}
