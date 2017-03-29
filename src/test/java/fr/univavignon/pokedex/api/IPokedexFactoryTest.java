package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Classe de test de l'interface IPokedexFactory.
 */
public class IPokedexFactoryTest {
    protected static Pokemon pokemonMock = new Pokemon(
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
    @Mock
    protected static IPokemonFactory pokemonFactoryMock;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(pokemonFactoryMock.createPokemon(0, 613, 64, 4000, 4)).thenReturn(pokemonMock);
    }

    /**
     * Creates a new pokedex instance using the given
     * <tt>metadataProvider</tt> and <tt>pokemonFactory</tt>.
     */
    @Test
    public void testCreatePokedex() {
        Pokemon pokemon = pokemonFactoryMock.createPokemon(0, 613, 64, 4000, 4);
        assertEquals("Bulbizarre", pokemon.getName());
    }
}
