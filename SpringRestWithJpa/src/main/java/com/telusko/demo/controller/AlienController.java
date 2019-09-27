package com.telusko.demo.controller;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.telusko.demo.dao.AlienRepo;
import com.telusko.demo.model.Alien;

@RestController
public class AlienController
{
	@Autowired
	AlienRepo repo;

	@RequestMapping("/")
	public String home()
	{
		return "home.jsp";
	}
	@PostMapping(path="/addAlien",consumes= {"application/json"})
	public Alien addAlien(@RequestBody Alien alien)
	{
		repo.save(alien);
		return alien;
	}
	@GetMapping("/aliens")

	public List<Alien> getAliens()
	{
		return repo.findAll();

		
		
	}
	@GetMapping("/alien/{aid}")
	
	public Optional<Alien> getAlien(@PathVariable("aid")int aid)
	{
		return repo.findById(aid);
		
		
	}
	@DeleteMapping("alienDelete/{aid}")
	public  String deleteAlign(@PathVariable("aid")int aid) {
		
		repo.deleteById(aid);
		return aid+"alien deleted";
	}
	
	@PutMapping(path="updateAlien/{aid}",consumes={"application/json"})
	public Alien updateAlien(@RequestBody Alien  alien,@PathVariable("aid") int aid) {
		
		return repo.findById(aid).map(ali ->
		{
			ali.setAname(alien.getAname());
			ali.setTech(alien.getTech());
			return repo.save(ali);
			
			
		}
		).orElseGet(()->{
			
			alien.setAid(aid);
			return repo.save(alien);
			
		});
				
		
		
		

	
		
		
	}
	
}
