package br.com.zup.rover.core;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.zup.rover.core.exception.ValidationException;
import br.com.zup.rover.core.model.DirectionType;
import br.com.zup.rover.core.model.Rover;
import br.com.zup.rover.service.RoverForm;
import br.com.zup.rover.service.RoverService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context/application-context.xml" })
public class RoverServiceTest {

	private static final String COORDINATES = "1 2 N";

	private static final String COORDINATES_INCORRECT = "1 2 P";

	private static final String DIRECTION = "LMLMLMLMM";

	private static final String DIRECTION_INCORRECT = "LMLMLMOMM";

	private static final String VALUE = "Teste";

	@Autowired
	private RoverService roverService;

	private RoverForm roverForm;

	private Rover rover;

	@Rule
	public ExpectedException expected = ExpectedException.none();

	@Before
	public void setUp() throws Exception {

		roverForm = buildRoverForm();
		rover = buildRover();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateRoverForm() {

		RoverForm roverForm = buildRoverForm();
		Assert.assertNotNull(roverForm);
	}

	@Test
	public void testCreateRover() {

		Rover rover = buildRover();
		Assert.assertNotNull(rover);
	}

	@Test
	public void testCreateRoverWithCoordinates() {

		RoverForm roverForm = buildRoverForm();
		Assert.assertNotNull(roverForm);

		Rover rover = roverService.createCoordinates(roverForm);

		Assert.assertEquals(rover.getPositionX(), 1);
		Assert.assertEquals(rover.getPositionY(), 3);
		Assert.assertEquals(rover.getDirectionType(), DirectionType.N);
	}

	@Test
	public void testCreateRoverNullPlateauX() {

		expect("A posição X do Platô é obrigatória.");
		roverForm.setPositionX(StringUtils.EMPTY);

		roverService.createCoordinates(roverForm);
	}

	@Test
	public void testCreateRoverIsNotNumericPlateauX() {

		expect("A posição X do Platô deve ser númerica e com valor positivo.");
		roverForm.setPositionX(VALUE);

		roverService.createCoordinates(roverForm);
	}

	@Test
	public void testCreateRoverNullPlateauY() {

		expect("A posição Y do Platô é obrigatória.");
		roverForm.setPositionY(StringUtils.EMPTY);

		roverService.createCoordinates(roverForm);
	}

	@Test
	public void testCreateRoverIsNotNumericPlateauY() {

		expect("A posição Y do Platô deve ser númerica e com valor positivo.");
		roverForm.setPositionY(VALUE);

		roverService.createCoordinates(roverForm);
	}

	@Test
	public void testCreateRoverNullCoordinates() {

		expect("A coordenada inicial é obrigatória.");
		roverForm.setCoordinates(StringUtils.EMPTY);

		roverService.createCoordinates(roverForm);
	}

	@Test
	public void testCreateRoverWithCoordinatesIncorrect() {

		expect("O ponto cardial de partida está incorreto.");
		roverForm.setCoordinates(COORDINATES_INCORRECT);

		roverService.createCoordinates(roverForm);
	}

	@Test
	public void testCreateRoverNullDirection() {

		expect("A movimentação é obrigatória.");
		roverForm.setDirection(StringUtils.EMPTY);

		roverService.createCoordinates(roverForm);
	}

	@Test
	public void testCreateRoverWithDirectionIncorrect() {

		expect("A coordenada de movimentação está incorreta.");
		roverForm.setDirection(DIRECTION_INCORRECT);

		roverService.createCoordinates(roverForm);
	}

	@Test
	public void testCreateRoverWithCoordinatesPlateauXInvalid() {

		RoverForm roverForm = buildRoverForm();
		Assert.assertNotNull(roverForm);

		roverService.createCoordinates(roverForm);
		rover.setPositionX(6);

		Assert.assertFalse(rover.getPositionX() < Integer.parseInt(roverForm.getPositionX()));
	}
	
	@Test
	public void testCreateRoverWithCoordinatesPlateauYInvalid() {

		RoverForm roverForm = buildRoverForm();
		Assert.assertNotNull(roverForm);

		roverService.createCoordinates(roverForm);
		rover.setPositionY(6);

		Assert.assertFalse(rover.getPositionY() < Integer.parseInt(roverForm.getPositionY()));
	}

	@Test
	public void testCreateRoverCardinalPoint() {

		String direction = StringUtils.upperCase(rover.splitPositionDirection(roverForm.getCoordinates()));

		Assert.assertEquals(DirectionType.N.toString(), direction);
		Assert.assertTrue("O ponto cardial de partida está correto.", DirectionType.N.toString().equals(direction));
	}

	private void expect(String message) {

		expected.expect(ValidationException.class);
		expected.expectMessage(message);
	}

	private RoverForm buildRoverForm() {

		RoverForm roverForm = new RoverForm();

		roverForm.setCoordinates(COORDINATES);
		roverForm.setDirection(DIRECTION);
		roverForm.setPositionX("5");
		roverForm.setPositionY("5");

		return roverForm;
	}

	private Rover buildRover() {

		Rover rover = new Rover();

		rover.setPositionX(1);
		rover.setPositionY(2);
		rover.setDirectionType(DirectionType.N);

		return rover;
	}
}