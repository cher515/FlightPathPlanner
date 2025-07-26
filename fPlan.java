import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class fPlan {
        List<CityNode> cities = new ArrayList<>();
        List<CityNode> marked = new ArrayList<>();

        void addFlight(String depart, String arrive, double cst, int time) {
            CityNode departCity = custCity(depart);
            CityNode arriveCity = custCity(arrive);


            Flight des = new Flight(departCity, arriveCity, cst, time);
            Flight arr = new Flight(arriveCity, departCity, cst, time);


            departCity.flights.add(des);
            arriveCity.flights.add(arr);
        }

        CityNode custCity(String nme) {
        for (int i = 0; i < cities.size(); i++) {
            CityNode city = cities.get(i);
            if (city.nme.equals(nme)) {
                return city;
            }
        }
        CityNode newCity = new CityNode(nme);
        cities.add(newCity);
        return newCity;
    }

    void fLocate(String depart, String arrive, char ord, PrintWriter writer) {
        CityNode departCity = findCity(depart);
        CityNode arriveCity = findCity(arrive);
        if (departCity == null ) {
            writer.println("No route there " + depart + " to " + arrive);
            return;
        }

        if (arriveCity == null ) {
            writer.println("No route there " + depart + " to " + arrive);
            return;
        }

        List<List<Flight>> sumPaths = new ArrayList<>();
        marked.clear();
        fPaths(departCity, arriveCity, new ArrayList<>(), sumPaths);
        
        if (ord == 'C') {
            for (int i = 0; i < sumPaths.size() - 1; i++) {
                for (int j = 0; j < sumPaths.size() - i - 1; j++) {
                    if (totCost(sumPaths.get(j)) > totCost(sumPaths.get(j + 1))) {
                        List<Flight> temp = sumPaths.get(j);
                        sumPaths.set(j, sumPaths.get(j + 1));
                        sumPaths.set(j + 1, temp);
                    }
                }
            }
        } else {
            for (int i = 0; i < sumPaths.size() - 1; i++) {
                for (int j = 0; j < sumPaths.size() - i - 1; j++) {
                    if (totTime(sumPaths.get(j)) > totTime(sumPaths.get(j + 1))) {
                        List<Flight> temp = sumPaths.get(j);
                        sumPaths.set(j, sumPaths.get(j + 1));
                        sumPaths.set(j + 1, temp);
                    }
                }
            }
        }

        int count = 0;
            for (List<Flight> path : sumPaths) {
                if (count >= 3) break; 
                writer.println("path " + (count + 1) + ": " + custPath(path));
                count++;
            }

}

        double totCost(List<Flight> path) {
            double totalCost = 0;
            for (Flight flight : path) {
                totalCost += flight.cost;
            }
            return totalCost;
        }

        int totTime(List<Flight> path) {
            int totalTime = 0;
            for (Flight flight : path) {
                totalTime += flight.time;
            }
            return totalTime;
        }


        CityNode findCity(String nme) {
        for (int i = 0; i < cities.size(); i++) {
            CityNode city = cities.get(i);
            if (city.nme.equals(nme)) {
                return city;
                }
            }
            return null;
        }

        void fPaths(CityNode curr, CityNode arriveCity, List<Flight> currPath, List<List<Flight>> sumPaths) {
            marked.add(curr);
            if (curr == arriveCity) {
                sumPaths.add(new ArrayList<>(currPath));
            } else {
                for (Flight flight : curr.flights) {
                    if (!marked.contains(flight.arriveCity)) {
                        currPath.add(flight);
                        fPaths(flight.arriveCity, arriveCity, currPath, sumPaths);
                        currPath.remove(currPath.size() - 1);
                    }
                }
            }
            marked.remove(curr);
        }

        String custPath(List<Flight> path) {
            String pString = "";
            for (int i = 0; i < path.size(); i++) {
                Flight flight = path.get(i);
                if (i > 0) pString += " -> ";
                pString += flight.arriveCity.nme;
            }
            pString += ". Time: " + path.stream().mapToInt(f -> f.time).sum();
            pString += " Cost: " + path.stream().mapToDouble(f -> f.cost).sum();
            return pString;
        }
    }