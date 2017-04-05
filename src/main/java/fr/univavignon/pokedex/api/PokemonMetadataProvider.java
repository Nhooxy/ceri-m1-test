package fr.univavignon.pokedex.api;

import com.google.gson.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * An IPokemonMetadataProvider aims to provide PokemonMetadata
 * for a given pokemon index.
 *
 * @author Pierre Perez
 */
public class PokemonMetadataProvider implements IPokemonMetadataProvider {
    private JsonArray doc = null;

    /**
     * Retrieves and returns the metadata for the pokemon
     * denoted by the given <tt>index</tt>.
     *
     * @param index Index of the pokemon to retrieve metadata for.
     * @return Metadata of the pokemon.
     * @throws PokedexException If the given <tt>index</tt> is not valid.
     */
    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        if (null == this.doc) {
            this.parseData();
        }
        JsonObject pokemon = doc.get(index-1).getAsJsonObject();

        return new PokemonMetadata(
                index,
                pokemon.get("Name").getAsString(),
                pokemon.get("BaseAttack").getAsInt(),
                pokemon.get("BaseDefense").getAsInt(),
                pokemon.get("BaseStamina").getAsInt()
        );
    }

    private void parseData() {
        HttpURLConnection request = null;
        URL url = null;
        final String url1 = "https://raw.githubusercontent.com/PokemonGoF/PokemonGo-Bot/master/data/pokemon.json";
        JsonParser jp = new JsonParser();
        JsonElement root = null;

        try {
            url = new URL(url1);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            request = (HttpURLConnection) url.openConnection();
            request.connect();
            root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        doc = root.getAsJsonArray();
    }
}
