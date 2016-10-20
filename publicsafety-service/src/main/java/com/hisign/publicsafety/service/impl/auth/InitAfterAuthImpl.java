/**
 * 
 */
package com.hisign.publicsafety.service.impl.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.common.api.CacheSessionService;
import com.hisign.publicsafety.entity.Account;
import com.hisign.publicsafety.entity.Roles;
import com.hisign.publicsafety.service.RolesService;
import com.hisign.publicsafety.service.auth.InitAfterAuth;
import com.hisign.publicsafety.util.WebUtil;

/**
 * 认证成功之后的初始化服务实现
 * @author chailiangzhi
 *
 */
@Service("initAfterAuth")
public class InitAfterAuthImpl implements InitAfterAuth {

	@Autowired
	private RolesService roleService;
	@Autowired
	private CacheSessionService cacheSessionService;

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.auth.InitAfterAuth#initLoginUserRelated(org.springframework.security.core.context.SecurityContext)
	 */
	@Override
	public boolean initLoginUserRelated(SecurityContext securityContext, Account user) throws Exception {
		// Spring Security上下文放入缓存
		cacheSessionService.setAttribute(Constant.SPRING_SECURITY_CONTEXT_CACHE, securityContext);
		// 保存授权列表到缓存
		WebUtil.saveAuthorityStrs(securityContext);
		// 把用户角色放在session里
		Roles role = roleService.findbyAccountRole(String.valueOf(user.getId()));
		cacheSessionService.setAttribute(Constant.LOGIN_USER_ROLE_KEY, role.getRoleKey());
		// 把用户信息放在session里
		cacheSessionService.setAttribute(Constant.LOGIN_USER_ENTITY, user);
		cacheSessionService.setAttribute(Constant.LOGIN_USER_ID, String.valueOf(user.getId()));
		cacheSessionService.setAttribute(Constant.LOGIN_USER_UNIT, user.getUnitCode()); //用户单位
		return true;
	}

}
