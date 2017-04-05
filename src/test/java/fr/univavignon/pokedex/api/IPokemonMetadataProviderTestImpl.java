package fr.univavignon.pokedex.api;

public class IPokemonMetadataProviderTestImpl extends IPokemonMetadataProviderTest {
    @Override
    public void setUp() throws PokedexException {
        pokemonMetadataProviderMock = new PokemonMetadataProvider();
    }
}
