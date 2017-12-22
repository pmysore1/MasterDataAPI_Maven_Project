/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aol.csopen.sapmasterdata.dao;

/**
 *
 * @author afitzwater1
 */
public class DAOException extends Exception
{
    private String niceMsg="Error retrieving data";
	private static final long serialVersionUID = 1L;
	public DAOException(String error){super(error);}
        	public DAOException(String error, String msg){
                    super(error);
                    setNiceMsg(msg);
                }
	public DAOException(String message, Throwable cause) {super(message, cause);}
	public DAOException(Throwable cause) {super(cause);}

    /**
     * @return the niceMsg
     */
    public String getNiceMsg() {
        return niceMsg;
    }

    /**
     * @param niceMsg the niceMsg to set
     */
    public void setNiceMsg(String niceMsg) {
        this.niceMsg = niceMsg;
    }
}

