package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPokemonTrainerFactoryTest {

    @Mock
    protected static IPokemonTrainerFactory pokemonTrainerFactoryMock;
    @Mock
    protected static IPokedexFactory pokedexFactoryMock;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        IPokedex mockPokedex = mock(IPokedex.class);
        when(mockPokedex.size()).thenReturn(151);
        when(pokemonTrainerFactoryMock
                .createTrainer("Pierre", Team.VALOR, pokedexFactoryMock)
        ).thenReturn(new PokemonTrainer("Pierre", Team.VALOR, mockPokedex));
    }

    /**
     * Creates and returns a PokemonTrainer instance.
     */
    @Test
    public void testCreateTrainer() {
        PokemonTrainer dresseur = pokemonTrainerFactoryMock.createTrainer(
                "Pierre",
                Team.VALOR,
                pokedexFactoryMock
        );
        assertEquals("Pierre", dresseur.getName());
        assertEquals(Team.VALOR, dresseur.getTeam());
        assertEquals(151, dresseur.getPokedex().size());
    }
}
