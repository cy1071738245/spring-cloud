package com.cy.joy.controller;

import com.cy.joy.enums.ResultState;
import com.cy.joy.enums.UsersState;
import com.cy.joy.pojo.Users;
import com.cy.joy.service.CartClientService;
import com.cy.joy.service.UsersService;
import com.cy.joy.vo.CartVo;
import com.cy.joy.vo.ResultVo;
import com.cy.joy.wx.pojo.LoginParams;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CallBackController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private CartClientService cartClientService;

    /**
     * 授权回调登录
     * @param loginParams
     * @return
     * @throws WxErrorException
     */
    @PostMapping("/wxCallBack")
    public ResultVo<List<CartVo>> wxCallBack(@RequestBody LoginParams loginParams) throws WxErrorException {
        ResultVo<List<CartVo>> resultVo = new ResultVo<>();
        //判断是否已授权
        if(this.isGranted(loginParams.getCode()) != null){
            Users user = this.isGranted(loginParams.getCode());
            List<CartVo> result = cartClientService.MergerOfCartsService(loginParams.getCarts(), user.getUserId());
            resultVo.setCode(ResultState.SUCCESS.getCode())
                    .setMsg("已登录")
                    .setCurrentUserId(user.getUserId())
                    .setResult(result);
            return resultVo;
        }
        //使用code换取token
        WxMpOAuth2AccessToken oAuth2AccessToken = wxMpService.oauth2getAccessToken(loginParams.getCode());
        //使用token换取用户信息
        WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(oAuth2AccessToken, "zh_CN");
        Users user = usersService.getOpenIdService(wxMpUser.getOpenId());
        if(user != null && user.getUserState() == UsersState.NORMAL.getCode()){
            this.saveInRedis(loginParams.getCode(), user);
            //购物车数据同步
            List<CartVo> result = cartClientService.MergerOfCartsService(loginParams.getCarts(), user.getUserId());
            resultVo.setCode(ResultState.SUCCESS.getCode())
                    .setMsg("登录成功")
                    .setCurrentUserId(user.getUserId())
                    .setResult(result);
            return resultVo;
        }else if(user != null && user.getUserState() == UsersState.FORBIDDEN.getCode()){
            resultVo.setCode(ResultState.ERROR.getCode())
                    .setMsg("已禁用");
            return resultVo;
        }else if(user != null && user.getUserState() == UsersState.DELETE.getCode()){
            usersService.recoverUserService(wxMpUser.getOpenId());
            this.saveInRedis(loginParams.getCode(), user);
            resultVo.setCode(ResultState.SUCCESS.getCode())
                    .setMsg("已成功为您注册")
                    .setCurrentUserId(user.getUserId());
            return resultVo;
        }else {
            usersService.addUserService(new Users().setUserName(wxMpUser.getNickname())
                    .setUserImg(wxMpUser.getHeadImgUrl())
                    .setOpenId(wxMpUser.getOpenId()));
            this.saveInRedis(loginParams.getCode(), user);
            resultVo.setCode(ResultState.SUCCESS.getCode())
                    .setMsg("已成功为您注册")
                    .setCurrentUserId(user.getUserId());
            return resultVo;
        }
    }

    /**
     * 检测是否已经授权
     * @param code
     * @return
     */
    private Users isGranted(String code){
        Users user = usersService.getUserInRedis(code);
        if(user != null){
            return user;
        }
        return null;
    }

    /**
     * 授权后用户信息保存
     * @param code
     * @param user
     */
    private void saveInRedis(String code, Users user){
        usersService.setUserInRedis(code,user);
    }

}
