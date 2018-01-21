import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {
	public static void main(String[] args) {
		double totalTime = 157788000.0;
		double dt = 25000.0;
		String pfile = "data/planets.txt";
		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			pfile = args[2];
		}

		// Read simulation data from file
		Planet[] planets = null; // readPlanets(pfile);
		double radius = 0.0; // readRadius(pfile);

		// TODO Draw the background

		// TODO Animate the simulation from time 0 until totalTime

		// Print final positions of planets
		System.out.printf("%d\n", planets.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", 
							  planets[i].myXPos, planets[i].myYPos,
							  planets[i].myXVel, planets[i].myYVel, 
							  planets[i].myMass, planets[i].myFileName);
		}
	}
}
