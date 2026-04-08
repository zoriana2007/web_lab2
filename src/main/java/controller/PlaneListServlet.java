package controller;

import model.service.PlaneService;
import model.entity.Plane;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/planes")
public class PlaneListServlet extends HttpServlet {
    private PlaneService planeService;

    @Override
    public void init() {
        planeService = new PlaneService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<Plane> planes = planeService.getAllPlanes();
            req.setAttribute("planes", planes);
            req.getRequestDispatcher("/WEB-INF/views/planes/list.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", "Помилка при отриманні списку літаків: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                planeService.deletePlane(id);
                resp.sendRedirect(req.getContextPath() + "/planes");
            } catch (Exception e) {
                req.setAttribute("error", "Помилка при видаленні літака: " + e.getMessage());
                doGet(req, resp);
            }
        }
    }
}