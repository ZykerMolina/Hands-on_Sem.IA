package hands.on_4;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import java.util.Arrays;

public class HandsOn_4 extends Agent{
    @Override
    protected void setup(){
        System.out.println("Inicializando el agente: " + getLocalName());
        addBehaviour(new algoritmoGenetico());
    }
    
    private class algoritmoGenetico extends Behaviour{
        private int step = 1, generacion = 0, totalFitness=0;
        int poblacion = 100;
        int cromosomas = 12;
        public int[][] newGenArr = new int[poblacion][cromosomas];
        public int[][] fitnessArr = new int[poblacion][2];
        public int[][] selectionArr = new int[poblacion][cromosomas];
        public int[][] fitnessArr2 = new int[poblacion][2];
        //int[] resultado = {1,0,1,0,1,0,0,0,1,0,1,1,1};//primeros 8 bits para b0 = 168 y 5 bits para b1 = 3
        int[] resultado = {1,1,0,0,1,0,1,1,0,0,1,0};//primeros 6 bits para b0 = 50 y 6 bits para b1 = 10;
        
        @Override
        public void action(){
            switch(step){
                case 1:
                    System.out.println("Paso 1: Populacion inicial");
                    for(int i = 0; i < poblacion; i++) {
                        System.out.println("Arreglo: "+i);
                        for(int x = 0; x < cromosomas; x++) {
                            double value = Math.random();
                            value = Math.round(value);
                            int i_val = (int) value;
                            newGenArr[i][x] = i_val;
                        }
                        System.out.println(Arrays.toString(newGenArr[i]));
                    }
                    break;
                case 2:
                    generacion ++;
                    System.out.println("\nGeneracion: "+ generacion);
                    System.out.println("\nPaso 2: Evaluacion con fitness");
                    totalFitness=0;
                    
                    //Sumatoria fitness
                    for(int i=0; i < newGenArr.length; i++){
                        int x=0,sumatoria=0;
                        for(x=0; x < newGenArr[i].length; x++){
                            if(newGenArr[i][x] == resultado[x]){
                                sumatoria += 1;
                            }
                            
                            //Total fitness
                            if(x == newGenArr[i].length-1){
                                fitnessArr[i][0] = sumatoria;
                                totalFitness = totalFitness + sumatoria;
                            }
                        }
                    }
                    System.out.println("Total Fitness: "+totalFitness);
                    for(int i=0; i < fitnessArr.length; i++){
                        float porcentaje=0;
                        //System.out.println(fitnessArr[i][0]+" * "+100+" / "+totalFitness);
                        porcentaje = fitnessArr[i][0]*100/totalFitness;
                        int porcentajeInt = Math.round(porcentaje);
                            fitnessArr[i][1] = porcentajeInt;
                    }
                    for(int i=0; i < fitnessArr.length; i++){
                        System.out.println("Arreglo: "+i);
                        System.out.println(fitnessArr[i][0]+" "+fitnessArr[i][1]+"%");
                    }
                    break;
                case 3:
                    System.out.println("\nPaso 3: Criterio de paro satisfecho?"); 
                    
                    for(int i=0; i < fitnessArr.length; i++){
                        boolean flag = fitnessArr[i][0] == cromosomas;
                        System.out.println("Arreglo: "+i);
                        System.out.println(fitnessArr[i][0]+" == "+cromosomas+": "+ flag);
                        if(flag == true){
                            System.out.println("Criterio de paro satisfecho!!!\n En la generacion: "+ generacion);
                            System.out.println(Arrays.toString(newGenArr[i]));
                            step = -1;
                            break;
                        }
                    }
                    break;
                case 4:
                    System.out.println("\nPaso 4: Seleccion:");
                    //Metodo Roulette Wheel Selection 
                    int min = fitnessArr[0][0];
                    int max = fitnessArr[0][0];
                    for(int i=0; i < fitnessArr.length; i++){
                        if(fitnessArr[i][0]<min){
                            min = fitnessArr[i][0];
                        }
                        if(fitnessArr[i][0]>max){
                            max = fitnessArr[i][0];
                        }
                    }
                    System.out.println("Rango:"+min+"-"+max);
                    System.out.println("\nMetodo Roulette Wheel Selection");
                    int conteo = 0;
                    for(int i=0; i < fitnessArr.length; i++){
                        int numero = (int) (Math.random()*((max+1)-min)) + min;
                        System.out.println("Numero generado:"+numero+"\n"+fitnessArr[i][0]+" es mayor o igual a: "+numero);
                        if(fitnessArr[i][0] >= numero){
                            System.out.println("Verdadero!!!");
                            System.out.println("Individuo elegido: "+ i);                            
                            selectionArr[conteo] = newGenArr[i];
                            fitnessArr2[conteo] = fitnessArr[i];
                            conteo ++;
                            if(conteo == poblacion){
                                break;
                            }
                        }
                        if(i == fitnessArr.length-1){
                            System.out.println("Reinicio!!");
                            i = -1;
                        }
                    }
                    System.out.println("Arreglo de Seleccion final:");
                    for(int i = 0; i < poblacion; i++) {
                        System.out.println(Arrays.toString(selectionArr[i]));
                    }
                    break;
                case 5:
                    System.out.println("\nPaso 5: Crossover:");
                    int crossoverRate = 5;
                    for(int i=0; i < selectionArr.length; i++){
                        int numero = (int) (Math.random()*((10)-1)) + 1;
                        System.out.println("Numero generado:"+numero+" es mayor o igual a: "+crossoverRate);
                        if(numero >= crossoverRate){
                            System.out.println("Verdadero!!!");
                            //Metodo Roulette Wheel Selection 
                            int min2 = fitnessArr2[0][0];
                            int max2 = fitnessArr2[0][0];
                            for(int i2=0; i2 < fitnessArr2.length; i2++){
                                if(fitnessArr2[i2][0]<min2){
                                    min2 = fitnessArr2[i2][0];
                                }
                                if(fitnessArr2[i2][0]>max2){
                                    max2 = fitnessArr2[i2][0];
                                }
                            }
                            System.out.println("Rango:"+min2+"-"+max2);
                            System.out.println("\nMetodo Roulette Wheel Selection");
                            for(int x=0; x < fitnessArr2.length; x++){
                                int numero2 = (int) (Math.random()*((max2+1)-min2)) + min2;
                                System.out.println("Numero generado:"+numero2+"\n"+fitnessArr2[x][0]+" es mayor o igual a: "+numero2);
                                if(fitnessArr2[x][0] >= numero2){
                                    System.out.println("Verdadero!!!");
                                    System.out.println("Individuo elegido: "+ x);
                                    int crossPoint = (int) (Math.random()*((cromosomas)-1)) + 1;
                                    System.out.println("Padre 1: "+ Arrays.toString(selectionArr[i]));
                                    System.out.println("Padre 2: "+ Arrays.toString(selectionArr[x]));
                                    System.out.println("Punto de cruzamiento: "+ crossPoint);
                                    for(int y=crossPoint; y<cromosomas; y++){
                                        int valor1 = selectionArr[i][y];
                                        int valor2 = selectionArr[x][y];
                                        selectionArr[i][y] = valor2;
                                        selectionArr[x][y] = valor1;
                                    }
                                    System.out.println("Resultado del cruzamiento: ");
                                    System.out.println("Padre 1: "+ Arrays.toString(selectionArr[i]));
                                    System.out.println("Padre 2: "+ Arrays.toString(selectionArr[x]));
                                    break;
                                }
                            }
                        }
                    }
                    break;
                case 6:
                    System.out.println("\nPaso 6: Mutacion:");
                    int mutacionRate = 8;
                    for(int i=0; i < selectionArr.length; i++){
                        int numero = (int) (Math.random()*((10)-1)) + 1;
                        System.out.println("Numero generado:"+numero+" es mayor o igual a: "+mutacionRate);
                        if(numero >= mutacionRate){
                            System.out.println("Verdadero!!!");
                            //Metodo Roulette Wheel Selection 
                            int mutationPoint = (int) (Math.random()*((cromosomas)-1)) + 1;
                            if(selectionArr[i][mutationPoint]==1){
                                selectionArr[i][mutationPoint] = 0;
                            }else{
                                selectionArr[i][mutationPoint] = 1;
                            }
                        }
                    }
                    for(int i=0;i<poblacion;i++){
                        System.out.println("Vieja: "+ Arrays.toString(newGenArr[i]));
                    }                    
                    newGenArr = selectionArr;
                    for(int i=0;i<poblacion;i++){
                        System.out.println("Nueva: "+ Arrays.toString(newGenArr[i]));
                    }
                    //fitnessArr = fitnessArr2;
                    step = 1;
                    break;
            }
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