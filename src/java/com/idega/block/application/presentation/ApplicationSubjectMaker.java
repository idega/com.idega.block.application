package com.idega.block.application.presentation;

import java.util.List;

import com.idega.block.application.business.ApplicationBusiness;
import com.idega.block.application.business.ApplicationFinder;
import com.idega.block.application.data.ApplicationSubject;
import com.idega.idegaweb.IWBundle;
import com.idega.idegaweb.IWResourceBundle;
import com.idega.presentation.Block;
import com.idega.presentation.IWContext;
import com.idega.presentation.PresentationObject;
import com.idega.presentation.Table;
import com.idega.presentation.text.Link;
import com.idega.presentation.text.Text;
import com.idega.presentation.ui.DataTable;
import com.idega.presentation.ui.DateInput;
import com.idega.presentation.ui.Form;
import com.idega.presentation.ui.HiddenInput;
import com.idega.presentation.ui.SubmitButton;
import com.idega.presentation.ui.TextInput;
import com.idega.presentation.util.Edit;
import com.idega.util.IWTimestamp;
import com.idega.util.LocaleUtil;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      idega.is
 * @author 2000 - idega team - <br><a href="mailto:aron@idega.is">Aron Birkir</a><br>
 * @version 1.0
 */


public class ApplicationSubjectMaker extends Block{

  protected final int ACT1 = 1,ACT2 = 2, ACT3 = 3,ACT4  = 4,ACT5 = 5;
  private final String strAction = "appsmaction";
  protected boolean isAdmin = false;
  private final static String IW_BUNDLE_IDENTIFIER="com.block.allocation";
  protected IWResourceBundle iwrb;
  protected IWBundle iwb,core;


  public ApplicationSubjectMaker() {

  }

  public String getLocalizedNameKey(){
    return "subjects";
  }

  public String getLocalizedNameValue(){
    return "Subjects";
  }

  protected void control(IWContext iwc){


      if(isAdmin){
        ApplicationSubject subject = null;
        if(iwc.isParameterSet("app_subject_id")){
          try {
            subject = ((com.idega.block.application.data.ApplicationSubjectHome)com.idega.data.IDOLookup.getHomeLegacy(ApplicationSubject.class)).findByPrimaryKeyLegacy(Integer.parseInt(iwc.getParameter("app_subject_id")));
          }
          catch (Exception ex) {

          }

        }

        if(iwc.isParameterSet("save")||iwc.isParameterSet("save.x")){
          doUpdate(iwc,subject);
        }
        else if(iwc.isParameterSet("delete")){
          doDelete(iwc);
        }

        Table T = new Table();
        T.setVerticalAlignment(1,1,"top");
        T.setVerticalAlignment(2,1,"top");
        T.add(getSubjectFormTable(subject),1,1);
        T.add(getSubjectTable(subject),2,1);
        add(T);

      }
      else
        this.add(new Text(iwrb.getLocalizedString("access_denied","Access denied")));

  }

  public PresentationObject makeLinkTable(int menuNr){
    Table LinkTable = new Table(6,1);

    return LinkTable;
  }

  private PresentationObject getSubjectTable(ApplicationSubject subject){

    List L = ApplicationFinder.listOfSubject();
    DataTable dTable = new DataTable();
    dTable.setTitlesHorizontal(true);
    dTable.addTitle(iwrb.getLocalizedString("subjects","Subjects"));
    dTable.add(Edit.formatText(iwrb.getLocalizedString("description", "Description")),1,1);
    dTable.add(Edit.formatText(iwrb.getLocalizedString("expiredate", "Expiredate")),2,1);

    if(L != null){
      int len = L.size();
      int a = 2;
      for (int i = 0; i < len; i++) {
        ApplicationSubject AS = (ApplicationSubject) L.get(i);
        dTable.add(getSubjectLink(AS),1,a);
        dTable.add(Edit.formatText(new IWTimestamp(AS.getExpires()).getLocaleDate(LocaleUtil.getIcelandicLocale())),2,a);
        dTable.add((getDeleteLink(AS)),3,a);
        a++;
      }
    }
    return dTable;
  }

  private PresentationObject getSubjectFormTable(ApplicationSubject subject){
    DataTable dTable = new DataTable();
    dTable.setTitlesHorizontal(true);
    dTable.addTitle(iwrb.getLocalizedString("new_subject","New subject"));

    TextInput Description = new TextInput("app_subj_desc");
    Edit.setStyle(Description);
    DateInput ExpireDate = new DateInput("app_subj_xdate",true);
    ExpireDate.setStyleAttribute("style",Edit.styleAttribute);
    ExpireDate.setDate(IWTimestamp.RightNow().getSQLDate());

    if(subject !=null){
      Description.setContent(subject.getDescription());
      ExpireDate.setDate(subject.getExpires());
      dTable.add(new HiddenInput("app_subject_id",String.valueOf(subject.getID())));
    }
    dTable.add(Edit.formatText(iwrb.getLocalizedString("description", "Description")),1,1);
    dTable.add(Edit.formatText(iwrb.getLocalizedString("expiredate", "Expiredate")),2,1);
    dTable.add(Description,1,2);
    dTable.add(ExpireDate,2,2);
    dTable.addButton(new SubmitButton(iwrb.getLocalizedImageButton("save","Save"),"save"));

    Form F = new Form();
    F.add(dTable);
    return F;
  }

  public Link getDeleteLink(ApplicationSubject AS){
    Link L = new Link("X");
    L.addParameter("delete",AS.getID());
    return L;
  }

   public Link getSubjectLink(ApplicationSubject AS){
    Link L = new Link(AS.getDescription());
    L.addParameter("app_subject_id",AS.getID());
    return L;
  }

  public void doDelete(IWContext iwc){
    int id = Integer.parseInt(iwc.getParameter("delete"));
    ApplicationBusiness.deleteApplicationSubject(id);
  }

  public void doUpdate(IWContext iwc,ApplicationSubject subject){
    String sDesc= iwc.getParameter("app_subj_desc").trim();
    String sDate = iwc.getParameter("app_subj_xdate");
    int id = subject !=null?subject.getID():-1;
    if(sDesc.length() > 0){
      ApplicationBusiness.saveApplicationSubject(id,sDesc,new IWTimestamp(sDate).getSQLDate());
    }
  }

   public void main(IWContext iwc){
    isAdmin = iwc.hasEditPermission(this);
    iwrb = getResourceBundle(iwc);
    iwb = getBundle(iwc);
    core = iwc.getApplication().getBundle( iwc.getApplication().CORE_BUNDLE_IDENTIFIER );
    control(iwc);
  }

}
