import java.util.Arrays;

public class HuffmanCoding {
    int matrix[][];
    char[] symbols;
    String text;
    String encryptedText;
    
    public void launch() {
        symbols = removeDuplicates(text);
        matrix = new int[5][symbols.length * 2 - 1];
        
        Arrays.sort(symbols);
        int[] frecuencys = getFrecuencys(text, String.valueOf(symbols));

        for(int[] row:matrix){
            Arrays.fill(row, 0);
        }
        
        for(int i = 0; i < symbols.length; i++){
            matrix[0][i] = frecuencys[i];
        }

        fillMatrix(symbols.length);
        System.out.println();
        String encryptedSymbols[] = encrypt(symbols.length);
        encryptedText = encryptedText(symbols, text, encryptedSymbols);
    }
    public char[] removeDuplicates(String text) {
        String letters = "";
        for (int i = 0; i < text.length(); i++) {
            if (!letters.contains(String.valueOf(text.charAt(i)))) {
                letters += text.charAt(i);
            }
        }
        return letters.toCharArray();
    }
    public String encryptedText(char symbols[], String text, String[] encryptedSymbols){
        int j = 0;
        String encryptedText = "";
        for(int i = 0; i < text.length(); i++){
            doCycle:
            do{
                if(symbols[j] == text.charAt(i)){
                    encryptedText += encryptedSymbols[j];
                    break doCycle;
                 }
                 j++;
            }while(j < symbols.length);
            encryptedText += " ";
            j = 0;
        }
        return encryptedText;
    }

    public int[] getFrecuencys(String text, String symbols) {
        int frecuencys [] = new int[symbols.length()];
        for (int i = 0; i < symbols.length(); i++) {
            frecuencys [i] = text.length() - text.replaceAll(String.valueOf(symbols.charAt(i)), "").length();
        }
        return frecuencys;
    }
    public void fillMatrix(int symbolsSize){
        int column = symbolsSize;
        int izq[],der[];
        int frecuencys[] = matrix[0].clone();
        do{
            izq = getNode(column,frecuencys);
            der = getNode(column,frecuencys);
            matrix[0][column] = izq[0]+der[0];
            matrix[3][column] = izq[1];
            matrix[4][column] = der[1];
            matrix[1][izq[1]] = column;
            matrix[1][der[1]] = column;
            matrix[2][izq[1]] = 1;
            matrix[2][der[1]] = 2;
            frecuencys[column] = izq[0]+der[0];
            column++;
        }while(column < matrix[0].length);
    }
    public int[] getNode(int symbolsSize,int frecuencys[]){
        int node[] = new int[2];
        for(int i = 0; i < symbolsSize; i++){
            if(frecuencys[i] != 0){
                node[0] = frecuencys[i];
                node[1] = i;
                break;
            }
        }
        for(int i = 0; i < symbolsSize; i++){
            if(frecuencys[i] < node[0] && frecuencys[i]>0){
                node[0] = matrix[0][i];
                node[1] = i;
            }
        }
        frecuencys[node[1]] = 0;
        return node;
    }
    public void printMatrix(int columns,char[]symbols){
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < columns ;j++){
                if(i == 0){
                    System.out.print(j+" ");    
                }
                else if(i == 1){
                    System.out.print(symbols[j]+" ");
                    if(j == symbols.length-1){
                        break;
                    }
                }else{
                    System.out.print(matrix[i-2][j]+" ");
                }
            }
            System.out.println();
        }
    }
    public String[] encrypt(int size){
        String[] encryptedSymbols = new String[size];
        Arrays.fill(encryptedSymbols, "");
        for(int i = 0; i < size; i++){
            encryptedSymbols[i] = encodeSymbol(i);
        }
        return encryptedSymbols;
    }
    public String encodeSymbol(int i){
        if(matrix[1][i] == matrix[0].length-1){
            if(matrix[2][i] == 1){
                return "0";
            }else{
                return "1";
            }
        }else{
            if(matrix[2][i] == 1){
                return encodeSymbol(matrix[1][i])+"0";
            }else{
                return encodeSymbol(matrix[1][i])+"1";
            }
        }
    }
    public int[][] getMatrix() {
        return matrix;
    }
    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
    public char[] getSymbols() {
        return symbols;
    }
    public void setSymbols(char[] symbols) {
        this.symbols = symbols;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getEncryptedText() {
        return encryptedText;
    }
    public void setEncryptedText(String encryptedText) {
        this.encryptedText = encryptedText;
    }
}