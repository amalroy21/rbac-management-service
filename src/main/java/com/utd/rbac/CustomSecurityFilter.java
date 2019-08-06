package com.utd.rbac;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.utd.rbac.data.dao.IRolesDao;
import com.utd.rbac.data.dao.IUsersDao;
import com.utd.rbac.data.model.Role;
import com.utd.rbac.data.model.User;
import com.utd.rbac.exception.InvalidUserEmailException;
import com.utd.rbac.exception.UserEmailNotFoundException;
import com.utd.rbac.util.AdminUtil;
import com.utd.rbac.util.BankerUtil;
import com.utd.rbac.util.Constants;
import com.utd.rbac.util.UserUtil;

@Component
public class CustomSecurityFilter extends OncePerRequestFilter{
	
	private IUsersDao usersDao;
	private IRolesDao rolesDao;
	
	@Autowired
	public CustomSecurityFilter(IUsersDao usersDao,IRolesDao rolesDao) {
		this.usersDao = usersDao;
		this.rolesDao = rolesDao;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(CustomSecurityFilter.class);

	/**
	 * This method is a customFilter which fetches user-email header from Request and thereafter fetches roles for the user and allows access for API's
	 * @param httpRequest {@link HttpServletRequest}
	 * @param httpResponse {@link HttpServletResponse}
	 * @param filterChain {@link FilterChain}
	 */
	@Override
	public void doFilterInternal(HttpServletRequest httpRequest, HttpServletResponse httpResponse, FilterChain filterChain)
			throws IOException, ServletException {
		logger.debug("Entering CustomSecurityFilter.doFilter() Method");
		
		Enumeration<String> headerAttributes = httpRequest.getHeaderNames();
		String requestURI = getFullURL(httpRequest);
		List<Role> RolesList = new ArrayList<>();
		boolean isValid = false;
		try {
			if (headerAttributes != null) {
				while (headerAttributes.hasMoreElements()) {
					
					ArrayList<String> headerValuesList=Collections.list(headerAttributes);
					
					if (headerValuesList.contains(Constants.HEADER_USER_EMAIL)) {
						Enumeration<String> headers = httpRequest.getHeaders(Constants.HEADER_USER_EMAIL);
						while (headers.hasMoreElements()) {
							String headerValue = headers.nextElement();
							User userRoles = usersDao.findByUserEmail(headerValue);
							if (null != userRoles) {
								if (!userRoles.getRoleIDs().isEmpty()) {
									for (String roleID : userRoles.getRoleIDs()) {
										if(!StringUtils.isBlank(roleID)) {
											Role role = rolesDao.findByRoleID(roleID);
											RolesList.add(role);
										}
									}
									for (Role roleNames : RolesList) {
										if (null != requestURI) {
											isValid = AllowAccessToApi(httpRequest, httpResponse, filterChain, requestURI, isValid,
													roleNames);
										}
									}
								}
							}							
							if (!isValid) {
								throw new InvalidUserEmailException("User Not Authorized");
							}
						}
					}else {
						boolean isValidURI = validateRequests(httpRequest,httpResponse,requestURI,filterChain);
						if(isValidURI) {
							throw new UserEmailNotFoundException(Constants.USER_EMAIL_MISSING);
						}
						else {
							filterChain.doFilter(httpRequest, httpResponse);
						}
					}
					
				}
			}
		} catch (InvalidUserEmailException invalidUserEmailException) {
			httpResponse.setContentType("application/json");
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			httpResponse.getOutputStream().println("{ \"error\": \"" + invalidUserEmailException.getMessage() + "\" }");
		} catch (UserEmailNotFoundException userEmailNotFoundException) {
			httpResponse.setContentType("application/json");
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			httpResponse.getOutputStream().println("{ \"error\": \"" + userEmailNotFoundException.getMessage() + "\" }");
		}
	}
	
	/**
	 * This method is used to validate Request URI's
	 * @param httpRequest {@link HttpServletRequest}
	 * @param httpResponse {@link HttpServletRequest}
	 * @param requestURI {@link String}
	 * @param chain {@link FilterChain}
	 * @return {@link Boolean}
	 * @throws IOException
	 * @throws ServletException
	 */
	private boolean validateRequests(HttpServletRequest httpRequest, HttpServletResponse httpResponse,String requestURI,FilterChain chain) throws IOException, ServletException {
		boolean isvalidURI = false;
		if(StringUtils.equals(requestURI,Constants.POST_ROLE_URL)) {
			isvalidURI = true;
		}
		if(StringUtils.equals(requestURI,Constants.GET_USERS_URL)) {
			isvalidURI = true;
		}
		if(StringUtils.equals(requestURI,Constants.POST_USER_URL)) {
			isvalidURI = true;
		}
		if(StringUtils.equals(requestURI,Constants.DELETE_USER_URL)) {
			isvalidURI = true;
		}
		return isvalidURI;
	}

	/**
	 * This method Allows access to URL's based on Roles.
	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @param chain {@link FilterChain}
	 * @param requestURI {@link String}
	 * @param isValid {@link boolean}
	 * @param roleNames {@link RoleNames}
	 * @return {@link Boolean}
	 * @throws IOException
	 * @throws ServletException
	 */
	private boolean AllowAccessToApi(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			String requestURI, boolean isValid, Role roleNames) throws IOException, ServletException {
		logger.debug("Entering CustomSecurityFilter.AllowAccessToApi() Method");
		if(StringUtils.equals(requestURI,Constants.POST_ROLE_URL)) {
			if(StringUtils.equalsIgnoreCase(AdminUtil.ADMIN.getRole(), roleNames.getRoleName())) {
				isValid = true;
				chain.doFilter(request, response);
			}
		}
		if(StringUtils.equals(requestURI,Constants.GET_USERS_URL)) {
			if(StringUtils.equalsIgnoreCase(UserUtil.USER.getRole(), roleNames.getRoleName())) {
				isValid = true;
				chain.doFilter(request, response);
			}
		}
		if(StringUtils.equals(requestURI,Constants.POST_USER_URL)) {
			if(StringUtils.equalsIgnoreCase(AdminUtil.ADMIN.getRole(), roleNames.getRoleName())) {
				isValid = true;
				chain.doFilter(request, response);
			}
		}
		if(StringUtils.equals(requestURI,Constants.DELETE_USER_URL)) {
			if(StringUtils.equalsIgnoreCase(BankerUtil.BANKER.getRole(), roleNames.getRoleName())) {
				isValid = true;
				chain.doFilter(request, response);
			}
		}
		logger.debug("Exiting CustomSecurityFilter.AllowAccessToApi() Method");
		return isValid;
	}
	
	/**
	 * This method is used to generate URI from Request.
	 * @param request {@link HttpServletRequest}
	 * @return {@link String}
	 */
	public String getFullURL(HttpServletRequest request) {
		logger.debug("Entering CustomSecurityFilter.getFullURL() Method");
	    StringBuilder requestURI = new StringBuilder(request.getRequestURI().toString());
	    logger.debug("Exiting CustomSecurityFilter.getFullURL() Method");
	    return requestURI.toString();
	}
}