package hands.on_5;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import java.util.Arrays;

public class HandsOn_5 extends Agent{
    @Override
    protected void setup(){
        System.out.println("Inicializando el agente: " + getLocalName());
        addBehaviour(new algoritmoGenetico());
    }
    
    private class algoritmoGenetico extends Behaviour{
        private int step = 1;
        double W=0.9;
        double C1=2,C2=2;
        int iteracionesMax = 0;
        double velocidad = 0.1;
        int particulas = 5;
        int variables = 3;
        double gBest = 0;
        int gBestP = 0;
        int bandera = 0;
        public double[][] particulasArr = new double[particulas][variables];
        /*public double[][] particulasArr = {
                        {1,23,651},
                        {2,26,762},
                        {3,30,856},
                        {4,34,1063},
                        {5,43,1190},
                        {6,48,1298},
                        {7,52,1421},
                        {8,57,1440},
                        {9,58,1518}
                    };
        /*public double[][] particulasArr = {
                        {8,9,1},
                        {9,6,1},
                        {3,5,10},
                        {10,2,10},
                        {10,5,8},
                    };*/
        public double[][] velocidadArr = new double[particulas][variables];
        public double[] fitnessArr = new double[particulas];
        public double[][] mejorPosicion = new double[particulas][variables];
        public double[] mejorfitness = new double[particulas];
        
        
        @Override
        public void action(){
            switch(step){
                case 1:
                    System.out.println("Paso 1: Populacion inicial");
                    System.out.println("\nArreglo de particulas");
                    for(int i = 0; i < particulas; i++) {
                        for(int x = 0; x < variables; x++) {
                            double value = Math.random()* (10 - 1) + 1;
                            value = Math.round(value);
                            int i_val = (int) value;
                            particulasArr[i][x] = i_val;
                        }
                        System.out.println(Arrays.toString(particulasArr[i]));
                    }
                    System.out.println("\nArreglo de velocidades Inicial ");
                    for(int i = 0; i < particulas; i++) {
                        for(int x = 0; x < variables; x++) {
                            double particula = particulasArr[i][x];
                            double i_val = particula*velocidad;
                            velocidadArr[i][x] = i_val;
                        }
                        System.out.println(Arrays.toString(velocidadArr[i]));
                    }
                    System.out.println("\nSumatoria particula + velocidad");
                    for(int i = 0; i < particulas; i++) {
                        for(int x = 0; x < variables; x++) {
                            double particula = particulasArr[i][x];
                            double v = velocidadArr[i][x];
                            double i_val = particula + v;
                            particulasArr[i][x] = i_val;
                        }
                        System.out.println(Arrays.toString(particulasArr[i]));
                    }
                    System.out.println("\nPaso 2: Evalucion fitness");
                    for(int i = 0; i < particulas; i++) {
                        double i_val = 0;
                        for(int x = 0; x < variables; x++) {
                            int X;
                            double particula = particulasArr[i][x];
                            X = 10*(x+1);
                            i_val = i_val + X*(Math.pow((particula - (x+1)),2));
                            fitnessArr[i] = i_val;
                        }
                        System.out.println(fitnessArr[i]);
                    }

                    gBest = fitnessArr[0];
                    for(int i = 0; i < particulas; i++) {
                        if(fitnessArr[i] < gBest){
                            gBest = fitnessArr[i];
                            gBestP = i;
                        }
                    }
                    System.out.println("\ngBest:");
                    System.out.println(gBest);
                    if(bandera == 0){
                        for(int i = 0; i < particulas; i++) {
                            mejorfitness[i] = fitnessArr[i];
                            mejorPosicion[i] = particulasArr[i];
                        }
                    }
                    break;
                    
                    
                case 2:
                    System.out.println("\nPaso 3: Actualizacion de la velocidad y Posicion");
                    System.out.println("\nActualizacion de la velocidad");
                    for(int i = 0; i < particulas; i++) {
                        for(int x = 0; x < variables; x++) {
                            double r1 = Math.random();
                            double r2 = Math.random();
                            double var1 = W*velocidadArr[i][x];
                            double var2 = (C1*r1)*(mejorPosicion[i][x]-particulasArr[i][x]);
                            double var3 = (C2*r2)*(particulasArr[gBestP][x]-particulasArr[i][x]);
                            double i_val = var1+var2+var3;
                            velocidadArr[i][x] = i_val;
                        }
                        System.out.println(Arrays.toString(velocidadArr[i]));
                    }
                    System.out.println("\nActualizacion de la posicion");
                    for(int i = 0; i < particulas; i++) {
                        for(int x = 0; x < variables; x++) {
                            double particula = particulasArr[i][x];
                            double v = velocidadArr[i][x];
                            double i_val = particula + v;
                            particulasArr[i][x] = i_val;
                        }
                        System.out.println(Arrays.toString(particulasArr[i]));
                    }
                    System.out.println("\nEvalucion fitness");
                    for(int i = 0; i < particulas; i++) {
                        double i_val = 0;
                        for(int x = 0; x < variables; x++) {
                            int X;
                            double particula = particulasArr[i][x];
                            X = 10*(x+1);
                            i_val = i_val + X*(Math.pow((particula - (x+1)),2));
                            fitnessArr[i] = i_val;
                        }
                        System.out.println(fitnessArr[i]);
                    }
                    for(int i = 0; i < particulas; i++) {
                        if(fitnessArr[i] < gBest){
                            gBest = fitnessArr[i];
                            gBestP = i;
                        }
                    }
                    System.out.println("\ngBest:");
                    System.out.println(gBest);
                    System.out.println(particulasArr[gBestP][0]+" "+particulasArr[gBestP][1]+" "+particulasArr[gBestP][2]);
                    
                    if (gBest == 0 ){
                        step = -1;
                    }else{
                        step = 1;
                    }
                    System.out.println("\nActualizacion de mejor posicion:");
                    for(int i = 0; i < particulas; i++) {
                        System.out.println("\nMejor Posicion: "+mejorfitness[i]);
                        System.out.println("Posicion actual: "+fitnessArr[i]);
                        if(fitnessArr[i] < mejorfitness[i]){
                            mejorfitness[i] = fitnessArr[i];
                            mejorPosicion[i] = particulasArr[i];
                            System.out.println("Mejor Posicion Actualizada: "+mejorfitness[i]);
                        }else{
                            System.out.println("Mejor Posicion Conservada: "+mejorfitness[i]);
                        }
                    }
                    break;
            }
            iteracionesMax++;
            step++;
        }
        @Override
        public boolean done(){
            return step == 0;
        }
        @Override
        public int onEnd(){
            myAgent.doDelete();
            return super.onEnd();
        }
    }
}