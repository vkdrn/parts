package com.application.parts.controller;

import com.application.parts.db.Database;
import com.application.parts.model.Part;
import com.application.parts.service.PartService;
import com.application.parts.service.PartServiceImpl;
import com.application.parts.utils.PartUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PartService partService = new PartServiceImpl();

    public HomeController() {

    }

    @Override
    public void init() throws ServletException {
        super.init();

        Database.createAndPopulateDB();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Part> parts = partService.filter(PartUtils.extractParams(request));

        request.setAttribute("partsList", parts);

        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

}
