package br.com.zup.rover.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import br.com.zup.rover.core.engine.RoverEngine;
import br.com.zup.rover.core.model.DirectionType;
import br.com.zup.rover.core.model.Rover;
import br.com.zup.rover.core.validation.ValidationEngine;

@Service
class RoverServiceImpl implements RoverService {

	public Rover createCoordinates(RoverForm roverForm) {

		Rover rover = addAtributtes(roverForm);
		RoverEngine roverEngine = new RoverEngine();

		return roverEngine.engine(rover, roverForm);
	}

	private Rover addAtributtes(RoverForm form) {

		Rover rover = new Rover();

		validateCreationRoverForm(rover, form);

		rover.setPositionX(toInteger(rover.splitPositionX(form.getCoordinates())));
		rover.setPositionY(toInteger(rover.splitPositionY(form.getCoordinates())));
		rover.setDirectionType(DirectionType.valueOf(rover.splitPositionDirection(form.getCoordinates())));

		validateCreationRover(rover, form);

		return rover;
	}

	private void validateCreationRoverForm(Rover rover, RoverForm form) {

		ValidationEngine.checkNotEmpty(form.getPositionX(), "A posição X do Platô é obrigatória.");
		ValidationEngine.checkNotEmpty(form.getPositionY(), "A posição Y do Platô é obrigatória.");
		ValidationEngine.checkTrue(!StringUtils.isNumeric(form.getPositionX()),
				"A posição X do Platô deve ser númerica e com valor positivo.");
		ValidationEngine.checkTrue(!StringUtils.isNumeric(form.getPositionY()),
				"A posição Y do Platô deve ser númerica e com valor positivo.");
		ValidationEngine.checkNotEmpty(form.getCoordinates(), "A coordenada inicial é obrigatória.");
		ValidationEngine.checkNotEmpty(form.getDirection(), "A movimentação é obrigatória.");
		ValidationEngine.checkTrue(!isDirection(StringUtils.upperCase(rover.splitPositionDirection(form.getCoordinates()))),
				"O ponto cardial de partida está incorreto.");
	}

	private void validateCreationRover(Rover rover, RoverForm form) {

		ValidationEngine.checkTrue(rover.getPositionX() > Integer.parseInt(form.getPositionX()),
				"A posição X da coordenada Inicial é maior que a posição X do Platô.");
		ValidationEngine.checkTrue(rover.getPositionY() > Integer.parseInt(form.getPositionY()),
				"A posição Y da coordenada Inicial é maior que a posição Y do Platô.");
	}

	private Boolean isDirection(String direction) {

		if (DirectionType.N.toString().equals(direction) || DirectionType.S.toString().equals(direction)
				|| DirectionType.E.toString().equals(direction) || DirectionType.W.toString().equals(direction)) {

			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	private static Integer toInteger(Object object) {

		if (object == null) {
			return null;
		}
		return new Integer((String) object);
	}
}