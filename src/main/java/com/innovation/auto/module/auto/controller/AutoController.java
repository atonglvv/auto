package com.innovation.auto.module.auto.controller;

import com.innovation.auto.entity.MotorInfo;
import com.innovation.auto.model.APIResult;
import com.innovation.auto.module.auto.service.AutoService;
import com.innovation.auto.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: carver
 * @Date: 2018/12/24 22:42
 * @email lt1550919167@163.com
 * @QQ 1550919167
 * @Description:
 */
@RestController
public class AutoController {

    @Autowired
    private AutoService autoService;


    /**
     * 查询汽车信息/all/byCountry
     * @return
     */
    @GetMapping("/queryAuto")
    public APIResult queryAuto(@RequestParam(value = "countryId",required = false) Integer countryId) {
        APIResult apiResult = new APIResult();
        apiResult.setMsg("ok");
        apiResult.setStatus(Constants.SUCCESS);

        Map<String,Object> map = new HashMap<>();
        map.put("countryId",countryId);
        List<MotorInfo> motorInfos = autoService.selectAuto(map);


        apiResult.setRes(motorInfos);
        apiResult.setStatus(Constants.SUCCESS);
        apiResult.setMsg("query success");
        return apiResult;
    }

}