package fr.univavignon.pokedex.api;

import org.junit.*;

import static org.junit.Assert.*;

import org.mockito.*;

import static org.mockito.Mockito.*;

import org.mockito.junit.*;

/**
 * Classe de test de l'interface IPokedexFactory.
 */
public class IPokedexFactoryTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private static Pokemon pokemonMock = new Pokemon(
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
    private static IPokemonFactory pokemonFactoryMock;

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
