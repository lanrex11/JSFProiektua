package beanak;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.file.UploadedFile;

import businessLogic.BLFacade;
import domain.Sale;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
//import gui.Utils;

@Named("createSale")
@SessionScoped // Saio osorako sortu.

public class CreateSaleBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String titulua;
	private String deskripzioa;
	private float prezioa;
	private String egoera;
	private List<String> egoerak;
	private UploadedFile file;
	private File argazkiaFile;
	private Date data;

	private BLFacade facadeBL;

	// TODO Facadetik irakurri
	public CreateSaleBean() {
		facadeBL = FacadeBean.getBusinessLogic();
		egoerak = new ArrayList<String>(Arrays.asList("Berria", "Oso Ona", "Egokia", "Oso zaharra"));
		data = new Date();
	}

	public String getTitulua() {
		return titulua;
	}

	public void setTitulua(String titulua) {
		this.titulua = titulua;
	}

	public String getDeskripzioa() {
		return deskripzioa;
	}

	public void setDeskripzioa(String deskripzioa) {
		this.deskripzioa = deskripzioa;
	}

	public float getPrezioa() {
		return prezioa;
	}

	public void setPrezioa(float prezioa) {
		this.prezioa = prezioa;
	}

	// Egoera ComboBoxarentzat
	public String getEgoera() {
		return egoera;
	}

	public void setEgoera(String egoera) {
		this.egoera = egoera;
	}

	public List<String> getEgoerak() {
		return egoerak;
	}

	// Kalendarioarentzat
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Data aukeratua: " + event.getObject()));
		// FacesContext.getCurrentInstance().addMessage(null, new
		// FacesMessage(FacesMessage.SEVERITY_INFO ,"Data aukeratua: " +
		// event.getObject(),null));
	}

	public Date getGaur() {
		return new Date();
	}

	// Argazkia
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void argazkiaIgo(FileUploadEvent event) {
		try {
			file = event.getFile();
			// Fitxaterik ez badago edo hutsa bada --> NOP
			if (file != null && file.getSize() > 0) {

				// Fitxategi tempora sortu
				Path path = Files.createTempFile("argazkia-", ".jpg");

				// Edukia sortutako fitxategian kopiatu
				try (InputStream input = file.getInputStream()) {
					Files.copy(input, path, StandardCopyOption.REPLACE_EXISTING);
				}
				argazkiaFile = path.toFile();
				System.out.println("ARGAZKIA ONDO IGO DA");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean eskaintzaSortu() {
		try {
			System.out.println(titulua+ " " + deskripzioa+ " " + egoerak.indexOf(egoera)+ " " + 
					prezioa+ " " + data+ " " + "seller1@gmail.com"+ " " + argazkiaFile);
			Sale s = facadeBL.createSale(titulua, deskripzioa, egoerak.indexOf(egoera), 
					prezioa, data, "seller1@gmail.com", argazkiaFile);
			System.out.println(s);
			return true;
			//TODO salbuespen desberdinak
		} catch (Exception e) {
			System.out.println("egiteko....");
			return false;
		}
	}
}