package beanak;

import java.io.Serializable;
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
	
	private List<Sale> sales;
	
	private BLFacade facadeBL;
	
	public BilatuEskaintzakBean() {
		facadeBL = FacadeBean.getBusinessLogic();
		sales = facadeBL.getPublishedSales("", new Date());	
	}
	
	public List<Sale> getSales() {
		return sales;
	}
	
}
