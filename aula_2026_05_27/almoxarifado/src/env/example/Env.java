package example;

// Environment code for project almoxarifado

/*
Desafios

1) finalizar o guardar peça grande no ambiente (remover a crença que tem peca(grd) no ambiente

2) colocar como contexto ou condição viagens.... ou seja, um robô só pode guardar peça se tiver viagens em aberto
 */

import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;

import java.text.ParseException;
import java.util.logging.*;

import java.util.Random;

public class Env extends Environment {

    String sortearPeca(){
        Random gerador = new Random();
        int sorteado = gerador.nextInt(3);
        
        if(sorteado == 0){
            return "peca(peq)";
        }
        if(sorteado == 1){
            return "peca(med)";
        }
        if(sorteado == 2)
        {
            return "peca(grd)";
        }

        return "";
    }

    int r1Viagens = 1;
    int r2Viagens = 1;

    private Logger logger = Logger.getLogger("almoxarifado."+Env.class.getName());
    String peca_sorteada = sortearPeca();

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);
        try {
            addPercept(ASSyntax.parseLiteral(peca_sorteada));
            //addPercept(ASSyntax.parseLiteral("dia(quarta)"));
            //addPercept(ASSyntax.parseLiteral("hora(19_06)"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        
        if (agName.equals("r1") && action.toString().equals("guardar(peq)") && r1Viagens > 0) {
             logger.info(agName + " está guardando peça pequena...");
             r1Viagens --;
        } else if(agName.equals("r2") && action.toString().equals("guardar(med)")  && r2Viagens > 0) {
            logger.info(agName + " está guardando peça média...");
            r2Viagens --;
        } else if(agName.equals("r2") && action.toString().equals("guardar(grd)")  && r1Viagens > 0  && r2Viagens > 0) {
            logger.info(agName + " estou guardando peça grande...");
            r1Viagens --;
            r2Viagens--;
        }else {
            //logger.info("executing: "+action+", but not implemented!");
            logger.info(agName + " está cansado. Não pode mais realizar viagens.");
        }

        try {
            removePercept(ASSyntax.parseLiteral(peca_sorteada));
            
            peca_sorteada = sortearPeca();

            Thread.sleep(4000);
            logger.info("uma nova peça está sendo colocada no almoxarifado...");

            addPercept(ASSyntax.parseLiteral(peca_sorteada));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true; // the action was executed with success
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}
