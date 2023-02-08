package ps5.takenoko.lanceur;

public class CSVStats {
    private String JoueurType;
    private String Gagne;
    private String PourcentageGagne;
    private String Perdu;
    private String PourcentagePerdu;
    private String Null;
    private String PourcentageNull;
    private String ScoreMoyen;
    private String NbObjectifsMoyen;

    @Override
    public String toString() {
        return "CVStats{" +
                "JoueurType='" + JoueurType + '\'' +
                ", Gagne='" + Gagne + '\'' +
                ", pGagne='" + PourcentageGagne + '\'' +
                ", Perdu='" + Perdu + '\'' +
                ", pPerdu='" + PourcentagePerdu + '\'' +
                ", Null='" + Null + '\'' +
                ", pNull='" + PourcentageNull + '\'' +
                ", ScoreMoyen='" + ScoreMoyen + '\'' +
                ", NbObjectifsMoyen='" + NbObjectifsMoyen + '\'' +
                '}';
    }

    public void update(String datum) {
        String[] data = datum.split(",");
        if(!JoueurType.equals(data[0])){
            throw new IllegalArgumentException("Fichier CSV n'est pas conforme");
        }
        Gagne = calculateSum(Gagne, data[1]);
        PourcentageGagne = calculateMoyenne(PourcentageGagne, data[2]);
        Perdu = calculateSum(Perdu, data[3]);
        PourcentagePerdu = calculateMoyenne(PourcentagePerdu, data[4]);
        Null = calculateSum(Null, data[5]);
        PourcentageNull = calculateMoyenne(PourcentageNull, data[6]);
        ScoreMoyen = calculateMoyenne(ScoreMoyen, data[7]);
        NbObjectifsMoyen = calculateMoyenne(NbObjectifsMoyen, data[8]);
    }

    public String calculateMoyenne(String data1, String data2){
        if(data1 == null){
            return data2;
        }
        else{
            return String.valueOf((Double.parseDouble(data1) + Double.parseDouble(data2))/2);
        }
    }

    public  String calculateSum(String data1, String data2){
        if(data1 == null){
            return data2;
        }
        return String.valueOf(Double.parseDouble(data1) + Double.parseDouble(data2));
    }

    public String[] toStringArray(){
        return new String[]{JoueurType, Gagne, PourcentageGagne, Perdu, PourcentagePerdu, Null, PourcentageNull, ScoreMoyen, NbObjectifsMoyen};
    }
}
