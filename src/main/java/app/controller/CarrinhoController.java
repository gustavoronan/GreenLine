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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Carrinho;
import app.service.CarrinhoService;

@RequestMapping("/api/carrinho")
@RestController
public class CarrinhoController {
	
	@Autowired
	private CarrinhoService carrinhoService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Carrinho carrinho){

		try {
			
			String mensagem = this.carrinhoService.save(carrinho);
			return new ResponseEntity<>(mensagem,HttpStatus.CREATED);	
			
		} catch (Exception e) {

			return new ResponseEntity<>(null,HttpStatus.BAD_GATEWAY);		

		}

	}

	@GetMapping("/listAll")
	public ResponseEntity <List<Carrinho>> listAll(){

		try {
			List<Carrinho> lista = this.carrinhoService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {			
			return new ResponseEntity<>(null,HttpStatus.BAD_GATEWAY);		

		}

	}

	@GetMapping("/findById/{idCarrinho}")
	public ResponseEntity <Carrinho> findById(@PathVariable long idCarrinho){

		try {
			Carrinho carrinho = this.carrinhoService.findById(idCarrinho);
			return new ResponseEntity<>(carrinho, HttpStatus.OK);
		} catch (Exception e) {			
			return new ResponseEntity<>(null,HttpStatus.BAD_GATEWAY);		

		}

	}
	
	@PutMapping("/update/{idCarrinho}")
	public ResponseEntity<String> update(@RequestBody Carrinho carrinho, @PathVariable long idCarrinho){

		try {
			String mensagem = this.carrinhoService.update(carrinho, idCarrinho);
			return new ResponseEntity<>(mensagem,HttpStatus.OK);		
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_GATEWAY);		

		}

	}
	
	@DeleteMapping("/delete/{idCarrinho}")
	public ResponseEntity<String> delete(@PathVariable long idCarrinho){

		try {
			String mensagem = this.carrinhoService.delete(idCarrinho);
			return new ResponseEntity<>(mensagem,HttpStatus.OK);		
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);		

		}

	}
	
	//consultas DB
	
//	@GetMapping("/findByCarrinho")
//    public ResponseEntity<List<Carrinho>> findByCarrinho (@RequestParam long idCarrinho){
//
//        try {
//
//            List<Carrinho> lista = this.carrinhoService.findByCarrinho(idCarrinho);
//            return new ResponseEntity<>(lista, HttpStatus.OK);
//
//        } catch (Exception e) {
//
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//
//        }
//
//    }
	
//	@GetMapping("/findByNomeProduto")
//	public ResponseEntity<List<Carrinho>> findByProdutoNomeProduto (@RequestParam String nomeProduto){
//		
//		try {
//			
//			List<Carrinho> lista = this.carrinhoService.findByProdutoNomeProduto(nomeProduto);
//			return new ResponseEntity<>(lista, HttpStatus.OK);
//			
//		} catch (Exception e) {
//			
//			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//			
//		}
//		
//	}
	
	@GetMapping("/findByIdCarrinho")
	public ResponseEntity<List<Carrinho>> findByIdCarrinho (@RequestParam long idCarrinho){
		
		try {
			
			List<Carrinho> lista = this.carrinhoService.findByIdCarrinho(idCarrinho);
			return new ResponseEntity<>(lista, HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
	@GetMapping("/buscarVendaAcimaValor")
	public ResponseEntity<List<Carrinho>> buscarVendaAcimaValor (@RequestParam double valorCarrinho){
		
		try {
			
			List<Carrinho> lista = this.carrinhoService.buscarVendaAcimaValor(valorCarrinho);
			return new ResponseEntity<>(lista, HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
	@GetMapping("/buscarVendaAbaixoValor")
	public ResponseEntity<List<Carrinho>> buscarVendaAbaixoValor (@RequestParam double valorCarrinho){
		
		try {
			
			List<Carrinho> lista = this.carrinhoService.buscarVendaAbaixoValor(valorCarrinho);
			return new ResponseEntity<>(lista, HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
}
