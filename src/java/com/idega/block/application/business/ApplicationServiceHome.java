package com.idega.block.application.business;


public interface ApplicationServiceHome extends com.idega.business.IBOHome
{
 public ApplicationService create() throws javax.ejb.CreateException, java.rmi.RemoteException;

}