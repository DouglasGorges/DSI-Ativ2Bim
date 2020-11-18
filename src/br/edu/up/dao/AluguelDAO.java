package br.edu.up.dao;

import br.edu.up.model.Aluguel;
import br.edu.up.model.Usuario;
import br.edu.up.model.Veiculo;

import java.util.List;

public class AluguelDAO extends GenericDAO<Aluguel> {
    public AluguelDAO() {
    }

    public List<Aluguel> buscarAlugueisAtivos(Usuario usuario){
        StringBuilder str = new StringBuilder();
        str.append("select a from Aluguel a ");
        str.append(" join fetch a.usuario u ");
        str.append(" join fetch a.veiculo v ");
        str.append(" where a.usuario.id = :idUsuario ");
        str.append("  and a.dtDevolucao is null ");

        return super.entityManager.createQuery(str.toString())
                .setParameter("idUsuario",  usuario.getId())
                .getResultList();
    }
}
