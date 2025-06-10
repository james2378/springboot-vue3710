package com.project.demo.controller;

import com.project.demo.entity.PetKnowledge;
import com.project.demo.service.PetKnowledgeService;
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
 *宠物知识：(PetKnowledge)表控制层
 *
 */
@RestController
@RequestMapping("/pet_knowledge")
public class PetKnowledgeController extends BaseController<PetKnowledge,PetKnowledgeService> {

    /**
     *宠物知识对象
     */
    @Autowired
    public PetKnowledgeController(PetKnowledgeService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
