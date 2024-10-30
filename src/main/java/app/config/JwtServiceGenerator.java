package app.config;

//JwtService.java

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.security.core.userdetails.UserDetails;

import app.auth.Autenticador;
import app.auth.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtServiceGenerator {  

// Trecho comentado para impllementação do keycloack
//  public String generateToken(Usuario userDetails) {
//	
//	  
//	  //AQUI VOCÊ PODE COLOCAR O QUE MAIS VAI COMPOR O PAYLOAD DO TOKEN
//      Map<String, Object> extraClaims = new HashMap<>();
//      extraClaims.put("username", userDetails.getUsername());
//      extraClaims.put("emailUsuario", userDetails.getUsername());
//      extraClaims.put("idUsuario", userDetails.getIdUsuario());
//      extraClaims.put("role", userDetails.getRole());
//      
//      return Jwts
//              .builder()
//              .setClaims(extraClaims)
//              .setSubject(userDetails.getUsername())
//              .setIssuedAt(new Date(System.currentTimeMillis()))
//              .setExpiration(new Date(new Date().getTime() + 3600000 * JwtConfig.HORAS_EXPIRACAO_TOKEN))
//              .signWith(getSigningKey(), JwtConfig.ALGORITMO_ASSINATURA)
//              .compact();
//  }
//  
//  private Claims extractAllClaims(String token) {
//      return Jwts
//              .parserBuilder()
//              .setSigningKey(getSigningKey())
//              .build()
//              .parseClaimsJws(token)
//              .getBody();
//  }
//
//
//  public boolean isTokenValid(String token, UserDetails userDetails) {
//      final String username = extractUsername(token);
//      return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
//  }
//
//  private boolean isTokenExpired(String token) {
//      return extractExpiration(token).before(new Date());
//  }
//
//  private Date extractExpiration(String token) {
//      return extractClaim(token, Claims::getExpiration);
//  }
//
//  private Key getSigningKey() {
//      byte[] keyBytes = Decoders.BASE64.decode(JwtConfig.SECRET_KEY);
//      return Keys.hmacShaKeyFor(keyBytes);
//  }
//  
//
//  public String extractUsername(String token) {
//      return extractClaim(token,Claims::getSubject);
//  }
//
//  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//      final Claims claims = extractAllClaims(token);
//      return claimsResolver.apply(claims);
//  }
//  
//  public String extractEmail(String token) {
//	    return extractClaim(token, claims -> claims.get("emailUsuario", String.class));
//	}
//
//  public String getEmailFromToken() {
//	  HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//      String token = request.getHeader("Authorization").substring(7); 
//      return extractEmail(token);
//  }
//  
//  public String getRoleFromToken() {
//      HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//      String token = request.getHeader("Authorization").substring(7);
//      return extractClaim(token, claims -> claims.get("role", String.class));
//  }
	
	public String getEmailFromToken() {
		return ("feature ser implementada");
	}
 
	public String getRoleFromToken() {
		return ("feature ser implementada");
	}
	
}
