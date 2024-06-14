package dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MesValorDTO {
	private String mes;
    private Double valorTotal;
    
    @Override
    public String toString() {
        return "MesValorDTO{" +
                "mes='" + mes + '\'' +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
