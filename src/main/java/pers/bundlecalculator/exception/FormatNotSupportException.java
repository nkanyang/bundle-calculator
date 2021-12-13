package pers.bundlecalculator.exception;

public class FormatNotSupportException extends IllegalArgumentException{
    public FormatNotSupportException( String s){
        super("Format \"" + s + "\" not supported!");
    }
}
