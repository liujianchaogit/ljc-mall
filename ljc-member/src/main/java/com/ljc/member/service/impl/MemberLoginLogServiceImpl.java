package com.ljc.member.service.impl;

import com.ljc.member.entity.MemberLoginLog;
import com.ljc.member.mapper.MemberLoginLogMapper;
import com.ljc.member.service.IMemberLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员登录记录 服务实现类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-12
 */
@Service
public class MemberLoginLogServiceImpl extends ServiceImpl<MemberLoginLogMapper, MemberLoginLog> implements IMemberLoginLogService {

}
