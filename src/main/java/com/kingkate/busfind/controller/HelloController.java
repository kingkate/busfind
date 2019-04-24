package com.kingkate.busfind.controller;

import com.kingkate.busfind.constants.ResultCodeEnum;
import com.kingkate.busfind.exception.BusFindException;
import com.kingkate.busfind.util.Error;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/hello")
@ApiIgnore
public class HelloController {
	
	@RequestMapping("hi")
	public String hi() {
		return "Hi,hello!!";
	}

	@RequestMapping("hi2")
	public void hi2() {
		//throw new BusFindException(ResultCodeEnum.SUCCESS);
		List list = null;
		System.out.println(list.get(13));
		System.out.println(1/0);
	}
}
