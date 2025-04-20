package com.aluracursos.screenmatch.excepcion;

public class ErrorEnConversionDeDuracionExcepcion extends RuntimeException {  //Cambie que extendiera de Thorowable
    //Porque esta creandome una chequeable, obliga a que se cree un thoows.
    //Por eso con runtime excepcion lo evito.
    private String mensaje;

    public ErrorEnConversionDeDuracionExcepcion(String mensaje) {
        this.mensaje = mensaje;
    }
    public String getMensaje() {
        return this.mensaje;
    }
}
