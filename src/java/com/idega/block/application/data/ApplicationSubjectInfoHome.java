package com.idega.block.application.data;


public interface ApplicationSubjectInfoHome extends com.idega.data.IDOHome
{
 public ApplicationSubjectInfo create() throws javax.ejb.CreateException;
 public ApplicationSubjectInfo findByPrimaryKey(Object pk) throws javax.ejb.FinderException;
 public java.util.Collection findAll()throws javax.ejb.FinderException;

}