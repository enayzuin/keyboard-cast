
package io.swagger.controller;

import java.awt.MouseInfo;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import io.swagger.listener.HumanInputListener;
import twitter4j.TwitterException;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-17T04:43:10.588Z")

@Controller
@CrossOrigin
public class UsersApiController implements UsersApi {
	
	@Autowired
	private HumanInputListener humanInputListener;

	@Override
	public ResponseEntity<ResponseDTO> mouseListener()
			throws IOException, TwitterException, InterruptedException, Exception {
		ResponseDTO responseDTO = new ResponseDTO();

		MouseDTO mouseDto = new MouseDTO();
		mouseDto.setX(String.valueOf(MouseInfo.getPointerInfo().getLocation().getX()));
		mouseDto.setY(String.valueOf(MouseInfo.getPointerInfo().getLocation().getY()));

		MouseDTO mouseHumanInput = humanInputListener.getMouse();
		mouseDto.setButton(mouseHumanInput.getButton());
		mouseDto.setPressed(mouseHumanInput.getPressed());
		mouseDto.setReleased(mouseHumanInput.getReleased());
		mouseDto.setWheel(mouseHumanInput.getWheel());
		mouseDto.setWheelScrol(mouseHumanInput.getWheelScrol());

		if (mouseHumanInput.getReleased()) {
			mouseHumanInput.setReleased(false);
		}
		if (mouseHumanInput.getWheel()) {
			mouseHumanInput.setWheel(false);
		}
		responseDTO.setMouse(mouseDto);

		List<KeyboardDTO> keysDTO = humanInputListener.getKeys().stream().map(key -> {
			KeyboardDTO keyDTO = new KeyboardDTO();
			keyDTO.setKeyCode(key.getKeyCode());
			keyDTO.setPressed(key.getPressed());
			return keyDTO;
		}).limit(5).collect(Collectors.toList());

		responseDTO.setKeyboards(keysDTO);

		humanInputListener.getKeys().removeIf(key -> !key.getPressed());

		return ResponseEntity.ok(responseDTO);
	}

}
