package com.ljc.member.service;

import com.ljc.common.dto.UserDto;
import com.ljc.member.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-12
 */
public interface IMemberService extends IService<Member> {

    UserDto loadUserByUsername(String username);
}
