package edu.dwes.PI_Raul_Lara_Back.exceptions;

public class NonExistentException extends Exception {
    private String msg;

    public NonExistentException(String mensaje) {
        this.msg = mensaje;
    }

    @Override
    public String toString() {
        return "NonUserException [msg=" + msg + "]";
    }
}
