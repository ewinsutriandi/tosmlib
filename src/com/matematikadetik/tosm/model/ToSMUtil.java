package com.matematikadetik.tosm.model;


/**
 * Created by ewin.sutriandi on 11/9/2017.
 */

public class ToSMUtil {
    public static final int OPERATION_ADD=0;
    public static final int OPERATION_SUB=1;
    public static final int OPERATION_MULT=2;
    public static final int OPERATION_DIV=3;
    public static final int[] AMBANG_A1 = {10,20,30,40,50,60,70};
    public static final int[] AMBANG_A2 = {8,16,24,32,40,48,56};

    public static int getCorrectAnswer(Question q){
        int correctAnswer=0;
        switch (q.getOperation()) {
            case OPERATION_ADD:
                correctAnswer = q.getFirstNum() + q.getSecondNum();
                break;
            case OPERATION_SUB:
                correctAnswer = q.getFirstNum() - q.getSecondNum();
                break;
            case OPERATION_MULT:
                correctAnswer = q.getFirstNum() * q.getSecondNum();
                break;
            case OPERATION_DIV:
                correctAnswer = q.getFirstNum() / q.getSecondNum();
                break;
        }
        return correctAnswer;
    }

    public static boolean evaluate(Question q, int answer){
        boolean result = false;
        int correctAnswer=getCorrectAnswer(q);
        if (answer == correctAnswer) result = true;
        return result;
    }

    public static ToSMStats getToSMStats(SessionManager sm){
        ToSMStats stat = new ToSMStats(sm.answerList,sm.operationType,sm.level);
        return stat;
    }

    public static ToSMResultCategory getToSMResultCategory(double opm, String level){
        int[] ambang = {};
        if (level.equalsIgnoreCase(ToSMLevel.A1.name())) {
            ambang = AMBANG_A1;
        } else if (level.equalsIgnoreCase(ToSMLevel.A2.name())) {
            ambang = AMBANG_A2;
        }
        for (int i=ambang.length-1; i >= 0;i--) {
            
            if (opm > ambang[i]){
                return ToSMResultCategory.values()[i+1];
            }
        }
        return ToSMResultCategory.HITAM;
    }

    public static ToSMResultCategory getToSMResultCategory(ToSMStats stats){
        double opm = stats.getOperationPerMinute().getOpm();
        String level = stats.getLevel().toString();
        return getToSMResultCategory(opm,level);
    }

    public static ToSMResultCategory getToSMResultCategory(ToSMStatsSimple stats){
        double opm = stats.getOpm();
        String level = stats.getLevel();
        return getToSMResultCategory(opm,level);
    }

    public static String getToSMResultColor(ToSMStats stats){
        return getToSMResultColor(stats.getOperationPerMinute().getOpm(),stats.getLevel().name());
    }

    public static String getToSMResultColor(ToSMStatsSimple stats){
        return getToSMResultColor(stats.getOpm(),stats.getLevel());
    }

    public static String getToSMResultColor(double opm, String level){
        ToSMResultCategory flag = ToSMUtil.getToSMResultCategory(opm,level);
        String c = "#000000";
        switch (flag){
            case HITAM:
                c = "#000000";
                break;
            case MERAH:
                c = "FF0000";
                break;
            case KUNING:
                c = "FFFF00";
                break;
            case HIJAU:
                c = "#00FF00";
                break;
            case BIRU:
                c = "#0000FF";
                break;
            case UNGU:
                c = "#800080";
                break;
            case BINTANG_SATU:
                 c = "#FFFFFF";
                break;
            case BINTANG_DUA:
                c = "#FFFFFF";
                break;
        }
        return c;
    }

    public static String getToSMResultLevel(ToSMStats stats){
        ToSMResultCategory flag = ToSMUtil.getToSMResultCategory(stats);
        if (flag.ordinal() >= ToSMResultCategory.BINTANG_SATU.ordinal()){
            return "Istimewa";
        } else if (flag.ordinal() >= ToSMResultCategory.HIJAU.ordinal()){
            return "Tuntas";
        } else {
            return "Belum Tuntas";
        }
    }

    public static int[] getAmbang(ToSMStats stats){
        if (stats.getLevel()==ToSMLevel.A1) return AMBANG_A1;
        if (stats.getLevel()==ToSMLevel.A2) return AMBANG_A2;
        return null;
    }
}
