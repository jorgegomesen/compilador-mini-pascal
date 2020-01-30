/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxAnalisys;

import AST.*;

import lexicalAnalisys.Token;
import java.util.Stack;

/**
 *
 * @author jorgec
 */
public class Printer implements Visitor {

    public int index = 0;
    public Stack<Boolean> level;
    public String end[] = {"├$", "└$"};

    public Printer() {
        this.level = new Stack<>();
    }

    void increment(boolean link) {
        index++;
        if (link) {
            level.add(true);
            return;
        }
        level.add(false);
    }

    void decrement() {
        level.pop();
        index--;
    }

    void indent() {
        System.out.print(String.format("%03d", index) + " ");
        for (int j = 0; j < index; j++) {
            if (j == index - 1) {
                if (level.get(j)) {
                    System.out.print(end[0]); // ├ 
                    return;
                }
                System.out.print(end[1]); // ├ 
                return;
            }
            if (level.get(j)) {
                System.out.print("|");
                return;
            }
            System.out.print(" ");
        }
    }

    void indent2() {
        System.out.print(index + " ");
        for (int j = 0; j < index; j++) {
            System.out.print("|");
        }
    }

    public void print(Program P) {
//        System.out.println("---> Iniciando impressão da árvore");
        P.visit(this);
    }

    @Override
    public void visitAssignmentCommand(AssignmentCommand AC) {
        indent();
        System.out.println("[COMANDO-DE-ATRIBUIÇÃO]");
        increment(false);
        if (AC.V != null) {
            AC.V.visit(this);
        }
        increment(true);
        indent();
        System.out.println(":=");
        decrement();
        increment(false);
        if (AC.E != null) {
            AC.E.visit(this);
        }
        decrement();
        decrement();
    }

    @Override
    public void visitCompostCommand(CompostCommand CC) {
        if (CC.CL != null) {
            CC.CL.visit(this);
        }
    }

    @Override
    public void visitConditionalCommand(ConditionalCommand CC) {
        indent();
        System.out.println("[COMANDO-CONDICIONAL]");
        increment(true);
        indent();
        System.out.println("[EXPRESSÃO]");
        increment(false);
        if (CC.E != null) {
            CC.E.visit(this);
        }
        decrement();

        indent();
        System.out.println("[C1]");
        increment(false);
        if (CC.C1 != null) {
            CC.C1.visit(this);
        }
        decrement();

        decrement();
        increment(false);
        indent();
        System.out.println("[C2]");
        increment(false);
        if (CC.C2 != null) {
            CC.C2.visit(this);
        }
        decrement();

        decrement();
    }

    @Override
    public void visitIterativeCommand(IterativeCommand IC) {
        indent();
        System.out.println("[COMANDO-ITERATIVO]");
        increment(true);

        indent();
        System.out.println("[E]");
        increment(false);
        if (IC.E != null) {
            IC.E.visit(this);
        }
        decrement();

        decrement();
        increment(false);

        if (IC.C != null) {
            IC.C.visit(this);
        }

        decrement();
    }

    @Override
    public void visitCommand(Command C) {
        if (C != null) {
            C.visit(this);
        }
    }

    @Override
    public void visitBody(Body B) {
        indent();
        System.out.println("[CORPO]");
        increment(true);
        if (B.D != null) {
            B.D.visit(this);
        }
        decrement();
        increment(false);
        if (B.C != null) {
            B.C.visit(this);
        }
        decrement();
    }

    @Override
    public void visitVariableDeclaration(VariableDeclaration VD) {
        indent();
        System.out.println("[DECLARAÇÃO-VARIÁVEL]");
        increment(true);
        if (VD.T != null) {
            VD.T.visit(this);
        }

        if (VD.IL != null) {
            if (VD.IL.nextIL == null) {
                decrement();
                increment(false);
            }
            VD.IL.visit(this);
        }
        decrement();
        increment(false);
        if (VD.nextD != null) {
            VD.nextD.visit(this);
        }
        decrement();
    }

    @Override
    public void visitDeclaration(Declaration D) {
        if (D != null) {
            D.visit(this);
        }
    }

    @Override
    public void visitSequentialDeclaration(SequentialDeclaration SD) {
        
    }

    @Override
    public void visitExpression(Expression E) {
        if (E.SE1 != null) {
//        System.out.println(E.SE1);
            E.SE1.visit(this);
        }
        if (E.O != null) {
            E.O.visit(this);
        }
        if (E.SE2 != null) {
            E.SE2.visit(this);
        }
    }

    @Override
    public void visitSimpleExpression(SimpleExpression SE) {
        if (SE.T != null) {
            SE.T.visit(this);
        }
        if (SE.TS != null) {
            increment(false);
            SE.TS.visit(this);
            decrement();
        }
    }

    @Override
    public void visitFactor(Factor F) {
            System.out.println("tem de entrar aqui");
        if (F != null) {
            F.visit(this);
        }
    }

    @Override
    public void visitCommandsList(CommandsList CL) {
        indent();

        System.out.println("[LISTA-DE-COMANDOS]");

        if (CL.nextCL == null) {
            increment(false);
        } else {
            increment(true);
        }

        if (CL.C != null) {
            CL.C.visit(this);
        }

        decrement();
        increment(false);

        if (CL.nextCL != null) {
            if (CL.nextCL.C != null) {
                CL.nextCL.visit(this);
            }
        }

        decrement();
    }

    @Override
    public void visitIdsList(IdsList IL) {
        indent();
        System.out.println("[LISTA-DE-IDS]");
        increment(false);
        if (IL.T != null) {
            indent();
            System.out.println(IL.T.spelling);
        }

        if (IL.nextIL != null) {

            increment(false);
            IL.nextIL.visit(this);
            decrement();
        }
        decrement();
    }

    @Override
    public void visitLiteral(Literal L) {
        if (L.T != null) {
            L.T.visit(this);
        }
    }

    @Override
    public void visitNode(Node N) {

    }

    @Override
    public void visitOperator(Operator O) {
        if (O.O != null) {
            indent();
            System.out.println(O.O.spelling);
        }
    }

    @Override
    public void visitProgram(Program P) {
        if (P != null) {
            indent();
            System.out.println("[PROGRAMA]");
            increment(true);
            indent();
            if (P.T != null) {
                System.out.println("TOKEN " + P.T.spelling);
            }
            decrement();
            increment(false);
            if (P.B != null) {
                P.B.visit(this);
            }
            decrement();
        }
    }

    @Override
    public void visitSelector(Selector S) {
        if (S.E != null) {
            S.E.visit(this);
        }
        if (S.nextS != null) {
            increment(false);
            S.nextS.visit(this);
            decrement();
        }
    }

    @Override
    public void visitFactorsSequence(FactorsSequence FS) {
        if (FS.O != null) {
            FS.O.visit(this);
        }
        if (FS.F != null) {
            FS.F.visit(this);
        }
        if (FS.nextFS != null) {
            increment(false);
            FS.nextFS.visit(this);
            decrement();
        }
    }

    @Override
    public void visitTermsSequence(TermsSequence TS) {
        decrement();
        increment(true);
        if (TS.O != null) {
            TS.O.visit(this);
        }
        decrement();
        increment(false);
        if (TS.T != null) {
            TS.T.visit(this);
        }
        if (TS.nextTS != null) {
            increment(false);
            TS.nextTS.visit(this);
            decrement();
        }
    }

    @Override
    public void visitTerm(Term T) {
        if (T.F != null) {
            System.out.println("Foi visitar o factor");
            T.F.visit(this);
        }
        if (T.FS != null) {
            increment(true);
            T.FS.visit(this);
            decrement();
        }
    }

    @Override
    public void visitAggregateType(AggregateType AT) {
        indent();
        System.out.println("[TIPO-AGREGADO]");
        increment(false);
        if (AT.T != null) {
            AT.T.visit(this);
        }
        if (AT.INDEX1 != null) {
            AT.INDEX1.visit(this);
        }
        if (AT.INDEX2 != null) {
            AT.INDEX2.visit(this);
        }
        decrement();
    }

    @Override
    public void visitType(Type T) {
        if (T != null) {
            T.visit(this);
        }
    }

    @Override
    public void visitSimpleType(SimpleType ST) {
        indent();
        System.out.println("[TIPO-SIMPLES]");
        increment(false);
        if (ST.T != null) {
            ST.T.visit(this);
        }
        decrement();
    }

    @Override
    public void visitToken(Token T) {
        if (T != null) {
            indent();
            System.out.println(T.spelling);
        }
    }

    @Override
    public void visitVariable(Variable V) {
        if (V.T != null) {
            V.T.visit(this);
        }
        increment(true);
        if (V.S != null) {
            V.S.visit(this);
        }
        decrement();
    }
}
