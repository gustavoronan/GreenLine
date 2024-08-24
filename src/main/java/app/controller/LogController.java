package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Carrinho;
import app.entity.Log;
import app.service.LogService;

@RequestMapping("/api/log")
@RestController
@CrossOrigin(origins = "*")
public class LogController {
	
	@Autowired
	private LogService logService;
	
	@GetMapping("/listAll")
	public ResponseEntity <List<Log>> listAll(){
		try {
			List<Log> lista = this.logService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
		}
	}


	
	
	
	
}
