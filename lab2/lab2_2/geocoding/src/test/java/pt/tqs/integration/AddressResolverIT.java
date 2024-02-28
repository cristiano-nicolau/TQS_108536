package pt.tqs.integration;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import pt.tqs.connection.TqsBasicHttpClient;
import pt.tqs.geocoding.Address;
import pt.tqs.geocoding.AddressResolverService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddressResolverIT {

    AddressResolverService resolver;

    @BeforeEach
    public void init(){
        resolver = new AddressResolverService(new TqsBasicHttpClient());
    }

    @Test
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException {

        Optional<Address> address = resolver.findAddressForLocation(40.63436, -8.65616);

        Address expected = new Address("Avenida da Universidade", "Aveiro", "3810-489", "");

        assertTrue(address.isPresent());
        assertEquals(expected, address.get());
    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddrress() throws IOException, URISyntaxException, ParseException {
        Optional<Address> result = resolver.findAddressForLocation(-300, -810);

        assertFalse(result.isPresent());    }

}
