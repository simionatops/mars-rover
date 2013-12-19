package br.com.zup.rover.core.engine;

import br.com.zup.rover.core.model.DirectionType;
import br.com.zup.rover.core.model.Rover;
import br.com.zup.rover.core.validation.ValidationEngine;
import br.com.zup.rover.service.RoverForm;

public class RoverEngine {

	private static final char RIGHT = 'R';

	private static final char LEFT = 'L';

	private static final char MOVE = 'M';

	private static final Integer ONE_VALUE = 1;

	public Rover engine(Rover rover, RoverForm form) {

		Rover roverCoordinates = roamCoordinates(rover, form);

		ValidationEngine.checkTrue(roverCoordinates.getPositionX() > Integer.parseInt(form.getPositionX()),
				"A coordenada X está fora do platô.");

		ValidationEngine.checkTrue(roverCoordinates.getPositionY() > Integer.parseInt(form.getPositionY()),
				"A coordenada Y está fora do platô.");

		return roverCoordinates;
	}

	private Rover roamCoordinates(Rover rover, RoverForm form) {

		Rover rv = null;
		for (char i : form.getDirection().toCharArray()) {

			ValidationEngine.checkTrue(!isDirection(i), "A coordenada de movimentação está incorreta.");
			rv = fillRover(rover, i);
		}

		return rv;
	}

	private Rover fillRover(Rover rover, char position) {

		return Direction.getFor(rover.getDirectionType()).fill(rover, position);
	}

	private enum Direction {

		N(DirectionType.N) {

			@Override
			Rover fill(Rover rover, char position) {

				return moveFromNorth(rover, position);
			}

			private Rover moveFromNorth(Rover rover, char position) {

				if (RIGHT == position) {
					rover.setDirectionType(DirectionType.N.turnRight());
					return rover;
				}

				if (LEFT == position) {
					rover.setDirectionType(DirectionType.N.turnleft());
					return rover;
				}

				rover.setPositionY(rover.getPositionY() + ONE_VALUE);
				return rover;
			}
		},
		E(DirectionType.E) {

			@Override
			Rover fill(Rover rover, char position) {

				return moveFromEast(rover, position);
			}

			private Rover moveFromEast(Rover rover, char position) {

				if (RIGHT == position) {
					rover.setDirectionType(DirectionType.E.turnRight());
					return rover;
				}

				if (LEFT == position) {
					rover.setDirectionType(DirectionType.E.turnleft());
					return rover;
				}

				rover.setPositionX(rover.getPositionX() + ONE_VALUE);
				return rover;
			}
		},
		S(DirectionType.S) {

			@Override
			Rover fill(Rover rover, char position) {

				return moveSouth(rover, position);
			}

			private Rover moveSouth(Rover rover, char position) {

				if (RIGHT == position) {
					rover.setDirectionType(DirectionType.S.turnRight());
					return rover;
				}

				if (LEFT == position) {
					rover.setDirectionType(DirectionType.S.turnleft());
					return rover;
				}

				rover.setPositionY(rover.getPositionY() - ONE_VALUE);
				return rover;
			}
		},
		W(DirectionType.W) {

			@Override
			Rover fill(Rover rover, char position) {

				return moveWest(rover, position);
			}

			private Rover moveWest(Rover rover, char position) {

				if (RIGHT == position) {
					rover.setDirectionType(DirectionType.W.turnRight());
					return rover;
				}

				if (LEFT == position) {
					rover.setDirectionType(DirectionType.W.turnleft());
					return rover;
				}

				rover.setPositionX(rover.getPositionX() - ONE_VALUE);
				return rover;
			}
		};

		private DirectionType directionType;

		private Direction(DirectionType directionType) {
			this.directionType = directionType;
		}

		abstract Rover fill(Rover rover, char position);

		static Direction getFor(DirectionType directionType) {
			for (Direction act : values()) {
				if (act.directionType.equals(directionType)) {
					return act;
				}
			}
			throw new IllegalArgumentException("Invalid coordinate type");
		}
	}

	private Boolean isDirection(char coordinate) {

		if (RIGHT == coordinate || LEFT == coordinate || MOVE == coordinate) {

			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}