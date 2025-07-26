# ✈️ FlightPath Planner

FlightPath Planner is a Java app that reads flight data and route requests from files, then finds and outputs the best routes based on cost or time. It showcases file I/O, graph traversal, and sorting logic to simulate basic flight planning.

---

📂 Project Structure
Main.java – Program entry point

fPlan.java – Flight planner logic and route calculation

Flight.java – Flight data model

CityNode.java – City node class used for graph structure

data.txt – Input file containing flight data

req.txt – Input file with route requests

output.txt – Output file where results are written


---

## 🧪 Example Features

- Parses and builds a flight graph
- Supports sorting routes by **time** or **cost**
- Outputs results to a cleanly formatted file

---

## 🛠 How to Compile

Make sure you have **Java installed** (Java 8 or later).

In the root folder of the project, open a terminal or command prompt and run:

```bash
javac Main.java
This compiles all required .java files.

▶️ How to Run
Run the program using:

bash
Copy
Edit
java Main data.txt req.txt output.txt
data.txt → contains flight data

req.txt → contains route queries

output.txt → the program writes results here

📄 Example Output
bash
Copy
Edit
TESTING
IT WORKS output.txt
Then check output.txt for the results.

📌 Notes
Do not use < redirection; the program uses command-line arguments.

Ensure data.txt and req.txt follow the expected format with |-delimited fields.

All files must be in the same folder as the .class files unless specified.
