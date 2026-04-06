import java.io.IOException;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static String grid = "........\n........\n........\n...xo...\n...ox...\n........\n........\n........\n";

    public static void main(String[] args) throws IOException {

        putPawn(4, 6, 'x');
        printGrid();
        ConnectionServ.createInstance();
    }

    public static char accessGridChar(int line, int column) {
        int position = (line-1)*9 + column - 1;  //-1 at the end because strings start from 0 & base 9 car les lignes sont faites de 8 'cases' et d'un retour à la ligne qui compte comme un seul char.
        System.out.println(grid.charAt(position));
        return grid.charAt(position);
    }

    public static int accessGridPos(int line, int column) {
        int position = (line-1)*9 + column - 1;
        return position;
    }
    public static void printGrid() {
        for (int i = 1; i < 72; i++) {   //loop tous les caractères de la string 'grille'
            if (grid.charAt(i - 1) == '.'
                    || grid.charAt(i - 1) == 'x'
                    || grid.charAt(i - 1) == 'o') { //si le caractère est une case, on l'imprime suivi d'espaces
                System.out.print(grid.charAt(i - 1));
                System.out.print("    ");
            } else if (grid.charAt(i - 1) == '\n') { //sinon on passe à la ligne suivante
                System.out.println("\n");
            }
        }
    }

    public static void putPawn(int line, int  column, char pawn) {
        if (pawn == 'x' || pawn == 'o') { // vérifie si le pion est un caractère autorisé
            if (accessGridChar(line, column) == '.') { // vérifie que la case est bien vide
                int pos = accessGridPos(line, column); // convertis la ligne et la colone en l'index du caractère dans la string
                char[] c = grid.toCharArray(); // crée un array contenant tous les caractères de la string
                c[pos] = pawn; // remplace le caractère à la position recherchée par le pion soumis par l'utilisateur
                //String temp = new String(c); convertis l'array 'c' en String, c'est le seul moyen car .toString() ne return pas de résultat valable (+erreur)
                //System.out.println(temp); test
                grid = new String(c);
                int checkUp = checkConvertabilityUp(line, column, pawn);
                fillUp(line, column, checkUp);
                int checkDown = checkConvertabilityDown(line, column, pawn);
                fillDown(line, column, checkDown);
                int checkRight = checkConvertabilityRight(line, column, pawn);
                fillRight(line, column, checkRight);
                int checkLeft = checkConvertabilityLeft(line, column, pawn);
                fillLeft(line, column, checkLeft);
            } else {
                System.out.println("Un autre pion se trouve déjà sur cette case.");
            }
        } else {
            System.out.println("Pion invalide. Merci d'utiliser 'x' ou 'o'");
        }
    }

    public static int checkConvertabilityUp(int line, int column, char pawn) {
        boolean possible = false;
        int numberOfPawnsToTurn = 0;
        char opponentPawn = 'n';
        if (pawn == 'o') {
            opponentPawn = 'x';
        } else if (pawn == 'x') {
            opponentPawn = 'o';
        }

        int lineUp = line - 1;
        int originalLine = line - 1;
        int finishLine = 0;
        boolean notBroken = true;
        boolean finished = false;
        while (lineUp > 1 && notBroken && !finished) {
            char c = accessGridChar(lineUp, column);
            if (c == '.') {
                notBroken = false;
            }
            if (c == pawn) {
                finished = true;
                finishLine = lineUp;
            }
            lineUp--;
        }

        if (notBroken && finished) {
            possible = true;
        }

        if (finishLine == originalLine) {
            possible = false;
        }
        if (possible) {
            numberOfPawnsToTurn = originalLine - finishLine;
        }
        System.out.println("nombre de pions à retourner: " + numberOfPawnsToTurn);
        return numberOfPawnsToTurn;
    }

    public static int checkConvertabilityDown(int line, int column, char pawn) {
        boolean possible = false;
        int numberOfPawnsToTurn = 0;
        char opponentPawn = 'n';
        if (pawn == 'o') {
            opponentPawn = 'x';
        } else if (pawn == 'x') {
            opponentPawn = 'o';
        }

        int lineDown = line + 1;
        int originalLine = line + 1;
        int finishLine = 0;
        boolean notBroken = true;
        boolean finished = false;
        while (lineDown > 1 && notBroken && !finished) {
            char c = accessGridChar(lineDown, column);
            if (c == '.') {
                notBroken = false;
            }
            if (c == pawn) {
                finished = true;
                finishLine = lineDown;
            }
            lineDown++;
        }

        if (notBroken && finished) {
            possible = true;
        }

        if (finishLine == originalLine) {
            possible = false;
        }
        if (possible) {
            numberOfPawnsToTurn = finishLine - originalLine;
        }
        System.out.println("nombre de pions à retourner: " + numberOfPawnsToTurn);
        return numberOfPawnsToTurn;
    }

    public static int checkConvertabilityRight(int line, int column, char pawn) {
        boolean possible = false;
        int numberOfPawnsToTurn = 0;
        char opponentPawn = 'n';
        if (pawn == 'o') {
            opponentPawn = 'x';
        } else if (pawn == 'x') {
            opponentPawn = 'o';
        }

        int columnRight = column + 1;
        int originalColumn = column + 1;
        int finishColumn = 0;
        boolean notBroken = true;
        boolean finished = false;
        while (columnRight > 1 && notBroken && !finished) {
            char c = accessGridChar(line, columnRight);
            if (c == '.') {
                notBroken = false;
            }
            if (c == pawn) {
                finished = true;
                finishColumn = columnRight;
            }
            columnRight++;
        }

        if (notBroken && finished) {
            possible = true;
        }

        if (finishColumn == originalColumn) {
            possible = false;
        }
        if (possible) {
            numberOfPawnsToTurn = finishColumn - originalColumn;
        }
        System.out.println("nombre de pions à retourner: " + numberOfPawnsToTurn);
        return numberOfPawnsToTurn;
    }

    public static int checkConvertabilityLeft(int line, int column, char pawn) {
        boolean possible = false;
        int numberOfPawnsToTurn = 0;
        char opponentPawn = 'n';
        if (pawn == 'o') {
            opponentPawn = 'x';
        } else if (pawn == 'x') {
            opponentPawn = 'o';
        }

        int columnLeft = column - 1;
        int originalColumn = column - 1;
        int finishColumn = 0;
        boolean notBroken = true;
        boolean finished = false;
        while (columnLeft > 1 && notBroken && !finished) {
            char c = accessGridChar(line, columnLeft);
            if (c == '.') {
                notBroken = false;
            }
            if (c == pawn) {
                finished = true;
                finishColumn = columnLeft;
            }
            columnLeft--;
        }

        if (notBroken && finished) {
            possible = true;
        }

        if (finishColumn == originalColumn) {
            possible = false;
        }
        if (possible) {
            numberOfPawnsToTurn = originalColumn - finishColumn;
        }
        System.out.println("nombre de pions à retourner: " + numberOfPawnsToTurn);
        return numberOfPawnsToTurn;
    }

    public static void fillUp (int line, int column, int nombreDePionsARetourner) {
        for (int i=0; i <= nombreDePionsARetourner; i++) {
            if (i > 0) {
                flipPawn(line - i, column);
            }
        }
    }

    public static void fillDown (int line, int column, int nombreDePionsARetourner) {
        for (int i=0; i <= nombreDePionsARetourner; i++) {
            if (i > 0) {
                flipPawn(line + i, column);
            }
        }
    }

    public static void fillRight (int line, int column, int nombreDePionsARetourner) {
        for (int i=0; i <= nombreDePionsARetourner; i++) {
            if (i > 0) {
                flipPawn(line, column + i);
            }
        }
    }

    public static void fillLeft (int line, int column, int nombreDePionsARetourner) {
        for (int i=0; i <= nombreDePionsARetourner; i++) {
            if (i > 0) {
                flipPawn(line, column - i);
            }
        }
    }

    public static void flipPawn (int line, int column) {
        System.out.println(line + "" + column);
        int pos = accessGridPos(line, column); // convertis la ligne et la colone en l'index du caractère dans la string
        char[] c = grid.toCharArray(); // crée un array contenant tous les caractères de la string
        if (c[pos] == 'x') {
            c[pos] = 'o'; // inverse le pion
        } else if (c[pos] == 'o') {
            c[pos] = 'x'; // inverse le pion
        }
        //String temp = new String(c); convertis l'array 'c' en String, c'est le seul moyen car .toString() ne return pas de résultat valable (+erreur)
        //System.out.println(temp); test
        grid = new String(c);
    }
}