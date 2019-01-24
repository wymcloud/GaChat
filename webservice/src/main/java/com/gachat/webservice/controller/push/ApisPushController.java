package com.gachat.webservice.controller.push;

import com.gachat.webservice.controller.base.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 喝咖啡的囊地鼠
 * @date 2017/9/14 下午10:57
 */


@RestController
@RequestMapping(value = "/apis/push")
public class ApisPushController extends BaseController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity pushOne(@RequestParam String message) {
        userPushService.pushAllUserMessage(message);
        return ResponseEntity.ok(null);
    }

}
