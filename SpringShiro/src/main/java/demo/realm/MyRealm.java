package demo.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import demo.entity.User;
import demo.service.UserService;
/**
 * 
 * @author Minzhe Xu	2017年3月31日下午5:54:49
 *shiro提供了抽象类 AuthenticatingRealm 专门用于从Realm中获得认证信息。
 *所以我们可以继承 抽象类 AuthenticatingRealm，然后实现其中的抽象方法
 *我们也可以使用 AuthenticatingRealm 的子类 AuthorizingRealm，
 *它本来是用于权限认证的Realm，但是因为他继承了 AuthenticatingRealm，
 *所以实际上我们只要继承 AuthorizingRealm，然后实现它的抽象方法就行了。
 *同时搞定 登录认证 和 权限认证(访问控制)
 */
public class MyRealm extends AuthorizingRealm {
	
	@Autowired
    private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal(); //获取用户名
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.getRoles(username));
        authorizationInfo.setStringPermissions(userService.getPermissions(username));
        return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		 String username = (String) token.getPrincipal(); // 获取用户名
	        User user = userService.getByUsername(username);
	        if(user != null) {
	            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "myRealm");
	            return authcInfo;
	        } else {
	            return null;
	        }       
	}

}
