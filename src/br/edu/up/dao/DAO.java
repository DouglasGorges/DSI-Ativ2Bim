package br.edu.up.dao;

import java.util.List;

public interface DAO<T> {
    T salvar(T var1);

    T atualizar(T var1);

    T buscarPorId(Integer var1);

    List<T> listar();

    void apagar(Integer var1);
}
