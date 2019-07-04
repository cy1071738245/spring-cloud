package com.cy.joy.wx.handler;

import com.cy.joy.mapper.UsersMapper;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class UnsubscribeHandler extends AbstractHandler {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        String openId = wxMessage.getFromUser();
        this.logger.info("取消关注用户 OPENID: " + openId);
        // TODO 可以更新本地数据库为取消关注状态
        usersMapper.deleteUser(openId);
        return null;
    }

}
