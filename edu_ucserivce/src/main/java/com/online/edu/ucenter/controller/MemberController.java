package com.online.edu.ucenter.controller;
import com.online.edu.common.R;
import com.online.edu.ucenter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-03-01
 */
@CrossOrigin
@RestController
@RequestMapping("/ucenter/member/")
public class MemberController {
    @Autowired
    private MemberService memberService;
     //统计某一天的提交
    @GetMapping("countRegisterNum/{day}")
    public R countRegisterNum(@PathVariable String day){
        int result=memberService.countRegisterNum(day);
        return R.ok().data("result",result);
    }
}

