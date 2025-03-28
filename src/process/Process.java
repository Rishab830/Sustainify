package process;

import java.util.Random;

public class Process {

    public static boolean contains(String s, char c){
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==c){
                return true;
            }
        }
        return false;
    }

    public static Object[] optimize(int Bmax, double Emin, double Smin, double SEmin) {
        boolean found = false;
        int[] k = new int[6];
        int[][] Cmp = {{2,4,3,1,2,4},{4,3,7,5,2,1},{3,4,6,3,5,3},{7,5,6,8,9,4},{9,5,8,7,4,8}};
        int[][][] SimpMin = {
                {{2,1,1,1,1,3},{2,2,3,2,1,2},{3,3,4,3,2,2},{4,3,3,3,2,3},{4,3,3,2,1,1}},
                {{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1}},
                {{1,1,1,1,1,2},{1,1,1,1,1,1},{1,1,1,2,2,1},{1,1,1,2,1,2},{1,1,1,1,1,1}},
                {{2,1,1,1,1,4},{2,2,3,2,1,2},{3,3,4,3,2,2},{4,3,2,3,3,4},{4,3,3,2,2,1}},
                {{2,1,1,1,1,3},{2,1,2,1,1,1},{3,2,2,2,2,1},{4,2,1,3,4,3},{4,2,2,3,4,1}},
                {{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,2,1},{1,1,1,2,3,1}},
                {{2,1,1,1,1,3},{3,1,3,2,1,1},{4,2,4,3,3,1},{4,2,2,4,4,3},{4,2,4,3,4,2}},
                {{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1}},
                {{3,1,1,1,1,2},{3,1,2,1,1,1},{4,1,3,1,1,1},{5,1,1,1,1,2},{5,1,2,1,1,1}},
                {{2,2,1,1,1,3},{2,3,4,2,1,1},{3,4,5,3,3,2},{4,4,3,4,1,3},{4,4,4,1,1,1}},
                {{1,1,1,1,1,2},{1,1,2,1,1,1},{1,2,3,2,2,1},{1,2,2,3,3,3},{1,2,2,2,3,1}},
                {{3,1,1,1,1,3},{4,1,3,2,1,1},{4,2,4,3,3,1},{5,2,2,4,3,3},{5,2,3,2,2,2}},
        };
        int[][][] SimpMax = {
                {{3,2,1,2,2,4},{4,3,4,3,3,3},{5,4,5,4,4,4},{5,4,4,5,2,5},{5,5,5,5,5,3}},
                {{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1}},
                {{2,1,1,1,1,3},{2,2,2,2,2,2},{2,2,2,3,3,2},{2,2,2,4,5,3},{2,2,2,5,5,2}},
                {{3,2,1,2,2,5},{4,3,4,3,3,3},{5,4,5,4,4,3},{5,4,3,5,5,5},{5,4,4,4,5,3}},
                {{3,1,1,1,1,4},{4,2,3,2,2,2},{5,3,4,3,3,1},{1,3,2,4,5,4},{5,4,3,5,5,2}},
                {{1,1,1,1,1,1},{1,1,2,1,1,1},{1,1,2,2,2,1},{5,1,1,3,4,1},{1,1,2,4,5,2}},
                {{2,1,1,2,2,4},{3,2,4,3,3,2},{4,3,5,4,4,2},{1,3,3,5,5,4},{5,3,5,4,5,3}},
                {{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{5,1,1,1,1,1},{1,1,1,1,1,1}},
                {{3,1,1,1,1,3},{4,1,3,1,1,1},{5,1,4,2,2,1},{5,1,2,2,2,3},{5,1,3,3,2,1}},
                {{3,3,1,2,1,4},{4,4,5,4,3,2},{5,5,5,5,4,3},{5,5,4,5,5,5},{5,5,5,5,5,3}},
                {{1,2,1,1,1,3},{1,3,3,2,1,1},{1,4,4,4,4,1},{1,4,3,5,5,4},{1,4,4,5,5,2}},
                {{4,1,1,2,2,2},{4,2,4,3,2,2},{5,3,5,4,4,2},{5,3,3,5,4,4},{5,3,4,4,4,3}},
        };
        int Bopt=0;
        double rfopt=0.0,rf=0.0;
        double Eoptmin=0,Soptmin=0,SEoptmin=0;
        double Eoptmax=0,Soptmax=0,SEoptmax=0;
        double Ecurrmin,Scurrmin,SEcurrmin;
        double Ecurrmax,Scurrmax,SEcurrmax;
        double Zcurrmin,Zopt=0;
        int[] SiMin = new int[12];
        int[] SiMax = new int[12];
        Random rand = new Random();
        int[][]Xopt = new int[5][6];							//Defined inputs have been inputed


        for(int it=0;it<100000;it++) {							//The iterative process has started
            int[][]Xcurr = new int[5][6];
            for(int p=0;p<6;p++) {
                k[p] = rand.nextInt(1, 6);
                Xcurr[k[p]-1][p] = 1;
            }													//Xcurr has been generated
            //k has been generated

            int Bcurr = 0;
            for(int m=0;m<5;m++) {
                for(int p=0;p<6;p++) {
                    Bcurr+=Cmp[m][p]*Xcurr[m][p];
                }
            }													//Bcurr has been calculated

            if(Bcurr>Bmax) {
                continue;
            }													//Budget condition checking

            for(int i=0;i<12;i++) {
                int sumMin=0;
                int sumMax=0;
                for(int p=0;p<6;p++) {
                    for(int m=0;m<5;m++) {
                        sumMin+=SimpMin[i][m][p]*Xcurr[m][p];
                        sumMax+=SimpMax[i][m][p]*Xcurr[m][p];
                    }
                }
                SiMin[i]=sumMin;
                SiMax[i]=sumMax;
            }													//Simin and Simax has been calculated

            Ecurrmin = SiMin[0]+SiMin[1]+SiMin[2]+SiMin[3];
            Scurrmin = SiMin[4]+SiMin[5]+SiMin[6]+SiMin[7];
            SEcurrmin = SiMin[8]+SiMin[9]+SiMin[10]+SiMin[11];

            Ecurrmax = SiMax[0]+SiMax[1]+SiMax[2]+SiMax[3];
            Scurrmax = SiMax[4]+SiMax[5]+SiMax[6]+SiMax[7];
            SEcurrmax = SiMax[8]+SiMax[9]+SiMax[10]+SiMax[11];

            Zcurrmin = Ecurrmin + Scurrmin + SEcurrmin;			//Simin and Simax has been turned into readable
                                                                //formats like Scurrmin, Ecurrmin, etc.

            if(Ecurrmin<Emin||Scurrmin<Smin||SEcurrmin<SEmin) {
                if(Ecurrmax<Emin||Scurrmax<Smin||SEcurrmax<SEmin) {
                    continue;
                }
                double sum=0;
                for(int i=0;i<12;i++){
                    sum+= (double) (SiMax[i] - SiMin[i]) /(30-SiMin[i]);
                }
                rf = sum/12;
                if(rf>0.5) {
                    continue;
                }
            }													//Minimum performance condition checking and
            //risk factoring

            if(Zcurrmin<=Zopt) {
                continue;
            }
            Zopt=Zcurrmin;
            Xopt=Xcurr;
            Eoptmin=Ecurrmin;
            Soptmin=Scurrmin;
            SEoptmin=SEcurrmin;
            Eoptmax=Ecurrmax;
            Soptmax=Scurrmax;
            SEoptmax=SEcurrmax;
            Bopt=Bcurr;
            rfopt=rf;
            found = true;
            System.out.println("Output found, searching for more...");
        }														//Iterative process has ended
        return new Object[]{Zopt,Xopt,Eoptmin,Soptmin,SEoptmin,Eoptmax,Soptmax,SEoptmax,Bopt,rfopt,found};
    }
}
