package beanak;

import java.io.Serializable;
import java.util.List;

import businessLogic.BLFacade;
import domain.Sale;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("bilatu")
@SessionScoped
public class BilatuEskaintzakBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private BLFacade facadeBL = FacadeBean.getBusinessLogic();
	private String eskaintzIzena;
	private List<Sale> eskaintzak;
	private int eskaintzaKop;
	private String tituloa;
	private boolean bilatua = false;
	private Sale aukeratutakoEskaintza;
	
	public void bilatu() {
		eskaintzak = facadeBL.getSales(tituloa);
		eskaintzaKop = eskaintzak.size();
		this.bilatua = true;
	}
	
	public String bistaratuEskaintza() {
		System.out.println("Click egin da");
		return "bistaratu";
	}
	
	public boolean getBilatua() {
		return this.bilatua;
	}
	public List<Sale> getEskaintzak(){
		return this.eskaintzak;
	}
	
	public int getEskaintzaKop() {
		return this.eskaintzaKop;
	}
	
	public void setEskaintzaKop(int kop) {
		this.eskaintzaKop = kop;
	}
	public String getEskaintzIzena() {
		return this.eskaintzIzena;
	}
	
	public String getTituloa() {
		return this.tituloa;
	}
	
	public void setTituloa(String tituloa) {
		this.tituloa = tituloa;
	}

	public Sale getAukeratutakoEskaintza() {
		return aukeratutakoEskaintza;
	}

	public void setAukeratutakoEskaintza(Sale aukeratutakoEskaintza) {
		this.aukeratutakoEskaintza = aukeratutakoEskaintza;
	}
}
