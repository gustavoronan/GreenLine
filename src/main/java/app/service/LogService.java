package app.service;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Log;
import app.entity.Produto;
import app.repository.LogRepository;

@Service
public class LogService {
	@Autowired
	LogRepository logRepository;
	
	public void gerarLog (String acao, String entity, Long entityId, String detalhes, String logName) {
		
		Log log = new Log();
		log.setAcao(acao);
		log.setTimestamp(LocalDateTime.now());
		log.setEntity(entity);
        log.setIdEntity(entityId);
        log.setDetalhes(detalhes);
        log.setLogName(logName);
        logRepository.save(log);
	}
	
	public List<Log> listAll(){
		return logRepository.findAll();
	}
}
