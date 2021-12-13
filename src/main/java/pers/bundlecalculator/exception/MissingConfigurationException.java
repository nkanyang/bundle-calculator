package pers.bundlecalculator.exception;

public class MissingConfigurationException extends IllegalArgumentException{
    public MissingConfigurationException(String s){
        super("Configuration key \"" + s + "\" in application.properties is Missing!");
    }
}
