package HuffmanCoding;

import java.util.Arrays;

public class HuffmanCoding {
    int matrix[][];
    char[] symbols;
    String text;
    String text2;
    String encryptedText;
    String encryptedSymbols[];
    
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
        encryptedSymbols = encrypt(symbols.length);
        encryptedText = encryptText(symbols, text, encryptedSymbols);
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
    public String encryptText(char symbols[], String text, String[] encryptedSymbols){
        String encryptedText = "";
        text2 = "";
        int aux = 0, c;
        for(int i = 0; i < text.length(); i++){
            c = Arrays.binarySearch(symbols, text.charAt(i));
            encryptedText += encryptedSymbols[c];
            text2 += symbols[c];
            while(aux < encryptedSymbols[c].length()-1){
                text2 += " ";
                aux++;
            }
            aux = 0;
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
            matrix[0][column] = izq[0]+der[0]; //frecuency
            matrix[1][izq[1]] = column;        //Parent izq
            matrix[1][der[1]] = column;        //Parent der
            matrix[2][izq[1]] = 1;             //Type izq
            matrix[2][der[1]] = 2;             //Type der
            matrix[3][column] = izq[1];        //izq
            matrix[4][column] = der[1];        //der
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
        //get smaller frecuency
        for(int i = 0; i < symbolsSize; i++){
            if(frecuencys[i] < node[0] && frecuencys[i] > 0){
                node[0] = matrix[0][i];
                node[1] = i;
            }
        }
        frecuencys[node[1]] = 0;
        return node;
    }    
    public String[] encrypt(int size){
        String[] encryptedSymbols = new String[size];
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
    public char[] getSymbols() {
        return symbols;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getEncryptedText() {
        return encryptedText;
    }
    public String getText2() {
        return text2;
    }
    public void setText2(String text2) {
        this.text2 = text2;
    }
    public String[] getEncryptedSymbols() {
        return encryptedSymbols;
    }
    public void setEncryptedSymbols(String[] encryptedSymbols) {
        this.encryptedSymbols = encryptedSymbols;
    }
}