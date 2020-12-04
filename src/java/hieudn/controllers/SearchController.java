/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.controllers;

import hieudn.blos.ShoppingBLO;
import hieudn.entities.Category;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp
 */
public class SearchController extends HttpServlet {

    private static final String SUCCESS = "index.jsp";
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
            int currentPage;
            int lower;
            int upper;
            boolean valid = true;
            String search = request.getParameter("txtSearch");
            if (search == null) {
                search = "";
            }
            if (request.getParameter("pageIDPaging") == null) {
                currentPage = 1;
            } else {
                currentPage = Integer.parseInt(request.getParameter("pageIDPaging"));
            }
            if (request.getParameter("txtLowerRange") == null || request.getParameter("txtUpperRange") == null || request.getParameter("txtLowerRange").equals("") || request.getParameter("txtUpperRange").equals("")) {
                lower = 0;
                upper = 0;
            } else {
                lower = Integer.parseInt(request.getParameter("txtLowerRange"));
                upper = Integer.parseInt(request.getParameter("txtUpperRange"));
                if (lower <= 0) {
                    valid = false;
                    request.setAttribute("INVALID", "greater than 0");
                }
                if (upper <= lower) {
                    valid = false;
                    request.setAttribute("INVALID1", "greater than The Lower Range!");
                }

            }
            int pageMaxSize = 20;
            ShoppingBLO blo = new ShoppingBLO();
            List cat = blo.getCategory();

            if (cat != null) {
                request.setAttribute("CATE", cat);
            }
            String catId = request.getParameter("Category");
            Category c = new Category(catId);
            HttpSession session = request.getSession();
            session.setAttribute("CATEGORY", cat);
            List result;
            if (lower > 0 && upper > lower) {
                result = blo.searchRangeMoney(lower, upper, currentPage, pageMaxSize);
                if (result != null) {
                    request.setAttribute("INFO", result);
                    request.setAttribute("CURRENT_PAGE", currentPage);
                    request.setAttribute("CAKE_COUNT", blo.getRangeMoneyAmount(lower, upper, pageMaxSize));
                    url = SUCCESS;
                } 
            } else if (catId != null) {
                result = blo.getCakeByCategory(c, currentPage, pageMaxSize);
                if (result != null) {
                    request.setAttribute("INFO", result);
                    request.setAttribute("CURRENT_PAGE", currentPage);
                    request.setAttribute("CAKE_COUNT", blo.getCakeByCatAmount(c, pageMaxSize));
                    url = SUCCESS;
                }
            } else {
                result = blo.searchByCakeName(search, currentPage, pageMaxSize);
                if (result != null) {
                    request.setAttribute("INFO", result);
                    request.setAttribute("CURRENT_PAGE", currentPage);
                    request.setAttribute("CAKE_COUNT", blo.getAmount(search, pageMaxSize));
                    url = SUCCESS;
                }
            }
            request.setAttribute("PAGEID", 1);

        } catch (Exception e) {
            log("Error at Search Controller: " + e.getMessage());
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
