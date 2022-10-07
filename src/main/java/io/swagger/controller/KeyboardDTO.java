package io.swagger.controller;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Component
@Data
public class KeyboardDTO  {
	Integer keyCode;
	Boolean pressed;
}
