/*
 * $Id: Application.java,v 1.7 2001/10/09 23:05:18 aron Exp $
 *
 * Copyright (C) 2001 Idega hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 *
 */
package com.idega.block.application.data;

import com.idega.data.GenericEntity;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.lang.IllegalStateException;
import com.idega.util.idegaTimestamp;

/**
 *
 * @author <a href="mailto:palli@idega.is">Pall Helgason</a>
 * @version 1.0
 */
public class Application extends GenericEntity {
  public static final String name_ = "app_application";
  private static final String subjectId_ = "app_subject_id";
  private static final String applicantId_ = "app_applicant_id";
  private static final String submitted_ = "submitted";
  private static final String status_ = "status";
  private static final String statusChanged_ = "status_changed";

  public static final String statusSubmitted = "S";
  public static final String statusApproved = "A";
  public static final String statusRejected = "R";
  public static final String statusSigned = "C";

  public Application() {
    super();
  }

  public Application(int id) throws SQLException {
    super(id);
  }

  public void initializeAttributes() {
    addAttribute(getIDColumnName());
    addAttribute(subjectId_,"Subject",true,true,java.lang.Integer.class,"one-to-many",com.idega.block.application.data.ApplicationSubject.class);
    addAttribute(applicantId_,"Applicant",true,true,java.lang.Integer.class,"one-to-many",com.idega.block.application.data.Applicant.class);
    addAttribute(submitted_,"Submitted",true,true,java.sql.Timestamp.class);
    addAttribute(status_,"Status",true,true,java.lang.String.class,1);
    addAttribute(statusChanged_,"Status changed",true,true,java.sql.Timestamp.class);
  }

  public static String getEntityTableName(){return name_ ;}
  public static String getColumnNameSubjectId(){return subjectId_;}
  public static String getColumnNameApplicantId(){return applicantId_;}
  public static String getColumnNameSubmitted(){return submitted_;}
  public static String getColumnNameStatus(){return status_;}
  public static String getColumnNameStatusChanged(){return statusChanged_;}

  public String getEntityName() {
    return(name_);
  }

  public String getSubjectIdColumnName() {
    return(subjectId_);
  }

  public String getStatusColumnName() {
    return(status_);
  }

  public String getApplicantIdColumnName() {
    return(applicantId_);
  }

  public String getSubmittedColumnName() {
    return(submitted_);
  }

  public String getStatusChangedColumnName() {
    return(statusChanged_);
  }

  public void setSubjectId(int id) {
    setColumn(subjectId_,id);
  }

  public void setSubjectId(Integer id) {
    setColumn(subjectId_,id);
  }

  public int getSubjectId() {
    return(getIntColumnValue(subjectId_));
  }

  public void setApplicantId(int id) {
    setColumn(applicantId_,id);
  }

  public void setApplicantId(Integer id) {
    setColumn(applicantId_,id);
  }

  public int getApplicantId() {
    return getIntColumnValue(applicantId_);
  }

  public void setSubmitted(Timestamp submitted) {
    setColumn(submitted_,submitted);
  }

  public Timestamp getSubmitted() {
    return((Timestamp)getColumnValue(submitted_));
  }

  public void setStatus(String status) throws IllegalStateException {
    if ((status.equalsIgnoreCase(statusSubmitted)) ||
        (status.equalsIgnoreCase(statusApproved)) ||
        (status.equalsIgnoreCase(statusSigned)) ||
        (status.equalsIgnoreCase(statusRejected))){
      setColumn(status_,status);
      setStatusChanged(idegaTimestamp.getTimestampRightNow());
    }
    else
      throw new IllegalStateException("Undefined state : " + status);
  }

  public void setStatusSubmitted() {
    setStatus(statusSubmitted);
  }

  public void setStatusApproved() {
    setStatus(statusApproved);
  }

  public void setStatusRejected() {
    setStatus(statusRejected);
  }

  public void setStatusSigned() {
    setStatus(statusSigned);
  }

  public String getStatus() {
    return(getStringColumnValue(status_));
  }

  public void setStatusChanged(Timestamp statusChanged) {
    setColumn(statusChanged_,statusChanged);
  }

  public Timestamp getStatusChanged() {
    return((Timestamp)getColumnValue(statusChanged_));
  }
}