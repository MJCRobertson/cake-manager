package com.waracle.cakemgr.Messages;

public class CakeNotFoundException extends RuntimeException{

    public CakeNotFoundException(Long id) {
        super("Could not find cake " + id);
    }
}
