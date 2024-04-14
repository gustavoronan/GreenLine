package app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Cliente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long idCliente;
  private String nomeCliente;
  private String telefoneCliente;
  private String enderecoCliente;
  private String emailCliente;
  private String senhaCliente;
  
}