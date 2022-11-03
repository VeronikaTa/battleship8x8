package com.epam.training.student_veronika_tarasova.battleship8x8.src.main.java.com.epam.rd.autotasks;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    public boolean shoot(String shot) {
        char[] key = shot.toCharArray();
        int column = 0;

        switch (key[0]) {
            case 'A' : column = 1;
            break;
            case 'B' : column = 2;
            break;
            case 'C' : column = 3;
            break;
            case 'D' : column = 4;
            break;
            case 'E' : column = 5;
            break;
            case 'F' : column = 6;
            break;
            case 'G' : column = 7;
            break;
            case 'H' : column = 8;
            break;
            default : throw new IllegalStateException("Unexpected value: " + key[0]);
        }
        int row = Character.digit(key[1],10);
        int index = 8 * (row - 1) + column - 1;

        char[] shootMap = toBinary64(shots);

        shootMap[index] = '1';
        String changedMap = String.valueOf(shootMap);
        shots = makeLong(changedMap);

            return shootMap[index] == toBinary64(ships)[index];
    }

    private static long makeLong(String input) {
        if(input.substring(0,1).equals("1")) {
            return -1 * (Long.MAX_VALUE - Long.parseLong(input.substring(1), 2) + 1);  }
        else { return Long.parseLong(input, 2);} }

    private char[] toBinary64(Long longToBinary){
        char[] binaryChar = new char[64];
        char[] shootMapString = Long.toBinaryString(longToBinary).toCharArray();
        int length = shootMapString.length;

        for(int i = 63, j = length - 1; i > 63 - length; i--, j--){
            binaryChar[i] = shootMapString[j];
        }
        for(int i = 63; i >=0 ; i--){
            if(binaryChar[i] == '\u0000'){
                binaryChar[i] = '0';
            }
        }
        return binaryChar;
    }
    public String state() {
        StringBuffer state = new StringBuffer();
         char[] shotsMap = toBinary64(shots);
         char[] shipsMap = toBinary64(ships);

         for(int i = 0; i < 64; i++){
         if(shotsMap[i] == '0' && shipsMap[i] == '0'){
             state.append(".");
         }
         else if(shotsMap[i] == '1' && shipsMap[i] == '0'){
             state.append("×");
         }
         else if (shotsMap[i] == '0' && shipsMap[i] == '1'){
             state.append("☐");
         }
         else{
             state.append("☒");
         }
         if(i != 0 && i != 63  && (i+1)%8 == 0){
             state.append("\n");
         }
         }

        return state.toString();
    }
}
