package br.com.zup.rover.core.model;

import org.apache.commons.lang.StringUtils;

public class Rover {

	private static final Integer POSITION_X = 0;

	private static final Integer POSITION_Y = 1;

	private static final Integer DIRECTION = 3;

	private int positionX;

	private int positionY;

	private DirectionType directionType;

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public DirectionType getDirectionType() {
		return directionType;
	}

	public void setDirectionType(DirectionType directionType) {
		this.directionType = directionType;
	}

	public String splitPositionX(String description) {
		return description == null ? null : StringUtils.trim(description.substring(POSITION_X, POSITION_Y));
	}

	public String splitPositionY(String description) {
		return description == null ? null : StringUtils.trim(description.substring(POSITION_Y, DIRECTION));
	}

	public String splitPositionDirection(String description) {
		return description == null ? null : StringUtils.trim(description.substring(DIRECTION));
	}
}