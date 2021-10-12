package com.ljc.member.service.impl;

import com.ljc.member.entity.MemberProductCategoryRelation;
import com.ljc.member.mapper.MemberProductCategoryRelationMapper;
import com.ljc.member.service.IMemberProductCategoryRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员与产品分类关系表（用户喜欢的分类） 服务实现类
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-12
 */
@Service
public class MemberProductCategoryRelationServiceImpl extends ServiceImpl<MemberProductCategoryRelationMapper, MemberProductCategoryRelation> implements IMemberProductCategoryRelationService {

}
