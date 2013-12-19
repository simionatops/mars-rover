package br.com.zup.rover.service;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class RoverForm implements Serializable {

	private static final long serialVersionUID = -3803964097560006668L;

	private String positionX;

	private String positionY;

	private String coordinates;

	private String direction;

	public String getPositionX() {
		return positionX;
	}

	public void setPositionX(String positionX) {
		this.positionX = positionX;
	}

	public String getPositionY() {
		return positionY;
	}

	public void setPositionY(String positionY) {
		this.positionY = positionY;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = StringUtils.upperCase(coordinates);
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = StringUtils.upperCase(direction);
	}
}