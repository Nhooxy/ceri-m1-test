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

    @Mock
    private Pokemon pokemonMock;

    @Before
    public void setUp() {
        when(pokemonMock.getName()).thenReturn("Bulbizarre");
        when(pokemonMock.getName()).thenThrow(new Exception());
    }

    /**
     * Creates a new pokedex instance using the given
     * <tt>metadataProvider</tt> and <tt>pokemonFactory</tt>.
     *
     * @param metadataProvider Metadata provider the created pokedex will use.
     * @param pokemonFactory   Pokemon factory the created pokedex will use.
     * @return Created pokedex instance.
     */
    @Test
    void testCreatePokedex() {
        assertEquals("Bulbizarre", this.pokemonMock.getName());
    }
}