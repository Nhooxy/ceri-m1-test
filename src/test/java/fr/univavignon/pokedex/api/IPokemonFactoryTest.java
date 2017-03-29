package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Classe de test de l'interface IPokemonFactory.
 */
public class IPokemonFactoryTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    protected static IPokemonFactory pokemonFactoryMock;

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

    @Before
    public void setUp() throws PokedexException {
        MockitoAnnotations.initMocks(this);
        when(pokemonFactoryMock.createPokemon(0, 613, 64, 4000, 4)).thenReturn(bulbizarre);
    }

    /**
     * Creates a pokemon instance computing it IVs.
     */
    @Test
    public void testCreatePokemon() {
        Pokemon pokemon = pokemonFactoryMock.createPokemon(0, 613, 64, 4000, 4);
        assertEquals("Bulbizarre", pokemon.getName());
    }
}
