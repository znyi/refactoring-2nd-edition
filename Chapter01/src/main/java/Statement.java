import java.text.NumberFormat;
import java.util.Locale;

public class Statement {
    public String statement(Invoice invoice, Plays plays) throws Exception {
        double totalAmount = 0;
        int volumeCredits = 0;
        StringBuilder result = new StringBuilder("Statement for " + invoice.getCustomer() + "\n");

        for (Performance perf : invoice.getPerformances()) {
            Play play = plays.getPlays().get(perf.getPlayID());
            double thisAmount = 0;

            switch (play.getType()){
                case "tragedy": {
                    thisAmount = 40000;
                    if (perf.getAudience() > 30) {
                        thisAmount += 1000 * (perf.getAudience() - 30);
                    }
                    break;
                }
                case "comedy": {
                    thisAmount = 30000;
                    if (perf.getAudience() > 20) {
                        thisAmount += 10000 + 500 * (perf.getAudience() - 20);
                    }
                    thisAmount += 300 * perf.getAudience();
                    break;
                }
                default: {
                    throw new Exception("unknown type: "+play.getType());
                }
            }

            // add volume credits
            volumeCredits += Math.max(perf.getAudience()-30, 0);

            // add extra credit for every ten comedy attendees
            if ("comedy".equals(play.getType()))
                volumeCredits += (int) Math.floor((double) perf.getAudience() / 5);

            // print line for this order
            result.append("  ").append(play.getName()).append(": ").append(NumberFormat.getCurrencyInstance(Locale.US).format(thisAmount / 100)).append(" (").append(perf.getAudience()).append(" seats)").append("\n");
            totalAmount += thisAmount;
        }
        result.append("Amount owed is ").append(NumberFormat.getCurrencyInstance(Locale.US).format(totalAmount / 100)).append("\n");
        result.append("You earned ").append(volumeCredits).append(" credits\n");
        return result.toString();
    }
}
