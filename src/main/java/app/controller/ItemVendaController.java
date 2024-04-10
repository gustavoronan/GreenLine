package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.ItemVenda;
import app.service.ItemVendaService;

@RequestMapping("/api/itemvenda")
@RestController
public class ItemVendaController {
	
	@Autowired
	private ItemVendaService itemVendaService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody ItemVenda itemVenda){

		try {
			
			String mensagem = this.itemVendaService.save(itemVenda);
			return new ResponseEntity<>(mensagem,HttpStatus.CREATED);	
			
		} catch (Exception e) {

			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);		

		}

	}

	@GetMapping("/listAll")
	public ResponseEntity <List<ItemVenda>> listAll(){

		try {
			List<ItemVenda> lista = this.itemVendaService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.CREATED);
		} catch (Exception e) {			
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);		

		}

	}

	@GetMapping("/findById/{idItemVenda}")
	public ResponseEntity <ItemVenda> findById(@PathVariable long idItemVenda){

		try {
			ItemVenda itemVenda = this.itemVendaService.findById(idItemVenda);
			return new ResponseEntity<>(itemVenda, HttpStatus.CREATED);
		} catch (Exception e) {			
			return new ResponseEntity<>(null,HttpStatus.BAD_GATEWAY);		

		}

	}
	
	@PutMapping("/update/{idItemVenda}")
	public ResponseEntity<String> update(@RequestBody ItemVenda itemVenda, @PathVariable long idItemVenda){

		try {
			String mensagem = this.itemVendaService.update(itemVenda, idItemVenda);
			return new ResponseEntity<>(mensagem,HttpStatus.OK);		
		} catch (Exception e) {

			return new ResponseEntity<>(null,HttpStatus.BAD_GATEWAY);		

		}

	}
	
	@DeleteMapping("/delete/{idItemVenda}")
	public ResponseEntity<String> delete(@PathVariable long idItemVenda){

		try {
			String mensagem = this.itemVendaService.delete(idItemVenda);
			return new ResponseEntity<>(mensagem,HttpStatus.OK);		
		} catch (Exception e) {

			return new ResponseEntity<>(null,HttpStatus.BAD_GATEWAY);		

		}

	}
}
