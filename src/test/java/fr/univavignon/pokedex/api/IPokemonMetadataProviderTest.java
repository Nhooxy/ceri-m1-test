package fr.univavignon.pokedex.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.when;

public class IPokemonMetadataProviderTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    protected static IPokemonMetadataProvider pokemonMetadataProviderMock;

    @Before
    public void setUp() throws PokedexException {
        MockitoAnnotations.initMocks(this);
        when(pokemonMetadataProviderMock.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0, "Bulbizarre", 126, 126, 90));
    }

    /**
     * Retrieves and returns the metadata for the pokemon
     * denoted by the given <tt>index</tt>.
     */
    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        Assert.assertEquals("Bulbizarre", pokemonMetadataProviderMock.getPokemonMetadata(0).getName());
    }
}
