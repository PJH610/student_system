package com.letopo.wxuser.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 微信用户信息
 * </p>
 *
 * @author jh
 * @since 2020-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WxUser对象", description="微信用户信息")
public class WxUser implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * open_id
     */
    @TableId(value = "open_id",type = IdType.INPUT)
    private String openId;
    /**
     * skey
     */
    private String skey;
    /**
     * 创建时间
     */
    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    /**
     * 最后登录时间
     */
    @TableField("last_visit_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastVisitTime;
    /**
     * session_key
     */
    @TableField("session_key")
    private String sessionKey;
    /**
     * 市
     */
    @TableField("city")
    private String city;
    /**
     * 省
     */
    @TableField("province")
    private String province;
    /**
     * 国
     */
    @TableField("country")
    private String country;
    /**
     * 头像
     */
    @TableField("avatar_url")
    private String avatarUrl;
    /**
     * 性别
     */
    @TableField("gender")
    private Integer gender;
    /**
     * 网名
     */
    @TableField("nick_name")
    private String nickName;

}