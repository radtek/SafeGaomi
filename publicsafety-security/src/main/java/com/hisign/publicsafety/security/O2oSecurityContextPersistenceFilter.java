package com.hisign.publicsafety.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

/**
 * Populates the {@link SecurityContextHolder} with information obtained from
 * the configured {@link SecurityContextRepository} prior to the request and stores it back in the repository
 * once the request has completed and clearing the context holder. By default it uses an
 * {@link HttpSessionSecurityContextRepository}. See this class for information <tt>HttpSession</tt> related
 * configuration options.
 * <p>
 * This filter will only execute once per request, to resolve servlet container (specifically Weblogic)
 * incompatibilities.
 * <p>
 * This filter MUST be executed BEFORE any authentication processing mechanisms. Authentication processing mechanisms
 * (e.g. BASIC, CAS processing filters etc) expect the <code>SecurityContextHolder</code> to contain a valid
 * <code>SecurityContext</code> by the time they execute.
 * <p>
 * This is essentially a refactoring of the old <tt>HttpSessionContextIntegrationFilter</tt> to delegate
 * the storage issues to a separate strategy, allowing for more customization in the way the security context is
 * maintained between requests.
 * <p>
 * The <tt>forceEagerSessionCreation</tt> property can be used to ensure that a session is always available before
 * the filter chain executes (the default is <code>false</code>, as this is resource intensive and not recommended).
 *
 * @author Luke Taylor
 * @since 3.0
 */
@Service("o2oSecurityContextPersistenceFilter")
public class O2oSecurityContextPersistenceFilter extends SecurityContextPersistenceFilter {

	static final String FILTER_APPLIED = "__spring_security_scpf_applied";

	@Autowired
	private SecurityContextRepository cacheSecurityContextRepo;

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if (request.getAttribute(FILTER_APPLIED) != null) {
			// ensure that filter is only applied once per request
			chain.doFilter(request, response);
			return;
		}

		final boolean debug = logger.isDebugEnabled();

		request.setAttribute(FILTER_APPLIED, Boolean.TRUE);

		HttpRequestResponseHolder holder = new HttpRequestResponseHolder(request, response);
		SecurityContext contextBeforeChainExecution = cacheSecurityContextRepo.loadContext(holder);

		try {
			SecurityContextHolder.setContext(contextBeforeChainExecution);

			chain.doFilter(holder.getRequest(), holder.getResponse());

		} finally {
			SecurityContext contextAfterChainExecution = SecurityContextHolder.getContext();
			// Crucial removal of SecurityContextHolder contents - do this before anything else.
			SecurityContextHolder.clearContext();
			cacheSecurityContextRepo.saveContext(contextAfterChainExecution, holder.getRequest(), holder.getResponse());
			request.removeAttribute(FILTER_APPLIED);

			if (debug) {
				logger.debug("SecurityContextHolder now cleared, as request processing completed");
			}
		}
	}
}
