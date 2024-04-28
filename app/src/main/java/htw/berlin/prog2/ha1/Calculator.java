package htw.berlin.prog2.ha1;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/**
 * Eine Klasse, die das Verhalten des Online Taschenrechners imitiert, welcher auf
 * https://www.online-calculator.com/ aufgerufen werden kann (ohne die Memory-Funktionen)
 * und dessen Bildschirm bis zu zehn Ziffern plus einem Dezimaltrennzeichen darstellen kann.
 * Enthält mit Absicht noch diverse Bugs oder unvollständige Funktionen.
 */
public class Calculator {

    private String screen = "0";
    private double latestValue;
    private String latestOperation = "";
  //  private double zahl = 0;
    // Punkt vor Strich Lösung Arry
    private List<Double> numbers = new ArrayList<>();
    private List<String> operations = new ArrayList<>();

    /**
     * @return den aktuellen Bildschirminhalt als String
     */
    public String readScreen() {
        return screen;
    }

    /**
     * Empfängt den Wert einer gedrückten Zifferntaste. Da man nur eine Taste auf einmal
     * drücken kann muss der Wert positiv und einstellig sein und zwischen 0 und 9 liegen.
     * Führt in jedem Fall dazu, dass die gerade gedrückte Ziffer auf dem Bildschirm angezeigt
     * oder rechts an die zuvor gedrückte Ziffer angehängt angezeigt wird.
     *
     * @param digit Die Ziffer, deren Taste gedrückt wurde
     */
    public void pressDigitKey(int digit) {
        if (digit > 9 || digit < 0) throw new IllegalArgumentException();

        if (screen.equals("0") || latestValue == Double.parseDouble(screen)) screen = "";

        screen = screen + digit;
    }

    /**
     * Empfängt den Befehl der C- bzw. CE-Taste (Clear bzw. Clear Entry).
     * Einmaliges Drücken der Taste löscht die zuvor eingegebenen Ziffern auf dem Bildschirm
     * so dass "0" angezeigt wird, jedoch ohne zuvor zwischengespeicherte Werte zu löschen.
     * Wird daraufhin noch einmal die Taste gedrückt, dann werden auch zwischengespeicherte
     * Werte sowie der aktuelle Operationsmodus zurückgesetzt, so dass der Rechner wieder
     * im Ursprungszustand ist.
     */
    public void pressClearKey() {
        screen = "0";
        latestOperation = "";
        latestValue = 0.0;
    }
    /**
     * Empfängt den Wert einer gedrückten binären Operationstaste, also eine der vier Operationen
     * Addition, Substraktion, Division, oder Multiplikation, welche zwei Operanden benötigen.
     * Beim ersten Drücken der Taste wird der Bildschirminhalt nicht verändert, sondern nur der
     * Rechner in den passenden Operationsmodus versetzt.
     * Beim zweiten Drücken nach Eingabe einer weiteren Zahl wird direkt des aktuelle Zwischenergebnis
     * auf dem Bildschirm angezeigt. Falls hierbei eine Division durch Null auftritt, wird "Error" angezeigt.
     *
     * @param operation "+" für Addition, "-" für Substraktion, "x" für Multiplikation, "/" für Division
     */
    // Für test 2 und 3 ie eingaben speichern &
    public void pressBinaryOperationKey(String operation) {
            if (!"+-x/".contains(operation))
                throw new IllegalArgumentException("Unsupported operation");

            numbers.add(Double.parseDouble(screen)); // Save the current number
            operations.add(operation); // Save the operation
            screen = "0"; // Clear the screen for new number input
        }


        //double latestValue= Double.parseDouble(screen);
        // pressEqualsKey();
        //   } else {
        //       zahl = latestValue;
        //    }
        //  latestOperation = operation;
        //  screen = "0";

        //  public void pressBinaryOperationKey(String operation)
        //    latestValue = Double.parseDouble(screen);
        //    zahl = latestValue;
        //    latestOperation =  operation;



    /**
     * Empfängt den Wert einer gedrückten unären Operationstaste, also eine der drei Operationen
     * Quadratwurzel, Prozent, Inversion, welche nur einen Operanden benötigen.
     * Beim Drücken der Taste wird direkt die Operation auf den aktuellen Zahlenwert angewendet und
     * der Bildschirminhalt mit dem Ergebnis aktualisiert.
     *
     * @param operation "√" für Quadratwurzel, "%" für Prozent, "1/x" für Inversion
     */
    public void pressUnaryOperationKey(String operation) {
        double currentValue = Double.parseDouble(screen);
        double result;
        // latestOperation = operation;

        switch (operation) {
            case "√" -> result =  Math.sqrt(currentValue);
            case "%" -> result =  currentValue / 100;
            case "1/x"-> {
                if (currentValue == 0) {
                    screen = "Error";
                    return;
                }
                result = 1 / currentValue;
            }
            default -> throw new IllegalArgumentException();
        };
        // screen = "0";
        //   screen = Double.toString(result);
       screen = formatResult(result);

       // operations.add(operation);  // Füge die Operation zur Liste hinzu
       // numbers.add(result);
        //if (screen.equals("NaN")) screen = "Error";
       // if (screen.contains(".") && screen.length() > 11) screen = screen.substring(0, 10);
    }
      //  if (screen.equals("NaN")) screen = "Error";
      //  if (screen.contains(".") && screen.length() > 11) screen = screen.substring(0, 10);

  //  }

    /**
     * Empfängt den Befehl der gedrückten Dezimaltrennzeichentaste, im Englischen üblicherweise "."
     * Fügt beim ersten Mal Drücken dem aktuellen Bildschirminhalt das Trennzeichen auf der rechten
     * Seite hinzu und aktualisiert den Bildschirm. Daraufhin eingegebene Zahlen werden rechts vom
     * Trennzeichen angegeben und daher als Dezimalziffern interpretiert.
     * Beim zweimaligem Drücken, oder wenn bereits ein Trennzeichen angezeigt wird, passiert nichts.
     */
    public void pressDotKey() {
        if (!screen.contains(".")) screen = screen + ".";
    }

    /**
     * Empfängt den Befehl der gedrückten Vorzeichenumkehrstaste ("+/-").
     * Zeigt der Bildschirm einen positiven Wert an, so wird ein "-" links angehängt, der Bildschirm
     * aktualisiert und die Inhalt fortan als negativ interpretiert.
     * Zeigt der Bildschirm bereits einen negativen Wert mit führendem Minus an, dann wird dieses
     * entfernt und der Inhalt fortan als positiv interpretiert.
     */
    public void pressNegativeKey() {
        screen = screen.startsWith("-") ? screen.substring(1) : "-" + screen;

    }

    /**
     * Empfängt den Befehl der gedrückten "="-Taste.
     * Wurde zuvor keine Operationstaste gedrückt, passiert nichts.
     * Wurde zuvor eine binäre Operationstaste gedrückt und zwei Operanden eingegeben, wird das
     * Ergebnis der Operation angezeigt. Falls hierbei eine Division durch Null auftritt, wird "Error" angezeigt.
     * Wird die Taste weitere Male gedrückt (ohne andere Tasten dazwischen), so wird die letzte
     * Operation (ggf. inklusive letztem Operand) erneut auf den aktuellen Bildschirminhalt angewandt
     * und das Ergebnis direkt angezeigt.
     */
    public void pressEqualsKey() {
        numbers.add(Double.parseDouble(screen));

        // First handle all multiplication and division from left to right
        for (int i = 0; i < operations.size(); i++) {
            if (operations.get(i).equals("x") || operations.get(i).equals("/")) {
                double result = operations.get(i).equals("x") ? numbers.get(i) * numbers.get(i + 1) : numbers.get(i) / numbers.get(i + 1);
                if (Double.isInfinite(result)) {
                    screen = "Error";
                    return;
                }
                numbers.set(i + 1, result); // setzt das ergbnis an die Position i + 1
                numbers.remove(i); // entfernt die zahl an Position i
                operations.remove(i); // entfernt die operation an Position i
                i--; // Veränderung der listen länge zu berücksichigen
            }
        }
        // Minus und Plus
        double result = numbers.get(0); // wird mit dem ersten Element der liste initialisiert
        for (int i = 0; i < operations.size(); i++) {
            result = operations.get(i).equals("+") ? result + numbers.get(i + 1) : result - numbers.get(i + 1);
        }
        // Formatieren das ergebnis
        screen = formatResult(result); // Runden und Zurückgeben als double
        //  screen = Double.toString(result); // Konvertieren in String nur für Anzeigezwecke
        numbers.clear();
        operations.clear();
}
    private String formatResult(double result) {
        if (Double.isNaN(result)) {
            return "Error";  // Bei ungültigem Ergebnis NaN zurückgeben.
        }
        // Überprüfen, ob das Ergebnis effektiv eine Ganzzahl ist
        if (Math.abs(result - Math.round(result)) < 0.000001) {
            return String.format(Locale.US, "%d", (long) result);    // Ganzzahlige Ergebnisse ohne Dezimalpunkt
        } else {
            // Formatierung auf fünf Nachkommastellen, wenn nötig
            return String.format(Locale.US, "%.5f", result).replaceAll("0*$", "").replaceAll("\\.$", "");
        }
    }

}


