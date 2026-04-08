package controller;

import model.service.PlaneService;
import model.entity.Plane;
import model.entity.Aviacompany;
import model.entity.Manufacturer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/plane")
public class PlaneFormServlet extends HttpServlet {
    private PlaneService planeService;

    @Override
    public void init() {
        planeService = new PlaneService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String idParam = req.getParameter("id");
            Plane plane = null;

            if (idParam != null && !idParam.isEmpty()) {
                plane = planeService.getPlaneById(Integer.parseInt(idParam));
            }

            List<Aviacompany> aviacompanies = planeService.getAllAviacompanies();
            List<Manufacturer> manufacturers = planeService.getAllManufacturers();

            req.setAttribute("plane", plane);
            req.setAttribute("aviacompanies", aviacompanies);
            req.setAttribute("manufacturers", manufacturers);
            req.getRequestDispatcher("/WEB-INF/views/planes/form.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", "Помилка при завантаженні форми: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Plane plane = new Plane();
        try {
            String idParam = req.getParameter("id");
            String model = req.getParameter("model");
            String fuelCapacityParam = req.getParameter("fuelCapacity");
            String maxSpeedParam = req.getParameter("maxSpeed");
            String aviacompanyIdParam = req.getParameter("aviacompanyId");
            String manufacturerIdParam = req.getParameter("manufacturerId");

            if (idParam != null && !idParam.isEmpty()) {
                plane.setId(Integer.parseInt(idParam));
            }

            plane.setModel(model);

            try {
                plane.setFuelCapacity(Integer.parseInt(fuelCapacityParam));
            } catch (NumberFormatException e) {
                plane.setFuelCapacity(0);
            }

            try {
                plane.setMaxSpeed(Integer.parseInt(maxSpeedParam));
            } catch (NumberFormatException e) {
                plane.setMaxSpeed(0);
            }

            if (aviacompanyIdParam != null && !aviacompanyIdParam.isEmpty()) {
                Aviacompany aviacompany = planeService.getAllAviacompanies().stream()
                        .filter(a -> a.getId() == Integer.parseInt(aviacompanyIdParam))
                        .findFirst().orElse(null);
                plane.setAviacompany(aviacompany);
            }

            if (manufacturerIdParam != null && !manufacturerIdParam.isEmpty()) {
                Manufacturer manufacturer = planeService.getAllManufacturers().stream()
                        .filter(m -> m.getId() == Integer.parseInt(manufacturerIdParam))
                        .findFirst().orElse(null);
                plane.setManufacturer(manufacturer);
            }

            if (plane.getId() == 0) {
                planeService.createPlane(plane);
            } else {
                planeService.updatePlane(plane);
            }

            resp.sendRedirect(req.getContextPath() + "/planes");

        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            req.setAttribute("plane", plane);
            req.setAttribute("aviacompanies", planeService.getAllAviacompanies());
            req.setAttribute("manufacturers", planeService.getAllManufacturers());
            req.getRequestDispatcher("/WEB-INF/views/planes/form.jsp").forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("error", "Помилка при збереженні літака: " + e.getMessage());
            req.setAttribute("plane", plane);
            req.setAttribute("aviacompanies", planeService.getAllAviacompanies());
            req.setAttribute("manufacturers", planeService.getAllManufacturers());
            req.getRequestDispatcher("/WEB-INF/views/planes/form.jsp").forward(req, resp);
        }
    }
}