package com.yd.taozi.shiro;

import com.yd.taozi.pojo.User;
import com.yd.taozi.service.UserPermissionService;
import com.yd.taozi.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.ws.Service;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by xiaotaozi on 2019/6/13.
 */
public class MyRealm extends AuthorizingRealm {
    //注入业务接口
    @Autowired
    private UserService userService;
    //注入权限接口
    @Autowired
    private UserPermissionService userPermissionService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //*授权 权限验证
        //获取用户名
        Object principal = principalCollection.getPrimaryPrincipal();
        String username = (String) principal;
        //根据用户名查询用户权限
        Set<User> userName = userPermissionService.findUserName(username);
        Set<String> users = new HashSet<>();
        if (userName!=null){
            for (User user:userName){
                users.add(user.getUname());
            }
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(users);
        return authorizationInfo;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       //*认证登录
        //获取用户名
        String principal = (String) authenticationToken.getPrincipal();
        User userByInUname = userService.getUserByInUname(principal);

        SimpleAuthenticationInfo authenticationInfo=null;
        if (userByInUname!=null){
            authenticationInfo=new SimpleAuthenticationInfo(
              principal,userByInUname.getUpw(),this.getName()
            );
        }
        return authenticationInfo;
    }
}
