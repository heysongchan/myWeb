package com.web;

import java.io.ByteArrayInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.service.testService;

@Controller
@Scope(value = "prototype")
public class testController {
	@Autowired
	private testService s;

	@RequestMapping(method = RequestMethod.GET, value = "/dodo")
	public void dodo(HttpServletRequest req, HttpServletResponse res) {
		try {
			ServletOutputStream out = res.getOutputStream();
			byte[] buf = "你好".getBytes("gbk");
			ByteArrayInputStream in = new ByteArrayInputStream(buf);
			FileCopyUtils.copy(in, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getStr")
	public void getStr(HttpServletResponse res) {
		String str = s.getStr();
		if (str != null) {
			try {
				byte[] buf = str.getBytes("utf-8");
				ByteArrayInputStream in = new ByteArrayInputStream(buf);
				ServletOutputStream out = res.getOutputStream();
				FileCopyUtils.copy(in, out);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
