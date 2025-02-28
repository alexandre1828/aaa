package br.com.api.carro.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.carro.modelo.CarroModelo;
import br.com.api.carro.modelo.CarroResposta;
import br.com.api.carro.servico.CarroServico;

@RestController
public class CarroControle {

    @Autowired
    private CarroServico ps;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody CarroModelo pm){
        return ps.cadastrarAlterar(pm,"alterar");
    }

    @PutMapping("/alterar")
    public ResponseEntity<?> alterar(@RequestBody CarroModelo pm){
        return ps.cadastrarAlterar(pm, "alterar");
    }


    @GetMapping("/listar")
    public Iterable< CarroModelo> listar(){
        return ps.listar();
    }

    @GetMapping("")

    public String rota(){
        return "api de produtos funcionando";
    }

    @DeleteMapping("/remover/{codigo}")
        public ResponseEntity<CarroResposta> remover(@PathVariable long codigo){
            return ps.remover(codigo);
        }
    }

    

