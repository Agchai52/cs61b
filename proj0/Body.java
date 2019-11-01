public class Body {
	public double xxPos; // cur x pos
	public double yyPos; // cur y pos
	public double xxVel; // cur velocity in the x direction
	public double yyVel; // cur velocity in the x direction
	public double mass;
	public String imgFileName; // file name of image

	/** First Constructor.*/
	public Body(double xP, double yP, double xV, 
		double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

    /** Second Constructor.*/
    public Body(Body b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
    }

    /** Compute the distance between the two bodies. */
    public double calcDistance(Body b) {
    	double dx = b.xxPos - xxPos;
    	double dy = b.yyPos - yyPos;
    	return Math.sqrt(dx * dx + dy * dy);
    }

	private final static double G_CONST = 6.67e-11;
    /** Compute the Force between the two bodies. */
    public double calcForceExertedBy(Body b) {
    	double r = calcDistance(b);
    	return G_CONST * mass * b.mass / (r * r);
	}

	/** Compute the force exerted in the X direction. */
	public double calcNetForceExertedByX(Body[] bs) {
		double dx;
		double r;
		double totalF;
		double sumFx = 0.;
		for (Body b: bs) {
			if (this.equals(b)) {
				continue;
			}
			dx = b.xxPos - xxPos;
			r = calcDistance(b);
			totalF = calcForceExertedBy(b);
			sumFx += totalF * dx / r;
		}
		return sumFx;
	}

	/** Compute the force exerted in the X direction. */
	public double calcNetForceExertedByY(Body[] bs) {
		double dy;
		double r;
		double totalF;
		double sumFy = 0.;
		for (int i = 0; i < bs.length; i++) {
			if (this.equals(bs[i])) {
				continue;
			}
			dy = bs[i].yyPos - yyPos;
			r = calcDistance(bs[i]);
			totalF = calcForceExertedBy(bs[i]);
			sumFy += totalF * dy / r;
		}

		return sumFy;
	}

	/** Update the change of body's velocity and position in 
	    a small period of time. */
	public void update(double dt, double xxF, double yyF) {
		/* Compute Acceleration in the x and y directions. */
		double xxAcc = xxF / mass;
		double yyAcc = yyF / mass;
		
		/* Update Velocities. */
		xxVel += dt * xxAcc;
		yyVel += dt * yyAcc;

		/* Update Positions. */
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}

	/** Draw the body use StdDraw. */
	public void draw() {
		String img_body = "./images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, img_body);
	}
}