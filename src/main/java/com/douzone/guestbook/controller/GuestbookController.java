package com.douzone.guestbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.guestbook.dao.GuestbookDao;
import com.douzone.guestbook.vo.GuestbookVo;

@Controller
public class GuestbookController {
	
	@Autowired
	private GuestbookDao dao;
	
	@RequestMapping({"","/list"})
	public String list(Model model) {
		
		model.addAttribute("list", dao.getList());
		
		return "list";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@ModelAttribute GuestbookVo vo) {
		dao.insert(vo);
		
		return "redirect:/list";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String deleteform(@RequestParam(value="no", required=false) Long no, Model model) {
		model.addAttribute("no", no);
		return "deleteform";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(GuestbookVo vo) {
		dao.delete(vo);
		
		return "redirect:/list";
	}
}
