package br.edu.up.controller;

import br.edu.up.dao.UsuarioDAO;
import br.edu.up.model.Usuario;

import java.util.Date;
import java.util.List;

public class teste {
    public static void main(String[] args) {
        UsuarioDAO uDao = new UsuarioDAO();
        Usuario usuario = new Usuario("Douglas", "Gorges", new Date());
        uDao.salvar(usuario);

        List<Usuario> listaUsuarios = uDao.listar();
        for(Usuario itemUsuario : listaUsuarios){
            System.out.printf(itemUsuario.toString());
        }
    }
}
