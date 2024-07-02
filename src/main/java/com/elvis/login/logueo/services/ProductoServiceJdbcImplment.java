package com.elvis.login.logueo.services;

import com.elvis.login.logueo.models.Categoria;
import com.elvis.login.logueo.models.Producto;
import com.elvis.login.logueo.repositories.CategoriaRepositoryJdbcImplement;
import com.elvis.login.logueo.repositories.ProductoRepositoryJdbcImpl;
import com.elvis.login.logueo.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImplment implements ProductoService {
    private Repository<Producto> repositorioJdbc;
    private Repository<Categoria> repositoryCategoriaJdbc;

    public ProductoServiceJdbcImplment(Connection connection) {
        this.repositorioJdbc = new ProductoRepositoryJdbcImpl(connection);
        this.repositoryCategoriaJdbc = new CategoriaRepositoryJdbcImplement(connection);
    }

    @Override
    public List<Producto> listar() {
        try{
            return repositorioJdbc.listar();

        }catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Producto> porId(Integer id) {
        try{
            return Optional.ofNullable(repositorioJdbc.porId(id));
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repositorioJdbc.guardar(producto);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(Integer id) {
        try {
            repositorioJdbc.eliminar(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategoria() {
        try{
            return  repositoryCategoriaJdbc.listar();
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }


    @Override
    public Optional<Producto> poridCategoria(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Categoria> listarCategorias() {

        try{
            return  repositoryCategoriaJdbc.listar();
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Integer id){
        try {
            return Optional.ofNullable(repositoryCategoriaJdbc.porId(id));
        }
        catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }

    }}