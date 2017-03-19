/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Battle;
import entities.Pokemon;
import entities.Trainer;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author x6095888z
 */
@Stateless
public class stukemonEJB {

    @PersistenceUnit
    EntityManagerFactory emf;

//ej1
    public boolean insertarEntrenador(Trainer t) {
        if (!existeEntrenador(t)) {
            EntityManager em = emf.createEntityManager();
            em.persist(t);
            em.close();
            return true;
        }
        return false;
    }

    public boolean existeEntrenador(Trainer t) {
        EntityManager em = emf.createEntityManager();
        Trainer entrenadorEncontrado = em.find(Trainer.class, t.getName());
        em.close();
        return entrenadorEncontrado != null;
    }

//ej2
    public Trainer encontrarEntrenador(String entrenador) {
        return (Trainer) emf.createEntityManager().createNamedQuery("Trainer.findByName").setParameter("name", entrenador).getSingleResult();
    }

    public boolean tieneSeisPokemon(Trainer t) {
        List<Pokemon> resultado = emf.createEntityManager().createNamedQuery("Pokemon.findByTrainer").setParameter("trainer", t).getResultList();
        return (resultado.size() <= 5);
    }

    public boolean insertPoke(Pokemon p) {
        if (!existePokemon(p)) {
            EntityManager em = emf.createEntityManager();
            em.persist(p);
            em.close();
            return true;
        }
        return false;
    }

    public boolean existePokemon(Pokemon p) {
        EntityManager em = emf.createEntityManager();
        Pokemon pokeEncontrado = em.find(Pokemon.class, p.getName());
        em.close();
        return pokeEncontrado != null;
    }

    public List<Trainer> seleccionEntrenadoresNo6Pokemon() {
        List<Trainer> listaEntrenador = emf.createEntityManager().createNamedQuery("Trainer.findAll").getResultList();
        List<Trainer> entrena = new ArrayList<>();
        listaEntrenador.stream().filter((entrenadorAhora) -> (entrenadorAhora.getPokemonCollection().size() < 6)).forEachOrdered((entrenadorAhora) -> {
            entrena.add(entrenadorAhora);
        });
        return entrena;
    }

    //ej3/7
    public boolean borrarPokemon(String nombre) {
        Pokemon p = encontrarPokemon(nombre);
        EntityManager em = emf.createEntityManager();
        Pokemon pokemonEncontrado = em.find(Pokemon.class, p.getName());
        if (existePokemon(pokemonEncontrado)) {
            em.remove(pokemonEncontrado);
            em.close();
        } else {
            return false;
        }
        return true;
    }

    public Pokemon encontrarPokemon(String nombre) {
        return (Pokemon) emf.createEntityManager().createNamedQuery("Pokemon.findByName").setParameter("name", nombre).getSingleResult();
    }

    public List<Pokemon> seleccionarTodosPokemonOrden() {
        return emf.createEntityManager().createNamedQuery("Pokemon.findAllOrder").getResultList();
    }

    //ej8
    public List<Trainer> seleccionarTodosEntrenadoresOrden() {
        return emf.createEntityManager().createNamedQuery("Trainer.findAllOrder").getResultList();
    }

    //6
    public void actuEntrenador(Trainer t) {
        EntityManager em = emf.createEntityManager();
        em.merge(t);
    }

    public List<Trainer> seleccionarTodosEntrenadores() {
        return emf.createEntityManager().createNamedQuery("Trainer.findAll").getResultList();
    }

    //5
    public List<Trainer> entrenadorConPotis() {
        List<Trainer> todos = emf.createEntityManager().createNamedQuery("Trainer.findAll").getResultList();
        List<Trainer> entrena = new ArrayList<>();
        todos.stream().filter((entrenadorAhora) -> (entrenadorAhora.getPokemonCollection().size() > 0 && entrenadorAhora.getPotions() > 0)).forEachOrdered((entrenadorAhora) -> {
            entrena.add(entrenadorAhora);
        });
        return entrena;
    }

    public List<Pokemon> pokemonPorEntrenador(String name) {
        Trainer t = encontrarEntrenador(name);
        return emf.createEntityManager().createNamedQuery("Pokemon.findByTrainer").setParameter("trainer", t).getResultList();
    }

    public void actuPokemon(Pokemon p) {
        EntityManager em = emf.createEntityManager();
        em.merge(p);
    }
}
