package com.online.edu.ucenter.service;

import com.online.edu.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-03-01
 */
public interface MemberService extends IService<Member> {

    int countRegisterNum(String day);
}
