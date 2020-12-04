/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.controllers;

import hieudn.blos.ShoppingBLO;
import hieudn.entities.Cake;
import hieudn.entities.Category;
import hieudn.entities.Users;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import static org.apache.tomcat.jni.User.username;

/**
 *
 * @author hp
 */
public class UpdateController extends HttpServlet {

    private static final String SUCCESS = "DetailsDisplayController";
    private static final String ERROR = "error.jsp";
    private static final String INVALID = "update.jsp";

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
            String realPath_DB = "";
            boolean valid = true;
            Hashtable params = null;
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (!isMultiPart) {
            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload((FileItemFactory) factory);
                List items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (FileUploadException e) {
//                    log("ERROR FileUploadException at PostController: " + e.getMessage());
//                    e.printStackTrace();
                }
                Iterator iter = items.iterator();
                params = new Hashtable();
                String fileName = null;
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString());
                    } else {
                        try {
                            String itemName = item.getName();
                            fileName = itemName.substring(itemName.lastIndexOf("\\") + 1);
                            String realPath = getServletContext().getRealPath("/")
                                    + "images\\" + fileName;
                            System.out.println("Real Path " + realPath);
                            realPath_DB = fileName;
                            File savedFile = new File(realPath);
                            item.write(savedFile);
                        } catch (Exception e) {
                            log("ERROR ImageUploadException at PostController: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
                String cakeId = (String) params.get("txtIdCake");
                String cakeName = (String) params.get("txCakeName");
                int price = Integer.parseInt((String) params.get("txCakePrice"));
                if(price <= 0) {
                    valid = false;
                    request.setAttribute("INVALID", "Greater than 0");
                }
                String cakeDes = (String) params.get("txCakeDes");
                int quantity = Integer.parseInt((String) params.get("txCakeQuantity"));
                if(quantity <= 0) {
                    valid = false;
                    request.setAttribute("INVALID1", "Greater than 0");
                }
                String cakeImage = realPath_DB;
                String createCake = (String) params.get("txCreateDate");
                LocalDate ldc = LocalDate.parse(createCake);
                Date createDate = Date.valueOf(ldc);
                String expireCake = (String) params.get("txExpireDate");
                LocalDate ld = LocalDate.parse(expireCake);
                Date expiredDate = Date.valueOf(ld);
                if(createDate.compareTo(expiredDate) > 0) {
                    valid = false;
                    request.setAttribute("INVALID2", "Create Date before Expired Date");
                }
                ShoppingBLO blo = new ShoppingBLO();
                String category = (String) params.get("catego");
                Category cat = new Category(category);
                String status = (String) params.get("status");
                LocalDateTime myDate = LocalDateTime.now();
                DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                String date = myDate.format(myFormat);
                HttpSession session = request.getSession();
                String user = (String) session.getAttribute("IDU");
                Users u = new Users(user);
                if(valid){
                Cake cake = new Cake(cakeId, cakeName, price, cakeDes, quantity, cakeImage, createDate, expiredDate, status, cat);
                request.setAttribute("CAKEID", cake.getCakeId());
                if (blo.updateCake(cakeId, cakeName, price, cakeDes, quantity, cakeImage, createDate, expiredDate, status, cat)) {
                    url = SUCCESS;
                    if(blo.insertHistory(date, u, new Cake(cakeId))) {
                        System.out.println("Insert History successfully");
                    }
                } else {
                    url = ERROR;
                }
                request.setAttribute("cakeidd", cakeId);
                }else {
                    url = INVALID;
                    request.setAttribute("CI", cakeId);
                    request.setAttribute("CN", cakeName);
                    request.setAttribute("CD", cakeDes);
                    request.setAttribute("CP", price);
                }
            }
        } catch (Exception e) {
            log("Error at update Controller: " + e.getMessage());
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
