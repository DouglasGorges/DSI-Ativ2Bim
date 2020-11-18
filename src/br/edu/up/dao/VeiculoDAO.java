package br.edu.up.dao;

import br.edu.up.model.Usuario;
import br.edu.up.model.Veiculo;

import java.util.List;

public class VeiculoDAO extends GenericDAO<Veiculo> {
    public VeiculoDAO() {
    }

    public List<Veiculo> buscarVeiculosDisponiveis(){
        StringBuilder str = new StringBuilder();
        str.append("select v from Veiculo v ");
        str.append(" where v.idLocado != 1 ");

        return super.entityManager.createQuery(str.toString())
                .getResultList();
    }
}
