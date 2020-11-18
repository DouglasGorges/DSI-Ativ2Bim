package br.edu.up.dao;

import br.edu.up.model.Usuario;

import javax.persistence.EntityManager;

public class UsuarioDAO extends GenericDAO<Usuario> {
    protected EntityManager entityManager;

    public UsuarioDAO() {
    }

    public Usuario buscar(Usuario usuario){
        StringBuilder str = new StringBuilder();
        str.append("select u from Usuario u ");
        str.append(" where u.nome = :nome ");
        str.append("  and u.sobrenome = :sobrenome");
        str.append("  and u.dtNascimento = :dtNascimento");

        return (Usuario)super.entityManager.createQuery(str.toString())
                .setParameter("nome",  usuario.getNome())
                .setParameter("sobrenome",  usuario.getSobrenome())
                .setParameter("dtNascimento",  usuario.getDtNascimento())
                .getSingleResult();
    }
}
