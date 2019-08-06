package com.utd.rbac.util;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.utd.rbac.outbound.response.ApiResponse;
import com.utd.rbac.outbound.response.Message;
import com.utd.rbac.outbound.response.Payload;
import com.utd.rbac.outbound.response.Status;

/**
 * This contains all the HTTP util methods
 *
 */
public class HttpUtil {
	 private HttpUtil() {}

	/**
	 * This methods creates API response using payload and status
	 * @param payload
	 * @return {@link ApiResponse}
	 */
	public static ApiResponse buildSuccessApiResponse(@SuppressWarnings("rawtypes") Payload payload) {
		ArrayList<Message> messages = new ArrayList<>();
		String description = Constants.SUCCESS_RESPONSE_MESSAGE;
		Message message = new Message(description, Constants.SUCCESS_RESPONSE_MESSAGE, description);
		messages.add(message);
		Status status = new Status(messages);
		return new ApiResponse(status, payload);
	}

	public static ResponseEntity<ApiResponse> buildErrorResponse() {
        List<Message> messages = new ArrayList<>();
        String errorDescription = Constants.SUCCESS_RESPONSE_MESSAGE;
        messages.add(new Message(errorDescription, Constants.FAILURE_RESPONSE_MESSAGE, errorDescription));
        Status errorResponseStatus = new Status(messages);
        return new ResponseEntity<>(new ApiResponse(errorResponseStatus, null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
