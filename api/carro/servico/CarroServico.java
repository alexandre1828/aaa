package br.com.api.carro.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.carro.modelo.CarroModelo;
import br.com.api.carro.modelo.CarroResposta;
import br.com.api.carro.repositorio.CarroRepositorio;

@Service
public class CarroServico {

    @Autowired
    private CarroRepositorio pr;

    @Autowired
    private CarroResposta rm;

    public Iterable<CarroModelo> listar(){
        return pr.findAll();
    }


    public ResponseEntity<?> cadastrarAlterar(CarroModelo pm, String acao) {

        // Verificando campos obrigatórios
        if (pm.getMarca().equals("")) {
            rm.setMensagem("O nome da marca é obrigatório");
            return new ResponseEntity<CarroResposta>(rm, HttpStatus.BAD_REQUEST);
        } else if (pm.getModelo().equals("")) {
            rm.setMensagem("O nome do modelo é obrigatório");
            return new ResponseEntity<CarroResposta>(rm, HttpStatus.BAD_REQUEST);
        } else if (pm.getAno().equals("")) {
            rm.setMensagem("O ano é obrigatório");
            return new ResponseEntity<CarroResposta>(rm, HttpStatus.BAD_REQUEST);
        } else {
            
            if (acao.equals("cadastrar")) {
                return new ResponseEntity<CarroModelo>(pr.save(pm), HttpStatus.CREATED);
            } else {
               
                return new ResponseEntity<CarroModelo>(pr.save(pm), HttpStatus.OK);
            }
        }
    }
    
    public ResponseEntity<CarroResposta> remover(long codigo) {
        pr.deleteById(codigo);  // Deleta o carro com o código fornecido
    
        rm.setMensagem("O carro foi removido com sucesso");
        return new ResponseEntity<CarroResposta>(rm, HttpStatus.OK);  // Retorna a mensagem de sucesso
    }
    

    
}
