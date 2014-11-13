package com.pfyuit.myblog.security;

import java.util.HashSet;
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
import org.springframework.stereotype.Service;

@Service
public class AuthRealm extends AuthorizingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		String principal = (String) arg0.getPrincipal();
		String credential = new String((char[]) arg0.getCredentials());
		if ((principal.equals("root") && credential.equals("apidoc123")) || (principal.equals("admin") && credential.equals("apidoc123"))
				|| (principal.equals("user") && credential.equals("apidoc123"))) {
			return new SimpleAuthenticationInfo(principal, credential, getName());
		} else {
			throw new UnknownAccountException();
		}
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		String principal = (String) arg0.getPrimaryPrincipal();

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		if (principal.equals("root")) {
			Set<String> roles = new HashSet<String>();
			roles.add("Super Administrator");
			info.setRoles(roles);
			info.addStringPermission("resource:item:add,delete,update,view");
		} else if (principal.equals("admin")) {
			Set<String> roles = new HashSet<String>();
			roles.add("Administrator");
			info.setRoles(roles);
			info.addStringPermission("resource:item:update,view");
		} else if (principal.equals("user")) {
			Set<String> roles = new HashSet<String>();
			roles.add("Normal User");
			info.setRoles(roles);
			info.addStringPermission("resource:item:view");
		}

		return info;
	}

}
