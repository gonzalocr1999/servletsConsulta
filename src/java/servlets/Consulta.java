/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Consulta", urlPatterns = {"/Consulta"})
public class Consulta extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (
            PrintWriter out = response.getWriter()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/tesla?user=root&password=mysqladmin";
            Connection connect = DriverManager.getConnection(url);
            Statement statement = connect.createStatement();
            String query = "SELECT * FROM vehiculo";
            ResultSet resultSet = statement.executeQuery(query);
            
            JsonArray jarry = new JsonArray();
            JsonObject gson = new JsonObject();
            
            while(resultSet.next()) {
                int idVehiculo = resultSet.getInt("id_vehiculo");
                String marca = resultSet.getString("marca");
                double precio = resultSet.getDouble("precio");
                String color = resultSet.getString("color");
                
                gson = new JsonObject();
                gson.addProperty("id_vehiculo", idVehiculo);
                gson.addProperty("marca", marca);
                gson.addProperty("precio", precio);
                gson.addProperty("color", color);
                
                jarry.add(gson);
            }


            /*JsonObject gson2 = new JsonObject();
            gson2.addProperty("id_vehiculo", 2);
            gson2.addProperty("marca", "Audi");
            gson2.addProperty("precio", 2800);
            gson2.addProperty("color", "Amarillo");
            
            JsonObject gson3 = new JsonObject();
            gson3.addProperty("id_vehiculo", 3);
            gson3.addProperty("marca", "Nissan");
            gson3.addProperty("precio", 1780);
            gson3.addProperty("color", "Azul");*/

            
            /*jarry.add(gson2);
            jarry.add(gson3);*/
            
            out.print(jarry.toString());
        }catch(Exception e){
            System.err.println(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
