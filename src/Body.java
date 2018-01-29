import java.math.*;
public class Body {
	double myXPos; //current x position
	double myYPos; //current y position
	double myXVel; //current velocity in x direction
	double myYVel; //current velocity in y direction
	double myMass; //mass of body
	String myFileName; //file name (in images folder)
	
	public Body(double xp, double yp, double xv, double yv, double mass, String filename) {
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}
	
	public Body(Body p) {
		myXPos = p.myXPos;
		myYPos = p.myYPos;
		myXVel = p.myXVel;
		myYVel = p.myYVel;
		myMass = p.myMass;
		myFileName = p.myFileName;
	}
	
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
	
	public double calcDistance(Body p) {
		double xpos = this.myXPos;
		double ypos = this.myYPos;
		double distance = (p.myXPos - xpos)*(p.myXPos - xpos) +  
				(p.myYPos - ypos)*(p.myYPos - ypos);
		return Math.sqrt(distance);
	}
	
	public double calcForceExertedBy(Body p) {
		double g = 6.67 * Math.pow(10, -11); 
		double force = g*(p.myMass * this.myMass)/Math.pow(this.calcDistance(p), 2);
		return force;
	}
	
	public double calcForceExertedByX(Body p) {
		double xpos = this.myXPos;
		double dist = this.calcDistance(p);
		double xforce = this.calcForceExertedBy(p) * (p.myXPos - xpos) / dist;
		return xforce;
	}
	
	public double calcForceExertedByY(Body p) {
		double ypos = this.myYPos;
		double dist = this.calcDistance(p);
		double yforce = this.calcForceExertedBy(p) * (p.myYPos - ypos) / dist;
		return yforce;
	}
	
	public double calcNetForceExertedByX(Body[] p) {
		double sum = 0;
		for (int i = 0; i < p.length; i++) {
			if (! p[i].equals(this)) {
				sum += calcForceExertedByX(p[i]);
			}
		}
		return sum;
	}
	
	public double calcNetForceExertedByY(Body[] p) {
		double sum = 0;
		for (int i = 0; i < p.length; i++) {
			if (! p[i].equals(this)) {
				sum += calcForceExertedByY(p[i]);
			}
		}
		return sum;
	}
	
	public void update(double seconds, double xforce, double yforce) {
		double ax = xforce / this.myMass;
		double ay = yforce / this.myMass;
		double xvelo = this.myXVel + seconds * ax;
		double yvelo = this.myYVel + seconds * ay;
		double xpos = this.myXPos + seconds * xvelo;
		double ypos = this.myYPos + seconds * yvelo;
		
		this.myXVel = xvelo;
		this.myYVel = yvelo;
		this.myXPos = xpos;
		this.myYPos = ypos;
	}
	
	
}
