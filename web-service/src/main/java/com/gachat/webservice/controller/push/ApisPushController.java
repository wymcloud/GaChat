package com.gachat.webservice.controller.push;

import com.gachat.webservice.controller.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "推送接口", description = "一对一、一对多推送", tags = "push")
@RestController
@RequestMapping(value = "/apis/push")
public class ApisPushController extends BaseController {

    @ApiOperation(value = "推送接口",
            notes = "推送接口",
            httpMethod = "GET",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity pushOne(@RequestParam String message) {
        userPushService.pushAllUserMessage(message);
        return ResponseEntity.ok(null);
    }

}
