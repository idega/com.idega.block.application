/*
 * $Id: ApplicationSubjectBMPBean.java,v 1.2 2003/04/03 07:05:44 laddi Exp $
 *
 * Copyright (C) 2001 Idega hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 *
 */
package com.idega.block.application.data;

import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author <a href="mailto:palli@idega.is">Pall Helgason</a>
 * @version 1.0
 */
public class ApplicationSubjectBMPBean extends com.idega.data.GenericEntity implements com.idega.block.application.data.ApplicationSubject {
  private static final String name_ = "app_subject";
  private static final String description_ = "description";
  private static final String created_ = "created";
  private static final String expires_ = "expires";
  private static final String status_ = "status";

  public ApplicationSubjectBMPBean() {
    super();
  }

  public ApplicationSubjectBMPBean(int id) throws SQLException {
    super(id);
  }

  public void initializeAttributes() {
    addAttribute(getIDColumnName());
    addAttribute(description_,"Description",true,true,"java.lang.String");
    addAttribute(created_,"Created",true,true,"java.sql.Date");
    addAttribute(expires_,"Expires",true,true,"java.sql.Date");
    addAttribute(status_,"Status?",true,true,"java.lang.String");
    setMaxLength(description_,255);
    setMaxLength(status_,1);
  }

  public String getEntityName() {
    return(name_);
  }

  public String getName() {
    return(getDescription());
  }

  public String getDescriptionColumnName() {
    return(description_);
  }

  public String getCreatedColumnName() {
    return(created_);
  }

  public String getExpiresColumnName() {
    return(expires_);
  }

  public String getStatusColumnName() {
    return(status_);
  }

  public void setDescription(String description) {
    setColumn(description_,description);
  }

  public String getDescription() {
    return((String)getColumnValue(description_));
  }

  public void setCreated(Date date) {
    setColumn(created_,date);
  }

  public Date getCreated() {
    return((Date)getColumnValue(created_));
  }

  public void setExpires(Date date) {
    setColumn(expires_,date);
  }

  public Date getExpires() {
    return((Date)getColumnValue(expires_));
  }

  public void setStatus(String status) {
    setColumn(status_,status);
  }

  public String getStatus() {
    return((String)getColumnValue(status_));
  }
}
