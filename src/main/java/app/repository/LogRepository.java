package app.repository;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.entity.Log;

public interface LogRepository extends JpaRepository<Log, Long>{
	
	@Query("SELECT l FROM Log l  " +
			"WHERE (:startDate IS NULL OR l.timestamp >= :startDate) " +
			"AND (:endDate IS NULL OR l.timestamp <= :endDate) " +
			"AND (:acao IS NULL OR l.acao = :acao) " +
			"AND (:roleUsuario IS NULL OR l.roleUsuario = :roleUsuario) " +
			"AND (:logName IS NULL OR l.logName = :logName) " +
			"AND (:entity IS NULL OR l.entity = :entity) " +
			"AND (:emailUsuario IS NULL OR l.emailUsuario = :emailUsuario)")
	List<Log> findLogsByCriterio(@Param("startDate") LocalDateTime startDate,
			@Param("endDate") LocalDateTime endDate,
			@Param("acao") String acao,
			@Param("roleUsuario") String roleUsuario,
			@Param("logName") String logName,
			@Param("entity") String entity,
			@Param("emailUsuario") String emailUsuario);
	
}
