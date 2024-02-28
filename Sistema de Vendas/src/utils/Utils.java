package utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class Utils {
    static NumberFormat numberFormat =
            new DecimalFormat("R$ #,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

    public static String doubleToString(double value) {  // retorna valor formatado  { Double > String }
        return numberFormat.format(value);
    }

}
