package com.letopo.wxuser.service.impl;

import com.letopo.wxuser.entity.WxUser;
import com.letopo.wxuser.mapper.WxUserMapper;
import com.letopo.wxuser.service.IWxUserService;
import com.letopo.common.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 微信用户信息 服务实现类
 * </p>
 *
 * @author jh
 * @since 2020-03-28
 */
@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser> implements IWxUserService {

}
