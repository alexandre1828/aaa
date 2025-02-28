package br.com.api.carro.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.api.carro.modelo.CarroModelo;

@Repository
public interface  CarroRepositorio extends CrudRepository<CarroModelo, Long> {

    
}
