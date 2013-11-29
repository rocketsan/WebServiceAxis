package enhol;

import enhol.types.*;
import oracle.jdbc.OracleCallableStatement;
import org.apache.axis2.context.MessageContext;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: uvv
 * Date: 09.09.13
 * Time: 10:46
 */
@WebService
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class CallCenterDataProvider {

    private Connection connection = null;

    private void openConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@10.10.11.204:1521:decYes", "uvv", "gjkyjtckexft");
//        connection = DriverManager.getConnection("jdbc:oracle:thin:@OraSrv:1521:decus", "uvv", "gjkyjtckexft");
    }

    private void closeConnection() {
        if (connection==null) return;
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* CALL-CENTER */

    /* 1 */
    @WebMethod
    public FilialResponse[] fetchAllFilials() {
        ArrayList<FilialResponse> allFilials = new ArrayList<FilialResponse>();
        try {
            openConnection();
            String query = "SELECT ref_subdiv_unit, name_subdiv_unit " +
                    "  FROM enenergy.subdiv_unit " +
                    " WHERE NAME_FIRM IS NOT NULL " +
                    " ORDER BY 1 ";
            OracleCallableStatement pstmt = (OracleCallableStatement) connection.prepareCall(query);
            ResultSet result = pstmt.executeQuery();

            while(result.next()) {
                allFilials.add(new FilialResponse(result.getInt(1), result.getString(2)));
            }
            result.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();

        return allFilials.toArray(new FilialResponse[allFilials.size()]);
    }

    /* 2 */
    @WebMethod
    public SectorResponse[] fetchAllSectors(String prefixText, int count) {
        ArrayList<SectorResponse> allSectors = new ArrayList<SectorResponse>();
        try {
            openConnection();

            String query = "SELECT s.ref_sector, s.name_sector, su.name_subdiv_unit " +
                    " FROM enaddress.sector s " +
                    " LEFT JOIN enenergy.subdiv_unit su ON (su.ref_subdiv_unit=s.ref_subdiv_unit) " +
                    " WHERE ref_sector<>0" +
                    " AND upper(s.name_sector) LIKE upper(?) " +
                    " ORDER BY name_sector";
            OracleCallableStatement pstmt = (OracleCallableStatement) connection.prepareCall(query);
            pstmt.setString(1, prefixText + "%");
            ResultSet result = pstmt.executeQuery();

            while(result.next() && allSectors.size()<count) {
                allSectors.add(new SectorResponse(result.getInt(1), result.getString(2), result.getString(3)));
            }
            result.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();

        return allSectors.toArray(new SectorResponse[allSectors.size()]);
    }

    /* 3 */
    @WebMethod
    public StreetResponse[] fetchStreetsBySector(int sectorId, String prefixText, int count) {
        ArrayList<StreetResponse> allStreets = new ArrayList<StreetResponse>();

        try {
            openConnection();

            String query = "SELECT distinct st.ref_street, trim(st.name_street) " +
                    " FROM enaddress.street st " +
                    " LEFT JOIN enaddress.house h ON (h.ref_street=st.ref_street) " +
                    " WHERE h.ref_sector = ? " +
                    "   AND upper(st.name_street) LIKE upper(?) " +
                    " ORDER BY trim(st.name_street)";

            OracleCallableStatement pstmt = (OracleCallableStatement) connection.prepareCall(query);
            pstmt.setBigDecimal(1, new BigDecimal(sectorId));
            pstmt.setString(2, prefixText+"%");
            ResultSet result = pstmt.executeQuery();

            while (result.next() && allStreets.size()<count) {
                allStreets.add(new StreetResponse(result.getInt(1), result.getString(2)));
            }

            pstmt.close();
            result.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allStreets.toArray(new StreetResponse[allStreets.size()]);
    }

    /* 4 */
    @WebMethod
    public StreetResponse[] fetchStreetsWithSectorName(String prefixText, int count) {
        ArrayList<StreetResponse> allStreets = new ArrayList<StreetResponse>();

        try {
            openConnection();

            String query = "SELECT distinct st.ref_street, trim(st.name_street), trim(s.name_sector) " +
                    " FROM enaddress.street st " +
                    " LEFT JOIN enaddress.house h ON (h.ref_street=st.ref_street) " +
                    " LEFT JOIN enaddress.sector s on (s.ref_sector=h.ref_sector) " +
                    " WHERE upper(st.name_street) LIKE upper(?) " +
                    " ORDER BY trim(s.name_sector), trim(st.name_street)";

            OracleCallableStatement pstmt = (OracleCallableStatement) connection.prepareCall(query);
            pstmt.setString(1, prefixText+"%");
            ResultSet result = pstmt.executeQuery();

            while (result.next() && allStreets.size()<count) {
                allStreets.add(new StreetResponse(result.getInt(1), result.getString(2), result.getString(3)));
            }

            pstmt.close();
            result.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allStreets.toArray(new StreetResponse[allStreets.size()]);
    }

    /* 5 */
    @WebMethod
    public HouseResponse[] fetchHousesByStreet(int streetId, String prefixText, int count) {
        ArrayList<HouseResponse> allHouses = new ArrayList<HouseResponse>();

        try {
            openConnection();

            String query = " SELECT ref_house, number_house||DECODE (number_block,\n" +
                    "                  '-1', '', " +
                    "                  '0', '', " +
                    "                  '-', '', " +
                    "                  ' к.' || number_block " +
                    "                 ) AS number_house " +
                    " FROM enaddress.house " +
                    " WHERE ref_street = ? " +
                    "  AND upper(number_house) LIKE upper(?) " +
                    " ORDER BY ed_endebtor.to_num(number_house), ed_endebtor.to_num(number_block) ";

            OracleCallableStatement pstmt = (OracleCallableStatement) connection.prepareCall(query);
            pstmt.setBigDecimal(1, new BigDecimal(streetId));
            pstmt.setString(2, prefixText+"%");
            ResultSet result = pstmt.executeQuery();

            while (result.next() && allHouses.size()<count) {
                allHouses.add(new HouseResponse(result.getInt(1), result.getString(2)));
            }

            pstmt.close();
            result.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allHouses.toArray(new HouseResponse[allHouses.size()]);
    }

        /* 5 */
    @WebMethod
    public HouseResponse[] fetchHousesBySectorAndStreet(int sectorId, int streetId, String prefixText, int count) {
        ArrayList<HouseResponse> allHouses = new ArrayList<HouseResponse>();

        try {
            openConnection();

            String query = " SELECT ref_house, number_house||DECODE (number_block,\n" +
                    "                  '-1', '', " +
                    "                  '0', '', " +
                    "                  '-', '', " +
                    "                  ' к.' || number_block " +
                    "                 ) AS number_house " +
                    " FROM enaddress.house " +
                    " WHERE ref_street = ? AND ref_sector = ? " +
                    "  AND upper(number_house) LIKE upper(?) " +
                    " ORDER BY ed_endebtor.to_num(number_house), ed_endebtor.to_num(number_block) ";

            OracleCallableStatement pstmt = (OracleCallableStatement) connection.prepareCall(query);
            pstmt.setBigDecimal(1, new BigDecimal(streetId));
            pstmt.setBigDecimal(2, new BigDecimal(sectorId));
            pstmt.setString(3, prefixText+"%");
            ResultSet result = pstmt.executeQuery();

            while (result.next() && allHouses.size()<count) {
                allHouses.add(new HouseResponse(result.getInt(1), result.getString(2)));
            }

            pstmt.close();
            result.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allHouses.toArray(new HouseResponse[allHouses.size()]);
    }

    /* 6 */
    @WebMethod
    public FlatResponse[] fetchFlatsByHouse(int houseId, String prefixText, int count) {
        ArrayList<FlatResponse> allFlats = new ArrayList<FlatResponse>();

        try {
            openConnection();

            String query = " SELECT DISTINCT flat " +
                    " FROM payer_s " +
                    " WHERE ref_house = ?" +
                    " AND upper(flat) LIKE upper(?) " +
                    " ORDER BY ed_endebtor.to_num(flat) ";

            OracleCallableStatement pstmt = (OracleCallableStatement) connection.prepareCall(query);
            pstmt.setBigDecimal(1, new BigDecimal(houseId));
            pstmt.setString(2, prefixText+"%");
            ResultSet result = pstmt.executeQuery();

            while (result.next() && allFlats.size()<count) {
                allFlats.add(new FlatResponse(result.getString(1)));
            }

            pstmt.close();
            result.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allFlats.toArray(new FlatResponse[allFlats.size()]);
    }

    /* 7 */
    @WebMethod
    public SearchAccountResponse[] searchAccount(int searchType, String clid, int account, int streetId, int houseId, String houseNumber, String flatNumber) {
        ArrayList<SearchAccountResponse> allAccounts = new ArrayList<SearchAccountResponse>();

        MessageContext inMessageContext = MessageContext.getCurrentMessageContext();
        String ip_address = ((inMessageContext == null)) ? "127.0.0.1" : (String) inMessageContext.getProperty(MessageContext.REMOTE_ADDR);

        try {
            openConnection();

            String query = "SELECT column_value FROM TABLE(enhol.ivr_work.get_searched_accounts(?,?,?,?,?,?,?,?,?))";
            OracleCallableStatement pstmt = (OracleCallableStatement) connection.prepareCall(query);
            pstmt.setBigDecimal(1, new BigDecimal(searchType));
            if (clid==null || clid.equals("")) {
                pstmt.setNull(2, Types.VARCHAR);
            } else {
                pstmt.setString(2, clid);
            }
            pstmt.setBigDecimal(3, new BigDecimal(account));
            pstmt.setBigDecimal(4, new BigDecimal(streetId));
            pstmt.setBigDecimal(5, new BigDecimal(houseId));
            if (houseNumber==null || houseNumber.equals("")) {
                pstmt.setNull(6, Types.NUMERIC);
            } else {
                pstmt.setString(6, houseNumber);
            }
            if (flatNumber==null || flatNumber.equals("")) {
                pstmt.setNull(7, Types.NUMERIC);
            } else {
                pstmt.setString(7, flatNumber);
            }
            pstmt.setString(8, "CC"); // pt_number
            pstmt.setString(9, ip_address); // ip_address

            pstmt.execute();

            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                SearchAccountResponse accountResponse = new SearchAccountResponse(result.getLong(1));

                String query2 = "BEGIN ? := enhol.IVR_WORK.get_account_info(?, ?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
                OracleCallableStatement pstmt2 = (OracleCallableStatement) connection.prepareCall(query2);
                pstmt2.registerOutParameter(1, Types.NUMERIC);
                pstmt2.setBigDecimal(2, new BigDecimal(accountResponse.getAccount())); // !!!
                pstmt2.registerOutParameter(3, Types.VARCHAR); // firstName
                pstmt2.registerOutParameter(4, Types.VARCHAR); // secondName
                pstmt2.registerOutParameter(5, Types.VARCHAR); // lastName
                pstmt2.registerOutParameter(6, Types.VARCHAR); // phone1
                pstmt2.registerOutParameter(7, Types.VARCHAR); // phone2
                pstmt2.registerOutParameter(8, Types.VARCHAR); // phone3
                pstmt2.registerOutParameter(9, Types.VARCHAR); // email1
                pstmt2.registerOutParameter(10, Types.VARCHAR); // email2
                pstmt2.registerOutParameter(11, Types.VARCHAR); // adrrFilialName
                pstmt2.registerOutParameter(12, Types.VARCHAR); // addrSectorName
                pstmt2.registerOutParameter(13, Types.VARCHAR); // adrrStreetName
                pstmt2.registerOutParameter(14, Types.VARCHAR); // adrrHouseNumber
                pstmt2.registerOutParameter(15, Types.VARCHAR); // adrrFlatNumber

                pstmt2.execute();

                accountResponse.setFirstName(pstmt2.getString(3));
                accountResponse.setSecondName(pstmt2.getString(4));
                accountResponse.setLastName(pstmt2.getString(5));
                accountResponse.setAddrFilialName(pstmt2.getString(11));
                accountResponse.setAddrSectorName(pstmt2.getString(12));
                accountResponse.setAddrStreetName(pstmt2.getString(13));
                accountResponse.setAddrHouseNumber(pstmt2.getString(14));
                accountResponse.setAddrFlatNumber(pstmt2.getString(15));

                allAccounts.add(accountResponse);

                pstmt2.close();
            }

            pstmt.close();
            result.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allAccounts.toArray(new SearchAccountResponse[allAccounts.size()]);
    }

    /* 8 */
/*
    @WebMethod
    public AccountInfoResponse getAccountInfo(int account) {
        AccountInfoResponse abonResponse = null;
        try {
            openConnection();

            String query = "begin ? := enhol.IVR_WORK.get_account_info(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?); end;";
            OracleCallableStatement pstmt = (OracleCallableStatement) connection.prepareCall(query);
            pstmt.registerOutParameter(1, Types.NUMERIC);
            pstmt.setBigDecimal(2, new BigDecimal(account));
            pstmt.registerOutParameter(3, Types.VARCHAR); // firstName
            pstmt.registerOutParameter(4, Types.VARCHAR); // secondName
            pstmt.registerOutParameter(5, Types.VARCHAR); // lastName
            pstmt.registerOutParameter(6, Types.VARCHAR); // phone1
            pstmt.registerOutParameter(7, Types.VARCHAR); // phone2
            pstmt.registerOutParameter(8, Types.VARCHAR); // phone3
            pstmt.registerOutParameter(9, Types.VARCHAR); // email1
            pstmt.registerOutParameter(10, Types.VARCHAR); // email2
            pstmt.registerOutParameter(11, Types.VARCHAR); // adrrFilialName
            pstmt.registerOutParameter(12, Types.VARCHAR); // addrSectorName
            pstmt.registerOutParameter(13, Types.VARCHAR); // adrrStreetName
            pstmt.registerOutParameter(14, Types.VARCHAR); // adrrHouseNumber
            pstmt.registerOutParameter(15, Types.VARCHAR); // adrrFlatNumber

            pstmt.execute();

            int result = pstmt.getNUMBER(1).intValue();

            abonResponse = new AccountInfoResponse(result, pstmt.getString(3), pstmt.getString(4), pstmt.getString(5),
                    pstmt.getString(6), pstmt.getString(7), pstmt.getString(8), pstmt.getString(9), pstmt.getString(10),
                    pstmt.getString(11), pstmt.getString(12), pstmt.getString(13), pstmt.getString(14), pstmt.getString(15));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return abonResponse;
    }
*/
    @WebMethod
    public TariffsAndAccountInfoResponse getTariffsAndAccountInfo(int account) {
        TariffsAndAccountInfoResponse abonResponse = null;
        try {
            openConnection();

            String query = "begin ? := enhol.IVR_WORK.get_account_info(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?); end;";
            OracleCallableStatement pstmt = (OracleCallableStatement) connection.prepareCall(query);
            pstmt.registerOutParameter(1, Types.NUMERIC);
            pstmt.setBigDecimal(2, new BigDecimal(account));
            pstmt.registerOutParameter(3, Types.VARCHAR); // firstName
            pstmt.registerOutParameter(4, Types.VARCHAR); // secondName
            pstmt.registerOutParameter(5, Types.VARCHAR); // lastName
            pstmt.registerOutParameter(6, Types.VARCHAR); // phone1
            pstmt.registerOutParameter(7, Types.VARCHAR); // phone2
            pstmt.registerOutParameter(8, Types.VARCHAR); // phone3
            pstmt.registerOutParameter(9, Types.VARCHAR); // email1
            pstmt.registerOutParameter(10, Types.VARCHAR); // email2
            pstmt.registerOutParameter(11, Types.VARCHAR); // adrrFilialName
            pstmt.registerOutParameter(12, Types.VARCHAR); // addrSectorName
            pstmt.registerOutParameter(13, Types.VARCHAR); // adrrStreetName
            pstmt.registerOutParameter(14, Types.VARCHAR); // adrrHouseNumber
            pstmt.registerOutParameter(15, Types.VARCHAR); // adrrFlatNumber

            pstmt.execute();

            int result = pstmt.getNUMBER(1).intValue();

            abonResponse = new TariffsAndAccountInfoResponse(result,
                    pstmt.getString(6), pstmt.getString(7), pstmt.getString(8), pstmt.getString(9), pstmt.getString(10));
            abonResponse.setAccount(account);

            pstmt.close();

            MessageContext inMessageContext = MessageContext.getCurrentMessageContext();
            String ip_address = ((inMessageContext == null)) ? "127.0.0.1" : (String) inMessageContext.getProperty(MessageContext.REMOTE_ADDR);

            query = "begin ? := enhol.IVR_WORK.GET_ABON_INFO(?,?,?,?,?,?,?,?,?, ?,?, ?,?,?,?,?,?); end;";
            pstmt = (OracleCallableStatement) connection.prepareCall(query);

            pstmt.registerOutParameter(1, Types.NUMERIC);
            pstmt.setString(2, "CC"); // pt-number
            pstmt.setString(3, "0"); // request-id
            pstmt.setBigDecimal(4, new BigDecimal(account)); // account
            pstmt.setString(5, ip_address); // ip-address
            pstmt.setNull(6, Types.VARCHAR); // clid
            pstmt.registerOutParameter(7, Types.NUMERIC); // out: debt-sum
            pstmt.registerOutParameter(8, Types.NUMERIC); // out: tariffs-count
            pstmt.registerOutParameter(9, Types.NUMERIC); // out: day-counter
            pstmt.registerOutParameter(10, Types.NUMERIC); // out: night-counter
            pstmt.registerOutParameter(11, Types.VARCHAR); // out: norma-payer
            pstmt.registerOutParameter(12, Types.NUMERIC); // out: hasMeter
            pstmt.registerOutParameter(13, Types.VARCHAR); // out: type_flat
            pstmt.registerOutParameter(14, Types.VARCHAR); // out: type_fund
            pstmt.registerOutParameter(15, Types.VARCHAR); // out: type_account
            pstmt.registerOutParameter(16, Types.VARCHAR); // out: type_meter
            pstmt.registerOutParameter(17, Types.NUMERIC); // out: digits_meter
            pstmt.registerOutParameter(18, Types.VARCHAR); // out: date_start_meter

            pstmt.execute();

            abonResponse.setHasMeter(pstmt.getInt(12));

            System.err.println("account:"+account);
            System.err.println("hasMeter:"+pstmt.getInt(12));

            abonResponse.setDayValuePrevious(abonResponse.getHasMeter() == 0 ? 0 : pstmt.getInt(9));
            abonResponse.setNightValuePrevious(abonResponse.getHasMeter() == 0 ? 0 : pstmt.getInt(10));
            abonResponse.setNormaPayer(abonResponse.getHasMeter()==0 ? pstmt.getString(11) : null);

            abonResponse.setTypeFlat(pstmt.getString(13));
            abonResponse.setTypeFund(pstmt.getString(14));
            abonResponse.setTypeAccount(pstmt.getString(15));
            abonResponse.setTypeMeter(pstmt.getString(16));
            abonResponse.setDigitsMeter(pstmt.getInt(17));
            abonResponse.setDateStartMeter(pstmt.getString(18));

            pstmt.close();

            query = "begin enhol.IVR_WORK.calc_cost_tariff(?,?,?); end;";
            pstmt = (OracleCallableStatement) connection.prepareCall(query);

            pstmt.setBigDecimal(1, new BigDecimal(account)); // account
            pstmt.registerOutParameter(2, Types.NUMERIC); // out: day-tariff
            pstmt.registerOutParameter(3, Types.NUMERIC); // out: night-tariff

            pstmt.execute();

            abonResponse.setDayTariff(pstmt.getFloat(2));
            abonResponse.setNightTariff(pstmt.getFloat(3));

            pstmt.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return abonResponse;
    }

    /* 9 */
    @WebMethod
    public int updateContacts(int account, String phone1, String phone2, String phone3, String email1, String email2) {
        int ret = 0;
        try {
            openConnection();

            String query = "begin ? := enhol.IVR_WORK.update_contacts(?,?,?,?,?,?); end;";
            OracleCallableStatement pstmt = (OracleCallableStatement) connection.prepareCall(query);

            pstmt.registerOutParameter(1, Types.NUMERIC);
            pstmt.setBigDecimal(2, new BigDecimal(account));
            pstmt.setString(3, phone1);
            pstmt.setString(4, phone2);
            pstmt.setString(5, phone3);
            pstmt.setString(6, email1);
            pstmt.setString(7, email2);

            pstmt.execute();

            ret = pstmt.getInt(1);

            pstmt.close();
        }  catch (ClassNotFoundException e) {
             e.printStackTrace();
        } catch (SQLException e) {
             e.printStackTrace();
        }

        return ret;
    }

    /* 10 */
    @WebMethod
    public PaymentResponse[] getLastPayments(int account) {
        ArrayList<PaymentResponse> allPayments = new ArrayList<PaymentResponse>();

        try {
            openConnection();

            String query = "SELECT to_char(date_create,'DD.MM.YYYY'), to_char(pay_date,'DD.MM.YYYY'), to_char(total_sum), to_char(nvl(peni_sum,0)) " +
                    "  FROM enenergy.pay_oper_date " +
                    " WHERE ref_payer = enhol.IVR_WORK.get_ref_payer_by_account(?) "+ // paradox!
                    " ORDER BY pay_date DESC ";
            OracleCallableStatement pstmt = (OracleCallableStatement) connection.prepareCall(query);
            pstmt.setBigDecimal(1, new BigDecimal(account));
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                allPayments.add(new PaymentResponse(result.getString(1), result.getString(2), result.getString(3), result.getString(4)));
            }
            result.close();
            pstmt.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allPayments.toArray(new PaymentResponse[allPayments.size()]);
    }

    /* 11 */
    @WebMethod
    public int putReadings(int account, int dayValue, int nightValue) {
        int result = 0;

        MessageContext inMessageContext = MessageContext.getCurrentMessageContext();
        String ip_address = ((inMessageContext == null)) ? "127.0.0.1" : (String) inMessageContext.getProperty(MessageContext.REMOTE_ADDR);

        try {
            openConnection();

            String query = "BEGIN ? := enhol.IVR_WORK.PUT_READINGS(?,?,?, ?,?,?); END;";
            OracleCallableStatement pstmt = (OracleCallableStatement) connection.prepareCall(query);
            pstmt.registerOutParameter(1, Types.NUMERIC);
            pstmt.setString(2, "CC");
            pstmt.setString(3, "1");
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

        return result;
    }

    /* 12 */
    @WebMethod
    public HistoryResponse[] getHistory(int account) {
        ArrayList<HistoryResponse> allHistory = new ArrayList<HistoryResponse>();

        try {
            openConnection();

            String query = "SELECT to_char(date_create,'DD.MM.YYYY HH24:MI:SS'), description, oper_name, clid, action, activity_name1, activity_name2, activity_name3" +
                           "  FROM enhol.cc_history " +
                           " WHERE account = ? " +
                           " ORDER BY date_create DESC ";

            OracleCallableStatement pstmt = (OracleCallableStatement) connection.prepareCall(query);
            pstmt.setBigDecimal(1, new BigDecimal(account));
            ResultSet result = pstmt.executeQuery();

            while(result.next()) {
                allHistory.add(new HistoryResponse(result.getString(1), result.getString(2), result.getString(3), result.getLong(4), result.getString(5), result.getString(6), result.getString(7), result.getString(8)));
            }
            result.close();
            pstmt.close();
        }  catch (ClassNotFoundException e) {
             e.printStackTrace();
        } catch (SQLException e) {
             e.printStackTrace();
        }

        return allHistory.toArray(new HistoryResponse[allHistory.size()]);
    }

    /* 13 */
    @WebMethod
    public PutHistoryResponse putNewHistoryRecord(int account, String clid, String operLoginId, String operName, String activityCode1, String activityName1, String activityCode2, String activityName2, String activityCode3, String activityName3, String action, String description) {
        PutHistoryResponse putHistoryResponse = null;
        try {

            openConnection();

            String query = "begin ? := enhol.IVR_WORK.put_history(?,?,?,?,?, ?,?,?,?,?,?,?,?); end;";

            OracleCallableStatement pstmt = (OracleCallableStatement) connection.prepareCall(query);
            pstmt.registerOutParameter(1, Types.NUMERIC);
            pstmt.setBigDecimal(2, new BigDecimal(account));
            pstmt.setString(3, clid);
            pstmt.setString(4, operLoginId);
            pstmt.setString(5, operName);
            pstmt.registerOutParameter(6, Types.NUMERIC);
            pstmt.setString(7, activityCode1);
            pstmt.setString(8, activityName1);
            pstmt.setString(9, activityCode2);
            pstmt.setString(10, activityName2);
            pstmt.setString(11, activityCode3);
            pstmt.setString(12, activityName3);
            pstmt.setString(13, action);
            pstmt.setString(14, description);

            pstmt.execute();

            putHistoryResponse = new PutHistoryResponse(pstmt.getNUMBER(1).intValue(), pstmt.getNUMBER(6).intValue());

            pstmt.close();
        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return putHistoryResponse;
    }

    /* 14 */
    @WebMethod
    public int updateDescriptionAtHistory(int callId, String description) {
        try {
            openConnection();

            String query = "UPDATE enhol.cc_history SET description=? WHERE call_id=?";

            OracleCallableStatement pstmt = (OracleCallableStatement) connection.prepareCall(query);
            pstmt.setString(1, description);
            pstmt.setBigDecimal(2, new BigDecimal(callId));
            pstmt.execute();

            pstmt.close();
        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    /* 15 */
    @WebMethod
    public int updateActionAtHistory(int callId, String action) {
        try {
            openConnection();

            String query = "UPDATE enhol.cc_history SET action=action||' '||? WHERE call_id=?";

            OracleCallableStatement pstmt = (OracleCallableStatement) connection.prepareCall(query);
            pstmt.setString(1, action);
            pstmt.setBigDecimal(2, new BigDecimal(callId));
            pstmt.execute();

            pstmt.close();
        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    /* 16 */
    @WebMethod
    public int updateActivitiesAtHistory(int callId, String activityCode1, String activityName1, String activityCode2, String activityName2, String activityCode3, String activityName3) {
        try {
            openConnection();

            String codeIndex = null; String activityCode = null; String activityName = null;

            // если строка не пустая, то апдейтить
            if (activityName1!=null && !activityName1.equals("")) {
                codeIndex = "1";
                activityCode = activityCode1;
                activityName = activityName1;
            }
            if (activityName2!=null && !activityName2.equals("")) {
                codeIndex = "2";
                activityCode = activityCode2;
                activityName = activityName2;
            }
            if (activityName3!=null && !activityName3.equals("")) {
                codeIndex = "3";
                activityCode = activityCode3;
                activityName = activityName3;
            }

            if (codeIndex==null)
                return 0;

            String query = "UPDATE enhol.cc_history SET activity_code"+codeIndex+" = ?, activity_name"+codeIndex+" = ? WHERE call_id = ?";
            OracleCallableStatement pstmt = (OracleCallableStatement) connection.prepareCall(query);
            pstmt.setString(1, activityCode);
            pstmt.setString(2, activityName);
            pstmt.setBigDecimal(3, new BigDecimal(callId));
            pstmt.execute();
            pstmt.close();

        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    /* 17 */
    @WebMethod
    public RoundResponse[] getLastRounds(int account) {
        ArrayList<RoundResponse> allRounds = new ArrayList<RoundResponse>();

        try {
            openConnection();

            String query = "SELECT tc.NAME_TYPE_CHANGE, to_char(pr.DATE_ROUND,'DD.MM.YYYY'), pr.DAY_COUNTER, pr.NIGHT_COUNTER " +
                    "  FROM enmeter.plan_rounds pr " +
                    "  LEFT JOIN enmeter.act_insert_meter aim ON (aim.ref_act=pr.ref_act) " +
                    "  LEFT JOIN enmeter.type_change tc ON (tc.ref_type_change=aim.ref_type_change) " +
                    " WHERE pr.REF_PAYER = enhol.IVR_WORK.get_ref_payer_by_account(?) "+
                    " ORDER BY pr.DATE_ROUND DESC ";
            OracleCallableStatement pstmt = (OracleCallableStatement) connection.prepareCall(query);
            pstmt.setBigDecimal(1, new BigDecimal(account));
            ResultSet result = pstmt.executeQuery();

            int i = 0;
            while (result.next() && i<5) {
                i++;
                allRounds.add(new RoundResponse(result.getString(1), result.getString(2), result.getInt(3), result.getInt(4)));
            }
            result.close();
            pstmt.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allRounds.toArray(new RoundResponse[allRounds.size()]);
    }


    /* 18 */
    @WebMethod
    public AccountResponse[] getLastAccounts(int account) {
        ArrayList<AccountResponse> allAccounts = new ArrayList<AccountResponse>();

        try {
            openConnection();

            String query = "SELECT lpad(month,2,'0')||'.'||year, dd.NAME_DOC, aa.DAY_COUNTER_READING_FROM, aa.DAY_COUNTER_READING_TO, aa.NIGHT_COUNTER_READING_FROM, aa.NIGHT_COUNTER_READING_TO, debt_sum+total_sum-much_sum, aa.total_sum, aa.total_sum_pay " +
                    "  FROM enaccount.activ_account aa " +
                    "  left JOIN enaccount.DECL_DOCUMENT dd ON (dd.ref_doc=aa.ref_doc) " +
                    " WHERE aa.ref_payer = enhol.IVR_WORK.get_ref_payer_by_account(?) " +
                    " ORDER BY aa.date_create desc ";
            OracleCallableStatement pstmt = (OracleCallableStatement) connection.prepareCall(query);
            pstmt.setBigDecimal(1, new BigDecimal(account));
            ResultSet result = pstmt.executeQuery();

            int i = 0;
            while (result.next() && i<10) {
                i++;
                allAccounts.add(new AccountResponse(result.getString(1), result.getString(2), result.getInt(3), result.getInt(4), result.getInt(5), result.getInt(6), result.getFloat(7), result.getFloat(8), result.getFloat(9)));
            }
            result.close();
            pstmt.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allAccounts.toArray(new AccountResponse[allAccounts.size()]);
    }


}
