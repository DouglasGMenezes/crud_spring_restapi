
package br.com.springboot.curso_jdev_treinamento.controllers;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdev_treinamento.mode.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;




@RestController
public class GreetingsController {
	
	@Autowired      /*IC/CD  OU CDI - Injeção de dependencia */
	private UsuarioRepository usuarioRepository;

    
    
    /* Primeiro Método da API ou END-POINT - Método para mostrar as informações */
    
    @GetMapping(value = "listatodos") 
    @ResponseBody // retorna os dados do usuario no corpo da resposta 
    public ResponseEntity<List<Usuario>> listUsuario() {
    	
    	List<Usuario> usuarios = usuarioRepository.findAll(); // Executa a consulta no banco de dados
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); // Retorna a lista em JSON
    }
    
    
    
    // Método para Salvar o Objeto/Usuarios
    
    @PostMapping(value = "salvar") // Mapeia a url 
    @ResponseBody /* Descrição da resposta */
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) { // Recebe os dados para salval
    	
    	Usuario user = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }


    // Método para Deletar Usuarios - end-point
    
    @DeleteMapping(value = "delete") // Mapeia a url 
    @ResponseBody /* Descrição da resposta */
    public ResponseEntity<String> delete(@RequestParam Long iduser) { // recebe os dados para deletar
    	
    	usuarioRepository.deleteById(iduser);
    	
    	return new ResponseEntity<String>("Usuario deletado com sucesso.", HttpStatus.OK);
    }
    

    // Método - End-point para buscar um usuario pelo id
    
    @GetMapping(value = "buscaruserid") // Mapeia a url 
    @ResponseBody /* Descrição da resposta */
    public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "iduser") Long iduser) { 
    	
    	Usuario usuario = usuarioRepository.findById(iduser).get();
    	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    

    // Método - End-point para Atualizar dados de um usuario

    @PutMapping(value = "atualizar") // Mapeia a url 
    @ResponseBody /* Descrição da resposta */
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) { // Recebe os dados atualizar

        if (usuario.getId() == null) {
            return new ResponseEntity<String>("Id não precisa ser informado.", HttpStatus.OK);
        }
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }


    // Método - End-point para buscar os usuarios por nome

    @GetMapping(value = "buscarPorNome") // Mapeia a url 
    @ResponseBody /* Descrição da resposta */
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name= "name") String name) { 
    	
    	List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }


}







