package com.bs.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bs.ucenter.feignclient.CheckCodeClient;
import com.bs.ucenter.mapper.XcUserMapper;
import com.bs.ucenter.feignclient.CheckCodeClient;
import com.bs.ucenter.model.dto.AuthParamsDto;
import com.bs.ucenter.model.dto.XcUserExt;
import org.apache.commons.lang.StringUtils;
import com.bs.ucenter.model.po.XcUser;
import com.bs.ucenter.service.AuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @description 账号密码认证
 * @author Mr.M
 * @date 2022/9/29 12:12
 * @version 1.0
 */
 @Service("password_authservice")
public class PasswordAuthServiceImpl implements AuthService {

 @Autowired
 XcUserMapper xcUserMapper;

 @Autowired
 PasswordEncoder passwordEncoder;

 @Autowired
 CheckCodeClient checkCodeClient;



 @Override
 public XcUserExt execute(AuthParamsDto authParamsDto) {

//  //校验验证码
//  String checkcode = authParamsDto.getCheckcode();
//  String checkcodekey = authParamsDto.getCheckcodekey();
//
//  if(StringUtils.isBlank(checkcodekey) || StringUtils.isBlank(checkcode)){
//   throw new RuntimeException("验证码为空");
//
//  }
//  Boolean verify = checkCodeClient.verify(checkcodekey, checkcode);
//  if(!verify){
//   throw new RuntimeException("验证码输入错误");
//  }


  //账号
  String username = authParamsDto.getUsername();
  XcUser user = xcUserMapper.selectOne(new LambdaQueryWrapper<XcUser>().eq(XcUser::getUsername, username));
  if(user==null){
   //返回空表示用户不存在
   throw new RuntimeException("账号不存在");
  }
  XcUserExt xcUserExt = new XcUserExt();
  BeanUtils.copyProperties(user,xcUserExt);
  //校验密码
  //取出数据库存储的正确密码
  String passwordDb  =user.getPassword();
  String passwordForm = authParamsDto.getPassword();
  boolean matches = passwordEncoder.matches(passwordForm, passwordDb);
  if(!matches){
   throw new RuntimeException("账号或密码错误");
  }
  return xcUserExt;
 }
}
