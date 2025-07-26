class Flight {
        CityNode departCity;
        CityNode arriveCity;
        double cost;
        int time;

        Flight(CityNode depart, CityNode arrive, double cst, int tme) {
            this.departCity = depart; 
            this.arriveCity = arrive;
            this.cost = cst;
            this.time = tme;
        }
    }