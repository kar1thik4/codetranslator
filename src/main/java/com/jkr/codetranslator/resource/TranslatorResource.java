package com.jkr.codetranslator.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jkr.codetranslator.dto.TranslateDto;
import com.jkr.codetranslator.service.TranslationService;

@RestController
public class TranslatorResource {

   
    
    @Autowired
    TranslationService service;

    @PostMapping("/translate")
    public ModelAndView translate(@ModelAttribute("emp")TranslateDto translate){
    	String out = service.translate(translate);
    	
       return new ModelAndView("welcome","compiled",out);
    }

    @RequestMapping("/")
    public ModelAndView homePage(@ModelAttribute("emp")TranslateDto translate) {
    	return new ModelAndView("welcome");
    }

    



}
