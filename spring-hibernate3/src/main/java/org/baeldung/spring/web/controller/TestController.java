/*
 * @date: 21-05-2013
 * @author: Muhammad Haris (mh.haris@gmail.com)
 */
package org.baeldung.spring.web.controller;

import java.util.List;

import org.baeldung.spring.persistence.model.Foo;
import org.baeldung.spring.persistence.service.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("test")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TestController {

	@Autowired
	private FooService fooService;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(final ModelMap model) {
		model.addAttribute("message", "Hello, World!");
		try {
			final List<Foo> foos = fooService.getAllFoos();
			model.addAttribute("data", foos);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "sample";
	}
}
