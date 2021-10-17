package com.ljc.member.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljc.common.dto.auth.User;
import com.ljc.member.entity.Member;
import com.ljc.member.mapper.MemberMapper;
import com.ljc.member.service.IMemberService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    @Override
    public User loadUserByUsername(String username) {
        Member member = getOne(Wrappers.<Member>lambdaQuery().eq(Member::getUsername, username));
        if (member == null)
            return null;
        User user = new User();
        user.setUsername(member.getUsername());
        user.setPassword(member.getPassword());
        Map<String, Object> additionalInformation = new HashMap<>();
        additionalInformation.put("user_id", member.getId());
        additionalInformation.put("integration", member.getIntegration());
        user.setAdditionalInformation(additionalInformation);
        return user;
    }

}
