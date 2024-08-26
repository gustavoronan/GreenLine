package app.service;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.config.JwtServiceGenerator;
import app.entity.Log;
import app.repository.LogRepository;

@Service
public class LogService {
	@Autowired
	LogRepository logRepository;
	@Autowired
	JwtServiceGenerator jwtServiceGenerator;
	
	//funcao usada para gerar o log 
	public void gerarLog (String acao, String entity, Long entityId, String detalhes, String logName) {
		
		//filtra o email e o role apartir do token do usuario que esta logado
		String email = jwtServiceGenerator.getEmailFromToken();
		String role = jwtServiceGenerator.getRoleFromToken();
		
		Log log = new Log();
		log.setAcao(acao);
		log.setTimestamp(LocalDateTime.now());
		log.setEntity(entity);
        log.setIdEntity(entityId);
        log.setDetalhes(detalhes);
        log.setLogName(logName);
        log.setEmailUsuario(email);
        log.setRoleUsuario(role);
             
        logRepository.save(log);
	}
	
	//funcao criada para a excessao gerada na hora de persistir um cadastro de usuario, ja que nesse caso nao existe tokem 
	public void gerarLogCadUsuario (String acao, String entity, Long entityId, String detalhes, String logName, String email, String role) {
			
		Log log = new Log();
		log.setAcao(acao);
		log.setTimestamp(LocalDateTime.now());
		log.setEntity(entity);
        log.setIdEntity(entityId);
        log.setDetalhes(detalhes);
        log.setLogName(logName);
        log.setDetalhes(email);
        log.setLogName(role);
             
        logRepository.save(log);
	}
	
	public List<Log> listAll(){
		return logRepository.findAll();
	}
}
