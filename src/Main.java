import java.io.IOException;
import java.util.FormatFlagsConversionMismatchException;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final int TIME = 10;

    public static void main(String[] args) throws IOException, InterruptedException {
        Connect connection = new Connect("Checky");

        GameInfoResponse.GIData curdata;

        do{
            curdata = connection.getInfo();
            if(curdata.status.equals("Game is over")) break;

            if(curdata.whose_turn.equals(Connect.color) && curdata.winner == null){
                Movement curmv = new Movement(Connect.color, connection);

                connection.sendMove(Connect.token, curmv.makeMove());

            }
            else TimeUnit.SECONDS.sleep(1);

        } while (!curdata.status.equals("Game is over") && curdata.winner == null);

    }


}
