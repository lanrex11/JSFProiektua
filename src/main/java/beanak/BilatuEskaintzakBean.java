package beanak;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import businessLogic.BLFacade;
import domain.Sale;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("bilatuEskaintza")
@SessionScoped
public class BilatuEskaintzakBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String filtro;
	private List<Sale> sales;
	private BLFacade facadeBL;
	private Sale selectedSale;

	public BilatuEskaintzakBean() {
		facadeBL = FacadeBean.getBusinessLogic();
		sales = new ArrayList<Sale>();
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

	public Sale getSelectedSale() {
		return selectedSale;
	}

	public void setSelectedSale(Sale selectedSale) {
		this.selectedSale = selectedSale;
	}
	
	public void onRowDblSelect(SelectEvent<Sale> event) throws IOException {
	    //Gertaera altxatu duen Sale lortu
		this.selectedSale = event.getObject(); 
	    
		//birbidaltzeko url prestatu: BASE(getRequestContextPath) + ofertaXehetasunak 
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.redirect(ec.getRequestContextPath() + "/ofertaXehetasunak.xhtml");
	}
	
	
	
	public StreamedContent getArgazkia() {
	    if (selectedSale == null || selectedSale.getFile() == null) {
	        return null;
	    }
	    //Sale klaseak definitu BASE_PATH hau
	    Path path = Paths.get("src/main/resources/images/", selectedSale.getFile());
	    System.out.println(path.toAbsolutePath() + " existe: " + Files.exists(path));
	    if (!Files.exists(path)) {
	        return null;
	    }
	    return DefaultStreamedContent.builder()
	            .contentType("image/png")
	            .stream(() -> {
	                try {
	                    return Files.newInputStream(path);
	                } catch (IOException e) {
	                    throw new RuntimeException(e);
	                }
	            })
	            .build();
	}
	
	


}
