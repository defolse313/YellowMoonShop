/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.controllers;

import hieudn.blos.ShoppingBLO;
import hieudn.carts.CartObj;
import hieudn.entities.Cake;
import hieudn.entities.Order1;
import hieudn.entities.OrderDetails;
import hieudn.entities.Users;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp
 */
public class AddOrderController extends HttpServlet {

    private static final String SUCCESS = "SearchController";
    private static final String ERROR = "error.jsp";

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
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String userId = "";
            HttpSession session = request.getSession();
            String u1 = (String) session.getAttribute("ROLE");
            if (u1 != null) {
                userId = (String) session.getAttribute("IDU");
                java.util.Date d = new java.util.Date();
                Date d1 = new Date(d.getTime());
                int orderPrice = Integer.parseInt(session.getAttribute("TOTAL").toString());
                String payment = "COD (Cash on delivery)";
                ShoppingBLO blo = new ShoppingBLO();
                Users u = new Users(userId);
                boolean result = blo.insertOrder(userId, d, orderPrice, payment);
                Order1 order = new Order1(d, orderPrice, payment, u);
                List<OrderDetails> list = new ArrayList<>();
                CartObj cart = (CartObj) session.getAttribute("CART");
                for (Map.Entry<Cake, Integer> entry : cart.getItems().entrySet()) {
                    boolean result1 = blo.insertOrderDetails(order, entry.getKey(), entry.getValue(), (entry.getValue() * entry.getKey().getCakePrice()));
                    if (result1) {
                        url = SUCCESS;
                    }
                }
                order.setOrderDetailsCollection(list);
                if (result) {
                    url = SUCCESS;
                } else {
                    url = ERROR;
                }
            } else {
                java.util.Date d = new java.util.Date();
                Date d1 = new Date(d.getTime());
                int orderPrice = Integer.parseInt(session.getAttribute("TOTAL").toString());
                String payment = "COD (Cash on delivery)";
                String name = request.getParameter("txtName");
                String address = request.getParameter("txtAddress");
                int phone = Integer.parseInt(request.getParameter("txtPhone"));
                ShoppingBLO blo = new ShoppingBLO();
                boolean result = blo.insertNewOrder(d, orderPrice, name, address, phone, payment);
                if (result) {
                    url = SUCCESS;
                } else {
                    url = ERROR;
                }
            }
        } catch (Exception e) {
            log("Error at Add Order Controller: " + e.getMessage());
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
