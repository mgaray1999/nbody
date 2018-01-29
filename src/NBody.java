import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {
	public static void main(String[] args) {
		double totalTime = Math.pow(10, 9); //157788000.0; 
		double dt = Math.pow(10, 6); //25000.0;
		String pfile = "data/planets.txt";
		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			pfile = args[2];
		}

		// Read simulation data from file
		Body[] planets = readBodies(pfile); // readBodies(pfile);
		double radius = readRadius(pfile); // readRadius(pfile);

		// TODO Draw the background
		StdDraw.setScale(-radius,radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");
		
		//draws all the bodies
		for (int i = 0; i < planets.length; i++) {
			planets[i].draw();
		}

		// TODO Animate the simulation from time 0 until totalTime
		double time = 0.0;
		for (double i = time; i < totalTime; i += dt) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for (int z = 0; z < planets.length; z++) {
				double netx = planets[z].calcNetForceExertedByX(planets);
				double nety = planets[z].calcNetForceExertedByY(planets);
				xForces[z] = netx;
				yForces[z] = nety;
			}
			for (int x = 0; x < planets.length; x++) {
				planets[x].update(dt, xForces[x], yForces[x]);
			}
			
			//draws the background
			StdDraw.setScale(-radius,radius);
			StdDraw.picture(0, 0, "images/starfield.jpg");
			
			//draws all the bodies
			for (int y = 0; y < planets.length; y++) {
				planets[y].draw();
			}
			StdDraw.show(10);
			if (time == 1000000 || time == 2000000) {
				System.out.printf("%d\n", planets.length);
				System.out.printf("%.2e\n", radius);
				for (int w = 0; w < planets.length; w++) {
					System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", 
									  planets[w].myXPos, planets[w].myYPos,
									  planets[w].myXVel, planets[w].myYVel, 
									  planets[w].myMass, planets[w].myFileName);
				}
			}
			time += dt;
			
		}

		// Print final positions of planets
		System.out.printf("%d\n", planets.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", 
							  planets[i].myXPos, planets[i].myYPos,
							  planets[i].myXVel, planets[i].myYVel, 
							  planets[i].myMass, planets[i].myFileName);
		}
	} // end main method
	
	public static double readRadius(String fname) {
		double radius = 0.0;
		try {
			Scanner scan = new Scanner(new File(fname));
			scan.nextInt();
			radius = scan.nextDouble();
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			System.exit(0);
		}
		return radius;
	}
	
	public static Body[] readBodies(String fname) {
		try {
			Scanner scan = new Scanner(new File(fname));
			int numbodies = scan.nextInt(); // reads the number of planets
			scan.nextDouble(); // reads and moves past the radius
			Body[] p = new Body[numbodies];
			for (int i = 0; i < numbodies; i++) {
				double xPos = scan.nextDouble();
				double yPos = scan.nextDouble();
				double xVel = scan.nextDouble();
				double yVel = scan.nextDouble();
				double myMass = scan.nextDouble();
				String myFileName = scan.next();
				
				Body q = new Body(xPos, yPos, xVel, yVel, myMass, myFileName);
				p[i] = q;
			}
			scan.close();
			return p;
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			Body[] a = null;
			System.exit(0);
			return a;
		}
	}
}
