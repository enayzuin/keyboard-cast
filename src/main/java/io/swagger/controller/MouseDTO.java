package io.swagger.controller;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class MouseDTO  {
	private String x;
	private String y;
	private Boolean pressed=false;
	private Boolean released=false;
	private Boolean wheel=false;
	private int wheelScrol;
	private int button;
}
