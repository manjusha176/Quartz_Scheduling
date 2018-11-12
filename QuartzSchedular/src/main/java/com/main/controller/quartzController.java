package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.service.quartzService;

@RestController
public class quartzController {

	@Autowired
	private quartzService qService;
	
	@RequestMapping(method=RequestMethod.POST, value="/schedular/{seconds}")
	public void startQuartzJob(@PathVariable String seconds)
	{
		qService.tringgerAndSchedular(seconds);
	}
	
}
