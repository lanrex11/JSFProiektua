package eredua.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.file.UploadedFile;

import businessLogic.BLFacade;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
//import gui.Utils;

@Named("createSale")
@SessionScoped // Saio osorako sortu.
//RequestScope --> HTTP eskaera egitean objetua sortu: kaixo.xhtml jarrita nabigatzailetik eskera, login egitean ez.
//Application --> Mantendu beste nabigatzailebatetik ere.
//Erroreak daudenean ez da ezer gordetzen.
//AJAX --> Idatzi bitartean mezuak agertzea --> Asinkronoa

public class CreateSaleBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String egoera;
	private List<String> egoerak;
	
	private Date data;
	
	private UploadedFile file;
	
	private BLFacade facadeBL;

	//TODO Facadetik irakurri
	public CreateSaleBean() {
		facadeBL = FacadeBean.getBusinessLogic();
		egoerak = new ArrayList<String>(Arrays.asList("Berria","Oso Ona","Egokia","Oso zaharra"));
		data = new Date();
	}
	
	//Egoera ComboBoxarentzat
    public String getEgoera() {
        return egoera;
    }

    public void setEgoera(String egoera) {
        this.egoera = egoera;
    }

    public List<String> getEgoerak() {
        return egoerak;
    }
    
    //Kalendarioarentzat
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	public void onDateSelect(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Data aukeratua: " + event.getObject()));
		//		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,"Data aukeratua: " + event.getObject(),null));
	}
	
	public Date getGaur() {
	    return new Date();
	}
	
	//Argazkia
	 public UploadedFile getFile() {
	        return file;
    }
	
	
	

	
}