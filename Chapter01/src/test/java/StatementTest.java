import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class StatementTest {

    @Test
    void statement() throws Exception {
        Play hamlet = new Play("hamlet", "Hamlet", "tragedy");
        Play asLike = new Play("as-like", "As You Like It", "comedy");
        Play othello = new Play("othello", "Othello", "tragedy");

        Plays plays = new Plays();
        plays.addPlay(hamlet);
        plays.addPlay(asLike);
        plays.addPlay(othello);

        String customer = "BigCo";
        ArrayList<Performance> performances = new ArrayList<>();
        performances.add(new Performance("hamlet", 55));
        performances.add(new Performance("as-like", 35));
        performances.add(new Performance("othello", 40));

        Invoice invoice = new Invoice(customer, performances);
        Statement statementObj = new Statement();

        String result = statementObj.statement(invoice, plays);

        assert result.equals("""
                Statement for BigCo
                  Hamlet: $650.00 (55 seats)
                  As You Like It: $580.00 (35 seats)
                  Othello: $500.00 (40 seats)
                Amount owed is $1,730.00
                You earned 47 credits
                """);
    }

    @Test
    void statementWithParsedData() throws Exception {
        Plays plays = Main.loadPlays("plays.json");

        ArrayList<Invoice> invoices = Main.loadInvoices("invoices.json");

        Statement statementObj = new Statement();

        assert invoices != null;

        String result = statementObj.statement(invoices.get(0), plays);

        assert result.equals("""
                Statement for BigCo
                  Hamlet: $650.00 (55 seats)
                  As You Like It: $580.00 (35 seats)
                  Othello: $500.00 (40 seats)
                Amount owed is $1,730.00
                You earned 47 credits
                """);
    }
}