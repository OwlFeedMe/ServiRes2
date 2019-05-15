/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import model.Miembro;
import model.ThImage;
import model.War;

/**
 *
 * @author cspor
 */
public class DB_Requests {

    public Connection connection;
    public String driver = "com.mysql.cj.jdbc.Driver";
    public String database = "CoC";
    public String hostname = "127.0.0.1";
    public String port = "3306";
    public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";
    public String username = "root";
    public String password = "";

    public Connection conectarMySQL() {
        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            this.connection = conn;
            System.out.println("conectado");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public void desconectar() {
        try {
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ThImage ThUrl(int Lvl) {
        ThImage thImage = new ThImage();
        conectarMySQL();

        try {
            // create the java statement

            Statement st = connection.createStatement();

            // execute the query, and get a java resultset
            String query = "SELECT * FROM ThImage where Lvl = " + Lvl;
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                // iterate through the java resultset

                thImage.setLvl(rs.getInt("Lvl"));
                thImage.setUrl(rs.getString("Url"));
            }
            // print the results
            st.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Failed to make update!");
            e.printStackTrace();
            return null;
        }
        desconectar();
        return thImage;

    }

    public String ClanWarState(War war) throws SQLException {

        conectarMySQL();

        Statement st = connection.createStatement();
        // create the java statement

        String query = "SELECT * FROM ClanTags where Tag = '" + war.getOpponent().getTag() + "'";
        ResultSet rs = st.executeQuery(query);
        if (!rs.first()) {
            if (war.getState().equals("warEnded")) {
                query = "insert into ClanTags (Tag, Name, State)"
                        + " values (?, ?, ?)";
                PreparedStatement preparedStmt = connection.prepareStatement(query);

                preparedStmt.setString(1, war.getClan().getTag());
                preparedStmt.setString(2, war.getClan().getName());
                preparedStmt.setString(3, war.getState());
                
                ClanWar(war);
            } else {
                desconectar();
                return war.getClan().getName() + " Vs " + war.getOpponent().getName() + "\n" + war.getClan().getStars() + " - " + war.getOpponent().getStars() + "\n" + war.getState() + "\n" + "The leaderboard is updated when the war ends." + "\n" + "Refresh the page.";
            }
        } else {
            desconectar();
            return war.getClan().getName() + " Vs " + war.getOpponent().getName() + "\n" + war.getClan().getStars() + " - " + war.getOpponent().getStars() + "\n" + war.getState() + "\n" + "The leaderboard is updated when the war ends." + "\n" + "Refresh the page.";
        }
        desconectar();
        return "";
    }

    public String ClanWar(War war) throws SQLException {

        conectarMySQL();

        try {
            Statement st = connection.createStatement();
            // create the java statement
            for (int i = 0; i < war.getTeamSize(); i++) {
                String query = "SELECT * FROM ClanWar where Tag = '" + war.getClan().getMembers().get(i).getTag() + "'";
                ResultSet rs = st.executeQuery(query);

                if (!rs.first()) {

                    System.out.println("aca otra vez");
                    query = "insert into ClanWar (Tag, Name, Nguerras, Estrellas, PorE, PorA, puntos)"
                            + " values (?, ?, ?, ?, ?, ?, ?)";

                    try {

                        PreparedStatement preparedStmt = connection.prepareStatement(query);

                        preparedStmt.setString(1, war.getClan().getMembers().get(i).getTag());
                        preparedStmt.setString(2, war.getClan().getMembers().get(i).getName());
                        preparedStmt.setInt(3, 1);
                        int stars = 0;
                        try {
                            for (int j = 0; j < war.getClan().getMembers().get(i).getAttacks().size(); j++) {
                                stars += war.getClan().getMembers().get(i).getAttacks().get(j).getStars();
                            }
                        } catch (Exception e) {

                        }

                        preparedStmt.setInt(4, stars);
                        double a = ((double) stars / 6);
                        preparedStmt.setDouble(5, a);
                        double por = 0;
                        try {
                            for (int j = 0; j < war.getClan().getMembers().get(i).getAttacks().size(); j++) {
                                por += war.getClan().getMembers().get(i).getAttacks().get(j).getDestructionPercentage();
                            }
                        } catch (Exception e) {

                        }

                        preparedStmt.setDouble(6, (por / 2) / 100);
                        double puntos = 0;
                        try {
                            for (int j = 0; j < war.getClan().getMembers().get(i).getAttacks().size(); j++) {
                                int Th = 0;
                                war.getClan().getMembers().get(i).getAttacks().get(j).getDefenderTag();
                                for (int k = 0; k < war.getOpponent().getMembers().size(); k++) {
                                    if (war.getOpponent().getMembers().get(k).getTag().equals(war.getClan().getMembers().get(i).getAttacks().get(j).getDefenderTag())) {
                                        Th = war.getOpponent().getMembers().get(k).getTownhallLevel();
                                    }
                                }

                                int ThP = war.getClan().getMembers().get(i).getTownhallLevel();

                                int starsP = war.getClan().getMembers().get(i).getAttacks().get(j).getStars();

                                double porcentaje = war.getClan().getMembers().get(i).getAttacks().get(j).getDestructionPercentage();
                                if ((Th - ThP) >= 0) {
                                    puntos += ((starsP * (ThP / 2)) * (1 + (Th - ThP) * 0.5)) + (((porcentaje / 100) * ThP) * (1 + (Th - ThP) * 0.5));
                                } else {
                                    puntos += ((starsP * (ThP / 2)) * (1 + (-1) * 0.5)) + (((porcentaje / 100) * ThP) * (1 + (-1) * 0.5));
                                }

                            }
                        } catch (Exception e) {
                        }

                        preparedStmt.setDouble(7, puntos);
                        preparedStmt.execute();

                        System.out.println("You made it, the insertion is ok!");
                    } catch (SQLException e) {

                    }
                } else {
                    System.out.println("diferente");
                    try {
                        //Update
                        int guerras = rs.getInt("Nguerras");
                        int Estrellas = rs.getInt("Estrellas");
                        double PorE = rs.getDouble("PorE");
                        double PorA = rs.getDouble("PorA");
                        int puntosV = rs.getInt("puntos");

                        guerras++;
                        int stars = 0;
                        try {
                            for (int j = 0; j < war.getClan().getMembers().get(i).getAttacks().size(); j++) {
                                stars += war.getClan().getMembers().get(i).getAttacks().get(j).getStars();
                            }
                        } catch (Exception e) {

                        }
                        Estrellas += stars;
                        PorE = ((double) Estrellas / (guerras * 6)) * 100;
                        double por = 0;
                        try {
                            for (int j = 0; j < war.getClan().getMembers().get(i).getAttacks().size(); j++) {
                                por += war.getClan().getMembers().get(i).getAttacks().get(j).getDestructionPercentage();
                            }
                        } catch (Exception e) {

                        }
                        por = (double) por / 2;
                        por = (double) por / 100;
                        System.out.println(por);
                        System.out.println(PorA);
                        PorA = ((double) (PorA + por) / 2);
                        System.out.println(PorA);
                        double puntos = 0;
                        try {
                            for (int j = 0; j < war.getClan().getMembers().get(i).getAttacks().size(); j++) {
                                int Th = 0;
                                war.getClan().getMembers().get(i).getAttacks().get(j).getDefenderTag();
                                for (int k = 0; k < war.getOpponent().getMembers().size(); k++) {
                                    if (war.getOpponent().getMembers().get(k).getTag().equals(war.getClan().getMembers().get(i).getAttacks().get(j).getDefenderTag())) {
                                        Th = war.getOpponent().getMembers().get(k).getTownhallLevel();
                                    }
                                }

                                int ThP = war.getClan().getMembers().get(i).getTownhallLevel();

                                int starsP = war.getClan().getMembers().get(i).getAttacks().get(j).getStars();

                                double porcentaje = war.getClan().getMembers().get(i).getAttacks().get(j).getDestructionPercentage();

                                if ((Th - ThP) >= 0) {
                                    puntos += ((starsP * (ThP / 2)) * (1 + (Th - ThP) * 0.5)) + (((porcentaje / 100) * ThP) * (1 + (Th - ThP) * 0.5));
                                } else {
                                    puntos += ((starsP * (ThP / 2)) * (1 + (-1) * 0.5)) + (((porcentaje / 100) * ThP) * (1 + (-1) * 0.5));
                                }
                            }
                        } catch (Exception e) {
                        }
                        puntosV += puntos;

                        query = "update ClanWar set Nguerras = ? ,Estrellas = ? , PorE = ? , PorA = ? , puntos = ? where Tag = ? ";
                        PreparedStatement preparedStmt = connection.prepareStatement(query);
                        preparedStmt.setInt(1, guerras);
                        preparedStmt.setInt(2, Estrellas);
                        preparedStmt.setDouble(3, PorE / 100);
                        preparedStmt.setDouble(4, PorA);
                        preparedStmt.setInt(5, puntosV);
                        preparedStmt.setString(6, war.getClan().getMembers().get(i).getTag());

                        // execute the java preparedstatement
                        preparedStmt.executeUpdate();

                    } catch (SQLException er) {
                        // TODO Auto-generated catch block
                        System.out.println("Failed to make update!");
                        er.printStackTrace();

                    }

                }

            }

            // execute the query, and get a java resultset
            // print the results
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("Failed to make update!");
            e.printStackTrace();
            return null;
        }
        desconectar();
        return "";

    }

    public ArrayList<Miembro> Miembros() throws SQLException {

        conectarMySQL();
        ArrayList<Miembro> List = new ArrayList<Miembro>();
        Statement st = connection.createStatement();
        // create the java statement

        String query = "SELECT * FROM ClanWar";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Miembro miembro = new Miembro();
            miembro.setName(rs.getString("Name"));
            miembro.setNguerras(rs.getInt("Nguerras"));
            miembro.setPorA(rs.getDouble("PorA"));
            miembro.setPorE(rs.getDouble("PorE"));
            miembro.setPuntos(rs.getInt("puntos"));
            List.add(miembro);

        }
        rs.close();
        desconectar();

        return List;
    }
}
//    public void agregar(Members mem) {
//
////        Insertion 
////	 create a sql date object so we can use it in our INSERT statement
//        // the mysql insert statement
//        String query = "insert into clan (tag,  name, expLevel, Lid, Lname, Ismall, Ilarge, Imedium, trophies, versusTrophies, role, clanRank, previousClanRank, donations, donationsReceived)"
//                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        // create the mysql insert preparedstatement       
//
//        try {
//
//            PreparedStatement preparedStmt = connection.prepareStatement(query);
//            System.out.println("mepeterito");
//            preparedStmt.setString(1, mem.getTag());
//            preparedStmt.setString(2, mem.getName());
//            preparedStmt.setInt(3, (int) mem.getExpLevel());
//            preparedStmt.setInt(4, (int) mem.getLeague().getId());
//            preparedStmt.setString(5, mem.getLeague().getName());
//            preparedStmt.setString(6, mem.getLeague().getIconUrls().getSmall());
//            preparedStmt.setString(7, mem.getLeague().getIconUrls().getLarge());
//            preparedStmt.setString(8, mem.getLeague().getIconUrls().getMedium());
//            preparedStmt.setInt(9, (int) mem.getTrophies());
//            preparedStmt.setInt(10, (int) mem.getVersusTrophies());
//            preparedStmt.setString(11, mem.getRole());
//            preparedStmt.setInt(12, (int) mem.getClanRank());
//            preparedStmt.setInt(13, (int) mem.getPreviousClanRank());
//            preparedStmt.setInt(14, (int) mem.getDonations());
//            preparedStmt.setInt(15, (int) mem.getDonationsReceived());
//            // execute the preparedstatement
//            preparedStmt.execute();
//            System.out.println("You made it, the insertion is ok!");
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            System.out.println("Failed to make insertion!");
//            e.printStackTrace();
//
//        }
//
//    }
//
//    public void Drop() throws SQLException {
// public void agregar(Members mem) {
//
////        Insertion 
////	 create a sql date object so we can use it in our INSERT statement
//        // the mysql insert statement
//        String query = "insert into clan (tag,  name, expLevel, Lid, Lname, Ismall, Ilarge, Imedium, trophies, versusTrophies, role, clanRank, previousClanRank, donations, donationsReceived)"
//                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        // create the mysql insert preparedstatement       
//
//        try {
//
//            PreparedStatement preparedStmt = connection.prepareStatement(query);
//            System.out.println("mepeterito");
//            preparedStmt.setString(1, mem.getTag());
//            preparedStmt.setString(2, mem.getName());
//            preparedStmt.setInt(3, (int) mem.getExpLevel());
//            preparedStmt.setInt(4, (int) mem.getLeague().getId());
//            preparedStmt.setString(5, mem.getLeague().getName());
//            preparedStmt.setString(6, mem.getLeague().getIconUrls().getSmall());
//            preparedStmt.setString(7, mem.getLeague().getIconUrls().getLarge());
//            preparedStmt.setString(8, mem.getLeague().getIconUrls().getMedium());
//            preparedStmt.setInt(9, (int) mem.getTrophies());
//            preparedStmt.setInt(10, (int) mem.getVersusTrophies());
//            preparedStmt.setString(11, mem.getRole());
//            preparedStmt.setInt(12, (int) mem.getClanRank());
//            preparedStmt.setInt(13, (int) mem.getPreviousClanRank());
//            preparedStmt.setInt(14, (int) mem.getDonations());
//            preparedStmt.setInt(15, (int) mem.getDonationsReceived());
//            // execute the preparedstatement
//            preparedStmt.execute();
//            System.out.println("You made it, the insertion is ok!");
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            System.out.println("Failed to make insertion!");
//            e.printStackTrace();
//
//        }
//
//    }
//
//    
//        Statement st = connection.createStatement();
//
//        String query = "delete from clan";
//        ResultSet rs = st.executeQuery(query);
//
//    }
//    public Employee buscar(int a, int b) throws SQLException {
//        Employee dos = new Employee();
//        try {
//            // create the java statement
//
//            Statement st = connection.createStatement();
//
//            // execute the query, and get a java resultset
//            String query = "SELECT * FROM Aseo where id_Aseo = " + a + " and id_residencia = " + b+" ";
//            ResultSet rs = st.executeQuery(query);
//
//            while (rs.next()) {
//            // iterate through the java resultset
//            dos.setId(rs.getInt("id_Aseo"));
//            dos.setId_re(rs.getInt("id_residencia"));
//            dos.setCompania_Aseo(rs.getString("compania_Aseo"));
//            dos.setFF(rs.getDate("fecha_Inicio_Contrato"));
//            dos.setFI(rs.getDate("fecha_Final_Contrato"));
//            dos.setPre(rs.getInt("precio_Aseo"));
//            }
//            // print the results
//            st.close();
//
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            System.out.println("Failed to make update!");
//            e.printStackTrace();
//            return null;
//        }
//
//        return dos;
//    }
//
//    public int actualizar(int a, int b, String c, java.sql.Date d, java.sql.Date e, int f) {
//        try {
//            //Update
//
//            if (buscar(a, b).getId()==0) {
//               return 0; 
//            }
//            // create the java mysql update preparedstatement
//            String query = "update Aseo set compania_Aseo = ? ,fecha_Inicio_Contrato = ? , fecha_Final_Contrato = ? , precio_Aseo = ? where id_Aseo = ? and id_residencia = ? ";
//            PreparedStatement preparedStmt = connection.prepareStatement(query);
//            preparedStmt.setString(1, c);
//            preparedStmt.setDate(2, d);
//            preparedStmt.setDate(3, e);
//            preparedStmt.setInt(4, f);
//            preparedStmt.setInt(5, a);
//            preparedStmt.setInt(6, b);
//
//            // execute the java preparedstatement
//            preparedStmt.executeUpdate();
//            
//        } catch (SQLException er) {
//            // TODO Auto-generated catch block
//            System.out.println("Failed to make update!");
//          er.printStackTrace();
//            
//        }
//        return 1;
//    }
//
//    public int eliminar(int a, int b) {
//        try {
//            if (buscar(a, b).getId()==0) {
//               return 0; 
//            }
//            String query = "delete from Aseo where id_Aseo = ? and id_residencia = ?";
//            PreparedStatement preparedStmt = connection.prepareStatement(query);
//            preparedStmt.setInt(1, a);
//            preparedStmt.setInt(2, b);
//            preparedStmt.execute();
//            System.out.println("eliminado");
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            System.out.println("Failed to make update!");
//            e.printStackTrace();
//        }
//        return 1;
//    }

//    public ArrayList<Members> cargar() {
//        conectarMySQL();
//        ArrayList<Members> arrayList = new ArrayList<Members>();
//        try {
//            // create the java statement
//
//            Statement st = connection.createStatement();
//            // execute the query, and get a java resultset
//            String query = "SELECT * FROM clan ";
//            ResultSet rs = st.executeQuery(query);
//
//            while (rs.next()) {
//                Members members = new Members();
//                //tag,  name, expLevel, Lid, Lname, Ismall, Ilarge, Imedium, trophies, versusTrophies, role, clanRank, previousClanRank, donations, donationsReceived
//                members.setTag(rs.getString("tag"));
//                members.setName(rs.getString("name"));
//                members.setExpLevel(rs.getInt("expLevel"));
//                members.setTrophies(rs.getInt("trophies"));
//                members.setVersusTrophies(rs.getInt("versusTrophies"));
//                members.setRole(rs.getString("role"));
//                members.setClanRank(rs.getInt("clanRank"));
//                members.setPreviousClanRank(rs.getInt("previousClanRank"));
//                members.setDonations(rs.getInt("donations"));
//                members.setDonationsReceived(rs.getInt("donationsReceived"));
//                IconUrls iconUrls = new IconUrls();
////                League league = new League((int) members.getTrophies());
////                league.setIconUrls(iconUrls);
////                members.setLeague(league);
//                arrayList.add(members);
//            }
//            // print the results
//            st.close();
//
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            System.out.println("Failed to make update!");
//            e.printStackTrace();
//            return null;
//        }
//        return arrayList;
//    }
//}
