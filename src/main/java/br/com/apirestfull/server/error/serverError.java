package br.com.apirestfull.server.error;

public class serverError extends RuntimeException {
    static serverError instance;
    private Integer statusCode; 
    private String message; 

    public serverError(String message, Integer statusCode) {
        super(message);

        this.message = message;
        this.statusCode = statusCode;
    }        

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public String getMessage() {
        return this.message;
    }
}
