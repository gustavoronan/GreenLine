package app.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import app.entity.Log;
import app.service.LogService;
import dto.MesValorDTO;

@RequestMapping("/api/log")
@RestController
@CrossOrigin(origins = "*")
public class LogController {
	
	@Autowired
	private LogService logService;
	

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/listAll")
	public ResponseEntity <List<Log>> listAll(){
		try {
			List<Log> lista = this.logService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/findLogsByCriterio")
	public ResponseEntity<List<Log>> findLogsByCriterio (@RequestParam(required = false) LocalDateTime startDate,
														 @RequestParam(required = false) LocalDateTime endDate,
														 @RequestParam(required = false) String acao,
														 @RequestParam(required = false) String roleUsuario,
														 @RequestParam(required = false) String logName,
														 @RequestParam(required = false) String entity,
														 @RequestParam(required = false) String emailUsuario) {
		try {
			
			List<Log> lista = this.logService.getLogsByCriterio(startDate, endDate, acao, roleUsuario, logName, entity, emailUsuario);
			return new ResponseEntity<>(lista, HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
	
}
