public class NBody {
    public static double readRadius(String filename){
        In in = new In(filename);

        /* Every time you call a read method from the In class,
         * it reads the next thing from the file, assuming it is
         * of the specified type. */

        /* Compare the calls below to the contents of BasicInDemo_input_file.txt */

        int firstItem = in.readInt();
        double radius = in.readDouble();
        return radius;

    }

    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] p = new Planet[num];
        for(int i=0;i<num;i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            p[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
        }
        return p;


    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering();
        double t = 0;

        Planet[] p =  readPlanets(filename);
        int plen = p.length;

        while (t<=T) {

            Double[] xForces = new Double[plen];
            Double[] yForces = new Double[plen];
            for (int i = 0; i < plen; i++) {
                xForces[i] = p[i].calcNetForceExertedByX(p);
                yForces[i] = p[i].calcNetForceExertedByY(p);
            }

            for (int i = 0; i < plen; i++) {
                p[i].update(dt, xForces[i], yForces[i]);

            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (int i = 0; i < plen; i++) {
                p[i].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            t += dt;

        }
        StdOut.printf("%d\n", p.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < p.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    p[i].xxPos, p[i].yyPos, p[i].xxVel,
                    p[i].yyVel, p[i].mass, p[i].imgFileName);
        }

    }


}
