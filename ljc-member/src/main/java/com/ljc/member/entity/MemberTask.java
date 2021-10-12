package com.ljc.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 会员任务表
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-12
 */
@TableName("ums_member_task")
public class MemberTask implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    /**
     * 赠送成长值
     */
    private Integer growth;

    /**
     * 赠送积分
     */
    private Integer intergration;

    /**
     * 任务类型：0->新手任务；1->日常任务
     */
    private Integer type;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrowth() {
        return growth;
    }

    public void setGrowth(Integer growth) {
        this.growth = growth;
    }

    public Integer getIntergration() {
        return intergration;
    }

    public void setIntergration(Integer intergration) {
        this.intergration = intergration;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MemberTask{" +
        "id=" + id +
        ", name=" + name +
        ", growth=" + growth +
        ", intergration=" + intergration +
        ", type=" + type +
        "}";
    }
}
