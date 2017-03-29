package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

public final class IPokedexTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    protected static IPokedex pokedexMock;

    protected static Pokemon bulbizarre = new Pokemon(
            0,
            "Bulbizarre",
            126,
            126,
            90,
            613,
            64,
            4000,
            4,
            56
    );

    protected static Pokemon aquali = new Pokemon(
            133,
            "Aquali",
            186,
            168,
            260,
            2729,
            202,
            5000,
            4,
            100
    );

    protected static Comparator<Pokemon> comparizonName = Comparator.comparing(PokemonMetadata::getName);
    protected static Comparator<Pokemon> comparizonAtk = Comparator.comparing(PokemonMetadata::getAttack);

    @Before
    public void setUp() throws PokedexException {
        MockitoAnnotations.initMocks(this);
        when(pokedexMock.size()).thenReturn(151);
        when(pokedexMock.getPokemon(0)).thenReturn(bulbizarre);
        when(pokedexMock.getPokemon(200)).thenThrow(new PokedexException("Le pokemon n'existe pas. (index invalide)"));
        List<Pokemon> listeAvecManquant = new ArrayList<>();
        List<Pokemon> liste = new ArrayList<>();
        listeAvecManquant.add(bulbizarre);
        listeAvecManquant.add(aquali);
        for (int i = 2; i < 151; i++) {
            listeAvecManquant.add(new Pokemon(0, "manquant", 0, 0, 0, 0, 0, 0, 0, 0));
        }
        when(pokedexMock.getPokemons()).thenReturn(Collections.unmodifiableList(listeAvecManquant));
        liste.add(aquali);
        liste.add(bulbizarre);
        when(pokedexMock.getPokemons(comparizonName)).thenReturn(Collections.unmodifiableList(liste));
        when(pokedexMock.getPokemons(comparizonAtk)).thenReturn(Collections.unmodifiableList(listeAvecManquant));
    }

    /**
     * Returns the number of pokemon this pokedex contains.
     */
    @Test
    public void testSize() {
        assertEquals(151, pokedexMock.size());
    }

    /**
     * Adds the given <tt>pokemon</tt> to this pokedex and returns
     * it unique index.
     */
    @Test
    public void testAddPokemon() {
        assertEquals(0, pokedexMock.addPokemon(bulbizarre));
    }

    /**
     * Locates the pokemon identified by the given <tt>id</tt>.
     */
    @Test
    public void testGetPokemon() {
        pokedexMock.addPokemon(bulbizarre);

        try {
            assertEquals("Bulbizarre", pokedexMock.getPokemon(0).getName());
        } catch (PokedexException e) {
            e.printStackTrace();
        }
        try {
            pokedexMock.getPokemon(200);
        } catch (PokedexException e) {
            assertEquals("Le pokemon n'existe pas. (index invalide)", e.getMessage());
        }
    }

    /**
     * Returns an unmodifiable list of all pokemons this pokedex contains.
     */
    @Test
    public void testGetPokemons() {
        List<Pokemon> liste = pokedexMock.getPokemons();
        assertEquals(pokedexMock.size(), liste.size());
        try {
            assertEquals(pokedexMock.getPokemon(0).getName(), liste.get(0).getName());
        } catch (PokedexException e) {
            e.printStackTrace();
        }

        try {
            liste.add(bulbizarre);
        } catch (UnsupportedOperationException e) {
            assertTrue(true);
        }
    }

    /**
     * Returns an unmodifiable list of all pokemons this pokedex contains.
     * The list view will be sorted using the given <tt>order</tt>.
     */
    @Test
    public void testGetPokemonsWithComparator() {
        List<Pokemon> liste = pokedexMock.getPokemons();
        List<Pokemon> listeOrdonneNom = pokedexMock.getPokemons(comparizonName);
        List<Pokemon> listeOrdonneAtk = pokedexMock.getPokemons(comparizonAtk);

        assertTrue(liste.indexOf(aquali) >= listeOrdonneNom.indexOf(aquali));
        assertTrue(listeOrdonneNom.indexOf(aquali) <= listeOrdonneAtk.indexOf(aquali));
    }
}
