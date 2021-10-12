package com.ljc.member.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ljc.common.dto.UserDto;
import com.ljc.member.entity.Member;
import com.ljc.member.mapper.MemberMapper;
import com.ljc.member.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-12
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    @Override
    public UserDto loadUserByUsername(String username) {
        Member member = getOne(Wrappers.<Member>lambdaQuery().eq(Member::getUsername, username));
        UserDto userDto = new UserDto();
        userDto.setId(member.getId());
        userDto.setUsername(member.getUsername());
        userDto.setPassword(member.getPassword());
        userDto.setRoles(new ArrayList<>());
        return userDto;
    }

}
