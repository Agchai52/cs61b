public class NBody {
	/** Return the radius of the universe*/
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int id = in.readInt();
		double radius = in.readDouble();
        return radius;
	}

	public static Body[] readBodies(String fileName) {
		In in = new In(fileName);
		int totalBodies = in.readInt();
		double radius = in.readDouble();
		Body[] bodies = new Body[totalBodies];

		int i = 0;
		while (i < totalBodies) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, img);
			i += 1;
		}
		return bodies;
	}
    
    public static void main(String[] args) {
    	double T = Double.parseDouble(args[0]);
    	double dt = Double.parseDouble(args[1]);
    	String fileName = args[2];
    	int waitTime = 5;
    	String img_background = "./images/starfield.jpg";
    	double curTime;


    	double radius = readRadius(fileName);
    	Body[] bodies = readBodies(fileName);

    	int N = bodies.length;
    	double[] xForces = new double[N];
    	double[] yForces = new double[N];

    	StdDraw.enableDoubleBuffering();
    	StdDraw.setScale(-radius, radius);
    	StdDraw.clear();

    	/* Draw the background. */
    	StdDraw.picture(0, 0, img_background);  	

    	//int totalBodies = bodies.length;
    	for (Body b: bodies) {
    		b.draw();
    	}

    	StdDraw.show();
    	StdDraw.pause(waitTime);

    	for (curTime = 0.; curTime <= T; curTime += dt) {
    		/* Compute current Forces. */
    		for (int i = 0; i < N; i += 1) {
    			xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
    			yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
    		}

    		/* Update positions of bodies */
    		for (int i = 0; i < N; i += 1) {
    			bodies[i].update(dt, xForces[i], yForces[i]);
    		}
    		
    		/* Draw the background. */
    	    StdDraw.picture(0, 0, img_background);

            /* Draw all the bodies. */
    	    for (Body b: bodies) {
    		    b.draw();
    	    }

    	    StdDraw.show();
    	    StdDraw.pause(waitTime);
    	}

    	StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
        }

	}
}
