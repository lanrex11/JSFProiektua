package beanak;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import businessLogic.BLFacade;
import domain.Sale;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("bilatuEskaintza")
@SessionScoped
public class BilatuEskaintzakBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String filtro;
	private List<Sale> sales;
	private BLFacade facadeBL;
	
	public BilatuEskaintzakBean() {
		facadeBL = FacadeBean.getBusinessLogic();
		sales = new ArrayList<Sale> ();
		filtro = "";
	}
	
	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Sale> getSales() {
		return sales;
	}
	public List<Sale> querySales() {
		sales = facadeBL.getPublishedSales(filtro, new Date());	
		return sales;
	}
	
}
