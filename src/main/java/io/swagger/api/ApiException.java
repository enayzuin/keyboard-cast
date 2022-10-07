package io.swagger.api;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-17T23:34:46.447Z")

public class ApiException extends Exception {
	private int code;

	public ApiException(int code, String msg) {
		super(msg);
		this.code = code;
	}
}
