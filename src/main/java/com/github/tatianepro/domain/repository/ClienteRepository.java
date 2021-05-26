package com.github.tatianepro.domain.repository;

import com.github.tatianepro.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    // Query Methods = queries através de convenção nomeada pelo Spring Data
    List<Cliente> findByNomeLike(String nome);

    // Query jpql
    @Query(value = "select c from Cliente c where c.nome like :nome")
    List<Cliente> buscarPorNome(@Param("nome") String nome);

    // Native Query sql
    @Query(value = "select * from cliente c where c.nome like '%:nome%'", nativeQuery = true)
    List<Cliente> encontratPorNome(@Param("nome") String nome);

    void deleteByNome(String nome);

    // Query de escrita
    @Query(value = "delete from Cliente c where c.nome =:nome")
    @Modifying  // queries de modificação: INSERT, UPDATE and DELETE
    void deletarPorNome(String nome);

    List<Cliente> findByNomeLikeOrIdOrderById(String nome, Integer id);

    Boolean existsByNome(String nome);

    // Query com Join
    @Query(value = "select c from Cliente c left join fetch c.pedidos where c.id = :id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);
}
