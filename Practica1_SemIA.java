package practica1_semia;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class Practica1_SemIA extends Agent {

  @Override
  protected void setup() {
    System.out.println("Inicializando el agente: " + getLocalName());
    addBehaviour(new MyOneShotBehaviour());
  } 

  private class MyOneShotBehaviour extends OneShotBehaviour {

    @Override
    public void action() {
        
        /*int[][] datos = {
            {1,3},
            {2,6},
            {3,9},
            {4,12},
            {5,15},
            {6,18},
            {7,21},
            {8,24},
            {9,27}
        };*/
        int[][] datos = {
            {23,651},
            {26,762},
            {30,856},
            {34,1063},
            {43,1190},
            {48,1298},
            {52,1421},
            {57,1440},
            {58,1518}
        };
        int i;
        float b1=0,b0=0,y0=0,y1=0,y2=0,xy=0,x=0,y=0,x2=0,X0=60,X1=70,X2=80;;
        System.out.println("\nArreglo inicial:");
        for(i=0; i<datos.length; i++){
            System.out.println(i+1+"    "+datos[i][0]+"    "+datos[i][1]);
            x   +=  datos[i][0];
            y   +=  datos[i][1];
            xy  +=  datos[i][0]*datos[i][1];
            x2  +=  datos[i][0]*datos[i][0];
            if (i == datos.length-1){
                System.out.println("\nVariables calculadas:");
                System.out.println("yi =    "+ y);
                System.out.println("xi =    "+ x);
                System.out.println("xiyi =  "+ xy);
                System.out.println("xi^2 =  "+ x2);
                
                b1 = ((datos.length*xy)-(x*y))/((datos.length*x2)-(x*x));
                System.out.println("b1 =    "+ b1);
                float Y_ = y/datos.length;
                float X_ = x/datos.length;
                b0 = Y_ - (b1 * X_);
                System.out.println("b0 =    "+ b0);
                y0 = b0 + b1 * X0;
                y1 = b0 + b1 * X1;
                y2 = b0 + b1 * X2;
                System.out.println("\nResultado: ");
                System.out.println("y0 =    "+ y0);
                System.out.println("y1 =    "+ y1);
                System.out.println("y2 =    "+ y2);
                System.out.println("\nResultado Final: ");
                for(i=0; i<datos.length; i++){
                    System.out.println(i+1+"    "+datos[i][0]+"    "+datos[i][1]);
                }
                System.out.println(10+"   "+y0+"    "+X0);
                System.out.println(11+"   "+y1+"    "+X1);
                System.out.println(12+"   "+y2+"    "+X2);
            }
        }
    } 
    
    @Override
    public int onEnd() {
      myAgent.doDelete();   
      return super.onEnd();
    } 
  } 
}


/*import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class Practica1_SemIA extends Agent{
    @Override
    protected void setup(){
        System.out.println("Inicializando el agente: " + getLocalName());
        
        //Add the CyclicBehaviour
        addBehaviour(new CyclicBehaviour(this){
            @Override
            public void action(){
                System.out.println("Cycling");
            }
        });
        
        //Add generic behaviour
        addBehaviour(new FourStepBehaviour());
    }
    
    private class FourStepBehaviour extends Behaviour{
        private int step = 1;
        
        @Override
        public void action(){
            switch(step){
                case 1:
                    System.out.println("Operation 1");
                    break;
                case 2:
                    System.out.println("Operation 2: Adding one-shot behaviour");
                    myAgent.addBehaviour(new OneShotBehaviour(myAgent){
                        @Override
                        public void action(){
                            System.out.println("One-shot");
                        }
                    });
                    break;
                case 3:
                   System.out.println("Operation 3"); 
                   break;
                case 4:
                    System.out.println("Operation 4");
                    break;
            }
            step++;
        }
        @Override
        public boolean done(){
            return step == 5;
        }
        @Override
        public int onEnd(){
            myAgent.doDelete();
            return super.onEnd();
        }
    }
}*/
