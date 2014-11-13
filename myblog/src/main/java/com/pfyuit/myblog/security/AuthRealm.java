package com.pfyuit.myblog.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfyuit.myblog.domain.User;
import com.pfyuit.myblog.service.UserService;

@Service
public class AuthRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		String principal = (String) arg0.getPrincipal();
		String credential = new String((char[]) arg0.getCredentials());

		boolean isAuthenticated = false;
		List<User> users = userService.findAll();
		for (User user : users) {
			if (user.getUserName().equals(principal) && user.getPassword().equals(credential)) {
				isAuthenticated = true;
			}
		}
		if (isAuthenticated) {
			return new SimpleAuthenticationInfo(principal, credential, getName());
		} else {
			throw new UnknownAccountException();
		}
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		String principal = (String) arg0.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		if (principal.equals("admin")) {
			Set<String> roles = new HashSet<String>();
			roles.add("Administrator");
			info.setRoles(roles);
			info.addStringPermission("resource:item:add,delete,update,view");
		}
		return info;
	}

}
