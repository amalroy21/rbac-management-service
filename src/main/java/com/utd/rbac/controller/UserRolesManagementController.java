package com.utd.rbac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utd.rbac.inbound.request.RoleRequest;
import com.utd.rbac.inbound.request.UserRequest;
import com.utd.rbac.outbound.response.Status;
import com.utd.rbac.outbound.response.UserRoles;
import com.utd.rbac.service.IUserRolesManagementService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;


/**
 * This Controller exposes Request Handlers for handling Roles Related Operations
 */
@Configuration
@RestController
@RequestMapping(path = "/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(allowCredentials = "false")
@Api(tags = {"Swagger Resource"})
@SwaggerDefinition(tags = {
		@Tag(name = "Swagger Resource", description = "Operations related to User_Roles")
})
public class UserRolesManagementController {

	@Autowired
	private IUserRolesManagementService userRolesManagementService;
	
	/**
     * This controller helps to post Roles.
     * @param httpHeaders {@link HttpHeaders}
     * @param role {@link Role}
     * @return ResponseEntity<ApiResponse>
     */
    @PostMapping(value = "/role")
    @ApiOperation(value = "Post Role", response = ApiResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful Request. Response Codes:- ERROR-0000 - Request processed Successfully.",response=Status.class),
			@ApiResponse(code = 400, message = "Bad Request. Response codes:- " +  "ERROR-0002 = Missing API Key\r\n"
					+ "ERROR-0004= Missing Correlation-ID\r\n"
					+ "ERROR-0005 = Invalid Correlation-ID\r\n"
					+ "ERROR-0006 = Missing Channel\r\n"+ "ERROR-0007 = Invalid Channel\r\n"
					+ "ERROR-0013: Invalid Payload\r\n" 
					+ "ERROR-0014 = Invalid Role Name\r\n"
					+ "ERROR-0016 = Duplicate Role Name"),
			@ApiResponse(code = 401, message = "ACCESS PROHIBITED,Response codes:- ERROR-0003 = Invalid API Key"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "X-CorrelationId ", value = "Unique GUID to trace the request end to end. New GUID has to be generated for each request", required = true, allowMultiple = true, paramType = "header"),
		@ApiImplicitParam(name = "Authorization ", value = "API Gateway API Key for identification", required = true, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "X-channel ", value = "Value has to be UI", required = true, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Api-Key ", value = "API Gateway API Key for identification", required = true, dataType = "String", paramType = "header")})

    public ResponseEntity<com.utd.rbac.outbound.response.ApiResponse> postRoles(@RequestHeader HttpHeaders httpHeaders, @RequestBody RoleRequest roleRequest) {
    	ResponseEntity<com.utd.rbac.outbound.response.ApiResponse> responseEntity = null;
		responseEntity = userRolesManagementService.postRoles(httpHeaders, roleRequest);
        return responseEntity;
    }
    
    /**
     * This controller helps to get Users.
     * @param httpHeaders {@link HttpHeaders}
     * @return ResponseEntity<ApiResponse>
     */
    @GetMapping(value = "/users")
    @ApiOperation(value = "Get Users", response = ApiResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful Request. Response Codes:- ERROR-0000 - Request processed Successfully.",response=UserRoles.class),
			@ApiResponse(code = 400, message = "Bad Request. Response codes:- " +  "ERROR-0002 = Missing API Key\r\n"
					+ "ERROR-0004= Missing Correlation-ID\r\n"
					+ "ERROR-0005 = Invalid Correlation-ID\r\n"
					+ "ERROR-0006 = Missing Channel\r\n"+ "ERROR-0007 = Invalid Channel\r\n"),
			@ApiResponse(code = 401, message = "ACCESS PROHIBITED,Response codes:- ERROR-0003 = Invalid API Key"),
			@ApiResponse(code = 404, message = "Response codes:- ERROR-0018 = User Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
    @ApiImplicitParams({
		@ApiImplicitParam(name = "X-CorrelationId", value = "Unique GUID to trace the request end to end. New GUID has to be generated for each request", required = true, allowMultiple = true, paramType = "header"),
		@ApiImplicitParam(name = "X-channel ", value = "Value has to be UI", required = true, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "user-email ", value = "Value has to be valid email address", required = true, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Authorization ", value = "API Gateway API Key for identification", required = true, dataType = "String", paramType = "header")})

    public ResponseEntity<com.utd.rbac.outbound.response.ApiResponse> getUsers(@RequestHeader HttpHeaders httpHeaders) {
    	ResponseEntity<com.utd.rbac.outbound.response.ApiResponse> responseEntity = null;
		responseEntity = userRolesManagementService.getUserRoles(httpHeaders);
        return responseEntity;
    }
    
    /**
     * This controller helps to post Users.
     * @param httpHeaders {@link HttpHeaders}
     * @param user {@link User}
     * @return ResponseEntity<ApiResponse>
     */
    @PostMapping(value = "/user")
    @ApiOperation(value = "Post User", response = ApiResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful Request. Response Codes:- ERROR-0000 - Request processed Successfully.",response=Status.class),
			@ApiResponse(code = 400, message = "Bad Request. Response codes:- " +  "ERROR-0002 = Missing API Key\r\n"
					+ "ERROR-0004= Missing Correlation-ID\r\n"
					+ "ERROR-0005 = Invalid Correlation-ID\r\n"
					+ "ERROR-0006 = Missing Channel\r\n"+ "ERROR-0007 = Invalid Channel\r\n"
					+ "ERROR-0013: Invalid Payload\r\n" 
					+ "ERROR-0014 = Invalid Role Name\r\n"
					+ "ERROR-0021 = Duplicate User Role"),
			@ApiResponse(code = 401, message = "ACCESS PROHIBITED,Response codes:- ERROR-0003 = Invalid API Key"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
    @ApiImplicitParams({
		@ApiImplicitParam(name = "X-CorrelationId", value = "Unique GUID to trace the request end to end. New GUID has to be generated for each request", required = true, allowMultiple = true, paramType = "header"),
		@ApiImplicitParam(name = "X-channel ", value = "Value has to be UI", required = true, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Authorization ", value = "API Gateway API Key for identification", required = true, dataType = "String", paramType = "header")})

    public ResponseEntity<com.utd.rbac.outbound.response.ApiResponse> postUsers(@RequestHeader HttpHeaders httpHeaders, @RequestBody UserRequest userRequest) {
    	ResponseEntity<com.utd.rbac.outbound.response.ApiResponse> responseEntity = null;
		responseEntity = userRolesManagementService.postUsers(httpHeaders, userRequest);
        return responseEntity;
    }
    
    /**
     * This controller helps to delete Users.
     * @param httpHeaders {@link HttpHeaders}
     * @param user {@link User}
     * @return ResponseEntity<ApiResponse>
     */
    @PostMapping(value = "/user")
    @ApiOperation(value = "Delete User", response = ApiResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful Request. Response Codes:- ERROR-0000 - Request processed Successfully.",response=Status.class),
			@ApiResponse(code = 400, message = "Bad Request. Response codes:- " +  "ERROR-0002 = Missing API Key\r\n"
					+ "ERROR-0013: Invalid Payload\r\n" 
					+ "ERROR-0014 = Invalid Role Name\r\n"
					+ "ERROR-0021 = Duplicate User Role"),
			@ApiResponse(code = 401, message = "ACCESS PROHIBITED,Response codes:- ERROR-0003 = Invalid API Key"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
    @ApiImplicitParams({
		@ApiImplicitParam(name = "X-CorrelationId", value = "Unique GUID to trace the request end to end. New GUID has to be generated for each request", required = true, allowMultiple = true, paramType = "header"),
		@ApiImplicitParam(name = "X-channel ", value = "Value has to be UI", required = true, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "Authorization ", value = "API Gateway API Key for identification", required = true, dataType = "String", paramType = "header")})

    public ResponseEntity<com.utd.rbac.outbound.response.ApiResponse> deleteUsers(@RequestHeader HttpHeaders httpHeaders, @RequestBody UserRequest userRequest) {
    	ResponseEntity<com.utd.rbac.outbound.response.ApiResponse> responseEntity = null;
		responseEntity = userRolesManagementService.deleteUsers(httpHeaders, userRequest);
        return responseEntity;
    }
}
