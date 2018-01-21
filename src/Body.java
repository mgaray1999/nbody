
public class Body {
	double myXPos;
	double myYPos;
	double myXVel;
	double myYVel;
	double myMass;
	String myFileName;
	
	public static double GRAVITY = 6.67e-11;
	
	public Body(Body p){
		myXPos = p.myXPos;
		myYPos = p.myYPos;
		myXVel = p.myXVel;
		myYVel = p.myYVel;
		myMass = p.myMass;
		myFileName = p.myFileName;
	}
	
	public Body(double xP, double yP, double xV, double yV, double m,
			      String fileName) {
		myXPos = xP;
		myYPos = yP;
		myXVel = xV;
		myYVel = yV;
		myMass = m;
		myFileName = fileName;
	}
	
	public double calcDistance(Body p){
		double xd = myXPos - p.myXPos;
		double yd = myYPos - p.myYPos;
		
		return Math.sqrt(xd*xd + yd*yd);
	}
	
	public double calcForceExertedBy(Body p){
		double d = calcDistance(p);
		return GRAVITY * myMass * p.myMass / (d*d);
	}
	
	public double calcForceExertedByX(Body p){
		double d = calcDistance(p);
		double dx = p.myXPos - myXPos;
		double f = calcForceExertedBy(p);
		return f*dx/d;
	}
	public double calcForceExertedByY(Body p){
		double d = calcDistance(p);
		double dy = p.myYPos - myYPos;
		double f = calcForceExertedBy(p);
		return f*dy/d;
	}
	
	public double calcNetForceExertedByX(Body[] planets){
		double sum = 0.0;
		for(Body p : planets){
			if (! p.equals(this)) {
				sum += calcForceExertedByX(p);
			}
		}
		
		return sum;
	}
	
	public double calcNetForceExertedByY(Body[] planets){
		double sum = 0.0;
		for(Body p : planets){
			if (! p.equals(this)) {
				sum += calcForceExertedByY(p);
			}
		}
		return sum;
	}
	
	public void update(double seconds, double xforce, double yforce){
		double ax = xforce/myMass;
		double ay = yforce/myMass;
		
		double vx = myXVel + seconds*ax;
		double vy = myYVel + seconds*ay;
		
		myXVel = vx;
		myYVel = vy;
		
		double px = myXPos + seconds*myXVel;
		double py = myYPos + seconds*myYVel;
		
		myXPos = px;
		myYPos = py;
	}
	
	public void draw(){
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
}
