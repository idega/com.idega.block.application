/*
 * $Id: ApplicationSubjectInfo.java,v 1.1 2001/12/11 14:16:35 aron Exp $
 *
 * Copyright (C) 2001 Idega hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 *
 */
package com.idega.block.application.data;


import com.idega.data.GenericEntity;
import java.sql.Timestamp;
import java.lang.IllegalStateException;
import java.sql.SQLException;

/**
 *
 * @author <a href="mailto:aron@idega.is">aron@idega.is
 * @version 1.0
 */
public class ApplicationSubjectInfo extends GenericEntity {
 /*
 CREATE VIEW "V_APP_SUBJECT_INFO" (
  "app_subject_id",
  "status",
  "apartment_count",
  "last_submitted",
  "last_changed",
) AS
  select s.app_subject_id ,a.status ,count(a.app_application_id),
  max(a.submitted) submitted  ,max(a.status_changed) changed
  from app_subject s,app_application a
  where a.app_subject_id = s.app_subject_id
  group by s.app_subject_id ,a.status

*/
  public static String getEntityTableName(){return "V_APP_SUBJECT_INFO";}
  public static String getSubjectIdColumnName(){return "APP_SUBJECT_ID";}
  public static String getStatusColumnName(){return  "STATUS";}
  public static String getApplicationCountColumnName(){return "APPLICATION_COUNT";}
  public static String getLastSubmittedColumnName(){return "LAST_SUBMITTED";}
  public static String getLastStatusChangeColumnName(){return "LAST_CHANGED";}

  public ApplicationSubjectInfo() {
  }
  public ApplicationSubjectInfo(int id) throws SQLException {

  }
  public void initializeAttributes() {
    addAttribute(getIDColumnName());
    addAttribute(getSubjectIdColumnName(),"Subject id",true,true,"java.lang.Integer");
    addAttribute(getStatusColumnName(),"User id",true,true,"java.lang.String");
    addAttribute(getApplicationCountColumnName(),"Apartment count",true,true,"java.lang.Integer");
    addAttribute(getLastSubmittedColumnName(),"Last Submitted",true,true,"java.lang.Timestamp");
    addAttribute(getLastStatusChangeColumnName(),"Last Status Change",true,true,"java.sql.Timestamp");
  }
  public String getEntityName() {
    return(getEntityTableName());
  }
  public int getSubjectId(){
   return getIntColumnValue(getSubjectIdColumnName());
  }
  public String getStatus(){
    return getStringColumnValue(getStatusColumnName());
  }
  public int getApplicationCount(){
    return getIntColumnValue(getApplicationCountColumnName());
  }
  public Timestamp getLastSubmitted(){
    return (Timestamp )getColumnValue(getLastSubmittedColumnName());
  }
  public Timestamp getLastStatusChange(){
    return (Timestamp )getColumnValue(getLastStatusChangeColumnName());
  }
  public void insert()throws SQLException{

  }
  public void delete()throws SQLException{

  }
}
