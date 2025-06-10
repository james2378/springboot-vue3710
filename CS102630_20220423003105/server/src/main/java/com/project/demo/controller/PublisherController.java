package com.project.demo.controller;

import com.project.demo.entity.Publisher;
import com.project.demo.service.PublisherService;
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
 *发布者：(Publisher)表控制层
 *
 */
@RestController
@RequestMapping("/publisher")
public class PublisherController extends BaseController<Publisher,PublisherService> {

    /**
     *发布者对象
     */
    @Autowired
    public PublisherController(PublisherService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> mappublisher_number = new HashMap<>();
        mappublisher_number.put("publisher_number",String.valueOf(paramMap.get("publisher_number")));
        List listpublisher_number = service.selectBaseList(service.select(mappublisher_number, new HashMap<>()));
        if (listpublisher_number.size()>0){
            return error(30000, "字段发布者编号内容不能重复");
        }
        this.addMap(paramMap);
        return success(1);
    }

}
