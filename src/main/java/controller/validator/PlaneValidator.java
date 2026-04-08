package controller.validator;

import model.entity.Plane;

public class PlaneValidator {

    public void validatePlane(Plane plane) {
        validateEmptyForm(plane);
        validateModel(plane.getModel());
        validateFuelCapacity(plane.getFuelCapacity());
        validateMaxSpeed(plane.getMaxSpeed());
        validateManufacturer(plane.getManufacturer());
        validateAviacompany(plane.getAviacompany());
    }

    private void validateEmptyForm(Plane plane) {
        if ((plane.getModel() == null || plane.getModel().trim().isEmpty()) &&
                plane.getManufacturer() == null &&
                plane.getAviacompany() == null &&
                plane.getFuelCapacity() == 0 &&
                plane.getMaxSpeed() == 0) {
            throw new IllegalArgumentException("Ви маєте заповнити всі поля!");
        }
    }

    private void validateModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Модель літака не може бути порожньою");
        }
        if (model.length() > 20) {
            throw new IllegalArgumentException("Модель літака не може бути настільки довгою");
        }
    }

    private void validateFuelCapacity(int fuelCapacity) {
        int maxCapacity = 320000;
        if (fuelCapacity <= 0) {
            throw new IllegalArgumentException("Місткість баку має бути більше 0");
        }
        if (fuelCapacity > maxCapacity) {
            throw new IllegalArgumentException("Місткість баку не може перевищувати 320.000 літрів");
        }
    }

    private void validateMaxSpeed(int maxSpeed) {
        int maxAllowedSpeed = 2000;
        if (maxSpeed <= 0) {
            throw new IllegalArgumentException("Швидкість має бути більше 0");
        }
        if (maxSpeed > maxAllowedSpeed) {
            throw new IllegalArgumentException("Максимальна швидкість не може перевищувати 2000 км/год");
        }
    }

    private void validateManufacturer(Object manufacturer) {
        if (manufacturer == null) {
            throw new IllegalArgumentException("Ви маєте обрати виробника літака із запропонованого списку");
        }
    }

    private void validateAviacompany(Object aviacompany) {
        if (aviacompany == null) {
            throw new IllegalArgumentException("Ви маєте обрати авіакомпанію до якої належить літак із запропонованого списку");
        }
    }
}