package fr.univavignon.pokedex.api;

import junit.framework.*;
import org.junit.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

/**
 * Classe de test de l'interface IPokedexFactory.
 */
public class IPokedexFactoryTest extends TestCase {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        User user = Mockito.mock(User.class);
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

    }
}