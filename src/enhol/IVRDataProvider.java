package enhol;

import enhol.types.AbonInfoResponse;
import enhol.types.DebtInfoResponse;
import enhol.types.PutReadingsResponse;
import oracle.jdbc.OracleCallableStatement;
import org.apache.axis2.context.MessageContext;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.math.BigDecimal;
import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: uvv
 * Date: 10.07.13
 * Time: 14:48
 */
@WebService
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class IVRDataProvider {

    private Connection connection = null;

    private void openConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
//        connection = DriverManager.getConnection("jdbc:oracle:thin:@10.10.10.211:1521:decXXX", "uvv", "gjkyjtckexft");
//        connection = DriverManager.getConnection("jdbc:oracle:thin:@10.10.11.204:1521:decYes", "uvv", "gjkyjtckexft");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@OraSrv:1521:decus", "uvv", "gjkyjtckexft");
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* IVR */

    @WebMethod
    public DebtInfoResponse getDebtInfo(String ptNumber, String requestId, int account, long clid) {
        int debtsValue = 0;
        int tariffscheme = 0;
        int dayValue = 0;
        int nightValue = 0;
        int result = 0;

        MessageContext inMessageContext = MessageContext.getCurrentMessageContext();
        String ip_address = ((inMessageContext == null)) ? "127.0.0.1" : (String) inMessageContext.getProperty(MessageContext.REMOTE_ADDR);

        try {
            openConnection();

            String query = "BEGIN ? := enhol.IVR_WORK.GET_ABON_INFO(?,?,?,?, ?,?,?,?,?, ?,?, ?,?,?,?,?,?); END;";
            OracleCallableStatement pstmt = (OracleCallableStatement) connection.prepareCall(query);
            pstmt.registerOutParameter(1, Types.NUMERIC);
            pstmt.setString(2, ptNumber);
            pstmt.setString(3, requestId);
            pstmt.setInt(4, account);
            pstmt.setString(5, ip_address);
            pstmt.setLong(6, clid);
            pstmt.registerOutParameter(7, Types.NUMERIC);
            pstmt.registerOutParameter(8, Types.NUMERIC);
            pstmt.registerOutParameter(9, Types.NUMERIC);
            pstmt.registerOutParameter(10, Types.NUMERIC);
            pstmt.registerOutParameter(11, Types.VARCHAR);
            pstmt.registerOutParameter(12, Types.NUMERIC);
            pstmt.registerOutParameter(13, Types.VARCHAR); // out: type_flat
            pstmt.registerOutParameter(14, Types.VARCHAR); // out: type_fund
            pstmt.registerOutParameter(15, Types.VARCHAR); // out: type_account
            pstmt.registerOutParameter(16, Types.VARCHAR); // out: type_meter
            pstmt.registerOutParameter(17, Types.NUMERIC); // out: digits_meter
            pstmt.registerOutParameter(18, Types.VARCHAR); // out: date_start_meter

            pstmt.execute();

            int retcode = pstmt.getNUMBER(1).intValue();

            if (retcode==1) {
                debtsValue = pstmt.getNUMBER(7).intValue();
                tariffscheme = pstmt.getNUMBER(8).intValue();
                dayValue = pstmt.getNUMBER(9).intValue();
                nightValue = pstmt.getNUMBER(10).intValue();
                result = 1;
            }
            pstmt.close();

        } catch (ClassNotFoundException e) {
            System.out.println("#ClassNotFoundException");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("#SQLException");
            e.printStackTrace();
        }

        closeConnection();

        return new DebtInfoResponse(requestId, result, debtsValue, tariffscheme, dayValue, nightValue);
    }

    @WebMethod
    public PutReadingsResponse putReadings(String ptNumber, String requestId, int account, int dayValue, int nightValue) {
        int result = 0;

        MessageContext inMessageContext = MessageContext.getCurrentMessageContext();
        String ip_address = ((inMessageContext == null)) ? "127.0.0.1" : (String) inMessageContext.getProperty(MessageContext.REMOTE_ADDR);

        try {
            openConnection();
            //log(ptNumber,sectorId,account,dayCounter,nightCounter);

            String query = "BEGIN ? := enhol.IVR_WORK.PUT_READINGS(?,?,?, ?,?,?); END;";
            OracleCallableStatement pstmt = (OracleCallableStatement) connection.prepareCall(query);
            pstmt.registerOutParameter(1, Types.NUMERIC);
            pstmt.setString(2, ptNumber);
            pstmt.setString(3, requestId);
            pstmt.setInt(4, account);
            pstmt.setInt(5, dayValue);
            pstmt.setInt(6, nightValue);
            pstmt.setString(7, ip_address);

            pstmt.execute();

            result = pstmt.getNUMBER(1).intValue();

            pstmt.close();

        } catch (ClassNotFoundException e) {
            System.out.println("#ClassNotFoundException");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("#SQLException");
            e.printStackTrace();
        }

        closeConnection();

        return new PutReadingsResponse(requestId,result);
    }

    @WebMethod
    public AbonInfoResponse searchAbonByClid(long clid) {
//        System.err.println("searchAbonByClid("+clid+")");

        MessageContext inMessageContext = MessageContext.getCurrentMessageContext();
        String ip_address = ((inMessageContext == null)) ? "127.0.0.1" : (String) inMessageContext.getProperty(MessageContext.REMOTE_ADDR);

        AbonInfoResponse abonInfo = new AbonInfoResponse();
        abonInfo.setResult(0);
        try {
            openConnection();

            String query = "SELECT column_value FROM TABLE(enhol.ivr_work.get_searched_accounts(?,?,?,?,?,?,?))";
            OracleCallableStatement pstmt = (OracleCallableStatement) connection.prepareCall(query);
            pstmt.setBigDecimal(1, new BigDecimal(0)); // searchtype
            pstmt.setString(2, String.valueOf(clid)); //clid
            pstmt.setNull(3, Types.NUMERIC); // account
            pstmt.setNull(4, Types.NUMERIC); // houseId
            pstmt.setNull(5, Types.VARCHAR); // flat
            pstmt.setString(6, "IVR"); // pt_number
            pstmt.setString(7, ip_address); // ip_address

            pstmt.execute();

            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                abonInfo.setAccount(result.getLong(1));
            }

            result.close();
            pstmt.close();

            if (abonInfo.getAccount()!=0)
                abonInfo.setResult(1);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return abonInfo;
    }

}
