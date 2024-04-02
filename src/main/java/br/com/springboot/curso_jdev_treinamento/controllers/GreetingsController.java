package br.com.springboot.curso_jdev_treinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Curso Spring Boot API: " + name + "!";
    }
    
    
    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK) // resposta do servidor
    public String retornaOlaMundo(@PathVariable String nome) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	
    	usuarioRepository.save(usuario); // grava no banco de dados
    	
    	return "Olá mundo: " + nome;
    }
    
    
    
    @GetMapping(value = "listatodos") // PRIMEIRO METODO DO CRUD
    @ResponseBody // retorna os dados para o corpo da resposta
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	
    	List<Usuario> usuario = usuarioRepository.findAll(); //EXECUTA A CONSULTA NO DB
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK); // retorna a lista em JSON
    	
    }
    
    
    @PostMapping(value = "salvar") // mapear a url
    @ResponseBody //descrição da resposta
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){ // recebe o objeto
    	
    	Usuario user = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    	
    }
    
    
    
    @DeleteMapping(value = "deletar") // mapear a url
    @ResponseBody //descrição da resposta
    public ResponseEntity<String> deletar(@RequestParam Long idUser){ // recebe o objeto
    	
    	usuarioRepository.deleteById(idUser);
    	
    	return new ResponseEntity<String>("Usuario deletado com sucesso! ", HttpStatus.OK);
    	
    }
    
    
}









