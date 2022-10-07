package io.swagger.listener;

import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.MemoryImageSource;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.swagger.controller.KeyboardDTO;
import io.swagger.controller.MouseDTO;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class HumanInputListener extends Frame implements MouseListener, MouseWheelListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1118867964761333072L;

	@Autowired
	private MouseDTO mouse;

	List<KeyboardDTO> keys = new ArrayList<>();

	Label label;

	public HumanInputListener() {
		addMouseListener(this);

		addMouseWheelListener(this);
		addKeyListener(this);

		label = new Label();
		label.setBounds(20, 50, 100, 20);
		add(label);
		setSize(1920, 1080);
		setLayout(null);
		setUndecorated(true);
		setOpacity(0.1f);
		setVisible(true);
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println("Clicked");
	}

	public void mouseEntered(MouseEvent e) {
		keys.forEach(key -> {
			key.setPressed(false);
		});
		System.out.println("Entered");
		
		Cursor cursor = Cursor.getDefaultCursor();
		
	}

	public void mouseExited(MouseEvent e) {
		System.out.println("Exited");
		int[] pixels = new int[16 * 16];
		Image image = Toolkit.getDefaultToolkit().createImage(
		        new MemoryImageSource(16, 16, pixels, 0, 16));
		
		Cursor defaultCursor =
		        Cursor.getDefaultCursor();
		
	}

	public void mousePressed(MouseEvent e) {
		System.out.println("Pressed");
		mouse.setButton(e.getButton());
		mouse.setPressed(true);
		mouse.setReleased(false);
	}

	public void mouseReleased(MouseEvent e) {
		System.out.println("Released");
		mouse.setButton(e.getButton());
		mouse.setPressed(false);
		mouse.setReleased(true);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		System.out.println("Scrolled");
		mouse.setWheel(true);
		mouse.setWheelScrol(e.getWheelRotation());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("Key typed: " + String.valueOf(e.getKeyCode()));
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (keys.stream().anyMatch(key -> key.getKeyCode().intValue() == e.getKeyCode())) {
			keys.stream().filter(key -> key.getKeyCode().intValue() == e.getKeyCode()).forEach(key -> {
				key.setPressed(true);
			});
		} else {
			keys.add(new KeyboardDTO(e.getKeyCode(), true));
		}

		System.out.println("Pressed");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys.stream().filter(key -> key.getKeyCode().intValue() == e.getKeyCode()).forEach(key -> {
			key.setPressed(false);
		});
		System.out.println("Released");

	}

}