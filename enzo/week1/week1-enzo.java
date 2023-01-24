public class Main {
    public String solution(String new_id) {
        String step1= new_id.toLowerCase();
        String step2 = step1.replaceAll("[^a-z0-9_.\\-]","");
        String step3 = step2.replaceAll("\\.{2,}",".");
        String step4 = step3.replaceAll("^\\.|\\.$","");
        String step5 = step4.isEmpty() ? "a" : step4;
        String step6 = getStep6(step5);
        String step7 = getStep7(step6);
        String answer = step7;
        return answer;
    }
    public String getStep6(String step5){
        String step6 = step5;
        if(step5.length()>=16){
            step6 = step5.substring(0,15);
        }
        if(step6.charAt(step6.length()-1) == '.'){
            step6 = step6.substring(0,step6.length()-1);
        }
        return step6;
    }
    public String getStep7(String step6){
        String step7 = step6;
        while(step7.length()<3){
            step7 += step6.charAt(step6.length()-1);
        }
        return step7;
    }
}