package com.hisign.publicsafety.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import com.hisign.publicsafety.common.SpringHolder;
import com.hisign.publicsafety.entity.Resources;
import com.hisign.publicsafety.mapper.ResourcesMapper;

/**
 * 加载资源与权限的对应关系
 * @author ly
 * 2013-11-19
 * 
 * @version 1.0v
 * */
@Service
public class DbSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	@Autowired
	private ResourcesMapper menuMapper;
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	/**
	 * @PostConstruct是Java EE 5引入的注解，
	 * Spring允许开发者在受管Bean中使用它。当DI容器实例化当前受管Bean时，
	 * @PostConstruct注解的方法会被自动触发，从而完成一些初始化工作，
	 * 
	 * //加载所有资源与权限的关系
	 */
	@PostConstruct
	private void loadResourceDefine() {
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<Resources> menus = menuMapper.queryAll(new Resources());
			for (Resources m : menus) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				// TODO:ZZQ 通过资源名称来表示具体的权限 注意：必须"RES_"开头
				// 关联代码：applicationContext-security.xml
				// 关联代码：UserDetailServiceImpl#obtionGrantedAuthorities
				ConfigAttribute configAttribute = new SecurityConfig("RES_" + m.getResKey());
				configAttributes.add(configAttribute);
				resourceMap.put(m.getResUrl(), configAttributes);
			}
		}
	}

	//返回所请求资源所需要的权限
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		if ("/findAuthority.html".equals(requestUrl)) {
			return null;
		}
		if (resourceMap == null) {
			loadResourceDefine();
		}
		Collection<ConfigAttribute> configAttributes = null;
		//去除?
		int paraIdx = requestUrl.indexOf("?");
		if (paraIdx > -1) {
			requestUrl = requestUrl.substring(0, paraIdx);
		}
		configAttributes = resourceMap.get(requestUrl);
		return configAttributes;
	}

}
