package io.swagger.controller;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ResponseDTO  {
	private MouseDTO mouse;
	private List<KeyboardDTO> keyboards;
}
