import java.util.ArrayList;
import java.util.List;

public class CityNode {
        String nme;
        List<Flight> flights;

        CityNode(String nme) {
            this.nme = nme;
            this.flights = new ArrayList<>();
        }
    }