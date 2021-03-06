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
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Classe de test de l'interface IPokedex.
 */
public class IPokedexTest {

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

    protected static final int[] pokedexSize = {0};

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() throws PokedexException {
        MockitoAnnotations.initMocks(this);
        pokedexSize[0] = 0;
        when(pokedexMock.size()).thenAnswer(i -> pokedexSize[0]);
        when(pokedexMock.addPokemon(any())).then(i -> pokedexSize[0]++);

        when(pokedexMock.getPokemon(0)).thenReturn(bulbizarre);
        when(pokedexMock.getPokemon(1)).thenThrow(new PokedexException("Invalid index"));

        List<Pokemon> list1 = new ArrayList<>();
        List<Pokemon> list2 = new ArrayList<>();
        list1.add(bulbizarre);
        list1.add(aquali);

        when(pokedexMock.getPokemons()).thenReturn(Collections.unmodifiableList(list1));
        list2.add(aquali);
        list2.add(bulbizarre);
        when(pokedexMock.getPokemons(any()))
                .thenReturn(Collections.unmodifiableList(list2))
                .thenReturn(Collections.unmodifiableList(list1));
    }

    /**
     * Returns the number of pokemon this pokedex contains.
     */
    @Test
    public void testSize() {
        assertEquals(0, pokedexMock.size());
    }

    /**
     * Adds the given <tt>pokemon</tt> to this pokedex and returns
     * it unique index.
     */
    @Test
    public void testAddPokemon() {
        assertEquals(0, pokedexMock.addPokemon(bulbizarre));
        assertEquals(1, pokedexMock.size());
        assertEquals(1, pokedexMock.addPokemon(bulbizarre));
        assertEquals(2, pokedexMock.size());
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
            pokedexMock.getPokemon(1);
        } catch (PokedexException e) {
            assertEquals("Invalid index", e.getMessage());
        }
    }

    /**
     * Returns an unmodifiable list of all pokemons this pokedex contains.
     */
    @Test
    public void testGetPokemons() throws PokedexException {
        pokedexMock.addPokemon(bulbizarre);
        pokedexMock.addPokemon(aquali);
        List<Pokemon> list = pokedexMock.getPokemons();

        assertEquals(pokedexMock.size(), list.size());
        assertEquals(pokedexMock.getPokemon(0).getName(), list.get(0).getName());

        try {
            list.add(bulbizarre);
            fail("Expected UnsupportedOperationException to be thrown");
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns an unmodifiable list of all pokemons this pokedex contains.
     * The list view will be sorted using the given <tt>order</tt>.
     */
    @Test
    public void testGetPokemonsWithComparator() {
        pokedexMock.addPokemon(bulbizarre);
        pokedexMock.addPokemon(aquali);
        List<Pokemon> listOrderedWithName = pokedexMock.getPokemons(PokemonComparators.NAME);
        List<Pokemon> listOrderedWithIndex = pokedexMock.getPokemons(PokemonComparators.INDEX);

        assertEquals(0, listOrderedWithName.indexOf(aquali));
        assertEquals(1, listOrderedWithIndex.indexOf(aquali));
    }
}
