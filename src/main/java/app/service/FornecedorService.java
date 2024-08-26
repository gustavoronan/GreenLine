package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Cliente;
import app.entity.Fornecedor;
import app.repository.FornecedorRepository;

@Service
public class FornecedorService {

        @Autowired
        private FornecedorRepository fornecedorRepository; 
        @Autowired
    	private LogService logService;

        //CRUD


        //Create
        public String save(Fornecedor fornecedor) {
        	
        	//FORÇANDO A VALIDAÇÃO QUE DEVERIA OCORRER AUTOMATICAMENTE PELA ENTITY
        	//if(fornecedor.getCnpj() == null)
        	//	throw new RuntimeException();
        	
        	//FORÇANDO A VALIDAÇÃO QUE DEVERIA OCORRER AUTOMATICAMENTE PELA ENTITY
        //	else if(fornecedor.getCnpj().isBlank())
        //		throw new RuntimeException();
        	
        	
            this.fornecedorRepository.save(fornecedor);
            
            String detalheFornecedor = fornecedor.getNomeFornecedor();
            String formato = "o fornecedor: %s, foi criado";
            String detalhes = String.format(formato, detalheFornecedor);
            logService.gerarLog("SAVE", "Categoria", fornecedor.getIdFornecedor(), detalhes, detalheFornecedor);
            
            return fornecedor.getNomeFornecedor()+" Fornecedor salvo com sucesso!!";
        }


        //Read
        public List<Fornecedor> listAll(){ 
            return this.fornecedorRepository.findAll(); 
        }
        public Fornecedor findById(long idFornecedor) { 
            Fornecedor fornecedor = this.fornecedorRepository.findById(idFornecedor).get(); 
            return fornecedor; 
        }


        //Update
        public String update (long id, Fornecedor fornecedor) { 
            fornecedor.setIdFornecedor(id); 
            Fornecedor fornecedorAntigo = fornecedorRepository.findById(id).get();
            String nomeAntigo = fornecedorAntigo.getNomeFornecedor();
            String cnpjAntigo = fornecedorAntigo.getCnpj();
            String emailAntigo = fornecedorAntigo.getEmailFornecedor();
            
            this.fornecedorRepository.save(fornecedor);
            
            String detalheFornecedor = fornecedor.getNomeFornecedor();
            String formato = "Atualizado: nome antigo: %s novo: %s | cnpj antigo: %S novo: %s | email antigo: %s novo: %s";
            String detalhes = String.format(formato, nomeAntigo, detalheFornecedor, cnpjAntigo, fornecedor.getCnpj(), emailAntigo, fornecedor.getEmailFornecedor());
            logService.gerarLog("UPDATE", "Categoria", fornecedor.getIdFornecedor(), detalhes, detalheFornecedor);
            
            return fornecedor.getNomeFornecedor()+" Atualizado fornecedor com sucesso!!";
        }


        //Delete
        public String delete (long idFornecedor) {
            String detalheFornecedor = fornecedorRepository.findById(idFornecedor).get().getNomeFornecedor();
            logService.gerarLog("DELETE", "Categoria", fornecedorRepository.findById(idFornecedor).get().getIdFornecedor(), null, detalheFornecedor);
            this.fornecedorRepository.deleteById(idFornecedor);
            return " Fornecedor deletado com sucesso";
        }


        //-- Consulta ao SGDB (Read)--

        public List<Fornecedor> findByNomeFornecedor(String nomeFornecedor){
            return this.fornecedorRepository.findByNomeFornecedor(nomeFornecedor);
        }
    }





