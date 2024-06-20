package app.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import app.entity.Carrinho;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Usuario implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUsuario;
	@NotBlank(message = "Email do cliente nao pode estar vazio")
	private String emailUsuario;
	@NotBlank(message = "Senha do cliente nao pode estar vazio")
	private String senhaUsuario;
	private String role;
	
	

	//relacao de um cliente para muitas vendas
		@OneToMany(mappedBy = "usuario")
		@JsonIgnoreProperties("usuario")
		private List<Carrinho> carrinho;
		
	/*	@OneToMany(mappedBy = "usuario")
		@JsonIgnoreProperties("usuario")
		//@JoinColumn(name = "id_cliente")//Define a coluna de chave estrangeira na tabela de Usuario
		private Cliente cliente;*/

		
		
		
		
		
		
		
		
		
		/* métodos específicos do spring securiyt*/
		
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			List<GrantedAuthority> authorities = new ArrayList<>();
		    authorities.add(new SimpleGrantedAuthority(this.role));
		    return authorities;
		}
		
		
		@Override
		public String getPassword() {
			return senhaUsuario;
		}

		@Override
		public String getUsername() {
			return emailUsuario;
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}


}
