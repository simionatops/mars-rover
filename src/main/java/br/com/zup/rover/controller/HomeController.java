package br.com.zup.rover.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zup.rover.core.exception.ValidationException;
import br.com.zup.rover.core.model.Rover;
import br.com.zup.rover.service.RoverForm;
import br.com.zup.rover.service.RoverService;

@Controller
@RequestMapping("/")
public class HomeController {

	public static final String HOME = "pages/public/home";

	private final RoverService roverService;

	@Autowired
	public HomeController(RoverService roverService) {

		this.roverService = roverService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String load(Model model, HttpServletRequest request) {

		return HOME;
	}

	@RequestMapping(value = "/coordenada", method = RequestMethod.POST)
	public String save(Model model, @ModelAttribute("coordinate") RoverForm form) {

		try {

			Rover coordinates = roverService.createCoordinates(form);

			model.addAttribute("coordinates", coordinates);

		} catch (ValidationException e) {
			addMessage(model, e.getMessage());
		}
		return HOME;
	}

	private void addMessage(Model model, String string) {

		model.addAttribute("message", string);
	}
}